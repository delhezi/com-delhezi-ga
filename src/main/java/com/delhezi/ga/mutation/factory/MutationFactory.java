/**
 * @(#)MutationFactory.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation.factory;

import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.mutation.IMutation;
import com.delhezi.ga.mutation.InversionMutation;
import com.delhezi.ga.mutation.SwapMutation;
import com.delhezi.ga.mutation.heuristics.LinKernighan;
import com.delhezi.ga.mutation.heuristics._2Opt;
import com.delhezi.ga.mutation.heuristics._3Opt;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sparametryzowana metoda wytwórcza operatora mutacji.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class MutationFactory {

    /** Logger object. */
    private static final Logger LOGGER =
        Logger.getLogger(MutationFactory.class.getName());

    /** Delhezi Error Code. */
    private static final String DERC = "1-6.1-1-";

    /** Class name. */
    private static final String CLASS_NAME = MutationFactory.class.getName();

    /**
     * Tworzy nowy obiekt operatora mutacji i zwraca referencję do niego.
     * @param mutationOperator Określa typ tworzonego obiektu
     * operatora mutacji.
     * @return Referencja do obiektu operatora mutacji.
     * @throws GeneticAlgorithmException DERC-1-6.1-1-1
     * @since 1.0
     */
    public static <GENE_TYPE> IMutation<GENE_TYPE> getMutationOperator(final MutationOperatorType mutationOperator)
            throws GeneticAlgorithmException {
        LOGGER.entering(CLASS_NAME, "getMutationOperator", mutationOperator);
        switch (mutationOperator) {
        case _2Opt:
            return new _2Opt<GENE_TYPE>();
        case _3Opt:
            return new _3Opt<GENE_TYPE>();
        case LinKernighan:
            return new LinKernighan<GENE_TYPE>();
        case InversionMutation:
            return new InversionMutation<GENE_TYPE>();
        case SwapMutation:
            return new SwapMutation<GENE_TYPE>();
        default:
            break;
        }

        GeneticAlgorithmException e = new GeneticAlgorithmException(
                "DERC-" + DERC + "1: Parametr mutationOperator=" + mutationOperator + " is not recognized.");
        LOGGER.log(Level.WARNING, "CrossoverFactory", e);
        throw e;
    }

    /**
     * Zwraca typ operatora mutacji określony dla parametru.
     * @param mutationOperator Referencja do obiektu operatora mutacji.
     * @return Typ operatora mutacji.
     * @throws GeneticAlgorithmException DERC-1-6.1-1-2
     * @since 1.0
     */
    public static <GENE_TYPE> MutationOperatorType getMutationOperatorType(final IMutation<GENE_TYPE> mutationOperator)
            throws GeneticAlgorithmException {
        LOGGER.entering(CLASS_NAME, "getMutationOperatorType", mutationOperator);

        String pClassName = mutationOperator.getClass().getName();

        if (pClassName.equals("com.delhezi.ga.mutation.heuristics._2Opt")) {
            return MutationOperatorType._2Opt;
        } else if (pClassName.equals("com.delhezi.ga.mutation.heuristics._3Opt")) {
            return MutationOperatorType._3Opt;
        } else if (pClassName.equals("com.delhezi.ga.mutation.heuristics.LinKernighan")) {
            return MutationOperatorType.LinKernighan;
        } else if (pClassName.equals("com.delhezi.ga.mutation.InversionMutation")) {
            return MutationOperatorType.InversionMutation;
        } else if (pClassName.equals("com.delhezi.ga.mutation.SwapMutation")) {
            return MutationOperatorType.SwapMutation;
        }

        GeneticAlgorithmException e = new GeneticAlgorithmException(
                "DERC-" + DERC + "2: Parametr mutationOperator=" + pClassName + " is not recognized.");
        LOGGER.log(Level.WARNING, "CrossoverFactory", e);
        throw e;
    }
}
