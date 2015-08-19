/**
 * @(#)InversionMutationTest.java
 * Copyright (C) 2008-2010 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;

public class InversionMutationTest {
    public InversionMutationTest() {
    }

    /**
     * Przypadek: chromosome = null.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome)
     */
    @Test(expected = IllegalArgumentException.class)
    public void illegal_1_Mutation() {
      InversionMutation<Integer> instance = new InversionMutation<Integer>();
      Chromosome<Integer> chromosome = null;
      instance.mutation(chromosome);
      }
    
    /**
     * Przypadek: chromosome.size() < 2;
     * Brak mutacji.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome)
     */
    @Test()
    public void test_1_Mutation() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        Integer[] expResult = {1};
        instance.mutation(chromosome); //brak mutacji
        for (int i = 0; i < chromosome.size(); i++) {
            assertEquals(expResult[i], chromosome.getGene(i));
            //System.out.println(chromosome.getGene(i).toString());
            }
        }

    /**
     * Test dla standardowych danych.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome)
     */
    @Test
    public void testMutation() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Chromosome<Integer> chromosome ;
        
        //test (2)
        Integer[] genes1 = {1};
        chromosome = new Chromosome<Integer>(genes1, chromosomeProperties);
        instance.mutation(chromosome);
        Integer[] expResult1 = {1};
        assertEquals(expResult1[0], chromosome.getGene(0));

        //test (3)
        Integer[] genes2 = {1, 4, 6, 8};
        chromosome = new Chromosome<Integer>(genes2, chromosomeProperties);
        instance.mutation(chromosome);
        for (int i = 0; i < chromosome.size(); i++) {
          assertThat(chromosome.getGene(i).toString(), anyOf(is("1"), is("4"), is("6"), is("8")));
            }
    }



    /**
     * Przypadek: chromosome = null.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test(expected = AssertionError.class)
    public void illegal_1_Mutation1() {
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Chromosome<Integer> chromosome = null;
        instance.mutation(chromosome, 0, 0, 0);
        }
    
    /**
     * Przypadek: cutPoint1 > chromosome.size().
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test(expected = AssertionError.class)
    public void illegal_2_Mutation1() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        int cutPoint1 = chromosome.size() + 1;
        instance.mutation(chromosome, cutPoint1, 0, 0);
        }
    
    /**
     * Przypadek: cutPoint1 < 0.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test(expected = AssertionError.class)
    public void illegal_3_Mutation1() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        int cutPoint1 = -1;
        instance.mutation(chromosome, cutPoint1, 0, 0);
        }
    
    /**
     * Przypadek: cutPoint2 > chromosome.size().
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test(expected = AssertionError.class)
    public void illegal_4_Mutation1() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        int cutPoint2 = chromosome.size() + 1;
        instance.mutation(chromosome, 0, cutPoint2, 0);
        }
    
    /**
     * Przypadek: cutPoint2 < 0.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test(expected = AssertionError.class)
    public void illegal_5_Mutation1() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        int cutPoint2 = -1;
        instance.mutation(chromosome, 0, cutPoint2, 0);
        }
    
    /**
     * Przypadek: insertPoint < 0.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test(expected = AssertionError.class)
    public void illegal_6_Mutation1() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        int cutPoint1 = 1;
        int cutPoint2 = 4;
        int insertPoint = -1;
        instance.mutation(chromosome, cutPoint1, cutPoint2, insertPoint);
        }
    
    /**
     * Przypadek: insertPoint > chromosome.size() - (cutPoint2 - cutPoint1).
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test(expected = AssertionError.class)
    public void illegal_7_Mutation1() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        int cutPoint1 = 1;
        int cutPoint2 = 4;
        int insertPoint = chromosome.size() - (cutPoint2 - cutPoint1) + 1;
        instance.mutation(chromosome, cutPoint1, cutPoint2, insertPoint);
        }

    /**
     * Przypadek: cutPoint1 = cutPoint2;
     * Brak mutacji.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test()
    public void test_1_Mutation1() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        Integer[] expResult = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int cutPoint1 = 2;
        int cutPoint2 = 2;
          instance.mutation(chromosome, cutPoint1, cutPoint2, 0); //brak mutacji
          for (int i = 0; i < chromosome.size(); i++) {
              assertEquals(expResult[i], chromosome.getGene(i));
              //System.out.println(chromosome.getGene(i).toString());
          }
        }
    
    /**
     * Przypadek: cutPoint1 = chromosome.size() && cutPoint2 = 0;
     * Brak mutacji.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test()
    public void test_2_Mutation1() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        Integer[] expResult = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int cutPoint1 = chromosome.size();
        int cutPoint2 = 0;
        instance.mutation(chromosome, cutPoint1, cutPoint2, 0); //brak mutacji
        for (int i = 0; i < chromosome.size(); i++) {
            assertEquals(expResult[i], chromosome.getGene(i));
            //System.out.println(chromosome.getGene(i).toString());
        }
      }
    
    /**
     * Przypadek: chromosome.size() < 2;
     * Brak mutacji.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test()
    public void test_3_Mutation1() {
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        Integer[] genes = {1};
        Chromosome<Integer> chromosome =
            new Chromosome<Integer>(genes, chromosomeProperties);
        Integer[] expResult = {1};
        int cutPoint1 = 0;
        int cutPoint2 = 1;
        instance.mutation(chromosome, cutPoint1, cutPoint2, 0); //brak mutacji
        for (int i = 0; i < chromosome.size(); i++) {
          assertEquals(expResult[i], chromosome.getGene(i));
          //System.out.println(chromosome.getGene(i).toString());
      }
    }

    /**
     * Test dla standardowych danych.
     * com.delhezi.ga.mutation.InversionMutation#mutation(com.delhezi.ga.Chromosome,int,int,int)
     */
    @Test
    public void testMutation1() {
        System.out.println("mutation");
        ChromosomeProperties chromosomeProperties = ChromosomeProperties.getInstance();
        InversionMutation<Integer> instance = new InversionMutation<Integer>();
        int cutPoint1;
        int cutPoint2;
        int insertPoint;
        
        //Inversion Mutation – przykład 1.
        Integer[] genes2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        Chromosome<Integer> chromosome2 =
            new Chromosome<Integer>(genes2, chromosomeProperties);
        cutPoint1 = 1; //1 | 2 3 4 5 6   7 8 9 10 11
        cutPoint2 = 6; //1   2 3 4 5 6 | 7 8 9 10 11
        insertPoint = 5; //1 7 8 9 10 | 11
        instance.mutation(chromosome2, cutPoint1, cutPoint2, insertPoint);
        Integer[] expResult2 = { 1, 7, 8, 9, 10, 6, 5, 4, 3, 2, 11 };
        for (int i = 0; i < chromosome2.size(); i++) {
            assertEquals(expResult2[i], chromosome2.getGene(i));
            //System.out.println(chromosome2.getGene(i).toString());
        }

        //Inversion Mutation – przykład 2.
        Integer[] genes3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        Chromosome<Integer> chromosome3 =
            new Chromosome<Integer>(genes3, chromosomeProperties);
        cutPoint1 = 7; //1 2   3 4 5 6 7 | 8 9 10 11
        cutPoint2 = 2; //1 2 | 3 4 5 6 7   8 9 10 11
        insertPoint = 4; //3 4 5 6 | 7
        instance.mutation(chromosome3, cutPoint1, cutPoint2, insertPoint);
        Integer[] expResult3 = { 3, 4, 5, 6, 2, 1, 11, 10, 9, 8, 7 };
        for (int i = 0; i < chromosome3.size(); i++) {
            assertEquals(expResult3[i], chromosome3.getGene(i));
            //System.out.println(chromosome3.getGene(i).toString());
        }
    }

}
