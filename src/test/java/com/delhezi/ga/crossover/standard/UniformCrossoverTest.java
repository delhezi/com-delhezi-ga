package com.delhezi.ga.crossover.standard;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import com.delhezi.ga.exception.GeneticAlgorithmException;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UniformCrossoverTest {
    public UniformCrossoverTest() {
    }

    @Before
    public void setUp() throws Exception {
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
     * @see UniformCrossover#crossover(com.delhezi.ga.Chromosome,com.delhezi.ga.Chromosome)
     */
    @Test
    public void testCrossover() {
      System.out.println("crossover");
      ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
      //TODO zamockowac geny
      Integer[] genes1 = {6, 11, 8, 5, 9, 7, 1, 4, 10, 2, 3};
      Chromosome<Integer> chromosome1 =
              new Chromosome<Integer>(genes1, chromosomeProperties);

      Integer[] genes2 = {5, 11, 3, 9, 8, 6, 7, 1, 10, 4, 2};
      Chromosome<Integer> chromosome2 =
              new Chromosome<Integer>(genes2, chromosomeProperties);

        UniformCrossover instance = new UniformCrossover();
        instance.crossover(chromosome1, chromosome2);

        //for (int i = 0; i < genes1.length; i++) {
        //    System.out.println("getGene(" + i + ") = " +
        //                       chromosome1.getGene(i).toString());
        //}

        //for (int i = 0; i < genes2.length; i++) {
        //    System.out.println("getGene(" + i + ") = " +
        //                       chromosome2.getGene(i).toString());
        //}
    }
}
