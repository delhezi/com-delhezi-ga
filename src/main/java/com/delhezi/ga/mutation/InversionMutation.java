/**
 * @(#)InversionMutation.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation;

import com.delhezi.ga.Chromosome;
import java.util.Random;
//import java.util.logging.Logger;

/**
 * Klasa <code>InversionMutation</code>: Mutacja przez inwersję
 * Inversion Mutation (IVM).
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class InversionMutation<GENE_TYPE> implements IMutation<GENE_TYPE> {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(InversionMutation.class.getName());

    /** Random.*/
    private static Random random = new Random();

    /**
     * Implementacja funkcji mutacji przez inwersję.
     * @param chromosome Chromosom podlegający mutacji.
     * @since 1.0
     */
    @Override
    public final void mutation(final Chromosome<GENE_TYPE> chromosome) {

        if (chromosome == null) {
            throw new IllegalArgumentException("Chromosome is null.");
            }
        int chromosomeSize = chromosome.size();
        //Chromosom ma tylko 1 gen.
        if (chromosomeSize < 2) {
            return;
            }

        //Losuje liczny z zakresu od 0 do chromosomeSize.
        //Początkowy punkt fragmentu inwersji cutPoint1=2 dla xx|iiixxxxx.
        int cutPoint1 = (int) (random.nextDouble() * (chromosomeSize + 1));
        //Końcowy punkt fragmentu inwersji cutPoint2=5 dla xxiii|xxxxx.
        int cutPoint2 = (int) (random.nextDouble() * (chromosomeSize + 1));

        //Korekta wyniku.
        //Istnieje bardzo małe prawdopodobienstwo wylosowania liczby o
        //wartosci chromosomeSize (poza zakresem), np. 4.999999999999999
        //zaokraglane jest do 4, ale sprawdzę:
        if (cutPoint1 == chromosomeSize + 1) {
            cutPoint1--;
        }
        if (cutPoint2 == chromosomeSize + 1) {
            cutPoint2--;
        }

        //Liczba genów poza fragmentem inwersji.
        int differentGenesSize = (cutPoint2 >= cutPoint1)
            ? chromosomeSize - (cutPoint2 - cutPoint1)
            : chromosomeSize - (chromosomeSize - cutPoint1) - cutPoint2;

        //Jeśli liczba genów fragmentu inwersji==0
        if (chromosomeSize - differentGenesSize == 0) {
            return;
        }

        //Losuje liczny z zakresu od 0 do chromosomeSize.
        //Liczby miejsc do wstawienia genów fragmentu inwersji jest
        //równa differentGenes + 1.
        //insertPoint=2 dla xx|xxxxx
        int insertPoint =
            (int) (random.nextDouble() * (differentGenesSize + 1));
        //Istnieje bardzo małe prawdopodobienstwo wylosowania liczby o
        //wartosci differentGenesSize + 1 (poza zakresem),
        //np. 4.999999999999999 zaokraglane jest do 4, ale sprawdzę:
        if (insertPoint == differentGenesSize + 1) {
            insertPoint--;
            }

        this.mutation(chromosome, cutPoint1, cutPoint2, insertPoint);
    }

     /**
     * Implementacja funkcji mutacji przez inwersję;
     * W przypadku kiedy
     * (cutPoint1==cutPoint2 || (cutPoint1==chromosomeSize && cutPoint2==0))
     * funkcja traktuje fragment dopasowania jako pusty.
     * @param chromosome Chromosom podlegający mutacji.
     * @param cutPoint1 Początkowy punkt fragmentu inwersji, wartość z
     * zakresu <0, chromosomeSize>.
     * Przykład: cutPoint1=2 dla xx|iiixxxxx.
     * @param cutPoint2 Końcowy punkt fragmentu inwersji, wartość z
     * zakresu <0, chromosomeSize>.
     * Przykład: cutPoint2=5 dla xxiii|xxxxx.
     * @param insertPoint Punkt do wstawienia odwróconych genów, wartość z
     * zakresu <0, differentGenesSize>.
     * Przykład: insertPoint=2 dla xx|xxxxx.
     * Nie są brane pod uwagę geny fragmentu inwersji.
     * @since 1.0
     */
    protected final void mutation(final Chromosome<GENE_TYPE> chromosome,
                                  final int cutPoint1,
                                  final int cutPoint2,
                                  final int insertPoint) {
         //Walidacja parametrów wejściowych.

         //Chromosom nie może być wartością null.
         assert chromosome != null : "Illegal argument chromosome: null";

         int chromosomeSize = chromosome.size();

         //Punkt cutPoint1 powinien zawierać się w obszare chromosomu.
         assert (cutPoint1 >= 0 && cutPoint1 <= chromosomeSize)
            : "Illegal argument cutPoint1: " + cutPoint1;
         //Punkt cutPoint2 powinien zawierać się w obszare chromosomu.
         assert (cutPoint2 >= 0 && cutPoint2 <= chromosomeSize)
             : "Illegal argument cutPoint2: " + cutPoint2;
         //Funkcja traktuje fragment dopasowania jako pusty.
         if (cutPoint1 == cutPoint2 || (cutPoint1 == chromosomeSize
                                        && cutPoint2 == 0)) {
             return;
             }
         //Dla mniej niż 2 genów mutacja przez inwersję nie spowoduje
         //żadnych zmian.
         if (chromosomeSize < 2) {
             return;
             }

         //Liczba genów podlegających inwersji.
         int inversionGenesSize = (cutPoint1 < cutPoint2)
             ? cutPoint2 - cutPoint1
             : (chromosomeSize - cutPoint1) + cutPoint2;
         int differentGenesSize =  chromosomeSize - inversionGenesSize;
         //Punkt insertPoint powinien zawierać się w obszare chromosomu z
         //pominięciem obszaru inwersji.
         assert (insertPoint >= 0 && insertPoint <= differentGenesSize)
             : "Illegal argument insertPoint: " + insertPoint;

        //Koniec walidacji parametrów wejściowych.

        //IMPLEMENTACJA DO ZMIANY, ZASTĄPIĆ WEKTORAMI.

        //invGenes - sekcja inwersji po operacji inwersji.
        @SuppressWarnings("unchecked")
        GENE_TYPE[] invGenes = (GENE_TYPE[]) new Object[inversionGenesSize];
        int ii = cutPoint2;
        for (int i = 0; i < inversionGenesSize; i++) {
            ii = (ii < 1) ? chromosomeSize - 1 : ii - 1;
            invGenes[i] = chromosome.getGene(ii);
            }

        //cpGenes - pozostałe geny.
        @SuppressWarnings("unchecked")
        GENE_TYPE[] cpGenes = (GENE_TYPE[]) new Object[chromosomeSize - inversionGenesSize];
        ii = 0;
        int ii2 = cutPoint2;
        if (cutPoint1 < cutPoint2) {
            for (int i = 0; i < cpGenes.length; i++) {
                ii = (ii < cutPoint1) ? ii : ii2++;
                cpGenes[i] = chromosome.getGene(ii);
                ii++;
                }
            }
        ii = 0;
        if (cutPoint2 < cutPoint1) {
            for (int i = cutPoint2; i < cutPoint1; i++) {
                cpGenes[ii] = chromosome.getGene(i);
                ii++;
                }
            }

        int inv = 0;
        int cp = 0;
        for (int i = 0; i < chromosomeSize; i++) {
            if ((i < insertPoint) || (i >= insertPoint + inversionGenesSize)) {
                chromosome.setGene(i, cpGenes[cp]);
                cp++;
            } else {
                chromosome.setGene(i, invGenes[inv]);
                inv++;
                }
            }

        //Zasygnalizuj, ze chromosom został zmieniony.
        chromosome.changed();
        }
     }
