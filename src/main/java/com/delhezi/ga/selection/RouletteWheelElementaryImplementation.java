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
 *
 * Metoda ruletki - (odmiana reprodukcji proporcjonalnej. W reprodukcji
 * proporcjonalnej prawdopodobieństwo wyboru osobnika do puli rodzicielskiej
 * zależne jest od wartości funkcji przystosowania danego osobnika;)
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class RouletteWheelElementaryImplementation<GENE_TYPE> extends AbstractRouletteWheel<GENE_TYPE> implements ISelect<GENE_TYPE> {

  /**
   * Elementarna implementacja funkcji ruletki.
   * @param chromosomes Lista chromosomów.
   * @param normals Tablica z określonym prawdopodobieństwem wyboru dla
   * każdego z chromosomów.
   * @return Wynikowa list chromosomów.
   * @since 1.0
   */
  @Override
    protected final LinkedList<Chromosome<GENE_TYPE>> rouletteWheelImpl(final LinkedList<Chromosome<GENE_TYPE>> chromosomes,
            final double[] normals) {
      
        LinkedList<Chromosome<GENE_TYPE>> newChromosomes = new LinkedList<Chromosome<GENE_TYPE>>();
        
        //Tworzymy uwzględniając przystosowanie NOWĄ listę chromosomów
        //newChromosomes o wielkości równej chromosomes.size().
        for (int i = 0; i < chromosomes.size(); i++) {
            
            double rndNumber = Random.nextDoubleInRange(0, 1);
            
            double offset = 0;
            int ii = 0;
            for (ii = 0; ii < chromosomes.size(); ii++) {
                offset += normals[ii];
                if (rndNumber <= offset) { // ZNALEZIONY
                    newChromosomes.add(chromosomes.get(ii).clone());
                    break;
                }
            }//wyjście break

            //System.out.println("losowanie=" + rndNumber + ", offset=" + offset + ", chromosom ii=" + (ii));
        }
        return newChromosomes;
    }
}
