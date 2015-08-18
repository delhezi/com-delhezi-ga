/**
 * @(#)RouletteWheelElementaryImplementationTest.java
 * Copyright (C) 2008-2010 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.selection;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.ChromosomeProperties;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import com.delhezi.ga.fitnessfunction.FitnessFunction;
import com.delhezi.ga.fitnessfunction.drivers.IFitnessFunctionDriver;
import com.delhezi.ga.fitnessfunction.drivers.factory.FitnessFunctionDriverFactory;
import com.delhezi.ga.genes.Point;
import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedList;
import org.apache.log4j.Logger;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import junitx.util.PrivateAccessor;

public class RouletteWheelElementaryImplementationTest {

    private IFitnessFunctionDriver fitnessFunctionDriver;
    private static FitnessFunction fitnessFunction;
    private static ChromosomeProperties chromosomeProperties;
    private static final Logger log =
        Logger.getLogger(RouletteWheelElementaryImplementationTest.class);

    private static Point[][] genes = {
            { new Point(101, 120), new Point(102, 220), new Point(103, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 220) }, // genes[0]
            { new Point(101, 120), new Point(102, 130), new Point(103, 140), new Point(104, 150), new Point(105, 160),
                    new Point(105, 170) }, // genes[1]
            { new Point(101, 121), new Point(102, 122), new Point(103, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 126) }, // genes[2]
            { new Point(101, 120), new Point(102, 120), new Point(103, 120), new Point(104, 120), new Point(105, 120),
                    new Point(106, 120) },
            { new Point(101, 121), new Point(102, 122), new Point(103, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 136) },
            { new Point(101, 120), new Point(102, 220), new Point(103, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 230) },
            { new Point(101, 120), new Point(102, 220), new Point(153, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 220) },
            { new Point(101, 120), new Point(102, 130), new Point(153, 140), new Point(104, 150), new Point(105, 160),
                    new Point(105, 170) },
            { new Point(101, 121), new Point(102, 122), new Point(153, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 126) },
            { new Point(101, 120), new Point(102, 120), new Point(153, 120), new Point(104, 120), new Point(105, 120),
                    new Point(106, 120) },
            { new Point(101, 121), new Point(102, 122), new Point(153, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 136) },
            { new Point(101, 120), new Point(102, 220), new Point(153, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 230) },
            { new Point(101, 120), new Point(102, 220), new Point(103, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 220) },
            { new Point(101, 120), new Point(102, 130), new Point(103, 140), new Point(104, 150), new Point(105, 160),
                    new Point(105, 170) },
            { new Point(101, 121), new Point(102, 122), new Point(103, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 126) },
            { new Point(101, 120), new Point(102, 120), new Point(103, 120), new Point(104, 120), new Point(105, 120),
                    new Point(106, 120) },
            { new Point(101, 121), new Point(102, 122), new Point(103, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 136) },
            { new Point(101, 120), new Point(102, 220), new Point(103, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 230) },
            { new Point(101, 120), new Point(102, 220), new Point(153, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 220) },
            { new Point(101, 120), new Point(102, 130), new Point(153, 140), new Point(104, 150), new Point(105, 160),
                    new Point(105, 170) },
            { new Point(101, 121), new Point(102, 122), new Point(153, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 126) },
            { new Point(101, 120), new Point(102, 120), new Point(153, 120), new Point(104, 120), new Point(105, 120),
                    new Point(106, 120) },
            { new Point(101, 121), new Point(102, 122), new Point(153, 123), new Point(104, 124), new Point(105, 125),
                    new Point(105, 136) },
            { new Point(101, 120), new Point(102, 220), new Point(153, 220), new Point(104, 220), new Point(105, 220),
                    new Point(106, 230) } };// genes[23]
    
    private long timeStart; //milisek
    private long timeEnd; //milisek
    
    
    public RouletteWheelElementaryImplementationTest() {
    }

    @Before
    public void setUp() throws Exception {
        String scriptEngineDriverPath ="";
        File dir1 = new File (".");
        try {
            scriptEngineDriverPath = dir1.getCanonicalPath().replaceAll("\\\\", "/") +"/scripts/";
            }
        catch(Exception e) {e.printStackTrace();}
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
          fitnessFunction =new FitnessFunction(fitnessFunctionDriver, "fitnessFunction");
          chromosomeProperties = ChromosomeProperties.getInstance();
          chromosomeProperties.setFitnessFunction(fitnessFunction);
        }

    /**
     * RouletteWheelElementaryImplementation#rouletteWheelImpl(java.util.LinkedList,double[])
     */
    @Test
    public void testRouletteWheelImpl() {
      }

    /**
     * AbstractRouletteWheel#select(java.util.LinkedList)
     */
    @Test
    public void testSelect() {
        timeStart = System.currentTimeMillis();

        // ZOBACZ WYNIKI TESTU
        // DELHEZI/com-delhezi-lib/com-delhezi-ga/testy_dodatkowe/RouletteWheelElementaryImplementationTest.xlsx

        fitnessFunction.setMaximisation(false);
        // fitnessFunction.setMaximisation(true);

        LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
        for (int i = 0; i < this.genes.length; i++) {
            chromosomes.add(new Chromosome(this.genes[i], chromosomeProperties));
        }

        RouletteWheelElementaryImplementation instance = new RouletteWheelElementaryImplementation();

        int i = 0;
        double fitnessSum = 0;
        /*
        System.out.println("------------------------------------");
        System.out.println(" isMaximisation() = " +
                           fitnessFunction.isMaximisation());
        System.out.println("------------------------------------");

        for (Chromosome ch : chromosomes) {
            try {
                System.out.println(i + " ch.getFitness(...) = " + ch.getFitness());
              fitnessSum += ch.getFitness();
            } catch (GeneticAlgorithmException e) {
            }
            i++;
        }
        System.out.println("--------");
        System.out.println(" fitnessSum = " + fitnessSum);
        System.out.println("------------------------------------");
*/
        try {
            chromosomes = instance.select(chromosomes);
        } catch (GeneticAlgorithmException e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }

        /*
        i = 0;
        fitnessSum = 0;
        for (Chromosome ch : chromosomes) {
            try {
                System.out.println(i + " ch.getFitness(...) = " + ch.getFitness());
                fitnessSum += ch.getFitness();
            } catch (GeneticAlgorithmException e) {
            }
            i++;
        }
        System.out.println("--------");
        System.out.println(" fitnessSum = " + fitnessSum);
        System.out.println("------------------------------------");

        
        timeEnd = System.currentTimeMillis();
        double seconds = (timeEnd-timeStart)/1000.0;
        System.out.println("testSelect Time elapsed: "+new DecimalFormat("0.000").format(seconds)+" sec\n");
         */
    }

    
    /**
     * AbstractRouletteWheel#probabilities(java.util.LinkedList)
     */
    @Test
    public void testProbabilities() {
        probabilities(false);
        //probabilities(true);
    }

    public void probabilities(boolean maximisation) {
        fitnessFunction.setMaximisation(maximisation);

        LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
        for (int i = 0; i < this.genes.length; i++) {
            chromosomes.add(new Chromosome(this.genes[i], chromosomeProperties));
        }
        
        double[] normals = new double[chromosomes.size()];

        RouletteWheelElementaryImplementation instance = new RouletteWheelElementaryImplementation();
        
        Class parameterTypes[] = { LinkedList.class };
        Object args[] = { chromosomes };

        try {
            normals = (double[]) PrivateAccessor.invoke(instance, "probabilities", parameterTypes, args);
        } catch (Throwable e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }

        double sump = 0;
        double zakresMin = 0;
        for (int z = 0; z < chromosomes.size(); z++) {
            sump += normals[z];
            /*
            try {
                System.out.println("chromosom "+z+" fitnes= " + chromosomes.get(z).getFitness() + 
                        ", prawd. wyb. = " + normals[z] + 
                        ", zakres koła od "+ zakresMin + " do " + sump);
            } catch (GeneticAlgorithmException e) {
                e.printStackTrace();
            }
                         */
            zakresMin += normals[z];
        }

        double d_expResult = 1.0;
        assertEquals("suma prawdopodobieństw ", d_expResult, sump, 0.0000000000001);
    }

    /**
     * AbstractRouletteWheel#setVariable(java.util.LinkedList)
     */
    @Test
    public void testSetVariable() {
        setVariable(true);
        setVariable(false);
    }

    public void setVariable(boolean maximisation) {
        fitnessFunction.setMaximisation(maximisation);

        LinkedList<Chromosome> chromosomes = new LinkedList<Chromosome>();
        for (int i = 0; i < this.genes.length; i++) {
            chromosomes.add(new Chromosome(this.genes[i], chromosomeProperties));
        }

        RouletteWheelElementaryImplementation instance = new RouletteWheelElementaryImplementation();

        Class parameterTypes[] = { LinkedList.class };
        Object args[] = { chromosomes };

        try {
            PrivateAccessor.invoke(instance, "setVariable", parameterTypes, args);
        } catch (Throwable e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }

        /* 
        int i = 0;
        try {
            for (Chromosome ch : chromosomes) {
                System.out.println(i + " ch.getFitness() = " + ch.getFitness());
                i++;
            }
        } catch (GeneticAlgorithmException e) {
        }
        */

        int i_expResult;
        double d_expResult;

        try {
            double minFitnessResult = (Double) PrivateAccessor.getField(instance, "minFitness");
            double sumFitnessResult = (Double) PrivateAccessor.getField(instance, "sumFitness");
            double sumaOdwrotnosciResult = (Double) PrivateAccessor.getField(instance, "sumaOdwrotnosci");

            //System.out.println("minFitnessResult = " + minFitnessResult);
            //System.out.println("sumFitnessResult = " + sumFitnessResult);
            //System.out.println("sumaOdwrotnosciResult = " + sumaOdwrotnosciResult);

            d_expResult = 10.0;
            assertEquals("minFitness ", d_expResult, minFitnessResult, 0.000000000000000001);

            d_expResult = 3472.123621911467;
            assertEquals("sumFitness ", d_expResult, sumFitnessResult, 0.000000000000000001);

            d_expResult = 0.5299154612182531;
            assertEquals("sumaOdwrotnosci ", d_expResult, sumaOdwrotnosciResult, 0.000000000000000001);

        } catch (NoSuchFieldException e) {
            log.debug("Exception: ", e);
            e.printStackTrace();
            fail();
        }

    }
}
