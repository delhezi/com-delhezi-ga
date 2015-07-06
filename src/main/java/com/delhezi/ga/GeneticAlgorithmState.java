/**
 * @(#)GeneticAlgorithmState.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga;

/** Status w jakim znajduje się instancja algorytmu. */
public enum GeneticAlgorithmState {
    /** Zainicjowana. */
    INITIALIZED,

    /** Uruchomiona. */
    RUNNING,

    /** Zatrzymana. */
    STOPPED,

    /** Błąd. */
    ERROR;
}
