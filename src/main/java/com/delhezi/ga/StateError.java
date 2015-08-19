/**
 * @(#)StateError.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga;

import com.delhezi.ga.exception.GeneticAlgorithmException;
//import java.util.logging.Logger;


/**
 * <code>StateError</code>: Klasa określająca stan
 * algorytmu genetycznego.
 * @version 1.0 2011-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class StateError<GENE_TYPE> implements State {
    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(StateInitialized.class.getName());

    private GeneticAlgorithm<GENE_TYPE> ga;

    /**
     * Konstruktor.
     * @param ga Referencja do obiektu algorytmu genetycznego.
     * @since 1.0
     */
    public StateError(final GeneticAlgorithm<GENE_TYPE> ga) {
        this.ga = ga;
    }

    /**
     * Uruchamia działanie algorytmu gentycznego.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    @Override
    public final void run() throws GeneticAlgorithmException {
        throw new GeneticAlgorithmException("Current status : "
                                            + this.getState().toString());
    }

    /**
     * Zatrzymuje działanie algorytmu gentycznego.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    @Override
    public final void stop() throws GeneticAlgorithmException {
        throw new GeneticAlgorithmException("Current status : "
                                            + this.getState().toString());
    }

    /**
     * Zwraca status algorytmu gentycznego.
     * @return Status algorytmu gegentycznego.
     * @since 1.0
     */
    @Override
    public final GeneticAlgorithmState getState() {
        return GeneticAlgorithmState.ERROR;
    }
}

