/**
 * @(#)StateStopped.java
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
 * <code>StateStopped</code>: Klasa określająca stan
 * algorytmu genetycznego.
 * @version 1.0 2011-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class StateStopped implements State {
    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(StateStopped.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-11-";
    private GeneticAlgorithm ga;

    /**
     * Konstruktor.
     * @param ga Referencja do obiektu algorytmu genetycznego.
     * @since 1.0
     */
    public StateStopped(final GeneticAlgorithm ga) {
        this.ga = ga;
    }

    /**
     * Uruchamia działanie algorytmu gentycznego.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    @Override
    public final void run() throws GeneticAlgorithmException {
        ga.setState(GeneticAlgorithmState.RUNNING);

        while ((ga.getPopulation().getGeneration() <
                ga.getMaxGenerationCount() ||
                ga.getMaxGenerationCount() < 1) &&
               (ga.getPopulation().getGeneration() -
                ga.getPopulation().getTopChromosomeGenerationFound() <
                ga.getLastGenerationTopChromosomeFind() ||
                ga.getLastGenerationTopChromosomeFind() < 1)) {

            if (ga.getState() != GeneticAlgorithmState.RUNNING) {
                return;
            }

            ga.getPopulation().generation();
        }

        if (ga.getState() != GeneticAlgorithmState.ERROR) {
            ga.setState(GeneticAlgorithmState.STOPPED);
        }
    }

    /**
     * Zatrzymuje działanie algorytmu gentycznego.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    @Override
    public final void stop() throws GeneticAlgorithmException {
        return;
    }

    /**
     * Zwraca status algorytmu gentycznego.
     * @return Status algorytmu gegentycznego.
     * @since 1.0
     */
    @Override
    public final GeneticAlgorithmState getState() {
        return GeneticAlgorithmState.STOPPED;
    }
}
