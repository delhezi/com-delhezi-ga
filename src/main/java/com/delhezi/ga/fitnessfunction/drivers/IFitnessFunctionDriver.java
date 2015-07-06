/**
 * @(#)IFitnessFunctionDriver.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.fitnessfunction.drivers;

import com.delhezi.ga.exception.GeneticAlgorithmException;

/**
 * Klasa <code>IFitnessFunctionDriver</code>: Interfejs FitnessFunctionDriver.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public interface IFitnessFunctionDriver {

    /**
     * Wywołuje funkcję celu zdefiniowaną w skrypcie.
     * @param functionName Nazwa funkcji celu zdefiniowanej w skrypcie.
     * @param args Argumenty do przekazania do wywoływanej funkcji.
     * @return Wartość zwrócona przez funkcję celu.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    double invokeFunction(final String functionName,
                     final Object... args) throws GeneticAlgorithmException;
}
