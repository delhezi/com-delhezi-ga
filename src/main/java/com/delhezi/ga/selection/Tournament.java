/**
 * @(#)Tournament.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.selection;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import java.util.LinkedList;
import java.util.Random;
//import java.util.logging.Logger;

/**
 * Klasa <code>Tournament</code>: Metoda turniejowa.
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class Tournament<GENE_TYPE> implements ISelect<GENE_TYPE> {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(Tournament.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-8-5-";

    /** */
    private static Random random = new Random();

    /** Liczba chromosomów biorących udział w turnieju. */
    private int arity = 2;

    /**
     * Funkcja select.
     * @param chromosomes Lista chromosomów.
     * @return Wynikowa list chromosomów.
     * @throws GeneticAlgorithmException (chromosomes == null)
     * or (fitnessFunction == null)
     * @since 1.0
     */
    public final LinkedList<Chromosome<GENE_TYPE>> select(final LinkedList<Chromosome<GENE_TYPE>> chromosomes)
            throws GeneticAlgorithmException {
        if (chromosomes == null) {
            throw new IllegalArgumentException("Chromosomes list must not be null.");
        }
        if (chromosomes.size() < this.arity) {
            throw new IllegalArgumentException("Tournament arity cannot be bigger than population size.");
        }
        // Jest tylko jeden chromosom.
        if (chromosomes.size() < 2) {
            return chromosomes;
        }

        // Tworzymy kopię listy chromosomów.
        LinkedList<Chromosome<GENE_TYPE>> newChromosomes = new LinkedList<Chromosome<GENE_TYPE>>();

        // Tworzymy uwzględniając przystosowanie NOWĄ listę chromosomów
        // newChromosomes o wielkości równej chromosomes.size().
        for (int i = 0; i < chromosomes.size(); i++) {
            Chromosome<GENE_TYPE> bestTournamentChromosome = null;
            for (int j = 0; j < this.arity; j++) {
                Chromosome<GENE_TYPE> chTmp = chromosomes.get(random.nextInt(chromosomes.size()));
                if (bestTournamentChromosome == null) {
                    bestTournamentChromosome = chTmp;
                } else {
                    if (bestTournamentChromosome.isFitnessMaximisation()) {
                        if (bestTournamentChromosome.getFitness() < chTmp.getFitness()) {
                            bestTournamentChromosome = chTmp;
                        }
                    } else {
                        if (bestTournamentChromosome.getFitness() > chTmp.getFitness()) {
                            bestTournamentChromosome = chTmp;
                        }
                    }
                }
            }
            newChromosomes.add(bestTournamentChromosome.clone());
        }
        return newChromosomes;
    }

    /**
     * Pobierz liczbę chromosomów biorących udział w turnieju.
     * @return Liczba chromosomów biorących udział w turnieju.
     */
    public final int getArity() {
        return arity;
    }

    /**
     * Ustaw liczbę chromosomów biorących udział w turnieju.
     * @param arity Liczba chromosomów biorących udział w turnieju.
     */
    public final void setArity(final int arity) {
        this.arity = arity;
    }
}

