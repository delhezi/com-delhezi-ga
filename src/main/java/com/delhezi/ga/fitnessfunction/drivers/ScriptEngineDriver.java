/**
 * @(#)ScriptEngineDriver.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.fitnessfunction.drivers;

import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.utility.ScriptEngine;

/**
 * <code>ScriptEngineDriver</code>: Silnik skryptów dla funkcji celu.
 * @version 1.0 2009-12-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class ScriptEngineDriver extends ScriptEngine
                                       implements IFitnessFunctionDriver {

  /**
   * Konstruktor.
   * @param scriptEnginName Nazwa silnika skryptów. Przykładowe wartości:
   *                        [js, rhino, JavaScript, javascript,
   *                        ECMAScript, ecmascript].
   * @param scriptPath  Bezwzględna ścieżka zakończona "/" do katalogu w
   * którym składowane są skrytpy.
   * @param scriptFile  Naza pliku ze skrytem.
   * @throws GeneticAlgorithmException Propagacja DERC-1-9-1-1, DERC-1-9-1-2,
   * DERC-1-9-1-3
   * @since 1.0
   */
    public ScriptEngineDriver(final String scriptEnginName,
                              final String scriptPath,
                              final String scriptFile)
    throws GeneticAlgorithmException {
        super(scriptEnginName, scriptPath, scriptFile);
    }

    /**
     * Wywołuje funkcję celu zdefiniowaną w skrypcie.
     * @param functionName Nazwa funkcji celu zdefiniowanej w skrypcie.
     * @param args Argumenty do przekazania do wywoływanej funkcji.
     * @return Wartość zwrócona przez funkcję celu.
     * @throws GeneticAlgorithmException Propagacja DERC-1-9-1-4, DERC-1-9-1-5
     * @since 1.0
     */
    public final double invokeFunction(final String functionName,
                                       final Object... args)
    throws GeneticAlgorithmException {
        return (Double) super.invoke(functionName, args);
    }

}
