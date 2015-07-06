/**
 * @(#)SelectionFactory.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.selection.factory;

import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.selection.ISelect;
import com.delhezi.ga.selection.LinearRanking;
import com.delhezi.ga.selection.RouletteWheelEffectiveImplementation;
import com.delhezi.ga.selection.RouletteWheelElementaryImplementation;
import com.delhezi.ga.selection.Tournament;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sparametryzowana metoda wytwórcza metody reprodukcji.
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class SelectionFactory {

    /** Logger object. */
    private static final Logger LOGGER =
        Logger.getLogger(SelectionFactory.class.getName());

    /** Delhezi Error Code. */
    private static final String DERC = "1-8.1-1-";

    /** Class name. */
    private static final String CLASS_NAME = SelectionFactory.class.getName();

    /**
     * Tworzy nowy obiekt funkcji selekcji i zwraca referencję do niego.
     * @param selectionMethod Określa typ tworzonego obiektu
     * funkcji selekcji.
     * @return Referencja do obiektu funkcji selekcji.
     * @throws GeneticAlgorithmException DERC-1-8.1-1-1
     * @since 1.0
     */
    public static ISelect getSelectionMethod(
                                   final SelectionMethodType selectionMethod)
    throws GeneticAlgorithmException {
        LOGGER.entering(CLASS_NAME, "getSelectionMethod", selectionMethod);
        switch (selectionMethod) {
        case RouletteWheelElementaryImplementation:
            return new RouletteWheelElementaryImplementation();
        case RouletteWheelEffectiveImplementation:
            return new RouletteWheelEffectiveImplementation();
        case Tournament:
            return new Tournament();
        case LinearRanking:
          return new LinearRanking();
        }

        GeneticAlgorithmException e =
            new GeneticAlgorithmException("DERC-" + DERC +
                                          "1: Parametr selectionMethod=" +
                                          selectionMethod +
                                          " is not recognized.");
        LOGGER.log(Level.WARNING, "CrossoverFactory", e);
        throw e;
    }

    /**
     * Zwraca typ funkcji selekcji określony dla parametru.
     * @param selectionMethod Referencja do obiektu funkcji selekcji.
     * @return Typ funkcji selekcji.
     * @throws GeneticAlgorithmException DERC-1-8.1-1-2
     * @since 1.0
     */
    public static SelectionMethodType getSelectionMethodType(
                                               final ISelect selectionMethod)
    throws GeneticAlgorithmException {
        LOGGER.entering(CLASS_NAME, "getSelectionMethodType", selectionMethod);

        String pClassName = selectionMethod.getClass().getName();

        if (pClassName.equals("com.delhezi.ga.selection.RouletteWheelElementaryImplementation")) {
            return SelectionMethodType.RouletteWheelElementaryImplementation;
        } else if (pClassName.equals("com.delhezi.ga.selection.RouletteWheelEffectiveImplementation")) {
            return SelectionMethodType.RouletteWheelEffectiveImplementation;
        } else if (pClassName.equals("com.delhezi.ga.selection.Tournament")) {
            return SelectionMethodType.Tournament;
        } else if (pClassName.equals("com.delhezi.ga.selection.LinearRanking")) {
            return SelectionMethodType.LinearRanking;
        }

        GeneticAlgorithmException e =
            new GeneticAlgorithmException("DERC-" + DERC +
                                          "2: Parametr selectionMethod=" +
                                          pClassName + " is not recognized.");
        LOGGER.log(Level.WARNING, "CrossoverFactory", e);
        throw e;
    }

}
