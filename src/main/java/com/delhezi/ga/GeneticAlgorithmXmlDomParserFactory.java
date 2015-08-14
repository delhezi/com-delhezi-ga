/**
 * @(#)GeneticAlgorithmXmlDomParserFactory.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga;

import com.delhezi.ga.crossover.factory.CrossoverOperatorType;
import com.delhezi.ga.exception.GeneticAlgorithmException;

import com.delhezi.ga.fitnessfunction.FitnessFunction;
import com.delhezi.ga.fitnessfunction.FitnessFunctionOption;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;
import com.delhezi.ga.initialize.data.SampleTsp;
import com.delhezi.ga.mutation.factory.MutationOperatorType;
import com.delhezi.ga.selection.factory.SelectionMethodType;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.traversal.DocumentTraversal;

/**
 * <code>GeneticAlgorithmXmlDomParserFactory </code>: Analiza składniwa dokumentu xml
 * z użyciem parsera JAXP. Walidacja w oparciu o newInstanceParameters.xsd
 * @version 1.0 2009-12-14
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class GeneticAlgorithmXmlDomParserFactory {
    /** Logger object. */
    private static final Logger LOGGER =
        Logger.getLogger(GeneticAlgorithmXmlSaxParserFactory.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-5-";

    /** Źródło inicjalizacji populacji. */
    public static enum InitializeDataSource {
        /** Baza danych. */
        db,

        /** Przykład komiwojażera. */
        sampleTSP;
    }
    private static final String XML_SCHEMA_FILE = "newInstanceParameters.xsd";
    private GeneticAlgorithm ga;

    private Population population;
    private PopulationType populationType;
    private CrossoverOperatorType crossoverOperator;
    private double crossoverProbability;
    private MutationOperatorType mutationOperator;
    private double mutationProbability;
    private boolean elitism;
    private String fitnessFunctionScript;
    private FitnessFunctionOption fitnessFunctionOption;
    private SelectionMethodType selectionMethod;
    private int maxLT;
    private int minLT;
    private InitializeDataSource initializeDataSource;
    private String initializePopulationConnectionName;
    private String initializePopulationSQLQuery;
    private int populationSize;

    private Document doc = null;

    public GeneticAlgorithm newGeneticAlgorithm(final String fitnassFunctionScriptsPath,
                                                final String xmlParams) throws GeneticAlgorithmException {
        ga = new GeneticAlgorithm();
        try {
            doc = parserXML(xmlParams);
            parse(doc);
            initGA(fitnassFunctionScriptsPath);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return ga;
    }

    private void initGA(final String fitnassFunctionScriptsPath) throws GeneticAlgorithmException {
        FitnessFunction fitnessFunction = null;
        IFitnessFunctionDriver fitnessFunctionDriver;
          try {
            fitnessFunctionDriver =
                              FitnessFunctionDriverFactory.getFitnessFunctionEngineDriver("javascript",
                                                                                          fitnassFunctionScriptsPath,
                                                                                          fitnessFunctionScript);
              fitnessFunction = new FitnessFunction(fitnessFunctionDriver, "fitnessFunction");
        } catch (GeneticAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            throw new GeneticAlgorithmException(ex.getMessage());
        }
          if (fitnessFunctionOption == FitnessFunctionOption.minimisation) {
            fitnessFunction.setMaximisation(false);
        } else if (fitnessFunctionOption == FitnessFunctionOption.maximisation) {
            fitnessFunction.setMaximisation(true);
          }

          ChromosomeProperties chromosomeProperties =
              ChromosomeProperties.getInstance();
          chromosomeProperties.setFitnessFunction(fitnessFunction);

          LinkedList<Chromosome> chromosomes = null;
          switch (initializeDataSource) {
        case sampleTSP:
              chromosomes = SampleTsp.newChromosomes(populationSize, chromosomeProperties);
              break;
        case db:
              //UZUPEŁNIĆ
              //initializePopulationConnectionName
              //initializePopulationSQLQuery
              chromosomes = null;
              break;
            }

        switch (populationType) {
        case PopulationChangeableSize:
              try {
                  population = PopulationChangeableSize.newPopulationChangeableSize(
                      maxLT, minLT, chromosomes,
                      crossoverOperator, crossoverProbability,
                      mutationOperator, mutationProbability,
                      chromosomeProperties);
            } catch (GeneticAlgorithmException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                throw new GeneticAlgorithmException(ex.getMessage());
            }
            break;
        case PopulationConstantSize:
            try {
                  population = PopulationConstantSize.newPopulationConstantSize(
                      selectionMethod, chromosomes,
                      crossoverOperator, crossoverProbability,
                      mutationOperator, mutationProbability,
                      chromosomeProperties);
            } catch (GeneticAlgorithmException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                throw new GeneticAlgorithmException(ex.getMessage());
            }
            break;
        }
        ga.setPopulation(population);
        ga.setState(GeneticAlgorithmState.INITIALIZED);
    }


    /**
     * @param doc Document
     */
    public void parse(Document doc) {
        //Znajdz wierzcholek od ktorego ma zaczynac sie iterakcja.
        Element root = doc.getDocumentElement();

        //Uzyskaj NodeIterator.
        NodeIterator i =
            ((DocumentTraversal)doc).createNodeIterator(root, NodeFilter.SHOW_ALL,
                                                        null, true);

        Node n;
        String tmpNodeName = null;
        while ((n = i.nextNode()) != null) {
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                
                tmpNodeName = n.getNodeName();
                
              if (tmpNodeName.equalsIgnoreCase("changeablePopulationSize")) {
                  populationType = PopulationType.PopulationChangeableSize;
              } else if (tmpNodeName.equalsIgnoreCase("constantPopulationSize")) {
                  populationType = PopulationType.PopulationConstantSize;
              } else if (tmpNodeName.equalsIgnoreCase("elitism")) {
                  elitism = true; //Jeśli występuje pusty element to domyślnie jest true
              } else if (tmpNodeName.equalsIgnoreCase("db")) {
                  initializeDataSource =
                          GeneticAlgorithmXmlDomParserFactory.InitializeDataSource.db;
              } else if (tmpNodeName.equalsIgnoreCase("sampleTSP")) {
                  initializeDataSource =
                          GeneticAlgorithmXmlDomParserFactory.InitializeDataSource.sampleTSP;
              }
                //System.out.println("Encountered Element:" + n.getNodeName());

            } else if (n.getNodeType() == Node.TEXT_NODE) {
                if (tmpNodeName.equalsIgnoreCase("maxLT")) {
                    maxLT = Integer.parseInt(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("minLT")) {
                    minLT = Integer.parseInt(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("selectionMethod")) {
                    selectionMethod =
                            SelectionMethodType.valueOf(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("crossoverOperator")) {
                    crossoverOperator =
                            CrossoverOperatorType.valueOf(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("crossoverProbability")) {
                    crossoverProbability =
                            Double.parseDouble(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("mutationOperator")) {
                    mutationOperator =
                            MutationOperatorType.valueOf(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("mutationProbability")) {
                    mutationProbability = Double.parseDouble(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("elitism")) {
                    //Jeśli element ma wartość to nadpisz wartość wstawioną w startElement.elitism.
                    elitism = Boolean.valueOf(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("fitnessFunctionScript")) {
                    fitnessFunctionScript = n.getNodeValue();
                } else if (tmpNodeName.equalsIgnoreCase("fitnessFunctionOption")) {
                    fitnessFunctionOption =
                            FitnessFunctionOption.valueOf(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("initializePopulationConnectionName")) {
                    this.initializePopulationConnectionName = n.getNodeValue();
                } else if (tmpNodeName.equalsIgnoreCase("initializePopulationSQLQuery")) {
                    this.initializePopulationSQLQuery = n.getNodeValue();
                } else if (tmpNodeName.equalsIgnoreCase("populationSize")) {
                    this.populationSize = Integer.parseInt(n.getNodeValue());
                } else if (tmpNodeName.equalsIgnoreCase("maxGenerationCount")) {
                    ga.setMaxGenerationCount(Integer.parseInt(n.getNodeValue()));
                } else if (tmpNodeName.equalsIgnoreCase("lastGenerationTopChromosomeFind")) {
                    ga.setLastGenerationTopChromosomeFind(Integer.parseInt(n.getNodeValue()));
                }
                //System.out.println("Encountered Text:" + n.getNodeValue());
            }
        }
    }

    public Document parserXML(String xmlParams) throws SAXException,
                                                       IOException,
                                                       ParserConfigurationException {
        InputStream xmlSchemaInputStream =
            GeneticAlgorithmXmlSaxParserFactory.class.getClassLoader().getResourceAsStream(XML_SCHEMA_FILE);

        // Create schema
        SchemaFactory xsdFactory =
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema =
            xsdFactory.newSchema(new Source[] { new StreamSource(xmlSchemaInputStream) });

        // Parse and validate
        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        df.setValidating(false);
        df.setNamespaceAware(true);
        df.setSchema(schema);
        DocumentBuilder builder = df.newDocumentBuilder();
        InputSource xmlInputSource =
            new InputSource(new java.io.ByteArrayInputStream(xmlParams.getBytes()));
        return builder.parse(xmlInputSource);
    }
}
