/**
 * @(#)SampleTsp.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.initialize.data;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import com.delhezi.ga.genes.Point;
import java.util.LinkedList;

/**
 *
 * @author Wojciech.Wolszczak
 */
public class SampleTsp {

    /**
     * Populacja o zmiennej liczebności.
     * @param populationSize Wielkość populacji
     * @param maxPopulationSize Maksymalna wielkość populacji
     * @param chromosomeProperties Właściwości chromosomu
     * @return Lista chromosomów
     */
    public static LinkedList<Chromosome> newChromosomes(int populationSize,
            int maxPopulationSize,
            ChromosomeProperties chromosomeProperties){
        LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
        return setChromosomes(chromosomes, populationSize, chromosomeProperties);
    }
    
    /**
     * Populacja o stałej liczebności.
     * @param populationSize Wielkość populacji
     * @param chromosomeProperties Właściwości chromosomu
     * @return Lista chromosomów
     */
    public static LinkedList<Chromosome> newChromosomes(int populationSize,
            ChromosomeProperties chromosomeProperties){
        LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
        return setChromosomes(chromosomes, populationSize, chromosomeProperties);
        }

    
    private static LinkedList<Chromosome> setChromosomes(LinkedList<Chromosome> chromosomes,
            int populationSize, ChromosomeProperties chromosomeProperties){
            Point[] chromosome_tmp;

                int chromosomeSize =  60; //STAŁA ILOŚĆ MIAST, ALE MOŻNA ZMIENIĆ
                chromosome_tmp = new Point[chromosomeSize];
                for (int i = 0; i < chromosomeSize; i++)
                    chromosome_tmp[i] = new Point((int) (Math.random() * 200),
                            (int) (Math.random() * 200));

            Point[] chromosome_i;
            for (int i = 0; i < populationSize; i++) {
                //Przemieszaj geny.
                com.delhezi.ga.utility.ShuffleList.shuffle(chromosome_tmp);
                chromosome_i = new Point[chromosomeSize];
                System.arraycopy(chromosome_tmp, 0, chromosome_i, 0,chromosome_tmp.length);
                chromosomes.add(new Chromosome(chromosome_i, chromosomeProperties));
            }
            return chromosomes;
    }

}
