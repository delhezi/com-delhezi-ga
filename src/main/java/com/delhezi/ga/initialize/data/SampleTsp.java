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
import com.delhezi.ga.genes.PointGene;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Wojciech.Wolszczak
 */
public class SampleTsp<GENE_TYPE> {

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
    public static <GENE_TYPE> LinkedList<Chromosome<GENE_TYPE>> newChromosomes(final int populationSize, final int maxPopulationSize,
            final ChromosomeProperties chromosomeProperties) {
        LinkedList<Chromosome<GENE_TYPE>> chromosomes = new LinkedList<Chromosome<GENE_TYPE>>();
        return setChromosomes(chromosomes, populationSize, chromosomeProperties);
    }

    /**
     * Populacja o stałej liczebności.
     * @param populationSize Wielkość populacji
     * @param chromosomeProperties Właściwości chromosomu
     * @return Lista chromosomów
     */
    public static <GENE_TYPE> LinkedList<Chromosome<GENE_TYPE>> newChromosomes(final int populationSize, final ChromosomeProperties chromosomeProperties) {
        LinkedList<Chromosome<GENE_TYPE>> chromosomes = new LinkedList<Chromosome<GENE_TYPE>>();
        return setChromosomes(chromosomes, populationSize, chromosomeProperties);
    }

    /**
     * Inicjalizacja chromosomów.
     * @param chromosomes Lista chromosomów
     * @param populationSize Wielkość populacji
     * @param chromosomeProperties Właściwości chromosomu
     * @return Lista chromosomów
     */
    @SuppressWarnings("unchecked")
    private static <GENE_TYPE> LinkedList<Chromosome<GENE_TYPE>> setChromosomes(final LinkedList<Chromosome<GENE_TYPE>> chromosomes, final int populationSize,
            final ChromosomeProperties chromosomeProperties) {
        PointGene[] chromosomeTmp;

        int chromosomeSize = COUNTRY_COUNT; // STAŁA ILOŚĆ MIAST, ALE MOŻNA ZMIENIĆ
        chromosomeTmp = new PointGene[chromosomeSize];
        for (int i = 0; i < chromosomeSize; i++) {
            chromosomeTmp[i] = new PointGene((int) (Math.random() * POINT_MAX_X), (int) (Math.random() * POINT_MAX_Y));
        }

        GENE_TYPE[] chromosomeI;
        for (int i = 0; i < populationSize; i++) {
            // Przemieszaj geny.
            com.delhezi.ga.utility.ShuffleList.shuffle(chromosomeTmp);  //ISTNIEJE OPERACJA Collections.shuffle
            //Collections.shuffle(Arrays.asList(chromosomeTmp));
            chromosomeI = (GENE_TYPE[]) new Object[chromosomeSize];
            System.arraycopy(chromosomeTmp, 0, chromosomeI, 0, chromosomeTmp.length);
            chromosomes.add(new Chromosome<GENE_TYPE>(chromosomeI, chromosomeProperties));
        }
        return chromosomes;
    }
}
