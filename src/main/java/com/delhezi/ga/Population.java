/**
 * @(#)Population.java
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
import java.util.LinkedList;

/**
 * <code>Population</code>: Abstrakcyjna klasa populacji.
 * @version 1.0 2010-05-14
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public abstract class Population<GENE_TYPE> {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(Population.class.getName());

    /**
     * Konstruktor.
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
    protected Population(final LinkedList<Chromosome<GENE_TYPE>> chromosomes,
                         final ICrossover<GENE_TYPE> crossoverOperator,
                         final double crossoverProbability,
                         final IMutation<GENE_TYPE> mutationOperator,
                         final double mutationProbability,
                         final ChromosomeProperties chromosomeProperties)
    throws GeneticAlgorithmException {
        //W konstruktorze i metodzie clone inicjuj bezposrednio
        //wartości zmiennych lub wywołuj tylko metody final.
        this.chromosomes = chromosomes;
        this.crossover = crossoverOperator;
        this.crossoverProbability = crossoverProbability;
        this.mutation = mutationOperator;
        this.mutationProbability = mutationProbability;
        this.chromosomeProperties = chromosomeProperties;
        this.topChromosome = this.findTopChromosome();
        this.topChromosomeGenerationFound = this.generation;
        }

    /**
     * Tworzenie populacji: t+1.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    abstract void generation() throws GeneticAlgorithmException;

    /**
     * Ustawia referencję do obiektu implementującego operator krzyżowania.
     * @param crossover Referencja do obiektu implementującego
     * operator krzyżowania.
     * @since 1.0
     */
    public final void setCrossover(final ICrossover<GENE_TYPE> crossover) {
        this.crossover = crossover;
    }

    /**
     * Ustawia referencję do obiektu implementującego operator krzyżowania.
     * @param crossoverOperator Typ operatora krzyżowania.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public final void setCrossover(
                              final CrossoverOperatorType crossoverOperator)
    throws GeneticAlgorithmException {
        this.crossover = CrossoverFactory.getCrossoverOperator(crossoverOperator);
    }

    /**
     * Zwraca referencję do obiektu implementującego operator krzyżowania.
     * @return Referencja do obiektu implementującego operator krzyżowania.
     * @since 1.0
     */
    public final ICrossover<GENE_TYPE> getCrossover() {
        return this.crossover;
    }

    /**
     * Ustawia referencję do obiektu implementującego operator mutacji.
     * @param mutation Referencja do obiektu implementującego operator mutacji.
     * @since 1.0
     */
    public final void setMutation(final IMutation<GENE_TYPE> mutation) {
        this.mutation = mutation;
    }

    /**
     * Ustawia referencję do obiektu implementującego operator mutacji.
     * @param mutationOperator Typ operatora mutacji.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public final void setMutation(final MutationOperatorType mutationOperator)
    throws GeneticAlgorithmException {
        this.mutation = MutationFactory.getMutationOperator(mutationOperator);
    }

    /**
     * Zwraca referencję do objektu implementującego operator mutacji.
     * @return Referencja do obiektu implementującego operator mutacji.
     * @since 1.0
     */
    public final IMutation<GENE_TYPE> getMutation() {
        return this.mutation;
    }

    /**
     * Ustawia referencję do do obiektu przechowującego
     * parametry wspólne dla wszystkich chromosomów w ramach jednej
     * instancji populacji.
     * @param chromosomeProperties Referencja do obiektu.
     * @since 1.0
     */
    public final void setChromosomeProperties(
                          final ChromosomeProperties chromosomeProperties) {
        this.chromosomeProperties = chromosomeProperties;
        }

    /**
     * Zwraca referencję do do obiektu przechowującego
     * parametry wspólne dla wszystkich chromosomów w ramach jednej
     * instancji populacji.
     * Uwaga:
     * Jeśli za pomocą otrzymanej referencji dojdzie do modyfikacji objektu
     * FitnessFunction należy wykonać funkcję changed() klasy Chromosome w
     * celu ponownego wyznaczenia wskaźnika przystosowania chromosomu.
     * @return Referencja do obiektu.
     * @since 1.0
     */
    public final ChromosomeProperties getChromosomeProperties() {
        return this.chromosomeProperties;
        }

    /**
     * Zwraca refrencję do najlepszego chromosomu.
     * @return Chromosom.
     * @since 1.0
     */
    public final Chromosome<GENE_TYPE> getTopChromosome() {
        return topChromosome;
        }

    /**
     * Wyszukuje najlepszy chromosom.
     * @return Chromosom.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public final Chromosome<GENE_TYPE> findTopChromosome()
    throws GeneticAlgorithmException {
        if (this.chromosomeProperties.getFitnessFunction() == null) {
            throw new GeneticAlgorithmException(
               new NullPointerException("fitnessFunction is null"));
            }
        if (this.chromosomes == null) {
            throw new GeneticAlgorithmException(
               new NullPointerException("chromosomes is null"));
            }

        Chromosome<GENE_TYPE> top = this.getChromosomes().getFirst();
        for (Chromosome<GENE_TYPE> ch : chromosomes) {
            if (ch.compareTo(top) == 1) {
              top = ch;
            }
        }
        return top;
        }

    /**
     * Zwraca referencję do pojedynczego chromosomu;
     * Uwaga; Ze względu na implementację zbioru chromosomów populacji jako
     * listy, każdorazowo przy wywołaniu funkcji następuje przeszukanie
     * listy od pierwszego elementu.
     * Dla cyklicznego przetworzenia chromosow z kolekcji należy skorzystać
     * z iteratora dostępnego w ramach LinkedList.
     * @param i Pozycja chromosomu (0 dla pierwszego).
     * @return Referencja do chromosomu.
     * @since 1.0
     */
    public final Chromosome<GENE_TYPE> getChromosome(final int i) {
        return this.chromosomes.get(i);
        }

    /**
     * Ustawia referencję do pojedynczego chromosomu.
     * Uwaga; Ze względu na implementację zbioru chromosomów populacji jako
     * listy, każdorazowo przy wywołaniu funkcji następuje przeszukanie
     * listy od pierwszego elementu.
     * Dla cyklicznego przetworzenia chromosow z kolekcji należy skorzystać
     * operacji dostępnych na listach.
     * @param i Pozycja chromosomu (0 dla pierwszego).
     * @param chromosome Referencja do chromosomu.
     * @since 1.0
     */
    public final void setChromosome(final int i, final Chromosome<GENE_TYPE> chromosome) {
        this.chromosomes.set(i, chromosome);
        }

    /**
     * Zwraca referencję do listy chromosomów.
     * @return Lista chromosomów.
     * @since 1.0
     */
    public final LinkedList<Chromosome<GENE_TYPE>> getChromosomes() {
        return this.chromosomes;
    }

    /**
     * Ustawia referencję do listy chromosomów.
     * @param chromosomes Referencja do listy chromosomów.
     * @since 1.0
     */
    public final void setChromosomes(final LinkedList<Chromosome<GENE_TYPE>> chromosomes) {
        this.chromosomes = chromosomes;
    }

    /**
     * Zwraca wielkość populacji.
     * @return Wielkość populacji.
     * @since 1.0
     */
    public final int getPopulationSize() {
        return this.chromosomes.size();
    }

    /**
     * Zwraca prawdopodobieństwo mutacji.
     * @return Prawdopodobieństwo mutacji.
     * @since 1.0
     */
    public final double getMutationProbability() {
        return this.mutationProbability;
    }

    /**
     * Określa prawdopodobieństwo mutacji.
     * @param mutationProbability Prawdopodobieństwo mutacji.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public final void setMutationProbability(final double mutationProbability)
            throws GeneticAlgorithmException {
        if (crossoverProbability > 1 || crossoverProbability < 0) {
            throw new GeneticAlgorithmException(new IllegalArgumentException(
                    "Nieprawidłowa wartośc parametru "
                    + "mutationProbability=" + mutationProbability));
        }
        this.mutationProbability = mutationProbability;
    }

    /**
     * Zwraca prawdopodobieństwo krzyżowania.
     * @return Prawdopodobieństwo krzyżowania.
     * @since 1.0
     */
    public final double getCrossoverProbability() {
        return this.crossoverProbability;
        }

    /**
     * Określa prawdopodobieństwo krzyżowania.
     * @param crossoverProbability Prawdopodobieństwo krzyżowania.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public final void setCrossoverProbability(final double crossoverProbability)
            throws GeneticAlgorithmException  {
        if (crossoverProbability > 1 || crossoverProbability < 0) {
            throw new GeneticAlgorithmException(new IllegalArgumentException(
                    "Nieprawidłowa wartośc parametru "
                    + "crossoverProbability=" + crossoverProbability));
        }
        this.crossoverProbability = crossoverProbability;
    }

    /**
     * Zwraca informację czy zastosowano elitaryzm.
     * @return Elitaryzm.
     */
    public final boolean getElitism() {
        return this.elitism;
    }

    /**
     * Ustawia elitaryzm.
     * @param elitism Elitaryzm.
     */
    public final void setElitism(final boolean elitism) {
        this.elitism = elitism;
    }

    /**
     * Licznik pokoleń - zwraca numer aktualnego pokolenia.
     * @return Numer aktualnego pokolenia.
     * @since 1.0
     */
    public final int getGeneration() {
        return this.generation;
        }

    /**
     * Inkrementuje pokolenie.
     * @return Numer pokolenia.
     * @since 1.0
     */
    protected final int incrementGeneration() {
        return this.generation++;
        }

    /**
     * Zwraca numer pokolenia w którym znaleziono najlepszy chromosom.
     * @return Numer pokolenia.
     * @since 1.0
     */
    public final int getTopChromosomeGenerationFound() {
        return this.topChromosomeGenerationFound;
    }

    //PARAMETRY WYMAGANE - inicjowane w konstruktorze.
    /** Referencja do listy chromosomów. */
    private LinkedList<Chromosome<GENE_TYPE>> chromosomes;
    /** Referencja do obiektu implementującego funkcję krzyżowania. */
    private ICrossover<GENE_TYPE> crossover;
    /** Prawdopodobieństwo krzyżowania. */
    private double crossoverProbability;
    /**  Referencja do obiektu implementującego funkcję mutacji. */
    private IMutation<GENE_TYPE> mutation;
    /** Prawdopodobieństwo mutacji. */
    private double mutationProbability;
    /**
     * Referencja do obiektu przechowującego parametry wspólne dla
     * wszystkich chromosomów w ramach jednej instancji populacji.
     */
    private ChromosomeProperties chromosomeProperties;

    //PARAMETRY - inicjowane wartościami domyślnymi.
    /** Czy uwzględnić elitaryzm. */
    private boolean elitism = true;

    //ZMIENNE INICJOWANE W KODZIE.
    /** Numer aktualnego pokolenia. */
    private int generation = 0;
    /** Numer pokolenia w którym znaleziono aktualnie najlepszy chromosom. */
    protected int topChromosomeGenerationFound = 0;
    /**
     * Referencja do kopi obiektu najlepszego chromsomu.
     * W przypadku braku elitaryzmu kopia najlepszego chromosomu może
     * zosotać utracona.
     **/
    protected Chromosome<GENE_TYPE> topChromosome = null;
}
