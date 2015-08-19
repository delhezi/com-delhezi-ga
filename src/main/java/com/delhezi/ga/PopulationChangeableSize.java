/**
 * @(#)PopulationChangeableSize.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga;

import com.delhezi.ga.crossover.ICrossover;
import com.delhezi.ga.crossover.factory.CrossoverFactory;
import com.delhezi.ga.crossover.factory.CrossoverOperatorType;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.mutation.IMutation;
import com.delhezi.ga.mutation.factory.MutationFactory;
import com.delhezi.ga.mutation.factory.MutationOperatorType;
import com.delhezi.ga.selection.factory.SelectionMethodType;

import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * <code>PopulationChangeableSize</code>: Klasa populacji o zmiennej
 * liczebności.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public final class PopulationChangeableSize<GENE_TYPE> extends Population<GENE_TYPE> {
    /** Logger object. */
    private static final Logger LOGGER =
        Logger.getLogger(PopulationChangeableSize.class.getName());

    /**
     * Konstruktor.
     * @param maxLT Największy dopuszczalny czas życia chromosomu.
     * @param minLT Najmniejszy dopuszczalny czas życia chromosomu.
     * @param chromosomes Lista chromosomów.
     * @param crossoverOperator Operator krzyżowania.
     * @param crossoverProbability Prawdopdobiestwo krzyżowania.
     * @param mutationOperator Operator mutacji.
     * @param mutationProbability Prawdopodobiestwo mutacji.
     * @param chromosomeProperties Referencja do obiektu przechowującego
     * parametry wspólne dla wszystkich chromosomów w ramach jednej
     * instancji populacji.
     * globalne dla instancji ustawienia.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    private PopulationChangeableSize(final int maxLT, final int minLT,
                               final LinkedList<Chromosome<GENE_TYPE>> chromosomes,
                               final ICrossover<GENE_TYPE> crossoverOperator,
                               final double crossoverProbability,
                               final IMutation<GENE_TYPE> mutationOperator,
                               final double mutationProbability,
                               final ChromosomeProperties chromosomeProperties)
    throws GeneticAlgorithmException {
        super(chromosomes, crossoverOperator, crossoverProbability,
              mutationOperator, mutationProbability, chromosomeProperties);
        //W konstruktorze i metodzie clone inicjuj bezposrednio
        //wartości zmiennych lub wywołuj tylko metody final.
        this.maxLT = maxLT;
        this.minLT = minLT;
        }

    /**
     * Statyczna metoda factory.
     * @param maxLT Największy dopuszczalny czas życia chromosomu.
     * @param minLT Najmniejszy dopuszczalny czas życia chromosomu.
     * @param chromosomes Lista chromosomów.
     * @param crossoverOperator Operator krzyżowania.
     * @param crossoverProbability Prawdopdobiestwo krzyżowania.
     * @param mutationOperator Operator mutacji.
     * @param mutationProbability Prawdopodobiestwo mutacji.
     * @param chromosomeProperties Referencja do obiektu przechowującego
     * parametry wspólne dla wszystkich chromosomów w ramach jednej
     * instancji populacji.
     * @return Populacja o zmiennej liczebności.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public static <GENE_TYPE> PopulationChangeableSize newPopulationChangeableSize(
                final int maxLT,
                final int minLT,
                final LinkedList<Chromosome<GENE_TYPE>> chromosomes,
                final CrossoverOperatorType crossoverOperator,
                final double crossoverProbability,
                final MutationOperatorType mutationOperator,
                final double mutationProbability,
                final ChromosomeProperties chromosomeProperties)
    throws GeneticAlgorithmException {
        return new PopulationChangeableSize(maxLT, minLT, chromosomes,
                     CrossoverFactory.getCrossoverOperator(crossoverOperator),
                                            crossoverProbability,
                     MutationFactory.getMutationOperator(mutationOperator),
                                            mutationProbability,
                                            chromosomeProperties);
        }

  /**
   * Statyczna metoda factory, pomocna przy przekształcaniu
   * populacji o stałej liczebności na populację o zmiennej liczebności.
   * @param maxLT Największy dopuszczalny czas życia chromosomu.
   * @param minLT Najmniejszy dopuszczalny czas życia chromosomu.
   * @param chromosomes Lista chromosomów.
   * @param crossoverOperator Operator krzyżowania.
   * @param crossoverProbability Prawdopdobiestwo krzyżowania.
   * @param mutationOperator Operator mutacji.
   * @param mutationProbability Prawdopodobiestwo mutacji.
   * @param chromosomeProperties Referencja do obiektu przechowującego
   * parametry wspólne dla wszystkich chromosomów w ramach jednej
   * instancji populacji.
   * @return Populacja o zmiennej liczebności.
   * @throws GeneticAlgorithmException xxx
   * @since 1.0
   */
  public static <GENE_TYPE> PopulationChangeableSize newPopulationChangeableSize(
              final int maxLT,
              final int minLT,
              final LinkedList<Chromosome<GENE_TYPE>> chromosomes,
              final ICrossover<GENE_TYPE> crossoverOperator,
              final double crossoverProbability,
              final IMutation<GENE_TYPE> mutationOperator,
              final double mutationProbability,
              final ChromosomeProperties chromosomeProperties)
  throws GeneticAlgorithmException {
      return new PopulationChangeableSize(maxLT, minLT, chromosomes,
                                          crossoverOperator,
                                          crossoverProbability,
                                          mutationOperator,
                                          mutationProbability,
                                          chromosomeProperties);
    }

    /**
     * Przekszta populację na populację o stałej liczebności.
     * @param selectionMethod Typ metody selekcji.
     * @return Funkcja celu.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public PopulationConstantSize<GENE_TYPE> toPopulationConstantSize(
                                final SelectionMethodType selectionMethod)
    throws GeneticAlgorithmException {
        return PopulationConstantSize.newPopulationConstantSize(
                  selectionMethod, getChromosomes(), getCrossover(),
                  getCrossoverProbability(), getMutation(),
                  getMutationProbability(), getChromosomeProperties());
    }

    /**
     * Tworzenie populacji: t+1 (wywołanie funkcji select,
     * crossover i mutation).
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    @Override
    public void generation() throws GeneticAlgorithmException {
      throw new UnsupportedOperationException("No implements yet");
    }

    /**
     * Ustawia maksymalny czas życia dopuszczalny dla chromosomu.
     * @param maxLT Maksymalny czas życia dopuszczalny dla chromosomu.
     */
    public void setMaxLT(final int maxLT) {
        this.maxLT = maxLT;
    }

    /**
     * Zwraca maksymalny czas życia dopuszczalny dla chromosomu.
     * @return Maksymalny czas życia dopuszczalny dla chromosomu.
     */
    public int getMaxLT() {
        return this.maxLT;
    }

    /**
     * Ustawia minimalny czas życia dopuszczalny dla chromosomu.
     * @param minLT Minimalny czas życia dopuszczalny dla chromosomu.
     */
    public void setMinLT(final int minLT) {
        this.minLT = minLT;
    }

    /**
     * Zwraca minimalny czas życia dopuszczalny dla chromosomu.
     * @return Minimalny czas życia dopuszczalny dla chromosomu.
     */
    public int getMinLT() {
        return this.minLT;
    }

    /**
     * String charakteryzujący populację.
     * @return String charakteryzujący populację.
     * @since 1.0
     */
    @Override
    public String toString() {
        String str = "[Population:]";
        return str;
    }

    /** Maksymalny czas życia dopuszczalny dla chromosomu. */
    private int maxLT;

    /** Minimalny czas życia dopuszczalny dla chromosomu. */
    private int minLT;
}
