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

import java.util.LinkedList;
import com.delhezi.ga.utility.Random;

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
public class RouletteWheelElementaryImplementation extends AbstractRouletteWheel implements ISelect {

  /**
   * Elementarna implementacja funkcji ruletki.
   * @param chromosomes Lista chromosomów.
   * @param normals Tablica z określonym prawdopodobieństwem wyboru dla
   * każdego z chromosomów.
   * @return Wynikowa list chromosomów.
   * @since 1.0
   */
  @Override
    protected final LinkedList<Chromosome> rouletteWheelImpl(final LinkedList<Chromosome> chromosomes,
            final double[] normals) {
      
        LinkedList<Chromosome> newChromosomes = new LinkedList<Chromosome>();

        Chromosome chTmp = chromosomes.get(0); // inicjlizacja pierwszym chromosomem
        
        //Tworzymy uwzględniając przystosowanie NOWĄ listę chromosomów
        //newChromosomes o wielkości równej chromosomes.size().
        for (int i = 0; i < chromosomes.size(); i++) {
            
            double chSelect = Random.nextDoubleInRange(0, 1);
            
            double normalsSum = 0;
            int ii = 0;
            while (normalsSum < chSelect) {
                normalsSum += normals[ii];
                chTmp = chromosomes.get(ii);
                ii++;
            }

          //  System.out.println("losowanie=" + chSelect + ", normalsSum=" + normalsSum + ", chromosom ii=" + (ii-1));
             newChromosomes.add(chTmp.clone());
        }
        return newChromosomes;
    }
}
