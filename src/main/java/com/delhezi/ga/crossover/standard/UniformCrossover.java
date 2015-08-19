/**
 * @(#)UniformCrossover.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.crossover.standard;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.utility.Random;
import java.util.logging.Logger;

/**
 * <code>UniformCrossover</code>: Krzyżowanie równomierne
 * Uniform Crossover (UX).
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class UniformCrossover<GENE_TYPE> implements com.delhezi.ga.crossover.ICrossover<GENE_TYPE> {

    /** Logger object. */
    private static final Logger LOGGER =
        Logger.getLogger(UniformCrossover.class.getName());

    /** Class name. */
    private static final String CLASS_NAME = UniformCrossover.class.getName();

    /**
     * Krzyżowanie.
     * @param chromosome1 Chromosom.
     * @param chromosome2 Chromosom.
     * @since 1.0
     */
    public final void crossover(Chromosome<GENE_TYPE> chromosome1,
                                Chromosome<GENE_TYPE> chromosome2) {
        LOGGER.entering(CLASS_NAME, "crossover",
                        new Object[] { chromosome1, chromosome2 });

        GENE_TYPE tmpG;
        for (int i = 0; i < chromosome1.size(); i++) {
            if (Random.nextDoubleInRange(0, 1) > 0.5) {
                tmpG = chromosome1.getGene(i);
                chromosome1.setGene(i, chromosome2.getGene(i));
                chromosome2.setGene(i, tmpG);
            }
        }
        LOGGER.exiting(CLASS_NAME, "crossover",
                       new Object[] { chromosome1, chromosome2 });
    }
}
