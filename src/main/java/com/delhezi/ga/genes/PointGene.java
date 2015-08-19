/**
 * @(#)PointGene.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.genes;

/**
 * Klasa <code>PointGene</code>: Klasa przechowująca informacje o punkcie;
 * Klasa przykładowa, ale może należało użyć java.awt.geom.Point2D;
 * UWAGA;
 * W przypadku kiedy w trakcie działania programu może dojść do zmiany
 * obiektu (np. mutacja pojedynczego genu), obiekt należy traktować jako
 * ZMIENIALY co wymagana zapewnienie GLEBOKIEGO kopiowania w klasie
 * com.delhezi.ga.Chromosome; Wówczas obiekt powinien implementować
 * interfejs Cloneable + funkcję clone() + fubkcje set, w innym wypadku
 * należy zrezygnowac z tych elementów.
 * @version 1.0 2008-11-02
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 */
public class PointGene implements com.delhezi.ga.genes.IGene { //extends java.awt.geom.Point2D //implements Cloneable

    /** Wspłórzędna x. */
    private int x;
    /** Wspłórzędna y. */
    private int y;

    /**
     * Konstruktor.
     * @param x Wspłórzędna x.
     * @param y Wspłórzędna y.
     * @since 1.0
     */
    public PointGene(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Zwraca wspłórzędną x.
     * @return Wspłórzędna x.
     * @since 1.0
     */
    public final int getx() {
        return this.x;
        }

    /**
     * Ustawia wspłórzędną y.
     * @return Wspłórzędna y.
     * @since 1.0
     */
    public final int gety() {
        return this.y;
        }

    /**
     * Ustawia wspłórzędną x.
     * @param x Wspłórzędna x.
     * @since 1.0
     */
    public final void setx(final int x) {
        this.x = x;
        }

    /**
     * Ustawia wspłórzędną y.
     * @param y Wspłórzędna y.
     * @since 1.0
     */
    public final void sety(final int y) {
        this.y = y;
        }

   //
   //  * Kopiuje chromosom.
   //  * W przypadku kiedy w trakcie mutacji może dojść do zmiany
   //  * wartości obiektu
   //  * wymagane jest zapewnienie GLEBOKIEGO kopiowania w klasie
   //  * com.delhezi.ga.Chromosome
   //  * W tym celu zaimplementować w klasie genu interfejs Cloneable,
   //  * klasę clone
   //  * oraz DOPISAC OPCJE w funkcji com.delhezi.ga.Chromosome.clone()
   //  * return Kopia chromosomu.
   //  * since 1.0
    //
    //@Override
    //public Point clone() {
    //    try {
    //        return (Point) super.clone();
    //    } catch (CloneNotSupportedException ex) {
    //        throw new AssertionError(); //Błąd JVM.
    //                                    Nie powinien się zdarzyć.
    //    }
    //}

    /**
     * @param object xxx
     * @return boolean Porównanie objektów.
     */
    @Override
    public final boolean equals(final Object object) {
        if (object instanceof PointGene) {
           PointGene other = (PointGene) object;
           return (this.getx() == other.getx() && this.gety() == other.gety());
        }
        return super.equals(object);
    }

  /**
   * @return int.
   */
    @Override
    public final int hashCode() {
     long bits = java.lang.Double.doubleToLongBits(getx());
     bits ^= java.lang.Double.doubleToLongBits(gety()) * 31;
     return (((int) bits) ^ ((int) (bits >> 32)));
    }

    /**
     * String charakteryzujący gen.
     * @return String charakteryzujący gen.
     * @since 1.0
     */
    @Override
    public final String toString() {
        return "GENE type=point [x=" + this.getx() + ",y=" + this.gety() + "]";
    }
}
