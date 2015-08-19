/**
 * @(#)CrossoverFactory.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.crossover.factory;

import com.delhezi.ga.crossover.ICrossover;
import com.delhezi.ga.crossover.permutation.OrderCrossover;
import com.delhezi.ga.crossover.permutation.PartiallyMatchedCrossover;
import com.delhezi.ga.crossover.standard.KPointCrossover;
import com.delhezi.ga.crossover.standard.UniformCrossover;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sparametryzowana metoda wytwórcza operatora krzyżowania.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class CrossoverFactory {

    /** Logger object. */
    private static final Logger LOGGER =
        Logger.getLogger(CrossoverFactory.class.getName());

    /** Delhezi Error Code. */
    private static final String DERC = "1-1.1-1-";

    /** Class name. */
    private static final String CLASS_NAME = CrossoverFactory.class.getName();

    /**
     * Tworzy nowy obiekt operatora krzyżowania i zwraca referencję do niego.
     * @param crossoverOperator Określa typ tworzonego obiektu
     * operatora krzyżowania.
     * @return Referencja do obiektu operatora krzyżowania.
     * @throws GeneticAlgorithmException DERC-1-1.1-1-1
     * @since 1.0
     */
    public static <GENE_TYPE> ICrossover<GENE_TYPE> getCrossoverOperator(final CrossoverOperatorType crossoverOperator) 
		throws GeneticAlgorithmException {
        LOGGER.entering(CLASS_NAME, "getCrossoverOperator", crossoverOperator);
        switch (crossoverOperator) {
        case OrderCrossover:
            return new OrderCrossover<GENE_TYPE>();
        case PartiallyMatchedCrossover:
            return new PartiallyMatchedCrossover<GENE_TYPE>();
        case KPointCrossover:
            return new KPointCrossover<GENE_TYPE>();
        case UniformCrossover:
            return new UniformCrossover<GENE_TYPE>();
        }

        GeneticAlgorithmException e =
            new GeneticAlgorithmException("DERC-" + DERC +
                                          "1: Parametr crossoverOperator=" +
                                          crossoverOperator +
                                          " is not recognized.");
        LOGGER.log(Level.WARNING, "CrossoverFactory", e);
        throw e;
    }

    /**
     * Zwraca typ operatora krzyżowania określony dla parametru.
     * @param crossoverOperator Referencja do obiektu operatora krzyżowania.
     * @return Typ operatora krzyżowania.
     * @throws GeneticAlgorithmException DERC-1-1.1-1-2
     * @since 1.0
     */
    public static <GENE_TYPE> CrossoverOperatorType getCrossoverOperatorType(final ICrossover<GENE_TYPE> crossoverOperator) 
		throws GeneticAlgorithmException {
        LOGGER.entering(CLASS_NAME, "getCrossoverOperatorType",
                        crossoverOperator);

        String pClassName = crossoverOperator.getClass().getName();

        if (pClassName.equals("com.delhezi.ga.crossover.permutation.OrderCrossover")) {
            return CrossoverOperatorType.OrderCrossover;
        } else if (pClassName.equals("com.delhezi.ga.crossover.permutation.PartiallyMatchedCrossover")) {
            return CrossoverOperatorType.PartiallyMatchedCrossover;
        } else if (pClassName.equals("com.delhezi.ga.crossover.standard.KPointCrossover")) {
            return CrossoverOperatorType.KPointCrossover;
        } else if (pClassName.equals("com.delhezi.ga.crossover.standard.UniformCrossover")) {
            return CrossoverOperatorType.UniformCrossover;
        }

        GeneticAlgorithmException e =
            new GeneticAlgorithmException("DERC-" + DERC +
                                          "2: Parametr crossoverOperator=" +
                                          pClassName + " is not recognized.");
        LOGGER.log(Level.WARNING, "CrossoverFactory", e);
        throw e;
    }
}
