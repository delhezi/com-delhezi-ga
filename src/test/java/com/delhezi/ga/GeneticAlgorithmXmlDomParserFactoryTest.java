package com.delhezi.ga;

import com.delhezi.ga.crossover.factory.CrossoverOperatorType;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.FitnessFunctionOption;
import com.delhezi.ga.mutation.factory.MutationOperatorType;
import com.delhezi.ga.selection.factory.SelectionMethodType;
import java.io.File;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeneticAlgorithmXmlDomParserFactoryTest {

    private static final Logger log =
        Logger.getLogger(GeneticAlgorithmXmlDomParserFactoryTest.class);

    /**
     * GeneticAlgorithmXmlDomParserFactory#newGeneticAlgorithm(String,String)
     */
    @Test
    public void testNewGeneticAlgorithm() {

        String scriptEngineDriverPath = "";
        File dir1 = new File("."); //D:\sun\NetBeansProjects\GA\lib
        //File dir2 = new File ("..");//D:\sun\NetBeansProjects\lib
        try {
            //System.out.println ("Current dir : " + dir1.getCanonicalPath());
            //System.out.println ("Parent  dir : " + dir2.getCanonicalPath());
            //scriptEngineDriverPath = "D:/sun/NetBeansProjects/lib/com-delhezi-ga_scripts/";
            scriptEngineDriverPath = dir1.getCanonicalPath().replaceAll("\\\\", "/") + "/scripts/";
        } catch (Exception e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }

        String xmlParameters = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><gaInitializeParameters><population><constantPopulationSize><selectionMethod>RouletteWheelElementaryImplementation</selectionMethod></constantPopulationSize></population><crossoverOperator>PartiallyMatchedCrossover</crossoverOperator><crossoverProbability>0.8</crossoverProbability><mutationOperator>_2Opt</mutationOperator><mutationProbability>0.5</mutationProbability><elitism>true</elitism><fitnessFunctionScript>fitnessFunction_TSP.js</fitnessFunctionScript><fitnessFunctionOption>minimisation</fitnessFunctionOption><initializeDataSource><sampleTSP><populationSize>100</populationSize></sampleTSP></initializeDataSource></gaInitializeParameters>";
        //<?xml version="1.0" encoding="UTF-8" standalone="no"?>
        //<gaInitializeParameters>
        //  <population>
        //    <constantPopulationSize>
        //      <selectionMethod>RouletteWheelElementaryImplementation</selectionMethod>
        //    </constantPopulationSize>
        //  </population>
        //  <crossoverOperator>PartiallyMatchedCrossover</crossoverOperator>
        //  <crossoverProbability>0.8</crossoverProbability>
        //  <mutationOperator>_2Opt</mutationOperator>
        //  <mutationProbability>0.5</mutationProbability>
        //  <elitism>true</elitism>
        //  <fitnessFunctionScript>fitnessFunction_TSP.js</fitnessFunctionScript>
        //  <fitnessFunctionOption>minimisation</fitnessFunctionOption>
        //  <initializeDataSource>
        //    <sampleTSP>
        //      <populationSize>100</populationSize>
        //    </sampleTSP>
        //  </initializeDataSource>
        //</gaInitializeParameters>

        GeneticAlgorithmXmlDomParserFactory gaXMLf =
            new GeneticAlgorithmXmlDomParserFactory();

        try {
            GeneticAlgorithm ga =
                gaXMLf.newGeneticAlgorithm(scriptEngineDriverPath,
                                           xmlParameters);
          //jawnie zadeklarowane w pliku xml
          assertEquals("selectionMethod ", ga.getSelectionMethod(), SelectionMethodType.RouletteWheelElementaryImplementation);
          assertEquals("crossoverOperator ", ga.getCrossoverOperator(), CrossoverOperatorType.PartiallyMatchedCrossover);
          assertEquals("crossoverProbability ", ga.getCrossoverProbability(), 0.8, 0.01);
          assertEquals("mutationOperator ", ga.getMutationOperator(), MutationOperatorType._2Opt);
          assertEquals("mutationProbability ", ga.getMutationProbability(), 0.5, 0.01);
          assertEquals("elitism ", ga.getElitism(), true);
          assertEquals("fitnessFunctionScript ", ga.getFitnessFunctionScriptFile(), "fitnessFunction_TSP.js");
          assertEquals("fitnessFunctionOption ", ga.getFitnessFunctionOption(), FitnessFunctionOption.minimisation);
          assertEquals("populationSize ", ga.getPopulationSize(), 100);
          
          //pochodne
          assertEquals("fitnessFunctionScriptPath ", ga.getFitnessFunctionScriptPath(), scriptEngineDriverPath);
          assertEquals("fitnessFunctionEnginName ", ga.getFitnessFunctionEnginName(), "javascript");
          assertEquals("topChromosomeGenerationFound ", ga.getTopChromosomeGenerationFound(), 0);
          assertEquals("populationType ", ga.getPopulationType(), PopulationType.PopulationConstantSize);
          assertEquals("state ", ga.getState(), GeneticAlgorithmState.INITIALIZED);
          
        } catch (GeneticAlgorithmException e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }
    }
}
