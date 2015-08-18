package com.delhezi.ga.selection;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.FitnessFunction;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;

import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;

import com.delhezi.ga.genes.Point;

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

    private static Point[][] genes = {
            { new Point(101, 120), new Point(102, 220), new Point(103, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 220) }, // genes[0]
            { new Point(101, 120), new Point(102, 130), new Point(103, 140), new Point(104, 150), new Point(105, 160),
                    new Point(105, 170) }, // genes[1]
            { new Point(101, 121), new Point(102, 122), new Point(103, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 126) }, // genes[2]
            { new Point(101, 120), new Point(102, 120), new Point(103, 120), new Point(104, 120), new Point(105, 120),
                    new Point(106, 120) },
            { new Point(101, 121), new Point(102, 122), new Point(103, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 136) },
            { new Point(101, 120), new Point(102, 220), new Point(103, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 230) },
            { new Point(101, 120), new Point(102, 220), new Point(153, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 220) },
            { new Point(101, 120), new Point(102, 130), new Point(153, 140), new Point(104, 150), new Point(105, 160),
                    new Point(105, 170) },
            { new Point(101, 121), new Point(102, 122), new Point(153, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 126) },
            { new Point(101, 120), new Point(102, 120), new Point(153, 120), new Point(104, 120), new Point(105, 120),
                    new Point(106, 120) },
            { new Point(101, 121), new Point(102, 122), new Point(153, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 136) },
            { new Point(101, 120), new Point(102, 220), new Point(153, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 230) },
            { new Point(101, 120), new Point(102, 220), new Point(103, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 220) },
            { new Point(101, 120), new Point(102, 130), new Point(103, 140), new Point(104, 150), new Point(105, 160),
                    new Point(105, 170) },
            { new Point(101, 121), new Point(102, 122), new Point(103, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 126) },
            { new Point(101, 120), new Point(102, 120), new Point(103, 120), new Point(104, 120), new Point(105, 120),
                    new Point(106, 120) },
            { new Point(101, 121), new Point(102, 122), new Point(103, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 136) },
            { new Point(101, 120), new Point(102, 220), new Point(103, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 230) },
            { new Point(101, 120), new Point(102, 220), new Point(153, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 220) },
            { new Point(101, 120), new Point(102, 130), new Point(153, 140), new Point(104, 150), new Point(105, 160),
                    new Point(105, 170) },
            { new Point(101, 121), new Point(102, 122), new Point(153, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 126) },
            { new Point(101, 120), new Point(102, 120), new Point(153, 120), new Point(104, 120), new Point(105, 120),
                    new Point(106, 120) },
            { new Point(101, 121), new Point(102, 122), new Point(153, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 136) },
            { new Point(101, 120), new Point(102, 220), new Point(153, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 230) } };// genes[23]
    
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

        LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
        for (int i = 0; i < this.genes.length; i++) {
            chromosomes.add(new Chromosome(this.genes[i], chromosomeProperties));
        }

        Tournament instance = new Tournament();
        instance.setArity(3);

        int i = 0;
        double fitnessSum = 0;
    /*
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


    timeEnd = System.currentTimeMillis();
    double seconds = (timeEnd-timeStart)/1000.0;
    System.out.println("testSelect Time elapsed: "+new DecimalFormat("0.000").format(seconds)+" sec\n");
     */
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
