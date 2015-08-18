/**
 * @(#)RouletteWheelEffectiveImplementation.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.selection;

import com.delhezi.ga.Chromosome;
import java.util.LinkedList;
import java.util.Random;

/**
 * Klasa <code>RouletteWheelEffectiveImplementation</code>: Metoda ruletki implementacja efektywna;
 * Koszt stały + liniowy koszt inicjacji tablic zależny od wielkości populacji.
 * M. D. Vose, A Linear Algorithm For Generating Random Numbers With a
 * Given Distribution, IEEE Transactions on Software Engineering,
 * vol. 17, no. 9, september 1991
 *
 * Metoda ruletki - (odmiana reprodukcji proporcjonalnej. W reprodukcji
 * proporcjonalnej prawdopodobieństwo wyboru osobnika do puli rodzicielskiej
 * zależne jest od wartości funkcji przystosowania danego osobnika;)
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class RouletteWheelEffectiveImplementation extends AbstractRouletteWheel implements ISelect {

  /** */
  private static Random random = new Random();

  /**
    * Efektywna implementacja funkcji ruletki.
    * @param chromosomes Lista chromosomów.
    * @param normals Tablica z określonym prawdopodobieństwem wyboru dla każdego z chromosomów.
    * @return Wynikowa list chromosomów.
    * @since 1.0
    */
  @Override
    protected final LinkedList<Chromosome> rouletteWheelImpl(final LinkedList<Chromosome> chromosomes,
            final double[] normals) {

        LinkedList<Chromosome> newChromosomes = new LinkedList<Chromosome>();
        int[] alias = new int[chromosomes.size()]; // Tab. indeksów zapasowych

        tableInitialize(chromosomes, normals, alias);

        double roll;
        int ui;

        for (int i = 0; i < chromosomes.size(); i++) {
            roll = random.nextDouble() * chromosomes.size() + 1;
            ui = (int) roll; //Obcina część ułamkową.

            if (i - ui <= normals[i]) {
                newChromosomes.add(chromosomes.get(i).clone());
            } else {
                newChromosomes.add(chromosomes.get(alias[i]).clone());
            }
        }
      return newChromosomes;
    }

  /**
   * Inicjalizacja.
   * @param chromosomes Lista chromosomów.
   * @param normals Tablica z określonym prawdopodobieństwem wyboru dla każdego z chromosomów.
   * @param alias Tablica indeksów zapasowych.
   * @since 1.0
   */
    protected final void tableInitialize(final LinkedList<Chromosome> chromosomes, final double[] normals,
            final int[] alias) {
        int n = chromosomes.size();
        double d1n = (double) 1 / n;

        int[] small = new int[n]; // Stosy pomocnicze indeksów do ps
        int[] large = new int[n]; // Stosy pomocnicze indeksów do ps

        int l = 0;
        int s = 0;
        for (int j = 0; j < n; j++) {
            if (normals[j] > d1n) {
                large[l] = j;
                l++;
            } else {
                small[s] = j;
                s++;
            }
        }

        // "redystrybucja" i przeskalowanie prawdopodobienstw
        double[] prob = new double[n]; // Tab. względnych częstości
        int j;
        int k;
        while ((s > 0) && (l > 0)) {
            s = s - 1;
            j = small[s];
            l = l - 1;
            k = large[l];
            prob[j] = n * normals[j];
            alias[j] = k;
            normals[k] = normals[k] + (normals[j] - d1n);
            if (normals[k] > d1n) {
                l = l + 1;
            } else {
                small[s] = k;
                s = s + 1;
            }
        }

        // zostały tylko "kawałki" długosci 1/N
        while (s > 0) {
            s = s - 1;
            prob[small[s]] = 1;
        }
        // naprawa błedów zaokraglen
        while (l > 0) {
            l = l - 1;
            prob[large[l]] = 1;
        }
    }
}
