/**
 * @(#)_2OptTest.java
 * Copyright (C) 2008-2010 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation.heuristics;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;

import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.FitnessFunction;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;
import com.delhezi.ga.genes.PointGene;

import java.io.File;

import org.apache.log4j.Logger;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class _2OptTest {

    private IFitnessFunctionDriver fitnessFunctionDriver;
    private static FitnessFunction fitnessFunction;
    private static ChromosomeProperties chromosomeProperties;
    private static final Logger log = Logger.getLogger(_2OptTest.class);

    public _2OptTest() {
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


    /**
     * com.delhezi.ga.mutation.heuristics._2Opt#mutation(com.delhezi.ga.Chromosome)
     */
    @Test
    public void testMutation() {
      //  System.out.println("mutation");
      //  Chromosome chromosome = null;
      //  _2Opt instance = new _2Opt();
      //  instance.mutation(chromosome);
    }

    /**
     * com.delhezi.ga.mutation.heuristics._2Opt#mutation(com.delhezi.ga.Chromosome,int,int)
     */
    @Test
    public void test_2opt() {

      ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
      _2Opt<PointGene> instance = new _2Opt<PointGene>();

      PointGene[] genes = { new PointGene(11,1), new PointGene(1,23),
                        new PointGene(1,31), new PointGene(1,4),
                        new PointGene(12,6), new PointGene(22,6),
                        new PointGene(32,4), new PointGene(2,1)};

      Chromosome<PointGene> chromosome = new Chromosome<PointGene>(genes, chromosomeProperties);
      int edge1=4;   //1 2 3 4 - 5 6 7 8
      int edge2=8;   //1 2 3 4   5 6 7 8 -

        /* try {
            System.out.println("fitnessFunction=" + fitnessFunction.calculateFitness(genes));
        } catch (GeneticAlgorithmException e) {
          log.debug("Exception: ", e);
          e.printStackTrace();
          fail();
        } */
        instance.mutation(chromosome, edge1, edge2);

        /* try {
            System.out.println("fitnessFunction=" + fitnessFunction.calculateFitness(genes));
        } catch (GeneticAlgorithmException e) {
          log.debug("Exception: ", e);
          e.printStackTrace();
          fail();
        } */
        PointGene[] expResult1 = { new PointGene(11,1), new PointGene(1,23),
                             new PointGene(1,31), new PointGene(1,4),
                             new PointGene(2,1), new PointGene(32,4),
                             new PointGene(22,6), new PointGene(12,6)};
      for (int i=0; i<chromosome.size(); i++){
          assertEquals(expResult1[i], chromosome.getGene(i));
          //System.out.println(chromosome.size().toString());
      }

    }

    /**
     * com.delhezi.ga.mutation.heuristics._2Opt#changeEdge(com.delhezi.ga.Chromosome,int,int)
     */
    @Test
    public void testChangeEdge() {
      System.out.println("changeEdge");
      ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
      _2Opt<PointGene> instance = new _2Opt<PointGene>();

      PointGene[] genes1 = { new PointGene(1,1), new PointGene(2,2),
                        new PointGene(3,3), new PointGene(4,4),
                        new PointGene(5,5), new PointGene(6,6),
                        new PointGene(7,7), new PointGene(8,8)};

      Chromosome<PointGene> chromosome1 = new Chromosome<PointGene>(genes1, chromosomeProperties);
      int edge1_1=4;   // 1 2 3 4a - b5 6 7 8
      int edge2_1=8;   //d1 2 3 4    5  6 7 8c -
      //Zawsze odwracamy odcinek bc.

      instance.changeEdge(chromosome1, edge1_1, edge2_1);

      PointGene[] expResult1 = { new PointGene(1,1), new PointGene(2,2),
                             new PointGene(3,3), new PointGene(4,4),
                             new PointGene(8,8), new PointGene(7,7),
                             new PointGene(6,6), new PointGene(5,5)};

      for (int i=0; i<chromosome1.size(); i++){
          assertEquals(expResult1[i], chromosome1.getGene(i));
          //System.out.println(chromosome1.getGene(i).toString());
      }

      //-------------------------------------------------------
      PointGene[] genes2 = { new PointGene(1,1), new PointGene(2,2),
                         new PointGene(3,3), new PointGene(4,4),
                         new PointGene(5,5), new PointGene(6,6),
                         new PointGene(7,7), new PointGene(8,8)};

      Chromosome<PointGene> chromosome2 = new Chromosome<PointGene>(genes2, chromosomeProperties);
      int edge1_2=6;   //1 2 3 4     5 6a - b7 8
      int edge2_2=4;   //1 2 3 4c - d5 6     7 8
      //Zawsze odwracamy odcinek bc.

      instance.changeEdge(chromosome2, edge1_2, edge2_2);

      PointGene[] expResult2 = { new PointGene(2,2), new PointGene(1,1),
                             new PointGene(8,8), new PointGene(7,7),
                             new PointGene(5,5), new PointGene(6,6),
                             new PointGene(4,4), new PointGene(3,3)};

      for (int i=0; i<chromosome2.size(); i++){
          assertEquals(expResult2[i], chromosome2.getGene(i));
          //System.out.println(chromosome2.getGene(i).toString());
      }

      //-------------------------------------------------------
      PointGene[] genes3 = { new PointGene(1,1), new PointGene(2,2),
                         new PointGene(3,3), new PointGene(4,4),
                         new PointGene(5,5), new PointGene(6,6),
                         new PointGene(7,7), new PointGene(8,8)};

      Chromosome<PointGene> chromosome3 = new Chromosome<PointGene>(genes3, chromosomeProperties);
      int edge1_3=8;   //b1 2 3 4    5  6 7 8a -
      int edge2_3=4;   // 1 2 3 4c - d5 6 7 8
      //Zawsze odwracamy odcinek bc.

      instance.changeEdge(chromosome3, edge1_3, edge2_3);

      PointGene[] expResult3 = { new PointGene(4,4), new PointGene(3,3),
                             new PointGene(2,2), new PointGene(1,1),
                             new PointGene(5,5), new PointGene(6,6),
                             new PointGene(7,7), new PointGene(8,8)};

      for (int i=0; i<chromosome3.size(); i++){
          assertEquals(expResult3[i], chromosome3.getGene(i));
          //System.out.println(chromosome3.size().toString());
      }
    }

    /**
     * com.delhezi.ga.mutation.heuristics._2Opt#selectEdge(com.delhezi.ga.Chromosome,int,int)
     */
    @Test
    public void testSelectEdge() {
      //  System.out.println("selectEdge");
      //  Chromosome chromosome = null;
      //  int edge1 = 0;
      //  int edge2 = 0;
      //  _2Opt instance = new _2Opt();
      //  boolean expResult = false;
      //  boolean result = instance.selectEdge(chromosome, edge1, edge2);
      //  assertEquals(expResult, result);
    }

}
