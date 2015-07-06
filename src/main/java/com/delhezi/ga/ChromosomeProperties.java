/**
 * @(#)ChromosomeProperties.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga;

import com.delhezi.ga.fitnessfunction.FitnessFunction;
//import java.util.logging.Logger;

/**
 * <code>ChromosomeProperties</code>: Parametry wspólne dla
 * wszystkich chromosomów w ramach jednej instancji populacji.
 * @version 1.0 2010-10-13
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public final class ChromosomeProperties {

    /** Logger object. */
    //private static final Logger LOGGER =
    //    Logger.getLogger(ChromosomeProperties.class.getName());

    /** Delhezi Error Code. */
    //private static final String DERC = "1-2-";

    /** Konstruktor. */
    private ChromosomeProperties() {
        super();
    }

    /**
     * Zwraca instancję obiektu.
     * @return instancja obiektu.
     * @since 1.0
     */
    public static ChromosomeProperties getInstance() {
        return new ChromosomeProperties();
    }

    /**
     * Ustawia referencję do obiektu implementującego funkcję celu.
     * @param fitnessFunction Referencja do obiektu implementującego
     * funkcję celu.
     * @since 1.0
     */
    public void setFitnessFunction(final FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        }

    /**
     * Zwraca referenceję do obiektu implementującego funkcję celu;
     * Uwaga;
     * Zwracana jest referencja do objektu a nie jego kopia;
     * Jeśli za pomocą otrzymanej referencji dojdzie do modyfikacji objektu
     * należy wykonać funkcję changed() klasy Chromosome w celu
     * ponownego wyznaczenia wskaźnika przystosowania chromosomu.
     * @return fitnessFunction Referencja do obiektu implementującego
     * funkcję celu.
     * @since 1.0
     */
    public FitnessFunction getFitnessFunction() {
        return this.fitnessFunction;
        }

    /** Referencja do obiektu implementującego funkcję celu. */
    private FitnessFunction fitnessFunction;
}
