/**
 * @(#)SwapMutationTest.java
 * Copyright (C) 2008-2010 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import com.delhezi.ga.selection.RouletteWheelElementaryImplementationTest;

import java.util.Random;

import org.apache.log4j.Logger;

import org.junit.Test;
import static org.junit.Assert.*;

public class SwapMutationTest {

    private static final Logger log = Logger.getLogger(SwapMutationTest.class);

    public SwapMutationTest() {
    }

  private static Random random = new Random();
  
    /**
     * @see com.delhezi.ga.mutation.SwapMutation#mutation(com.delhezi.ga.Chromosome)
     */
    @Test
    public void testMutation() {
      // System.out.println("mutation");
      // Chromosome chromosome = null;
      // SwapMutation instance = new SwapMutation();
      // instance.mutation(chromosome);
    }

    /**
     * @see com.delhezi.ga.mutation.SwapMutation#mutation(com.delhezi.ga.Chromosome,int,int)
     */
    @Test
    public void testMutation1() {
      System.out.println("mutation");
      ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
      SwapMutation instance = new SwapMutation();

      Integer[] genes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
      Chromosome<Integer> chromosome =
          new Chromosome<Integer>(genes, chromosomeProperties);
      int gene1=1;   //xXxxxxxxxxx
      int gene2=5;   //xxxxxXxxxxx
      instance.mutation(chromosome,gene1,gene2);
      Integer[] expResult1 = { 1, 6, 3, 4, 5, 2, 7, 8, 9, 10, 11 };
      for (int i=0; i<chromosome.size(); i++){
          assertEquals(expResult1[i], chromosome.getGene(i));
          //System.out.println(chromosome.getGene(i).toString());
      }
    }
}
