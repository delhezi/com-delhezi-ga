/**
 * @(#)PointTest.java
 * Copyright (C) 2008-2010 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.genes;

import static org.junit.Assert.*;
import org.junit.Test;

public class PointTest {
    public PointTest() {
    }

    /**
     * @see com.delhezi.ga.genes.Point#equals(Object)
     */
    @Test
    public void testEquals() {
      System.out.println("equals");
      Point x;
      Point y;
      Point z;
      boolean expResult;
      boolean result;

      //Jest zwrotna - dla dowolnej niepustej wartości referencyjnej x,
      //x.equals(x) musi zwracać true.
      x = new Point(100,200);
      expResult = true;
      result = x.equals(x);
      assertEquals(expResult, result);

      //Jest symetryczna - dla dowolnych niepustych wartości referencyjnych
      //x i y x.equals(y) musi zwracać true wtedy i tylko wtedy, gdy y.equals(x)
      //zwraca true.
      x = new Point(100,200);
      y = new Point(100,200);
      expResult = true;
      result = x.equals(y);
      assertEquals(expResult, result);
      result = y.equals(x);
      assertEquals(expResult, result);

      //Jest przechodnia - dla dowolnych niepustych wartości referencyjnych
      //x i y oraz z, jeśli x.equals(y) zwraca true i y.equals(z) zwraca true,
      //to x.equals(z) musi zwracać ture.
      x = new Point(100,200);
      y = new Point(100,200);
      z = new Point(100,200);
      expResult = true;
      result = x.equals(y);
      assertEquals(expResult, result);
      result = y.equals(z);
      assertEquals(expResult, result);
      result = x.equals(z);
      assertEquals(expResult, result);

      //Jest spójna - dla dowolnych niepustych wartości referencyjnych x i y
      //wieloktorne wywołania x.equals(y) zwracają konsekwentnie true lub
      //konsekwentnie false, przy założeniu, że nie została zmodyfikowana żadna
      //dana wykorzystywana do porównania obiektów.
      x = new Point(100,200);
      y = new Point(100,200);
      expResult = true;
      result = x.equals(y);
      assertEquals(expResult, result);
      result = x.equals(y);
      assertEquals(expResult, result);

      //Dla dowolnych niepustych referencji x różnych od null x.equals(null)
      //musi zwracać false.
      x = new Point(100,200);
      expResult = false;
      result = x.equals(null);
      assertEquals(expResult, result);
    }

    /**
     * @see com.delhezi.ga.genes.Point#hashCode()
     */
    @Test
    public void testHashCode() {
      System.out.println("hashCode");
      Point instance = new Point(100,200);
      int expResult = -1930559488;
      int result = instance.hashCode();
      assertEquals(expResult, result);
      //System.out.println("hashCode() result: "+ result);
    }
}
