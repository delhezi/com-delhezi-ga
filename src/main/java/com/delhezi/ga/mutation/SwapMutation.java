/**
 * @(#)SwapMutation.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation;

import com.delhezi.ga.Chromosome;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Klasa <code>SwapMutation</code>: Mutacja przez zamianę miejscami
 * Swap Mutation (SM).
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class SwapMutation<GENE_TYPE> implements IMutation<GENE_TYPE> {

    /** Logger object. */
    private static final Logger LOGGER = Logger.getLogger(SwapMutation.class.getName());

    /** Class name. */
    private static final String CLASS_NAME = SwapMutation.class.getName();

    /**
     * Random.
     */
    private static Random random = new Random();

    /**
     * Funkcja mutation implementuje mutację przez zamianę miejscami
     * dwóch wylosowanych genów.
     * @param chromosome Chromosom podlegający mutacji.
     * @since 1.0
     */
    public final void mutation(final Chromosome<GENE_TYPE> chromosome) {
        LOGGER.entering(CLASS_NAME, "mutation", chromosome);

        if (chromosome == null) {
            throw new IllegalArgumentException("Chromosome is null.");
        }
        int chromosomeSize = chromosome.size();
        // Chromosom ma tylko 1 gen.
        if (chromosomeSize < 2) {
            return;
        }

        // Losuje liczny z zakresu od 0 do chromosomeSize.
        int gene1 = (int) (random.nextDouble() * chromosomeSize);
        int gene2 = (int) (random.nextDouble() * chromosomeSize);

        // Korekta wyniku.
        // Istnieje bardzo małe prawdopodobienstwo wylosowania liczby o
        // wartosci chromosomeSize (poza zakresem), np. 4.999999999999999
        // zaokraglane jest do 4, ale sprawdzę:
        if (gene1 == chromosomeSize) {
            gene1--;
        }
        if (gene2 == chromosomeSize) {
            gene2--;
        }

        this.mutation(chromosome, gene1, gene2);

        LOGGER.exiting(CLASS_NAME, "mutation", new Object[] { chromosome, gene1, gene2 });
    }

    /**
     * Funkcja mutation implementuje mutację przez zamianę miejscami
     * dwóch wylosowanych genów.
     * @param chromosome Chromosom podlegający mutacji.
     * @param gene1 Gen podlegający zamianie. Przykład: gene1=1 dla xXxxxxxx.
     * @param gene2 Gen podlegający zamianie. Przykład: gene2=0 dla Xxxxxxxx.
     * @since 1.0
     */
    protected final void mutation(final Chromosome<GENE_TYPE> chromosome, final int gene1, final int gene2) {
        LOGGER.entering(CLASS_NAME, "mutation", new Object[] { chromosome, gene1, gene2 });

        // Chromosom nie może być wartością null.
        assert chromosome != null : "Illegal argument chromosome: null";

        int chromosomeSize = chromosome.size();

        // Punkt gene1 powinien zawierać się w obszare chromosomu.
        assert(gene1 >= 0 && gene1 < chromosomeSize) : "Illegal argument gene1: " + gene1;
        // Punkt gene2 powinien zawierać się w obszare chromosomu.
        assert(gene2 >= 0 && gene2 < chromosomeSize) : "Illegal argument gene2: " + gene2;

        // W przypadku kiedy gen1 i gen2 są tymi samymi genami mutacja przez
        // zamianę miejscami nie spowoduje żadnych zmian.
        if (gene1 == gene2) {
            return;
        }

        GENE_TYPE temp = chromosome.getGene(gene1);
        chromosome.setGene(gene1, chromosome.getGene(gene2));
        chromosome.setGene(gene2, temp);

        // Zasygnalizuj, ze chromosom został zmieniony.
        chromosome.changed();

        LOGGER.exiting(CLASS_NAME, "mutation", chromosome);
    }
}
