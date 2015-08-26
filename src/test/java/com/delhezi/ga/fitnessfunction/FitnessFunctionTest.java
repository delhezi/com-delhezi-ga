/**
 * @(#)FitnessFunctionTest.java
 * Copyright (C) 2008-2010 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.fitnessfunction;

import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;
import com.delhezi.ga.genes.PointGene;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FitnessFunctionTest {

    private IFitnessFunctionDriver fitnessFunctionDriver;
    private FitnessFunction instance;
    private static final Logger log = Logger.getLogger(FitnessFunctionTest.class);

    @Before
    public void setUp() {
        String scriptEngineDriverPath = "";
        File dir1 = new File(".");
        try {
            scriptEngineDriverPath = dir1.getCanonicalPath() .replaceAll("\\\\", "/") + "/scripts/";
        } catch (Exception e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }

        try {
            fitnessFunctionDriver =
                    FitnessFunctionDriverFactory.getFitnessFunctionEngineDriver("javascript",
                                                                                scriptEngineDriverPath,
                                                                                "fitnessFunction_TSP.js");
        } catch (GeneticAlgorithmException e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }
        instance = new FitnessFunction(fitnessFunctionDriver, "fitnessFunction");
        instance.setMaximisation(false);
    }

    /**
     * com.delhezi.ga.fitnessfunction.FitnessFunction#calculateFitness(java.lang.Object[])
     */
    @Test
    public void testCalculateFitness() {
        log.debug("calculateFitness");
        //given
        PointGene[] genes = {new PointGene(1, 1), new PointGene(1, 2),
                new PointGene(1, 3), new PointGene(2, 3),
                new PointGene(2, 2), new PointGene(1, 2)};
        double expResult = 6.0;

        //when
        double result = 0;

        //then
        try {
            result = instance.calculateFitness(genes);
        } catch (GeneticAlgorithmException e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }
       // System.out.println(result);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * com.delhezi.ga.fitnessfunction.FitnessFunction#isMaximisation()
     */
    @Test
    public void testIsMaximisation() {
        log.debug("isMaximisation");
        boolean expResult = false;
        boolean result = instance.isMaximisation();
        assertEquals(expResult, result);
    }

    /**
     * com.delhezi.ga.fitnessfunction.FitnessFunction#setMaximisation(boolean)
     */
    @Test
    public void testSetMaximisation() {
        log.debug("setMaximisation");
        boolean maximisation = false;
        instance.setMaximisation(maximisation);
    }
}
