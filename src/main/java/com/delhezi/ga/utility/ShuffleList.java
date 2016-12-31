/**
 * @(#)ShuffleList.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.utility;

//ISTNIEJE OPERACJA Collections.shuffle
//klasa do usunięcia
/**
 * <code>ShuffleList</code>: Klasa pomocnicza. Miesza elementy listy.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public final class ShuffleList {

    /**
     * Konstruktor.
     * @since 1.0
     */
    private ShuffleList() {
    }

    /**
     * Przemieszaj elementy listy.
     * @param a xxx
     */
    public static void shuffle(final Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + (int) (Math.random() * (n - i));   //between i and N-1
            exch(a, i, r);
        }
    }

  /**
   * @param a xxx
   * @param i xxx
   * @param j xxx
   */
    private static void exch(final Object[] a, final int i, final int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
