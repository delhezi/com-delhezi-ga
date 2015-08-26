/**
 * @(#)FitnessFunction.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.fitnessfunction;

import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import java.util.Arrays;
import java.util.List;
//import java.util.logging.Logger;

/**
 * Klasa <code>FitnessFunction</code>: Funkcja celu.
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class FitnessFunction {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(FitnessFunction.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-3-1-";

    /** Skrypt liczący wskażnik przystosowania. */
    private IFitnessFunctionDriver driv;
    /** Nazwa funkcji celu wewnątrz skryptu liczący wskaźnik
     * przystosowania. */
    private String functionName;

    /**
     * Parametr określający maksymalizację/maksymalizację funkcji celu.
     * true -  maksymalizacja funkcji celu (najlepszym jest osobnik o
     *         najwiekszej wartości wskaźnika przystosowania)
     * false - minimalizacja funkcji celu (najlepszym jest osobnik o
     *         najmniejszej wartości wskaźnika przystosowania)
     */
    private boolean maximisation = false;

    /**
     * Konstruktor.
     * @param driv  Interfejs pozwalający na dostęp do funkcji
     * zaimplementowanych w plikach w katalogu /scripts/; Jeden interfejs jest
     * dedykowany do obsługi pojedynczego pliku.
     * @param functionName  Nazwa funkcji dostarczonej w pakiecie engineDrive,
     * w której zaimplementowano wyliczenie wskażnika przystosowania.
     * @since 1.0
     */
    public FitnessFunction(final IFitnessFunctionDriver driv,
                           final String functionName) {
        this.driv = driv;
        this.functionName = functionName;
    }

    /**
     * Wyznaczenie wskażnika przystosowania.
     * @param genes Tablica genów.
     * @return double xxx
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    public final double calculateFitness(final Object[] genes)
    throws GeneticAlgorithmException {
        List<Object> genesList = Arrays.asList(genes);
        return this.driv.invokeFunction(this.functionName, genesList);
    }

    /**
     * Zwraca parametr określający maksymalizację/maksymalizację funkcji
     * celu.
     * @return true -  maksymalizacja funkcji celu (najlepszym jest osobnik o
     *                 najwiekszej wartości wskaźnika przystosowania)
     *         false - minimalizacja funkcji celu (najlepszym jest osobnik o
     *                 najmniejszej wartości wskaźnika przystosowania)
     */
    public final boolean isMaximisation() {
        return maximisation;
    }

    /**
     * Ustawia parametr określający maksymalizację/maksymalizację funkcji
     * celu.
     * @param maximisation  true - maksymalizacja funkcji celu (najlepszym jest
     *                             osobnik o najwiekszej wartości wskaźnika
     *                             przystosowania)
     *                      false - minimalizacja funkcji celu (najlepszym jest
     *                             osobnik o najmniejszej wartości wskaźnika
     *                             przystosowania)
     */
    public final void setMaximisation(final boolean maximisation) {
        this.maximisation = maximisation;
    }

    /**
     * @return ScriptEngineDriver
     */
    public final IFitnessFunctionDriver getScriptEngineDriver() {
        return this.driv;
    }

}
