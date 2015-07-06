/**
 * @(#)AbstractRouletteWheel.java
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
//import java.util.logging.Logger;

/**
 * <code>AbstractRouletteWheel</code>: Metoda ruletki (odmiana reprodukcji
 * proporcjonalnej. W reprodukcji proporcjonalnej prawdopodobieństwo wyboru
 * osobnika do puli rodzicielskiej zależne jest od wartości funkcji
 * przystosowania danego osobnika.)
 * <p/>
 * <b>Metoda źle sobie radzi z mała rozpiętością wartości wskaźnika
 * przystosowania</b> (selekcja praktycznie nie zachodzi, w przypadku kiedy
 * wartości wskaźnika przystosowania dozwolone są dla przedziału np. 0-100000,
 * a wszystkie chromosomy mają fitness z przedziału 950000-100000).
 * <p/>
 * Prawdopodobieństwo wybrania każdego z osobników do nowej populacji
 * określa się wzorem:
 * <br/>
 * a) w przypadku maksymalizacji funkcji celu:
 *    <code>wartość przystosowania danego osobnika / suma wartości
 *    przystosowania wszystkich osobników</code>,
 * <br/>
 * b) w przypadku minimalizacji funkcji celu:
 *    <code>odwrotność wartości przystosowania danego osobnika * odwrotność
 *    sumy odwrotności wartości przystosowania wszystkich osobników</code>.
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
abstract class AbstractRouletteWheel {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(AbstractRouletteWheel.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-8-1-";

    /**
     * Funkcja selekcji.
     * @param chromosomes Lista chromosomów.
     * @return Wynikowa list chromosomów.
     * @throws GeneticAlgorithmException (chromosomes == null)
     * or (fitnessFunction == null)
     * @since 1.0
     */
    public final LinkedList<Chromosome> select(
                              final LinkedList<Chromosome> chromosomes)
    throws GeneticAlgorithmException {
        if (chromosomes == null) {
            throw new IllegalArgumentException("chromosomes is null.");
            }
        //Jest tylko jeden chromosom.
        if (chromosomes.size() < 2) {
            return chromosomes;
            }

        this.setVariable(chromosomes);

        //Tablica z określonym prawdopodobieństwem wyboru dla każdego z
        //chromosomów.
        double[] normals = new double[chromosomes.size()];
        int i = 0;
        for (Chromosome ch : chromosomes) {
            if (ch.isFitnessMaximisation() == true) {
                normals[i] = ch.getFitness() / sumFitness;
            } else {
                normals[i] = 1 / (ch.getFitness() * sumaOdwrotnosci);
            }
            i++;
        }

        //double sump=0;
        //for(int z = 0; z < chromosomes.size(); z++){
        //   sump+=normals[z];
        //   System.out.println("normals["+z+"] = " + normals[z]
        // + ", chromosom = " + chromosomes.get(z).getFitness());
        //}
        //System.out.println("sum. prawd. = " +sump);//Suma prawdopodobieństw
        //powinna być równa 1.

        return this.rouletteWheelImpl(chromosomes, normals);
    }

    /**
     * Implementacja metody ruletki.
     * @param chromosomes Lista chromosomów.
     * @param normals Tablica z określonym prawdopodobieństwem wyboru dla
     * każdego z chromosomów.
     * @return Wynikowa list chromosomów.
     * @since 1.0
     */
    abstract LinkedList<Chromosome> rouletteWheelImpl(
                              final LinkedList<Chromosome> chromosomes,
                              final double[] normals);

    /**
     * Funkcja pomocnicza, wylicza wartości: minIndex,
     * minFitness, maxIndex, maxFitness, sumFitness, odwrotnoscSumyOdwrotnosci.
     * @param chromosomes Lista chromosomów.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    private final void setVariable(final LinkedList<Chromosome> chromosomes)
    throws GeneticAlgorithmException {
        assert chromosomes != null : "Illegal argument chromosomes: null";

        double fitness;
        this.minFitness = chromosomes.getFirst().getFitness();
        this.maxFitness = chromosomes.getFirst().getFitness();
        double tmpSumaOdwrotnosci = 0;

        for (Chromosome ch : chromosomes) {
            fitness = ch.getFitness();
            this.sumFitness += fitness;
            tmpSumaOdwrotnosci += (1 / ch.getFitness());
            if (fitness < this.minFitness) {
                this.minFitness = fitness;
            }
            if (fitness > this.maxFitness) {
                this.maxFitness = fitness;
            }
        }
        this.sumaOdwrotnosci = tmpSumaOdwrotnosci;
    }

    /** Najmniejsza znaleziona wartość wskaźnika przystosowania. */
    private double minFitness;

    /** Największa znaleziona wartość wskaźnika przystosowania. */
    private double maxFitness;

    /** Suma wartości wskaźników przystosowania osobników w całej populacji.*/
    private double sumFitness;

    /**
     * Suma odwrotności wskaźników przystosowania osobników w całej populacji.
     */
    private double sumaOdwrotnosci;
}
