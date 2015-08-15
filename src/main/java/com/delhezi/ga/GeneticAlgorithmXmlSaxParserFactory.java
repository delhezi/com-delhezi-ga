/**
 * @(#)GeneticAlgorithmXmlSaxParserFactory.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import com.delhezi.ga.crossover.factory.CrossoverOperatorType;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.initialize.data.SampleTsp;
import com.delhezi.ga.fitnessfunction.FitnessFunction;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;
import com.delhezi.ga.fitnessfunction.FitnessFunctionOption;
import com.delhezi.ga.mutation.factory.MutationOperatorType;
import com.delhezi.ga.selection.factory.SelectionMethodType;

/**
 * <code>GeneticAlgorithmXmlSaxParserFactory</code>: Analiza składniwa dokumentu xml
 * z użyciem parsera JAXP. Walidacja w oparciu o newInstanceParameters.xsd
 * @version 1.0 2009-12-14
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class GeneticAlgorithmXmlSaxParserFactory {

    /** Logger object. */
    private static final Logger LOGGER = Logger.getLogger(GeneticAlgorithmXmlSaxParserFactory.class.getName());

    /** Delhezi Error Code. */
    // private static final String DERC = "1-5-";

    /** Źródło inicjalizacji populacji. */
    public static enum InitializeDataSource {
        /** Baza danych. */
        db,

        /** Przykład komiwojażera. */
        sampleTSP;
    }

    /** */
    private static final String XML_SCHEMA_FILE = "newInstanceParameters.xsd";
    
    /** */
    private GeneticAlgorithm ga;

    public final GeneticAlgorithm newGeneticAlgorithm(final String fitnassFunctionScriptsPath,
            final String xmlParams) throws GeneticAlgorithmException {

        ga = new GeneticAlgorithm();

        DefaultHandler handler = new DefaultHandler() {
            String currTag;
            Population population;
            PopulationType populationType;
            CrossoverOperatorType crossoverOperator;
            double crossoverProbability;
            MutationOperatorType mutationOperator;
            double mutationProbability;
            boolean elitism;
            String fitnessFunctionScript;
            FitnessFunctionOption fitnessFunctionOption;
            SelectionMethodType selectionMethod;
            int maxLT;
            int minLT;
            InitializeDataSource initializeDataSource;
            String initializePopulationConnectionName;
            String initializePopulationSQLQuery;
            int populationSize;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)
                    throws SAXException {
                currTag = qName;
                if (qName.equalsIgnoreCase("changeablePopulationSize")) {
                    populationType = PopulationType.PopulationChangeableSize;
                } else if (qName.equalsIgnoreCase("constantPopulationSize")) {
                    populationType = PopulationType.PopulationConstantSize;
                } else if (qName.equalsIgnoreCase("elitism")) {
                    elitism = true; // Jeśli występuje pusty element to
                                    // domyślnie jest true
                } else if (qName.equalsIgnoreCase("db")) {
                    initializeDataSource = com.delhezi.ga.GeneticAlgorithmXmlSaxParserFactory.InitializeDataSource.db;
                } else if (qName.equalsIgnoreCase("sampleTSP")) {
                    initializeDataSource = com.delhezi.ga.GeneticAlgorithmXmlSaxParserFactory.InitializeDataSource.sampleTSP;
                }
            }

            @Override
            public void endDocument() throws SAXException {
                FitnessFunction fitnessFunction;
                IFitnessFunctionDriver fitnessFunctionDriver;
                try {
                    fitnessFunctionDriver = FitnessFunctionDriverFactory.getFitnessFunctionEngineDriver("javascript",
                            fitnassFunctionScriptsPath, fitnessFunctionScript);
                    fitnessFunction = new FitnessFunction(fitnessFunctionDriver, "fitnessFunction");
                } catch (GeneticAlgorithmException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                    throw new SAXException(ex.getMessage());
                }
                if (fitnessFunctionOption == FitnessFunctionOption.minimisation) {
                    fitnessFunction.setMaximisation(false);
                } else if (fitnessFunctionOption == FitnessFunctionOption.maximisation) {
                    fitnessFunction.setMaximisation(true);
                }

                ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
                chromosomeProperties.setFitnessFunction(fitnessFunction);

                LinkedList<Chromosome> chromosomes = null;
                switch (initializeDataSource) {
                case sampleTSP:
                    chromosomes = SampleTsp.newChromosomes(populationSize, chromosomeProperties);
                    break;
                case db:
                    // UZUPEŁNIĆ
                    // initializePopulationConnectionName
                    // initializePopulationSQLQuery
                    chromosomes = null;
                    break;
                }

                switch (populationType) {
                case PopulationChangeableSize:
                    try {
                        population = PopulationChangeableSize.newPopulationChangeableSize(maxLT, minLT, chromosomes,
                                crossoverOperator, crossoverProbability, mutationOperator, mutationProbability,
                                chromosomeProperties);
                    } catch (GeneticAlgorithmException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                        throw new SAXException(ex.getMessage());
                    }
                    break;
                case PopulationConstantSize:
                    try {
                        population = PopulationConstantSize.newPopulationConstantSize(selectionMethod, chromosomes,
                                crossoverOperator, crossoverProbability, mutationOperator, mutationProbability,
                                chromosomeProperties);
                    } catch (GeneticAlgorithmException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                        throw new SAXException(ex.getMessage());
                    }
                    break;
                }
                ga.setPopulation(population);
                ga.setState(GeneticAlgorithmState.INITIALIZED);
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (currTag.equalsIgnoreCase("maxLT")) {
                    maxLT = Integer.parseInt(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("minLT")) {
                    minLT = Integer.parseInt(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("selectionMethod")) {
                    selectionMethod = SelectionMethodType.valueOf(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("crossoverOperator")) {
                    crossoverOperator = CrossoverOperatorType.valueOf(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("crossoverProbability")) {
                    crossoverProbability = Double.parseDouble(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("mutationOperator")) {
                    mutationOperator = MutationOperatorType.valueOf(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("mutationProbability")) {
                    mutationProbability = Double.parseDouble(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("elitism")) {
                    // Jeśli element ma wartość to nadpisz wartość wstawioną w
                    // startElement.elitism.
                    elitism = Boolean.valueOf(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("fitnessFunctionScript")) {
                    fitnessFunctionScript = new String(ch, start, length);
                } else if (currTag.equalsIgnoreCase("fitnessFunctionOption")) {
                    fitnessFunctionOption = FitnessFunctionOption.valueOf(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("initializePopulationConnectionName")) {
                    this.initializePopulationConnectionName = new String(ch, start, length);
                } else if (currTag.equalsIgnoreCase("initializePopulationSQLQuery")) {
                    this.initializePopulationSQLQuery = new String(ch, start, length);
                } else if (currTag.equalsIgnoreCase("populationSize")) {
                    this.populationSize = Integer.parseInt(new String(ch, start, length));
                } else if (currTag.equalsIgnoreCase("maxGenerationCount")) {
                    ga.setMaxGenerationCount(Integer.parseInt(new String(ch, start, length)));
                } else if (currTag.equalsIgnoreCase("lastGenerationTopChromosomeFind")) {
                    ga.setLastGenerationTopChromosomeFind(Integer.parseInt(new String(ch, start, length)));
                }
            }
        };

        SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);// Wyłączenie walidacji (przestarzały mechanizm).
        factory.setNamespaceAware(true); // Obsługa przestrzeni nazw.
        try {
            // Wymagana biblioteka xercesImpl.jar !!!!, zostala dodana w POM
            // projektu.
            InputStream xmlSchemaInputStream = GeneticAlgorithmXmlSaxParserFactory.class.getClassLoader()
                    .getResourceAsStream(XML_SCHEMA_FILE);
            // InputStream xmlSchemaInputStream =
            // this.getClass().getClassLoader().getResourceAsStream(XML_SCHEMA_FILE);
            // InputStream stream =
            // Thread.currentThread().getContextClassLoader().getResourceAsStream(XML_SCHEMA_FILE);
            factory.setSchema(schemaFactory.newSchema(new Source[] { new StreamSource(xmlSchemaInputStream) })); // Walidacja
                                                                                                                 // ze schematem
            // factory.setSchema(schemaFactory.newSchema(new Source[]{new
            // StreamSource("contacts.xsd")}));
            // factory.setSchema(schemaFactory.newSchema(schemaFileUrl));
            SAXParser saxParser = factory.newSAXParser();
            XMLReader xmlReder = saxParser.getXMLReader(); // interfejs pozwala
                                                           // na analizę składni
                                                           // dokumentu xml
            xmlReder.setContentHandler(handler);
            xmlReder.setErrorHandler(new InitializeParametersXMLParserErrorHandler());
            InputSource xmlInputSource = new InputSource(new java.io.ByteArrayInputStream(xmlParams.getBytes()));
            xmlReder.parse(xmlInputSource);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GeneticAlgorithmXmlSaxParserFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new GeneticAlgorithmException(ex.getMessage());
        } catch (SAXException ex) {
            Logger.getLogger(GeneticAlgorithmXmlSaxParserFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new GeneticAlgorithmException(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(GeneticAlgorithmXmlSaxParserFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new GeneticAlgorithmException(ex.getMessage());
        }
        ga.setState(GeneticAlgorithmState.INITIALIZED);
        return ga;
    }

    private static class InitializeParametersXMLParserErrorHandler implements ErrorHandler {
        @Override
        public void warning(SAXParseException ex) throws SAXException {
            System.out.println("CCC1");
            Logger.getLogger(InitializeParametersXMLParserErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new SAXException(ex.getMessage());
        }

        @Override
        public void error(SAXParseException ex) throws SAXException {
            System.out.println("CCC2");
            Logger.getLogger(InitializeParametersXMLParserErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new SAXException(ex.getMessage());
        }

        @Override
        public void fatalError(SAXParseException ex) throws SAXException {
            System.out.println("CCC3");
            Logger.getLogger(InitializeParametersXMLParserErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new SAXException(ex.getMessage());
        }
    }

}
