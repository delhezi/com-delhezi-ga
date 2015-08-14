package com.delhezi.ga.utility;

import static org.junit.Assert.*;
import org.junit.Test;

public class RandomUniqueIntegerTest {
    public RandomUniqueIntegerTest() {
    }

    /**
     * RandomUniqueInteger#get()
     */
    @Test
    public void testGet() {
        int SIZE = 6;
        RandomUniqueInteger bezPowtorzenZUsuwaniem =
            new RandomUniqueInteger(SIZE);

        int sumaExpResult = 1 + 2 + 3 + 4 + 5 + 6;
        int sumaResult = 0;
        for (int i = 0; i < SIZE; i++) {
            sumaResult += bezPowtorzenZUsuwaniem.get().intValue();
        }
        assertEquals(sumaExpResult, sumaResult);
    }

    /**
     * RandomUniqueInteger#getSize()
     */
    @Test
    public void testGetSize() {
        int SIZE = 6;
        RandomUniqueInteger bezPowtorzenZUsuwaniem =
            new RandomUniqueInteger(SIZE);

        assertEquals(SIZE, bezPowtorzenZUsuwaniem.getSize());
        bezPowtorzenZUsuwaniem.get();
        assertEquals((SIZE - 1), bezPowtorzenZUsuwaniem.getSize());
    }
}
