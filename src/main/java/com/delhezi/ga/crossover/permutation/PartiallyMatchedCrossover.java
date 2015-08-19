/**
 * @(#)PartiallyMappedCrossover.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.crossover.permutation;

import com.delhezi.ga.Chromosome;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * <code>PartiallyMatchedCrossover</code>: Krzyżowanie z częściowym
 * odwzorowaniem; PartiallyMatchedCrossover (PMX).
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class PartiallyMatchedCrossover<GENE_TYPE> implements com.delhezi.ga.crossover.ICrossover<GENE_TYPE> {

    /** Logger object. */
    private static final Logger LOGGER = Logger.getLogger(PartiallyMatchedCrossover.class.getName());

    /** Class name. */
    private static final String CLASS_NAME = PartiallyMatchedCrossover.class.getName();

    /**
     * Funkcja crossover implementuje krzyżowanie z częściowym odwzorowaniem;
     * Geny w chromosomach powinny być unikalne, ich wartości nie mogą
     * sie powtarzać; Punkty krzyżowania wybierane są losowo w ciele funkcji.
     * @param chromosome1 Chromosom.
     * @param chromosome2 Chromosom.
     * @since 1.0
     */
    @Override
    public final void crossover(final Chromosome<GENE_TYPE> chromosome1, final Chromosome<GENE_TYPE> chromosome2) {
        LOGGER.entering(CLASS_NAME, "crossover", new Object[] { chromosome1, chromosome2 });
        assert(chromosome1.getGenes().length == chromosome2.getGenes().length);
        if (chromosome1.getGenes().length == 0) {
            return;
        }

        final int geneLength = chromosome1.getGenes().length;

        // Losowe określenie dwóch punktów krzyżowania.
        // cutLength = długość sekcji dopasowania min 1, max geneLength-1.
        final int cutLength = (int) (Math.random() * (geneLength - 1) + 1);
        final int cutpoint1 = (int) (Math.random() * (geneLength - cutLength));
        final int cutpoint2 = cutpoint1 + cutLength;
        this.crossover(chromosome1, chromosome2, cutpoint1, cutpoint2);
        LOGGER.exiting(CLASS_NAME, "crossover");
    }

    /**
     * Funkcja crossover implementuje krzyżowanie z częściowym odwzorowaniem;
     * Geny w chromosomach powinny być unikalne, ich wartości nie mogą
     * sie powtarzać.
     * @param chromosome1 Chromosom.
     * @param chromosome2 Chromosom.
     * @param cutpoint1 Punkt krzyżowania np. xx|xxx|xx cutpoint1=2.
     * @param cutpoint2 Punkt krzyżowania np. yy|yyy|yy cutpoint2=5.
     * @since 1.0
     */
    public final void crossover(final Chromosome<GENE_TYPE> chromosome1, final Chromosome<GENE_TYPE> chromosome2, final int cutpoint1,
            final int cutpoint2) {
        LOGGER.entering(CLASS_NAME, "crossover", new Object[] { chromosome1, chromosome2, cutpoint1, cutpoint2 });
        assert(chromosome1.getGenes().length == chromosome2.getGenes().length);
        assert(cutpoint1 < cutpoint2);
        if (chromosome1.getGenes().length == 0) {
            return;
        }

        final int geneLength = chromosome1.getGenes().length;

        // Tworzymy geny potomków.
        @SuppressWarnings("unchecked")
        GENE_TYPE[] child1 = (GENE_TYPE[]) new Object[geneLength];
        @SuppressWarnings("unchecked")
        GENE_TYPE[] child2 = (GENE_TYPE[]) new Object[geneLength];
        // Odwzorowania genów dla sekcji dopasowania.
        ArrayList<GENE_TYPE> substring1 = new ArrayList<GENE_TYPE>(cutpoint2 - cutpoint1);
        ArrayList<GENE_TYPE> substring2 = new ArrayList<GENE_TYPE>(cutpoint2 - cutpoint1);

        // Dla sekcji dopasowania.
        for (int i = cutpoint1; i < cutpoint2; i++) {
            // Wypełnij sekcję dopasowania chromosomów potomnych genami
            // drugiego rodzica.
            child1[i] = chromosome2.getGene(i);
            child2[i] = chromosome1.getGene(i);
            // Stwórz pary genów stanowiące odwzorowania dla sekcji
            // dopasowania.
            substring1.add(chromosome2.getGene(i));
            substring2.add(chromosome1.getGene(i));
        }
        // Optymalizacja tabeli odwzorowań dla sekcji dopasowania.
        optSubstring(substring1, substring2);

        // Dla każdego z genów spoza sekcji dopasowania.
        for (int i = 0; i < geneLength; i++) {
            if ((i < cutpoint1) || (i >= cutpoint2)) {

                // Dla child1.
                // Sprawdź czy w kolekcji substring1 występuje gen
                // parent1.getGene(i). contains(obj) zwraca wartość true jeśli
                // kolekcja zawiera obiekt identyczny z porównywanym
                // obiektem "obj". W przypadku kiedy występuje zastosuj
                // algorytm naprawy dla osobnika potomnyego zamieniając
                // niedozwolony gen zgodnie z tabelą odwzorowań dla sekcji
                // dopasowania. W przypadku kiedy nie występuje kopiuj gen.
                if (substring1.contains(chromosome1.getGene(i))) {
                    // Określ na którym miejscu występuje.
                    int subI = substring1.indexOf(chromosome1.getGene(i));
                    child1[i] = substring2.get(subI);
                } else {
                    child1[i] = chromosome1.getGene(i);
                }

                // Dla child2.
                if (substring2.contains(chromosome2.getGene(i))) {
                    int subI = substring2.indexOf(chromosome2.getGene(i));
                    child2[i] = substring1.get(subI);
                } else {
                    child2[i] = chromosome2.getGene(i);
                }
            } // if
        } // for

        chromosome1.setGenes(child1);
        chromosome2.setGenes(child2);
        LOGGER.exiting(CLASS_NAME, "crossover");
    }

    /**
     * Optymalizacja tabeli odwzorowań dla sekcji dopasowania.
     * Przykład dla substring1(6,9,2,1), substring2(3,2,7,9):
     * 6 - 3
     * 9 - 2
     * 2 - 7
     * 1 - 9
     * "zlepiając" powyższe odwzorowanie otrzymujemy:
     * 6 - 3
     * 1 - 9 - 2 - 7
     * co po skróceniu daje:
     * 6 - 3
     * 1 - 7
     *
     * @param <T> xxx
     * @param substring1 Sekcja dopasowania.
     * @param substring2 Sekcja dopasowania.
     * @since 1.0
     */
    public final <T> void optSubstring(final ArrayList<T> substring1, final ArrayList<T> substring2) {
        LOGGER.entering(CLASS_NAME, "optSubstring", new Object[] { substring1, substring2 });
        for (int i = 0; i < substring1.size(); i++) {
            if (substring1.get(i) == substring2.get(i)) {
                substring1.remove(i);
                substring2.remove(i);
                i--;
            } else {
                // Sprawdź czy w kolekcji substring2 występuje gen
                // substring1.get(i). contains(obj) zwraca wartość true jeśli
                // kolekcja zawiera obiekt identyczny z porównywanym
                // obiektem "obj".
                if (substring2.contains(substring1.get(i))) {
                    // Określ na którym miejscu występuje.
                    int sub2i = substring2.indexOf(substring1.get(i));
                    substring2.set(sub2i, substring2.get(i));
                    substring1.remove(i);
                    substring2.remove(i);
                    i--;
                }
            }
        }
        LOGGER.exiting(CLASS_NAME, "optSubstring");
    }
}
