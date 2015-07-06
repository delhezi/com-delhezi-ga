/**
 * @(#)KPointCrossover.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.crossover.standard;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.utility.RandomUniqueInteger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * <code>KPointCrossover</code>: Krzyżowanie wielopunktowe
 * k-Point Crossover (k-PX).
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class KPointCrossover implements com.delhezi.ga.crossover.ICrossover {

    /** Logger object. */
    private static final Logger LOGGER =
        Logger.getLogger(KPointCrossover.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-1.3-3-";

    /** Class name. */
    private static final String CLASS_NAME = KPointCrossover.class.getName();

    /** Ilość punktów krzyżowania.*/
    private int k = 1;

    /**
     * Krzyżowanie.
     * @param chromosome1 Chromosom.
     * @param chromosome2 Chromosom.
     * @since 1.0
     */
    public final void crossover(final Chromosome chromosome1,
                                final Chromosome chromosome2) {
        LOGGER.entering(CLASS_NAME, "crossover",
                        new Object[] { chromosome1, chromosome2 });
        this.crossover(chromosome1, chromosome2, this.k);
        LOGGER.exiting(CLASS_NAME, "crossover",
                       new Object[] { chromosome1, chromosome2 });
    }

    /**
     * Krzyżowanie.
     * @param chromosome1 Chromosom.
     * @param chromosome2 Chromosom.
     * @param k Ilość punktów krzyżowania,
     * wartość z przedziału <1,chromosome1.size()-1>.
     * @since 1.0
     */
    public final void crossover(final Chromosome chromosome1,
                                final Chromosome chromosome2, int k) {
        LOGGER.entering(CLASS_NAME, "crossover",
                        new Object[] { chromosome1, chromosome2, k });

        if (chromosome1.size() < 2) {
            return;
        }
        if (k > chromosome1.size() - 1) {
            throw new IllegalArgumentException("k > chromosome1.size() - 1");
        }
        if (k < 1) {
            throw new IllegalArgumentException("k < 1");
        }

        //Wybierz k losowych punktów krzyżowania.
        List<Integer> kPoints = new ArrayList<Integer>(k);

        RandomUniqueInteger bezPowtorzenZUsuwaniem =
            new RandomUniqueInteger(chromosome1.size()-1);

        for (int i = 0; i < k; i++) {
            kPoints.add(bezPowtorzenZUsuwaniem.get());
        }

        this.crossover(chromosome1, chromosome2, kPoints);

        LOGGER.exiting(CLASS_NAME, "crossover",
                       new Object[] { chromosome1, chromosome2 });
    }

    /**
     * Krzyżowanie.
     * @param chromosome1 Chromosom.
     * @param chromosome2 Chromosom.
     * @param kPoints Lista punktów krzyżowania.
     * np. k{1,4,6,9,10} = x|xxx|xx|xxx|x|x.
     * @since 1.0
     */
    private final void crossover(final Chromosome chromosome1,
                                 final Chromosome chromosome2,
                                 List<Integer> kPoints) {
        LOGGER.entering(CLASS_NAME, "crossover",
                        new Object[] { chromosome1, chromosome2, kPoints });

        //Chromosom nie może być wartością null.
        assert chromosome1 != null : "Illegal argument chromosome1: null";
        assert chromosome2 != null : "Illegal argument chromosome2: null";
        //Lista punktów krzyżowania nie może być wartością null.
        assert kPoints != null : "Illegal argument kPoints: null";
      
        Collections.sort(kPoints);

        Object tmpCh;
        boolean s = false;
        for (int i = 0; i < chromosome1.size(); i++) {
            if (s) {
                tmpCh = chromosome1.getGene(i);
                chromosome1.setGene(i, chromosome2.getGene(i));
                chromosome2.setGene(i, tmpCh);
            }
            if (kPoints.size() > 0) {
                if (i == kPoints.get(0) - 1) {
                    s = !s;
                    kPoints.remove(0);
                }
                //jeśli jest ostatnia sekcja i nie trzeba wymieniać genów
                if ((kPoints.size() == 0) && (s==false)) {
                    return;
                }
            }
        }

        LOGGER.exiting(CLASS_NAME, "crossover",
                       new Object[] { chromosome1, chromosome2 });
    }

    /**
     * Ustaw ilość punktów krzyżowania..
     * @param k Ilość punktów krzyżowania.
     * @since 1.0
     */
    public void setK(int k) {
        this.k = k;
    }

    /**
     * Zwraca ilość punktów krzyżowania.
     * @return Ilość punktów krzyżowania.
     * @since 1.0
     */
    public int getK() {
        return this.k;
    }
}
