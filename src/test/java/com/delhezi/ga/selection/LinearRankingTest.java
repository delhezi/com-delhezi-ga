package com.delhezi.ga.selection;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.FitnessFunction;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;
import com.delhezi.ga.genes.Point;
import java.io.File;
import java.util.LinkedList;
import junitx.util.PrivateAccessor;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinearRankingTest {

    private IFitnessFunctionDriver fitnessFunctionDriver;
    private static FitnessFunction fitnessFunction;
    private static ChromosomeProperties chromosomeProperties;
    private static final Logger log =
        Logger.getLogger(LinearRankingTest.class);

    public LinearRankingTest() {
    }

    @Before
  public void setUp() throws Exception {
      String scriptEngineDriverPath ="";
      File dir1 = new File (".");//D:\sun\NetBeansProjects\GA\lib
      //File dir2 = new File ("..");//D:\sun\NetBeansProjects\lib
      try {
          //System.out.println ("Current dir : " + dir1.getCanonicalPath());
          //System.out.println ("Parent  dir : " + dir2.getCanonicalPath());
          //scriptEngineDriverPath = "D:/sun/NetBeansProjects/lib/com-delhezi-ga_scripts/";
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

    @After
    public void tearDown() throws Exception {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * LinearRanking#select(java.util.LinkedList)
     */
    @Test
  public void testSelect() {
    
    //ZOBACZ WYNIKI TESTU
    //DELHEZI/com-delhezi-lib/com-delhezi-ga/testy_dodatkowe/RouletteWheelElementaryImplementationTest.xlsx
    
    //fitnessFunction.setMaximisation(false);
    fitnessFunction.setMaximisation(true);

    Point[] genes_0 = {new Point(101,120), new Point(102,220),
                       new Point(103,220), new Point(104,220),
                       new Point(105,220), new Point(106,220)};
    Point[] genes_1 = {new Point(101,120), new Point(102,130),
                       new Point(103,140), new Point(104,150),
                       new Point(105,160), new Point(105,170)};
    Point[] genes_2 = {new Point(101,121), new Point(102,122),
                       new Point(103,123), new Point(104,124),
                       new Point(105,125), new Point(105,126)};
    Point[] genes_3 = {new Point(101,120), new Point(102,120),
                       new Point(103,120), new Point(104,120),
                       new Point(105,120), new Point(106,120)};
    Point[] genes_4 = {new Point(101,121), new Point(102,122),
                       new Point(103,123), new Point(104,124),
                       new Point(105,125), new Point(105,136)};
    Point[] genes_5 = {new Point(101,120), new Point(102,220),
                       new Point(103,220), new Point(104,220),
                       new Point(105,220), new Point(106,230)};
    Point[] genes_6 = {new Point(101,120), new Point(102,220),
                       new Point(153,220), new Point(104,220),
                       new Point(105,220), new Point(106,220)};
    Point[] genes_7 = {new Point(101,120), new Point(102,130),
                       new Point(153,140), new Point(104,150),
                       new Point(105,160), new Point(105,170)};
    Point[] genes_8 = {new Point(101,121), new Point(102,122),
                       new Point(153,123), new Point(104,124),
                       new Point(105,125), new Point(105,126)};
    Point[] genes_9 = {new Point(101,120), new Point(102,120),
                       new Point(153,120), new Point(104,120),
                       new Point(105,120), new Point(106,120)};
    Point[] genes_10 = {new Point(101,121), new Point(102,122),
                        new Point(153,123), new Point(104,124),
                        new Point(105,125), new Point(105,136)};


        LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
        chromosomes.add(new Chromosome(genes_0, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_1, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_2, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_3, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_4, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_5, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_6, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_7, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_8, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_9, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_10, chromosomeProperties));

        LinearRanking instance = new LinearRanking();


        /*
        System.out.println("------------------------------------");
        System.out.println(" isMaximisation() = " +
                           fitnessFunction.isMaximisation());
        System.out.println("------------------------------------");
        int i = 0;
        double fitnessSum = 0;
        for (Chromosome ch : chromosomes) {
            try {
                System.out.println(i + " ch.getFitness() = " +
                                   ch.getFitness());
              fitnessSum += ch.getFitness();
            } catch (GeneticAlgorithmException e) {
            }
            i++;
        }
        System.out.println("--------");
        System.out.println(" fitnessSum = " + fitnessSum);
        System.out.println("------------------------------------");
      */
        
        try {
        chromosomes = instance.select(chromosomes);
    } catch (GeneticAlgorithmException e) {
      log.debug("Exception: ", e);
      e.printStackTrace();
            fail();
        }

        /*
        i = 0;
        fitnessSum = 0;
        for (Chromosome ch : chromosomes) {
            try {
                System.out.println(i + " ch.getFitness() = " +
                                   ch.getFitness());
                fitnessSum += ch.getFitness();
            } catch (GeneticAlgorithmException e) {
            }
            i++;
        }
        System.out.println("--------");
        System.out.println(" fitnessSum = " + fitnessSum);
        System.out.println("------------------------------------");
      */
          
    }

    /**
     * LinearRanking#getNormal(int,int)
     */
    @Test
    public void testGetNormal() {
        LinearRanking instance = new LinearRanking();

        Class parameterTypes[] = { int.class, int.class };
        Object args0[] = { 5, 1 };
        Object args1[] = { 5, 2 };
        Object args2[] = { 5, 3 };
        Object args3[] = { 5, 4 };
        Object args4[] = { 5, 5 };
        double[] res = new double[5];
        double sumNormal = 0;

        try {
            res[0] =
                    (Double)PrivateAccessor.invoke(instance, "getNormal", parameterTypes,
                                                   args0);
            res[1] =
                    (Double)PrivateAccessor.invoke(instance, "getNormal", parameterTypes,
                                                   args1);
            res[2] =
                    (Double)PrivateAccessor.invoke(instance, "getNormal", parameterTypes,
                                                   args2);
            res[3] =
                    (Double)PrivateAccessor.invoke(instance, "getNormal", parameterTypes,
                                                   args3);
            res[4] =
                    (Double)PrivateAccessor.invoke(instance, "getNormal", parameterTypes,
                                                   args4);

            for (int i = 0; i < 5; i++) {
                //System.out.println("normal[" + i + "] = " + res[i]);
                sumNormal += res[i];
            }

            assertEquals("suma prawdopodobieństw ", 1.0, sumNormal, 0.01);
            // Powinna być równa 1.0
        } catch (Throwable e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }
    }
}

