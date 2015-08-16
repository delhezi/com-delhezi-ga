/**
 * @(#)LinearRanking.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.selection;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Klasa <code>LinearRanking</code>: Selekcja liniowa wg rang.
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class LinearRanking implements ISelect {

    /** Delhezi Error Code. */
    //private static final String DERC = "1-8-2-";

    private static Random random = new Random();

    /**
     * Funkcja selekcji tworzy uwzględniając przystosowanie nową listę chromosomów
     * o wielkości równej chromosomes.size().
     * @param chromosomes Lista chromosomów.
     * @return Wynikowa list chromosomów.
     * @throws GeneticAlgorithmException (chromosomes == null) or (fitnessFunction == null)
     * @since 1.0
     */
    public final LinkedList<Chromosome> select(final LinkedList<Chromosome> chromosomes)
            throws GeneticAlgorithmException {
        if (chromosomes == null) {
            throw new IllegalArgumentException("Chromosomes list must not be null.");
        }
        if (chromosomes.size() == 0) {
            throw new IllegalArgumentException("Chromosomes to select must be greater than zero.");
        }
        if (chromosomes.size() < 2) { // Jest tylko jeden chromosom.
            return chromosomes;
        }

        // Sortowanie chromosomów.
        // W przypadku maksymalizacji sortowanie od najwiekszej do najmniejszej wartości fitnes (najlepsze osobniki na początku listy).
        // W przypadku minimalizacji sortowanie od najmniejszej do najwiekszej wartości fitnes (najlepsze osobniki na początku listy).
        Collections.sort(chromosomes);
        
        LinkedList<Chromosome> newChromosomes = new LinkedList<Chromosome>();

        // Po utworzeniu iterator wskazuje na specjalną wartość przed
        // pierwszym elementem listy, tak by pierwszy element był pobrany
        // przy pierwszym wywołaniu next()
        ListIterator<Chromosome> itr = chromosomes.listIterator(0);

        int j = 1; // Pozycja rozpatrywanego chromosomu w populacji.
        double roll;
        boolean find;
        Chromosome<?> chTmp;
        double normal;
        // Tworzymy uwzględniając przystosowanie NOWĄ listę chromosomów
        // newChromosomes o wielkości równej chromosomes.size().
        for (int i = 0; i < chromosomes.size(); i++) {
            find = false;
            while (!find) { // Wyznaczamy i-ty chromosom do newChromosomes.

                // Wróć na początek listy jeśli doszedłeś do końca.
                if (j > chromosomes.size()) {
                    itr = chromosomes.listIterator(0);
                    j = 1;
                }

                roll = random.nextDouble() / chromosomes.size();
                normal = getNormal(chromosomes.size(), chromosomes.size() + 1 - j);
                chTmp = itr.next();

                if (normal >= roll) {
                    newChromosomes.add(chTmp.clone());
                    find = true;
                }
                j++;
            }
        }
        return newChromosomes;
    }

    /**
     * Wylicza prawdopodobieństwo wybrania chromosomu.
     * @param populationSize Wielkość populacji.
     * @param chromosomeRank Ranga chromosomu.
     * @return Prawdopodobieństwo wybrania chromosomu <0,1>.
     * @since 1.0
     */
    private double getNormal(final int populationSize, final int chromosomeRank) {
        return (double) (2 * chromosomeRank) / (populationSize * (populationSize + 1));
    }
}
