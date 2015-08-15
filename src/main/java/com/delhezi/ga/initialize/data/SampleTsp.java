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

    /** */
    private static final int COUNTRY_COUNT = 60;
    /** */
    private static final int POINT_MAX_X = 200;
    /** */
    private static final int POINT_MAX_Y = 200;

    /**
     * Populacja o zmiennej liczebności.
     * @param populationSize Wielkość populacji
     * @param maxPopulationSize Maksymalna wielkość populacji
     * @param chromosomeProperties Właściwości chromosomu
     * @return Lista chromosomów
     */
    public static LinkedList<Chromosome> newChromosomes(final int populationSize, final int maxPopulationSize,
            final ChromosomeProperties chromosomeProperties) {
        LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
        return setChromosomes(chromosomes, populationSize, chromosomeProperties);
    }

    /**
     * Populacja o stałej liczebności.
     * @param populationSize Wielkość populacji
     * @param chromosomeProperties Właściwości chromosomu
     * @return Lista chromosomów
     */
    public static LinkedList<Chromosome> newChromosomes(final int populationSize, final ChromosomeProperties chromosomeProperties) {
        LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
        return setChromosomes(chromosomes, populationSize, chromosomeProperties);
    }

    /**
     * Inicjalizacja chromosomów.
     * @param chromosomes Lista chromosomów
     * @param populationSize Wielkość populacji
     * @param chromosomeProperties Właściwości chromosomu
     * @return Lista chromosomów
     */
    private static LinkedList<Chromosome> setChromosomes(final LinkedList<Chromosome> chromosomes, final int populationSize,
            final ChromosomeProperties chromosomeProperties) {
        Point[] chromosomeTmp;

        int chromosomeSize = COUNTRY_COUNT; // STAŁA ILOŚĆ MIAST, ALE MOŻNA ZMIENIĆ
        chromosomeTmp = new Point[chromosomeSize];
        for (int i = 0; i < chromosomeSize; i++) {
            chromosomeTmp[i] = new Point((int) (Math.random() * POINT_MAX_X), (int) (Math.random() * POINT_MAX_Y));
        }

        Point[] chromosomeI;
        for (int i = 0; i < populationSize; i++) {
            // Przemieszaj geny.
            com.delhezi.ga.utility.ShuffleList.shuffle(chromosomeTmp);
            chromosomeI = new Point[chromosomeSize];
            System.arraycopy(chromosomeTmp, 0, chromosomeI, 0, chromosomeTmp.length);
            chromosomes.add(new Chromosome(chromosomeI, chromosomeProperties));
        }
        return chromosomes;
    }
}
