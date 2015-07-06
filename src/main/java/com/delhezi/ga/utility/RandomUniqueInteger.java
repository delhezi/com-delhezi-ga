/**
 * @(#)RandomIntegerList.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.utility;

import java.util.LinkedList;
import java.util.List;

/**
 * Klasa <code>RandomIntegerList</code>: Losowanie wartości typu Integer,
 * z zadanego przedziału (od 1 do size), bez powtórzeń.
 *
 * Rozwiązanie oparte na zbiorze uporządkowanym z dostępem po indeksie,
 * z którego wylosowana wartość jest automatycznie usuwana.
 * Po wylosowaniu elementu jest on usuwany ze zbioru i zwracany do
 * wywołującego. Kolejne losowanie odbywa się z mniejszego zbioru.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class RandomUniqueInteger {

        /** Lista liczb. */
        private List<Integer> liczby;

        /**
         * Inicjacja obiektu.
         * @since 1.0
         */
        private final void init(int size) {
                // Tworzymy zbiór liczb.
                liczby = new LinkedList<Integer>();
                // Dodajemy kolejne liczby do zbioru.
                for (int i = 1; i < size + 1; i++) {
                        liczby.add(new Integer(i));
                }
        }

        /**
         * Losowanie liczby z zakresu <1,size>.
         * @return Liczba z zakresu <1,size>.
         * @since 1.0
         */
        public final Integer get() {
                Integer i = (int) (Math.random() * liczby.size());
                i = liczby.get(i);
                liczby.remove(i);
                return i;
        }

        /**
         * Wielkość zbioru.
         * @return Wielkość zbioru.
         * @since 1.0
         */
        public int getSize() {
                return liczby.size();
        }

        /**
         * Konstruktor.
         * @param size Wielkość zbioru z którego losujemy elementy
         *             (zbiór będzie zawirał elementy 1 do size).
         * @since 1.0
         */
        public RandomUniqueInteger(final int size) {
            if (size <= 0) {
                throw new IllegalArgumentException(
                          "Wielkość musi być większa od 0");
            }
                init(size);
        }

}
