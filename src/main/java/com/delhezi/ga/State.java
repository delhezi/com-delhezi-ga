/**
 * @(#)State.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga;

import com.delhezi.ga.exception.GeneticAlgorithmException;

/**
 * <code>State</code>: Interfejs wzorca State - określający stan algorytmu genetycznego.
 * @version 1.0 2011-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public interface State {

    /**
     * Uruchamia działanie algorytmu gentycznego.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    void run() throws GeneticAlgorithmException;

    /**
     * Zatrzymuje działanie algorytmu gentycznego.
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    void stop() throws GeneticAlgorithmException;

    /**
     * Zwraca status algorytmu gentycznego.
     * @return Status algorytmu gegentycznego.
     * @since 1.0
     */
    GeneticAlgorithmState getState();
}
