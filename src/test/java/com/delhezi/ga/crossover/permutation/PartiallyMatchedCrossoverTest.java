/**
 * @(#)PartiallyMatchedCrossoverTest.java
 * Copyright (C) 2008-2010 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.crossover.permutation;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class PartiallyMatchedCrossoverTest {
    public PartiallyMatchedCrossoverTest() {
    }

    /**
     * @see com.delhezi.ga.crossover.permutation.PartiallyMatchedCrossover#crossover(com.delhezi.ga.Chromosome, com.delhezi.ga.Chromosome)
     */
    @Test
    public void testCrossover() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        //TODO zamockowac geny
        Integer[] genes1 = {6, 11, 8, 5, 9, 7, 1, 4, 10, 2, 3};
        Chromosome<Integer> chromosome1 =
                new Chromosome<Integer>(genes1, chromosomeProperties);

        Integer[] genes2 = {5, 11, 3, 9, 8, 6, 7, 1, 10, 4, 2};
        Chromosome<Integer> chromosome2 =
                new Chromosome<Integer>(genes2, chromosomeProperties);

        PartiallyMatchedCrossover instance = new PartiallyMatchedCrossover();
        instance.crossover(chromosome1, chromosome2);
    }

    /**
     * @see com.delhezi.ga.crossover.permutation.PartiallyMatchedCrossover#crossover(com.delhezi.ga.Chromosome, com.delhezi.ga.Chromosome, int, int)
     */
    @Test
    public void testCrossover1() {
        //given
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        Integer[] genes1 = {6, 11, 8, 5, 9, 7, 1, 4, 10, 2, 3};
        Chromosome<Integer> chromosome1 =
                new Chromosome<Integer>(genes1, chromosomeProperties);

        Integer[] genes2 = {5, 11, 3, 9, 8, 6, 7, 1, 10, 4, 2};
        Chromosome<Integer> chromosome2 =
                new Chromosome<Integer>(genes2, chromosomeProperties);

        int cutpoint1 = 1;
        int cutpoint2 = 6;

        //when
        PartiallyMatchedCrossover instance = new PartiallyMatchedCrossover();
        instance.crossover(chromosome1, chromosome2, cutpoint1, cutpoint2);
        //for (int i=0; i<chromosome1.size(); i++)
        //    System.out.println(chromosome1.getGene(i).toString());
        //System.out.println("-----------");
        //for (int i = 0; i < chromosome2.size(); i++)
        //    System.out.println(chromosome2.getGene(i).toString());

        //then
        assertEquals(chromosome1.size(), 11);
        assertEquals(chromosome1.getGene(0).intValue(), 7);
        assertEquals(chromosome1.getGene(1).intValue(), 11);
        assertEquals(chromosome1.getGene(2).intValue(), 3);
        assertEquals(chromosome1.getGene(3).intValue(), 9);
        assertEquals(chromosome1.getGene(4).intValue(), 8);
        assertEquals(chromosome1.getGene(5).intValue(), 6);
        assertEquals(chromosome1.getGene(6).intValue(), 1);
        assertEquals(chromosome1.getGene(7).intValue(), 4);
        assertEquals(chromosome1.getGene(8).intValue(), 10);
        assertEquals(chromosome1.getGene(9).intValue(), 2);
        assertEquals(chromosome1.getGene(10).intValue(), 5);


        assertEquals(chromosome2.size(), 11);
        assertEquals(chromosome2.getGene(0).intValue(), 3);
        assertEquals(chromosome2.getGene(1).intValue(), 11);
        assertEquals(chromosome2.getGene(2).intValue(), 8);
        assertEquals(chromosome2.getGene(3).intValue(), 5);
        assertEquals(chromosome2.getGene(4).intValue(), 9);
        assertEquals(chromosome2.getGene(5).intValue(), 7);
        assertEquals(chromosome2.getGene(6).intValue(), 6);
        assertEquals(chromosome2.getGene(7).intValue(), 1);
        assertEquals(chromosome2.getGene(8).intValue(), 10);
        assertEquals(chromosome2.getGene(9).intValue(), 4);
        assertEquals(chromosome2.getGene(10).intValue(), 2);
    }

    /**
     * @see com.delhezi.ga.crossover.permutation.PartiallyMatchedCrossover#optSubstring(java.util.ArrayList,java.util.ArrayList)
     */
    @Test
    public void testOptSubstring() {
        //given
        ArrayList<Integer> substring1 = new ArrayList<Integer>(5);
        substring1.add(11);
        substring1.add(8);
        substring1.add(5);
        substring1.add(9);
        substring1.add(7);
        ArrayList<Integer> substring2 = new ArrayList<Integer>(5);
        substring2.add(11);
        substring2.add(3);
        substring2.add(9);
        substring2.add(8);
        substring2.add(6);

        //when
        PartiallyMatchedCrossover instance = new PartiallyMatchedCrossover();
        instance.optSubstring(substring1, substring2);

        //Określenie owzorowania dla sekcji dopasowania.
        // 11 <-> 11
        // 8 <-> 3
        // 5 <-> 9
        // 9 <-> 8
        // 7 <-> 6
        // "zlepiając" powyższe odwzorowanie otrzymujemy
        // 5 <-> 9 <-> 8 <-> 3
        // 7 <-> 6
        // Dodatkowo pominęliśmy odwzorowanie 11<->11
        // Po skróceniu otrzymujemy
        // 5 <-> 3
        // 7 <-> 6
        //for (int i = 0; i < substring1.size(); i++)
        //    System.out.println(substring1.get(i) +" <=> "+substring2.get(i));

        //then
        assertEquals(substring1.size(), 2);
        assertEquals(substring1.get(0).intValue(), 5);
        assertEquals(substring1.get(1).intValue(), 7);
        assertEquals(substring2.get(0).intValue(), 3);
        assertEquals(substring2.get(1).intValue(), 6);
    }

}
