package com.delhezi.ga.selection;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.FitnessFunction;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;

import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;

import com.delhezi.ga.genes.PointGene;

import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TournamentTest {

    private IFitnessFunctionDriver fitnessFunctionDriver;
    private static FitnessFunction fitnessFunction;
    private static ChromosomeProperties chromosomeProperties;
    private static final Logger log = Logger.getLogger(TournamentTest.class);

    private static PointGene[][] genes = {
            { new PointGene(101, 120), new PointGene(102, 220), new PointGene(103, 220), new PointGene(104, 220), new PointGene(105, 220),
                    new PointGene(106, 220) }, // genes[0]
            { new PointGene(101, 120), new PointGene(102, 130), new PointGene(103, 140), new PointGene(104, 150), new PointGene(105, 160),
                    new PointGene(105, 170) }, // genes[1]
            { new PointGene(101, 121), new PointGene(102, 122), new PointGene(103, 123), new PointGene(104, 124), new PointGene(105, 125),
                    new PointGene(105, 126) }, // genes[2]
            { new PointGene(101, 120), new PointGene(102, 120), new PointGene(103, 120), new PointGene(104, 120), new PointGene(105, 120),
                    new PointGene(106, 120) },
            { new PointGene(101, 121), new PointGene(102, 122), new PointGene(103, 123), new PointGene(104, 124), new PointGene(105, 125),
                    new PointGene(105, 136) },
            { new PointGene(101, 120), new PointGene(102, 220), new PointGene(103, 220), new PointGene(104, 220), new PointGene(105, 220),
                    new PointGene(106, 230) },
            { new PointGene(101, 120), new PointGene(102, 220), new PointGene(153, 220), new PointGene(104, 220), new PointGene(105, 220),
                    new PointGene(106, 220) },
            { new PointGene(101, 120), new PointGene(102, 130), new PointGene(153, 140), new PointGene(104, 150), new PointGene(105, 160),
                    new PointGene(105, 170) },
            { new PointGene(101, 121), new PointGene(102, 122), new PointGene(153, 123), new PointGene(104, 124), new PointGene(105, 125),
                    new PointGene(105, 126) },
            { new PointGene(101, 120), new PointGene(102, 120), new PointGene(153, 120), new PointGene(104, 120), new PointGene(105, 120),
                    new PointGene(106, 120) },
            { new PointGene(101, 121), new PointGene(102, 122), new PointGene(153, 123), new PointGene(104, 124), new PointGene(105, 125),
                    new PointGene(105, 136) },
            { new PointGene(101, 120), new PointGene(102, 220), new PointGene(153, 220), new PointGene(104, 220), new PointGene(105, 220),
                    new PointGene(106, 230) },
            { new PointGene(101, 120), new PointGene(102, 220), new PointGene(103, 220), new PointGene(104, 220), new PointGene(105, 220),
                    new PointGene(106, 220) },
            { new PointGene(101, 120), new PointGene(102, 130), new PointGene(103, 140), new PointGene(104, 150), new PointGene(105, 160),
                    new PointGene(105, 170) },
            { new PointGene(101, 121), new PointGene(102, 122), new PointGene(103, 123), new PointGene(104, 124), new PointGene(105, 125),
                    new PointGene(105, 126) },
            { new PointGene(101, 120), new PointGene(102, 120), new PointGene(103, 120), new PointGene(104, 120), new PointGene(105, 120),
                    new PointGene(106, 120) },
            { new PointGene(101, 121), new PointGene(102, 122), new PointGene(103, 123), new PointGene(104, 124), new PointGene(105, 125),
                    new PointGene(105, 136) },
            { new PointGene(101, 120), new PointGene(102, 220), new PointGene(103, 220), new PointGene(104, 220), new PointGene(105, 220),
                    new PointGene(106, 230) },
            { new PointGene(101, 120), new PointGene(102, 220), new PointGene(153, 220), new PointGene(104, 220), new PointGene(105, 220),
                    new PointGene(106, 220) },
            { new PointGene(101, 120), new PointGene(102, 130), new PointGene(153, 140), new PointGene(104, 150), new PointGene(105, 160),
                    new PointGene(105, 170) },
            { new PointGene(101, 121), new PointGene(102, 122), new PointGene(153, 123), new PointGene(104, 124), new PointGene(105, 125),
                    new PointGene(105, 126) },
            { new PointGene(101, 120), new PointGene(102, 120), new PointGene(153, 120), new PointGene(104, 120), new PointGene(105, 120),
                    new PointGene(106, 120) },
            { new PointGene(101, 121), new PointGene(102, 122), new PointGene(153, 123), new PointGene(104, 124), new PointGene(105, 125),
                    new PointGene(105, 136) },
            { new PointGene(101, 120), new PointGene(102, 220), new PointGene(153, 220), new PointGene(104, 220), new PointGene(105, 220),
                    new PointGene(106, 230) } };// genes[23]
    
    private long timeStart; //milisek
    private long timeEnd; //milisek
    
    public TournamentTest() {
    }

    @Before
  public void setUp() throws Exception {
      String scriptEngineDriverPath ="";
      File dir1 = new File (".");
      try {
          scriptEngineDriverPath = dir1.getCanonicalPath().replaceAll("\\\\", "/") +"/scripts/";
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
     * Tournament#select(java.util.LinkedList)
     */
    @Test
    public void testSelect() {
        timeStart = System.currentTimeMillis();

        fitnessFunction.setMaximisation(false);
        // fitnessFunction.setMaximisation(true);

        LinkedList<Chromosome<PointGene>> chromosomes = new LinkedList<Chromosome<PointGene>>();
        for (int i = 0; i < TournamentTest.genes.length; i++) {
            chromosomes.add(new Chromosome<PointGene>(TournamentTest.genes[i], chromosomeProperties));
        }

        Tournament<PointGene> instance = new Tournament<PointGene>();
        instance.setArity(3);
        /*
        int i = 0;
        double fitnessSum = 0;

    System.out.println("------------------------------------");
    System.out.println(" isMaximisation() = " +
                       fitnessFunction.isMaximisation());
    System.out.println("------------------------------------");

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
    timeEnd = System.currentTimeMillis();
    double seconds = (timeEnd-timeStart)/1000.0;
    System.out.println("testSelect Time elapsed: "+new DecimalFormat("0.000").format(seconds)+" sec\n");

  }

    /**
     * Tournament#getArity()
     */
    @Test
    public void testGetArity() {
        //fail("Unimplemented");
    }

    /**
     * Tournament#setArity(int)
     */
    @Test
    public void testSetArity() {
        //fail("Unimplemented");
    }
}
