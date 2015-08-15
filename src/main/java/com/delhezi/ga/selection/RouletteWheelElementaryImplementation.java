/**
 * @(#)RouletteWheelElementaryImplementation.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.selection;

import com.delhezi.ga.Chromosome;
//import com.delhezi.ga.exception.GeneticAlgorithmException;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Random;
//import java.util.logging.Logger;

/**
 * Klasa <code>RouletteWheelElementaryImplementation</code>: Metoda ruletki
 * implementacja elementarna;
 * Liniowy koszt wykonania zależny od wielkości populacji;
 *
 * Metoda ruletki - (odmiana reprodukcji proporcjonalnej. W reprodukcji
 * proporcjonalnej prawdopodobieństwo wyboru osobnika do puli rodzicielskiej
 * zależne jest od wartości funkcji przystosowania danego osobnika;)
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class RouletteWheelElementaryImplementation
                             extends AbstractRouletteWheel implements ISelect {

    /** Logger object. */
    //private static final Logger LOGGER =
    // Logger.getLogger(RouletteWheelElementaryImplementation.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-8-4-";

    /** */
  private static Random random = new Random();

  /**
   * Elementarna implementacja funkcji ruletki.
   * @param chromosomes Lista chromosomów.
   * @param normals Tablica z określonym prawdopodobieństwem wyboru dla
   * każdego z chromosomów.
   * @return Wynikowa list chromosomów.
   * @since 1.0
   */
  @Override
  protected final LinkedList<Chromosome> rouletteWheelImpl(
                        final LinkedList<Chromosome> chromosomes,
                        final double [] normals) {
        LinkedList<Chromosome> newChromosomes = new LinkedList<Chromosome>();
        ListIterator<Chromosome> itr = chromosomes.listIterator(0);

        int j = 0;
        double roll;
        boolean wylosowany;
        Chromosome chTmp;
        //Tworzymy uwzględniając przystosowanie NOWĄ listę chromosomów
        //newChromosomes o wielkości równej chromosomes.size().
        for (int i = 0; i < chromosomes.size(); i++) {
            wylosowany = false;
            while (!wylosowany) { //Losujemy i-ty chromosom.
                roll = random.nextDouble() / chromosomes.size();

                chTmp = itr.next();
                if (normals[j] >= roll) {
                    newChromosomes.add(chTmp.clone());
                    wylosowany = true;
                }

                //System.out.println("--j=" + j + ", random=" + roll +
                 //", prawd=" + normals[j] + ", wybr=" + wylosowany);

                if (j == chromosomes.size() - 1) {
                    itr = chromosomes.listIterator(0);
                    j = 0;
                } else {
                    j++;
                }
            }
        }
        return newChromosomes;
    }
}
