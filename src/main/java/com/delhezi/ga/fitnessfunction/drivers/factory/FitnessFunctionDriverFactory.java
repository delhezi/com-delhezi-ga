/**
 * @(#)FitnessFunctionDriverFactory.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.fitnessfunction.drivers.factory;

import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import com.delhezi.ga.fitnessfunction.drivers.ScriptEngineDriver;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sparametryzowana metoda wytwórcza sterownika funkcji celu.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class FitnessFunctionDriverFactory {

    /** Logger object. */
    private static final Logger LOGGER =
        Logger.getLogger(FitnessFunctionDriverFactory.class.getName());

    /** Delhezi Error Code. */
    private static final String DERC = "1-3.1.1-1-";

    /** Class name. */
    private static final String CLASS_NAME =
        FitnessFunctionDriverFactory.class.getName();


    /**
     * Tworzy nowy obiekt sterownika finkcji celu i zwraca referencję do niego.
     * @param scriptEnginName Nazwa silnika skryptów. Przykładowe wartości:
     *                        [js, rhino, JavaScript, javascript,
     *                        ECMAScript, ecmascript].
     * @param scriptPath  Bezwzględna ścieżka zakończona "/" do katalogu w
     * którym składowane są skrytpy funkcji celu.
     * @param scriptFile  Naza pliku ze skrytem funkcji celu.
     * @return Funkcja celu.
     * @throws GeneticAlgorithmException Propagowany z funkcji
     * ScriptEngineDriver klas implementujących silniki funkcji celu.
     */
    public static IFitnessFunctionDriver getFitnessFunctionEngineDriver(
                                                final String scriptEnginName,
                                                final String scriptPath,
                                                final String scriptFile)
    throws GeneticAlgorithmException {
        LOGGER.entering(CLASS_NAME, "getFitnessFunctionEngineDriver",
                        new Object[] {scriptEnginName, scriptPath,
                                      scriptFile });
		LOGGER.log(Level.WARNING, "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
        IFitnessFunctionDriver driv =
            new ScriptEngineDriver(scriptEnginName, scriptPath, scriptFile);
        LOGGER.exiting(CLASS_NAME, "getFitnessFunctionEngineDriver", driv);
        return driv;
    }

  /**
   * Zwraca typ sterownika funkcji celu określony dla parametru.
   * @param fitnessFunctionDriver Referencja do obiektu sterownika funkcji celu.
   * @return Typ sterownika funkcji celu.
   * @throws GeneticAlgorithmException DERC-1-3.1.1-1-2
   * @since 1.0
   */
    public static FitnessFunctionDriverType getFitnessFunctionEngineDriverType(
                           final IFitnessFunctionDriver fitnessFunctionDriver)
    throws GeneticAlgorithmException {
        LOGGER.entering(CLASS_NAME, "getFitnessFunctionEngineDriverType",
                        fitnessFunctionDriver);

        String pClassName = fitnessFunctionDriver.getClass().getName();

        if (pClassName.equals("com.delhezi.ga.fitnessfunction.drivers.ScriptEngineDriver")) {
            return FitnessFunctionDriverType.ScriptEngineDriver;
        }

        GeneticAlgorithmException e =
            new GeneticAlgorithmException("DERC-" + DERC +
                                          "2: Parametr fitnessFunctionDriver=" +
                                          pClassName + " is not recognized.");
        LOGGER.log(Level.WARNING, "getFitnessFunctionEngineDriverType", e);
        throw e;
    }
}
