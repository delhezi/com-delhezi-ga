/**
 * @(#)SelectionMethodType.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.selection.factory;


/**
 * Typy funkcji selekcji.
 */
public enum SelectionMethodType {
    /** Metoda ruletki, implementacja elementarna. */
    RouletteWheelElementaryImplementation,

    /** Metoda ruletki, implementacja efektywna. */
    RouletteWheelEffectiveImplementation,

    /** Metoda turniejowa. */
    Tournament,

    /** Selekcja liniowa wg rang. */
    LinearRanking;
}
