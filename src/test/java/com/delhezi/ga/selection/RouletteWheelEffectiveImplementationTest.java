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

import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RouletteWheelEffectiveImplementationTest {

    private IFitnessFunctionDriver fitnessFunctionDriver;
    private static FitnessFunction fitnessFunction;
    private static ChromosomeProperties chromosomeProperties;
    private static final Logger log =
        Logger.getLogger(RouletteWheelEffectiveImplementationTest.class);

    public RouletteWheelEffectiveImplementationTest() {
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

    /**
     * RouletteWheelEffectiveImplementation#rouletteWheelImpl(java.util.LinkedList,double[])
     */
    @Test
    public void testRouletteWheelImpl() {
        //fail("Unimplemented");
    }

    /**
     * RouletteWheelEffectiveImplementation#tableInitialize(java.util.LinkedList,double [],int[])
     */
    @Test
    public void testTableInitialize() {
        //fail("Unimplemented");
    }

    /**
     * AbstractRouletteWheel#select(java.util.LinkedList)
     */
    @Test
    public void testSelect() {
        
        //ZOBACZ WYNIKI TESTU
        //DELHEZI/com-delhezi-lib/com-delhezi-ga/testy_dodatkowe/RouletteWheelElementaryImplementationTest.xlsx
        
        fitnessFunction.setMaximisation(false);
        //fitnessFunction.setMaximisation(true);

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
        Point[] genes_11 = {new Point(101,120), new Point(102,220),
                            new Point(153,220), new Point(104,220),
                            new Point(105,220), new Point(106,230)};
        Point[] genes_12 = {new Point(101,120), new Point(102,220),
                            new Point(103,220), new Point(104,220),
                            new Point(105,220), new Point(106,220)};
        Point[] genes_13 = {new Point(101,120), new Point(102,130),
                            new Point(103,140), new Point(104,150),
                            new Point(105,160), new Point(105,170)};
        Point[] genes_14 = {new Point(101,121), new Point(102,122),
                            new Point(103,123), new Point(104,124),
                            new Point(105,125), new Point(105,126)};
        Point[] genes_15 = {new Point(101,120), new Point(102,120),
                            new Point(103,120), new Point(104,120),
                            new Point(105,120), new Point(106,120)};
        Point[] genes_16 = {new Point(101,121), new Point(102,122),
                            new Point(103,123), new Point(104,124),
                            new Point(105,125), new Point(105,136)};
        Point[] genes_17 = {new Point(101,120), new Point(102,220),
                            new Point(103,220), new Point(104,220),
                            new Point(105,220), new Point(106,230)};
        Point[] genes_18 = {new Point(101,120), new Point(102,220),
                            new Point(153,220), new Point(104,220),
                            new Point(105,220), new Point(106,220)};
        Point[] genes_19 = {new Point(101,120), new Point(102,130),
                            new Point(153,140), new Point(104,150),
                            new Point(105,160), new Point(105,170)};
        Point[] genes_20 = {new Point(101,121), new Point(102,122),
                            new Point(153,123), new Point(104,124),
                            new Point(105,125), new Point(105,126)};
        Point[] genes_21 = {new Point(101,120), new Point(102,120),
                            new Point(153,120), new Point(104,120),
                            new Point(105,120), new Point(106,120)};
        Point[] genes_22 = {new Point(101,121), new Point(102,122),
                            new Point(153,123), new Point(104,124),
                            new Point(105,125), new Point(105,136)};
        Point[] genes_23 = {new Point(101,120), new Point(102,220),
                            new Point(153,220), new Point(104,220),
                            new Point(105,220), new Point(106,230)};

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
        chromosomes.add(new Chromosome(genes_11, chromosomeProperties));    
        chromosomes.add(new Chromosome(genes_12, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_13, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_14, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_15, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_16, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_17, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_18, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_19, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_20, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_21, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_22, chromosomeProperties));
        chromosomes.add(new Chromosome(genes_23, chromosomeProperties));

        RouletteWheelEffectiveImplementation instance =
            new RouletteWheelEffectiveImplementation();
        
       // int i=0;
        //for (Chromosome ch : chromosomes){
        //    try {
        //        System.out.println(i+ " ch.getFitness(...) = " + ch.getFitness());
        //    } catch (GeneticAlgorithmException e) {
        //    }
        //    i++;
        //}
        //System.out.println("------------------------------------");
        
        
        try {
            chromosomes = instance.select(chromosomes);
        } catch (GeneticAlgorithmException e) {
          log.debug("Exception: ", e);
          e.printStackTrace();
          fail();
        }

        //i=0;
        //for (Chromosome ch : chromosomes){
        //    try {
        //        System.out.println(i+ " ch.getFitness(...) = " + ch.getFitness());
        //    } catch (GeneticAlgorithmException e) {
        //    }
        //    i++;
        //}
        //System.out.println("------------------------------------");
      }

    /**
     * AbstractRouletteWheel#rouletteWheelImpl(java.util.LinkedList,double[])
     */
    @Test
    public void testRouletteWheelImpl1() {
        //fail("Unimplemented");
    }

    /**
     * AbstractRouletteWheel#setVariable(java.util.LinkedList)
     */
    @Test
    public void testSetVariable() {
        //fail("Unimplemented");
    }
}
