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
//import java.util.logging.Logger;

/**
 * Klasa <code>LinearRanking</code>: Selekcja liniowa wg rang.
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class LinearRanking implements ISelect {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(LinearRanking.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-8-2-";

    private static Random random = new Random();

    /**
     * Funkcja select.
     * @param chromosomes Lista chromosomów.
     * @return Wynikowa list chromosomów.
     * @throws GeneticAlgorithmException (chromosomes == null)
     * or (fitnessFunction == null)
     * @since 1.0
     */
    public final LinkedList<Chromosome> select(final LinkedList<Chromosome> chromosomes)
    throws GeneticAlgorithmException {
        if (chromosomes == null) {
            throw new IllegalArgumentException("chromosomes is null.");
        }
        //Jest tylko jeden chromosom.
        if (chromosomes.size() < 2) {
            return chromosomes;
        }

        //Sortuje chromosomy dla maksymalizacji: od najmniejszej do najwiekszej,
        //dla minimalizacji: od najwiekszej do najmniejszej wartosci fitness.
        Collections.sort(chromosomes);

        LinkedList<Chromosome> newChromosomes = new LinkedList<Chromosome>();
        
        //Zaraz po utworzeniu iterator wskazuje na specjalną wartość przed
        //pierwszym elementem, tak by pierwszy element był pobrany
        //przy pierwszym wywołaniu next()
        ListIterator<Chromosome> itr = chromosomes.listIterator(0);

        int j = 1; //Pozycja rozpatrywanego chromosomu  w populacji.
        double roll;
        boolean wylosowany;
        Chromosome chTmp;
        double normal;
        //Tworzymy uwzględniając przystosowanie NOWĄ listę chromosomów
        //newChromosomes o wielkości równej chromosomes.size().
        for (int i = 0; i < chromosomes.size(); i++) {
            wylosowany = false;
            while (!wylosowany) { //Losujemy i-ty chromosom do newChromosomes.

                //Wróć na początek listy jeśli doszedłeś do końca.
                if (j > chromosomes.size()) {
                    itr = chromosomes.listIterator(0);
                    j = 1;
                }

                roll = random.nextDouble() / chromosomes.size();
                normal = getNormal(chromosomes.size(), j);
                chTmp = itr.next();

                if (normal >= roll) {
                    newChromosomes.add(chTmp.clone());
                    wylosowany = true;
                }
                j++;
            }
        }
        return newChromosomes;
    }

    /**
     * Wylicza prawdopodobieństwo wybrania chromosomu.
     * @param populationSize Wielkość populacji.
     * @param chromosomePosition Pozycja chromosomu  w populacji dla którego
     * wyliczamy prawdopodobieństwo wybrania. Wymagane jest wcześniejsze
     * posortowanie populacji, dla przypadku
     * maksymalizacji: od najmniejszej do najwiekszej,
     * dla minimalizacji: od najwiekszej do najmniejszej wartości fitness.
     * @return Prawdopodobieństwo wybrania chromosomu <0,1>.
     * @since 1.0
     */
    private final double getNormal(int populationSize,
                                   int chromosomePosition) {
        return (double)(2 * chromosomePosition) /
            (populationSize * (populationSize + 1));
    }
}
