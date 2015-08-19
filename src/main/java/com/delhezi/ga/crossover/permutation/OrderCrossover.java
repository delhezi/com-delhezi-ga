/**
 * @(#)OrderCrossover.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.crossover.permutation;

import com.delhezi.ga.Chromosome;
//import java.util.logging.Logger;

/**
 * <code>OrderCrossover</code>: Krzyżowanie z porządkowaniem
 * OrderCrossover (OX1).
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class OrderCrossover<GENE_TYPE> implements com.delhezi.ga.crossover.ICrossover<GENE_TYPE> {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(OrderCrossover.class.getName());

    /** Class name. */
    //private static final String CLASS_NAME = OrderCrossover.class.getName();

    /**
     * Funkcja crossover implementuje krzyżowanie z porządkowaniem;
     * Geny w chromosomach powinny być unikalne, ich wartości nie mogą się
     * powtarzać; Punkty krzyżowania wybierane są losowo.
     * @param chromosome1 Chromosom.
     * @param chromosome2 Chromosom.
     * @since 1.0
     */
    @Override
    public final void crossover(final Chromosome<GENE_TYPE> chromosome1,
                                final Chromosome<GENE_TYPE> chromosome2) {
        assert (chromosome1.getGenes().length == chromosome2.getGenes().length);
        if (chromosome1.getGenes().length == 0) {
            return;
        }
        final int geneLength = chromosome1.getGenes().length;

        //Losowe określenie dwóch punktów krzyżowania.
        final int cutpoint1 = (int) (Math.random() * geneLength);
        final int cutpoint2 = (int) (Math.random() * geneLength);

        this.crossover(chromosome1, chromosome2, cutpoint1, cutpoint2);
        }

    /**
     * Funkcja crossover implementuje krzyżowanie z porządkowaniem;
     * Geny w chromosomach powinny być unikalne, ich wartości nie
     * mogą sie powtarzać.
     * @param chromosome1 Chromosom.
     * @param chromosome2 Chromosom.
     * @param cutpoint1 Punkt krzyżowania np. xx|xxx|xx cutpoint1=2.
     * @param cutpoint2 Punkt krzyżowania np. yy|yyy|yy cutpoint2=5.
     * @since 1.0
     */
    public final void crossover(final Chromosome<GENE_TYPE> chromosome1,
                                final Chromosome<GENE_TYPE> chromosome2,
                                final int cutpoint1, final int cutpoint2) {
        assert (chromosome1.getGenes().length == chromosome2.getGenes().length);
        if (chromosome1.getGenes().length == 0) {
            return;
        }

        final int start = Math.min(cutpoint1, cutpoint2);
        final int end = Math.max(cutpoint1, cutpoint2);

        if ((start == end) || (start == 0 && end == chromosome2.getGenes().length)) {
            return;
        }

        final int geneLength = chromosome1.getGenes().length;

        //Tworzymy geny potomków.
        GENE_TYPE[] child1 = (GENE_TYPE[]) new Object[geneLength];
        GENE_TYPE[] child2 = (GENE_TYPE[]) new Object[geneLength];

        //Dla sekcji dopasowania.
        for (int i = start; i < end; i++) {
            //Wypełnij sekcję dopasowania chromosomów potomnych genami
            //rodzica.
            child1[i] = chromosome1.getGene(i);
            child2[i] = chromosome2.getGene(i);
        }

        int currentIndex1 = 0;
        int currentIndex2 = 0;
        boolean znaleziono;


        // Dla SKRAJNYCH genów z lewej strony.
        for (int i = 0; i < start; i++) {
            // Dla child1
            for (; ; ) {
                znaleziono = false;
                //Sprawdzenie czy jest już taki gen w chromosomie
                for (int k = 0; k <= end && znaleziono == false; k++) {
                    if (child1[k] == chromosome2.getGene(currentIndex2)) {
                        znaleziono = true;
                        currentIndex2++;
                    }
                }
                //Jeśli nie ma to dodaj
                if (znaleziono == false) {
                    child1[i] = chromosome2.getGene(currentIndex2);
                    currentIndex2++;
                    break; //KONIEC for(;;)
                }
            }

            // Dla child2
            for (; ; ) {
                znaleziono = false;
                for (int k = 0; k <= end && znaleziono == false; k++) {
                    if (child2[k] == chromosome1.getGene(currentIndex1)) {
                        znaleziono = true;
                        currentIndex1++;
                    }
                }
                if (znaleziono == false) {
                    child2[i] = chromosome1.getGene(currentIndex1);
                    currentIndex1++;
                    break; //KONIEC  for(;;)
                }
            }
        } //for


        //Wypełniamy SKRAJNE geny obiektu potomnego z prawej strony.
        for (int i = end; i < geneLength; i++) {
            // Dla child1
            for (; ; ) {
                znaleziono = false;
                for (int k = 0; k < geneLength && znaleziono == false; k++) {
                    if (child1[k] == chromosome2.getGene(currentIndex2)) {
                        znaleziono = true;
                        if (currentIndex2 < geneLength - 1) {
                            currentIndex2++;
                        } else {
                            currentIndex2 = 0;
                        }
                    }
                }
                if (znaleziono == false) {
                    child1[i] = chromosome2.getGene(currentIndex2);
                    if (currentIndex2 < geneLength - 1) {
                        currentIndex2++;
                    } else {
                        currentIndex2 = 0;
                    }
                    break; //KONIEC for(;;)
                }
            }

            // Dla child2
            for (; ; ) {
                znaleziono = false;
                for (int k = 0; k < geneLength && znaleziono == false; k++) {
                    if (child2[k] == chromosome1.getGene(currentIndex1)) {
                        znaleziono = true;
                        if (currentIndex1 < geneLength - 1) {
                            currentIndex1++;
                        } else {
                            currentIndex1 = 0;
                        }
                    }
                }
                if (znaleziono == false) {
                    child2[i] = chromosome1.getGene(currentIndex1);
                    if (currentIndex1 < geneLength - 1) {
                        currentIndex1++;
                    } else {
                        currentIndex1 = 0;
                    }
                    break; //KONIEC  for(;;)
                }
            }
        } //for

        chromosome1.setGenes(child1);
        chromosome2.setGenes(child2);
    }
}
