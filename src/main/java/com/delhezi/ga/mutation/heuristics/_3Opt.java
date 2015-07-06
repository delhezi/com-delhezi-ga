/**
 * @(#)_3Opt.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation.heuristics;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.mutation.IMutation;
//import java.util.logging.Logger;

/**
 * Klasa <code>_3Opt</code>: Algorytm 3-opt;
 *
 * Algorytm lokalnego poszukiwania. Dedykowany dla TSP;

 * Losowo wybieramy 3 krawędzie. Jeśli długość najlepszego z cyklu
 * uzyskanego po wymianie jest mniejsza niż przed, krawędzie są zamieniane
 * (wybierany jest taka konfiguracja, która daje najlepszy cykl);
 * W innym przypadku przeszukiwana jest dostępna pula rozwiązań
 * (iterakcyjnie wybieramy kolejne krawędzie) w celu znalezienia
 * pierwszego wystąpienia cyklu lepszego.
 * @version 1.0 2010-01-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class _3Opt implements IMutation {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(_3Opt.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-6.2-2-";

    /**
     * Funkcja mutation wykorzystuje algorytm 3-opt.
     * @param chromosome Chromosom.
     * @since 1.0
     */
    public final void mutation(final Chromosome chromosome) {
            throw new UnsupportedOperationException("No implements yet");
    }

}
