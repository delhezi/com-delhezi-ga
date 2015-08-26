/**
 * @(#)PopulationConstantSize.java
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
import com.delhezi.ga.selection.ISelect;
import com.delhezi.ga.selection.factory.SelectionFactory;
import com.delhezi.ga.selection.factory.SelectionMethodType;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
//import java.util.logging.Logger;

/**
 * <code>PopulationConstantSize</code>: Klasa populacji o stałej
 * liczebności.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public final class PopulationConstantSize<GENE_TYPE> extends Population<GENE_TYPE> {

    /** Logger object. */
    //private static final Logger LOGGER = Logger.getLogger(PopulationConstantSize.class.getName());

    /**
     * Konstruktor.
     * @param selectionMethod Funkcja celu.
     * @param chromosomes Lista chromosomów.
     * @param crossoverOperator Operator krzyżowania.
     * @param crossoverProbability Prawdopdobiestwo krzyżowania.
     * @param mutationOperator Operator mutacji.
     * @param mutationProbability Prawdopodobiestwo mutacji.
     * @param chromosomeProperties Referencja do obiektu przechowującego
     * parametry wspólne dla wszystkich chromosomów w ramach jednej
     * instancji populacji.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    private PopulationConstantSize(final SelectionMethodType selectionMethod,
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
        this.select = SelectionFactory.getSelectionMethod(selectionMethod);
    }

    /**
     * Statyczna metoda factory.
     * @param selectionMethod Funkcja celu.
     * @param chromosomes Lista chromosomów.
     * @param crossoverOperator Operator krzyżowania.
     * @param crossProbability Prawdopdobiestwo krzyżowania.
     * @param mutationOperator Operator mutacji.
     * @param mutationProbability Prawdopodobiestwo mutacji.
     * @param chromosomeProperties Referencja do obiektu przechowującego
     * parametry wspólne dla wszystkich chromosomów w ramach jednej
     * instancji populacji.
     * @return Populacja o stałej liczebności.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public static <GENE_TYPE> PopulationConstantSize<GENE_TYPE> newPopulationConstantSize(
                        final SelectionMethodType selectionMethod,
                        final LinkedList<Chromosome<GENE_TYPE>> chromosomes,
                        final CrossoverOperatorType crossoverOperator,
                        final double crossProbability,
                        final MutationOperatorType mutationOperator,
                        final double mutationProbability,
                        final ChromosomeProperties chromosomeProperties)
    throws GeneticAlgorithmException {
        return new PopulationConstantSize<GENE_TYPE>(selectionMethod, chromosomes,
                CrossoverFactory.<GENE_TYPE> getCrossoverOperator(crossoverOperator), crossProbability,
                MutationFactory.<GENE_TYPE> getMutationOperator(mutationOperator), mutationProbability,
                chromosomeProperties);
    }

  /**
   * Statyczna metoda factory, pomocna przy przekształcaniu
   * populacji o zmiennej liczebności na populację o stałej liczebności.
   * @param selectionMethod Funkcja celu.
   * @param chromosomes Lista chromosomów.
   * @param crossoverOperator Operator krzyżowania.
   * @param crossProbability Prawdopdobiestwo krzyżowania.
   * @param mutationOperator Operator mutacji.
   * @param mutationProbability Prawdopodobiestwo mutacji.
   * @param chromosomeProperties Referencja do obiektu przechowującego
   * parametry wspólne dla wszystkich chromosomów w ramach jednej
   * instancji populacji.
   * @return Populacja o stałej liczebności.
   * @throws GeneticAlgorithmException xxx
   * @since 1.0
   */
  public static <GENE_TYPE> PopulationConstantSize<GENE_TYPE> newPopulationConstantSize(
                      final SelectionMethodType selectionMethod,
                      final LinkedList<Chromosome<GENE_TYPE>> chromosomes,
                      final ICrossover<GENE_TYPE> crossoverOperator,
                      final double crossProbability,
                      final IMutation<GENE_TYPE> mutationOperator,
                      final double mutationProbability,
                      final ChromosomeProperties chromosomeProperties)
  throws GeneticAlgorithmException {
        return new PopulationConstantSize<GENE_TYPE>(selectionMethod, chromosomes, crossoverOperator, crossProbability,
                mutationOperator, mutationProbability, chromosomeProperties);
      }

    /**
     * Przekształca populację na populację o zmiennej liczebności.
     * @param maxLT Maksymalny czas życia dopuszczalny dla chromosomu.
     * @param minLT Minimalny czas życia dopuszczalny dla chromosomu.
     * @return Populacja o zmiennej liczebności.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
  public PopulationChangeableSize<GENE_TYPE> toPopulationChangeableSize(final int maxLT,
                                         final int minLT)
   throws GeneticAlgorithmException {
    this.select = null; //czyszczenie
    return PopulationChangeableSize.newPopulationChangeableSize(maxLT,
                    minLT, getChromosomes(),
                    getCrossover(), getCrossoverProbability(),
                    getMutation(), getMutationProbability(),
                    getChromosomeProperties());
  }

    /**
     * Tworzenie populacji: t+1 (wywołanie funkcji select,
     * crossover i mutation).
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    @Override
    public void generation() throws GeneticAlgorithmException {

        super.incrementGeneration();

        Chromosome<GENE_TYPE> elitarChromosome = null;
        if (super.getElitism()) {
            elitarChromosome = super.getTopChromosome().clone();
        }
/*
        System.out.println("++++++++++++++++++++++++++++");
        for (Chromosome ch : super.getChromosomes()) {
            System.out.println(ch.getFitness());
        }
        System.out.println("++++++++++++++++++++++++++++");
*/
        // Operacja selekcji.
        super.setChromosomes(select.select(super.getChromosomes()));
/*
        System.out.println("================================");
        for (Chromosome ch : super.getChromosomes()) {
            System.out.println(ch.getFitness());
        }
        System.out.println("=================================");
*/
         //Ilość operacji krzyżowania (np. 100 * 0.7/2 = 35).
         final int countCross =
             (int) (this.getPopulationSize() * getCrossoverProbability() / 2);

         //KRZYŻOWANIE
         //Mieszamy listę w celu losowego dobrania osobników do krzyżowania.
         Collections.shuffle(super.getChromosomes(), random);
         Iterator<Chromosome<GENE_TYPE>> itr1 =
             super.getChromosomes().iterator(); //na początku listy
         Iterator<Chromosome<GENE_TYPE>> itr2 =
             super.getChromosomes().descendingIterator(); //na końcu listy
         //Operacje krzyżowania.
         for (int i = 0; i < countCross; i++) {
             super.getCrossover().crossover(itr1.next(), itr2.next());
             }
         //Mutacja
         //ZAMIAST W PETLI WSZYSTKIE TYLKO INDEXY TYCH CO MAJA
         //BYC MUTOWANE WYBRAC!!!!
         for (Chromosome<GENE_TYPE> ch : super.getChromosomes()) {
             if (random.nextDouble() < super.getMutationProbability()) {
                 ch.mutation(super.getMutation());
                 }
             }


           //-------------------
           Chromosome<GENE_TYPE> foundTopChromosome = super.findTopChromosome();

           //Jeśli w nowej populacji nie ma lepszego chromosomu niż ostatni
           //elitarny mymieniamy ostatni chromosom populacji na
           //chromosom elitarny.
        if (super.getElitism()) {
            if (elitarChromosome.compareTo(foundTopChromosome) == -1) {
                super.getChromosomes().removeLast();
                super.getChromosomes().add(elitarChromosome.clone());
            } else if (elitarChromosome.compareTo(foundTopChromosome) == 1) {
                super.topChromosome = foundTopChromosome;
                super.topChromosomeGenerationFound = getGeneration();
            }
        } else {
            if (super.topChromosome.compareTo(foundTopChromosome) == 1) {
                super.topChromosome = foundTopChromosome;
                super.topChromosomeGenerationFound = getGeneration();
            }
        }
    }

    /**
     * Ustawia referencję do obiektu implementującego funkcję selekcji.
     * @param select Referencja do obiektu implementującego funkcję selekcji.
     * @since 1.0
     */
    public void setSelect(final ISelect<GENE_TYPE> select) {
        this.select = select;
    }

    /**
     * Ustawia referencję do obiektu implementującego funkcję selekcji.
     * @param selectionMethod Funkcja celu.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public void setSelect(final SelectionMethodType selectionMethod)
    throws GeneticAlgorithmException {
        this.select = SelectionFactory.getSelectionMethod(selectionMethod);
    }

    /**
     * Zwraca referencję do obiektu implementującego funkcję selekcji.
     * @return Referencja do obiektu implementującego funkcję selekcji.
     * @since 1.0
     */
    public ISelect<GENE_TYPE> getSelect() {
        return this.select;
    }

    /**
     * Zmienia wielkość populacji.
     * @param populationSize Wielkość populacji.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public void changePopulationSize(final int populationSize)
    throws GeneticAlgorithmException {
        //Nie zaimplementowane jeszcze.
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

    /** Random. */
    private static Random random = new Random();

    /** Referencja do obiektu implementującego funkcję selekcji. */
    private ISelect<GENE_TYPE> select;

}
