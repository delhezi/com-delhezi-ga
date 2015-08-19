package com.delhezi.ga.crossover.standard;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import java.util.ArrayList;
import java.util.List;
import junitx.util.PrivateAccessor;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

public class KPointCrossoverTest {

    private static final Logger log =
        Logger.getLogger(KPointCrossoverTest.class);

    public KPointCrossoverTest() {
    }


    /**
     * KPointCrossover#crossover(com.delhezi.ga.Chromosome,com.delhezi.ga.Chromosome)
     */
    @Test
    public void testCrossover() {
        //fail("Unimplemented");
    }

    /**
     * KPointCrossover#crossover(com.delhezi.ga.Chromosome,com.delhezi.ga.Chromosome,int)
     */
    @Test
    public void testCrossover1() {
        ChromosomeProperties chromosomeProperties =
            ChromosomeProperties.getInstance();
        KPointCrossover<Integer> instance = new KPointCrossover<Integer>();

        Integer[] genes1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        Chromosome<Integer> chromosome1 =
            new Chromosome<Integer>(genes1, chromosomeProperties);

        Integer[] genes2 = { 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
        Chromosome<Integer> chromosome2 =
            new Chromosome<Integer>(genes2, chromosomeProperties);

        int k = 5;

        instance.crossover(chromosome1, chromosome2, k);

        //for (int i = 0; i < chromosome1.size(); i++) {
        //    System.out.println("chromosome1 = " + chromosome1.getGene(i).toString());
        //}
        //System.out.println(" --- ");
        //for (int i = 0; i < chromosome2.size(); i++) {
        //    System.out.println("chromosome2 = " + chromosome2.getGene(i).toString());
        //}
      }

    /**
     * KPointCrossover#crossover(com.delhezi.ga.Chromosome,com.delhezi.ga.Chromosome,java.util.List)
     */
    @Test
    public void testCrossover2() {
      ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
      KPointCrossover<Integer> instance = new KPointCrossover<Integer>();

      Integer[] genes1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
      Chromosome<Integer> chromosome1 =
          new Chromosome<Integer>(genes1, chromosomeProperties);
      
      Integer[] genes2 = { 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
      Chromosome<Integer> chromosome2 =
          new Chromosome<Integer>(genes2, chromosomeProperties);
      
      int k = 5;
      List<Integer> kPoints = new ArrayList<Integer>(k);
      kPoints.add(1);
      kPoints.add(4);
      kPoints.add(6);
      kPoints.add(9);
      kPoints.add(10);
      //x|xxx|xx|xxx|x|x
      
      Class parameterTypes[] = { Chromosome.class, Chromosome.class, List.class };
      Object args[] = { chromosome1, chromosome2, kPoints };
      
      try {
          PrivateAccessor.invoke(instance, "crossover", parameterTypes,
                                 args);
      } catch (Throwable e) {
          log.debug("Exception: ", e);
          e.printStackTrace();
          fail();
      }

        Integer[] expResult1 = {  1,  22, 23, 24,   5,  6,  27, 28, 29,  10,  31 };
        Integer[] expResult2 = { 21,   2,  3,  4,  25, 26,   7,  8,  9,  30,  11 };
        for (int i = 0; i < chromosome1.size(); i++) {
            assertEquals(expResult1[i], chromosome1.getGene(i));
            //System.out.println(chromosome1.getGene(i).toString());
            assertEquals(expResult2[i], chromosome2.getGene(i));
            //System.out.println(chromosome2.getGene(i).toString());
        }


    }

    /**
     * KPointCrossover#setK(int)
     */
    @Test
    public void testSetK() {
        //fail("Unimplemented");
    }

    /**
     * KPointCrossover#getK()
     */
    @Test
    public void testGetK() {
        //fail("Unimplemented");
    }
}
