package com.delhezi.ga.mutation.heuristics;

import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.FitnessFunction;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;
import com.delhezi.ga.genes.PointGene;

import junitx.util.PrivateAccessor;

public class _3OptTest {
    
    private IFitnessFunctionDriver fitnessFunctionDriver;
    private static FitnessFunction fitnessFunction;
    private static ChromosomeProperties chromosomeProperties;
    private static final Logger log = Logger.getLogger(_3OptTest.class);

    public _3OptTest() {
    }

  @Before
  public void setUp() throws Exception {
    String scriptEngineDriverPath ="";
    File dir1 = new File (".");
    try {
        scriptEngineDriverPath = dir1.getCanonicalPath()+"/scripts/";
        }
    catch(Exception e) {e.printStackTrace();}
      try {
          fitnessFunctionDriver =
                  FitnessFunctionDriverFactory.getFitnessFunctionEngineDriver("javascript",
                                                                              scriptEngineDriverPath,
                                                                              "fitnessFunction_TSP.js");
      } catch (GeneticAlgorithmException e) {
          log.debug("Exception: ", e);
          e.printStackTrace();
          fail();
      }
      fitnessFunction =new FitnessFunction(fitnessFunctionDriver, "fitnessFunction");
      chromosomeProperties = ChromosomeProperties.getInstance();
      chromosomeProperties.setFitnessFunction(fitnessFunction);
    }
  
    @Test
    public void testMutation() {

        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        _3Opt<PointGene> instance = new _3Opt<PointGene>();

        PointGene[] genes = { new PointGene(11,1), new PointGene(1,23),
                          new PointGene(1,31), new PointGene(1,4),
                          new PointGene(12,6), new PointGene(22,6),
                          new PointGene(32,4), new PointGene(2,1)};

        Chromosome<PointGene> chromosome = new Chromosome<PointGene>(genes, chromosomeProperties);
        int edge[] = new int[3];
        edge[0]=2;   //1 2 - 3 4   5 6 7 8
        edge[1]=4;   //1 2   3 4 - 5 6 7 8
        edge[2]=8;   //1 2   3 4   5 6 7 8 -

        
        Class<?> parameterTypes[] = { Chromosome.class, int[].class };
        Object args[] = { chromosome, edge };

        try {
            PrivateAccessor.invoke(instance, "mutation", parameterTypes, args);
        } catch (Throwable e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }
        
          /* try {
              System.out.println("fitnessFunction=" + fitnessFunction.calculateFitness(genes));
          } catch (GeneticAlgorithmException e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
          } */

          /* try {
              System.out.println("fitnessFunction=" + fitnessFunction.calculateFitness(genes));
          } catch (GeneticAlgorithmException e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
          } */
/*
          PointGene[] expResult1 = { new PointGene(11,1), new PointGene(1,23),
                               new PointGene(1,31), new PointGene(1,4),
                               new PointGene(2,1), new PointGene(32,4),
                               new PointGene(22,6), new PointGene(12,6)};
        for (int i=0; i<chromosome.size(); i++){
            assertEquals(expResult1[i], chromosome.getGene(i));
            //System.out.println(chromosome.size().toString());
        }
*/
      }

}
