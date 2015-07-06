/**
 * @(#)CrossoverOperatorType.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.crossover.factory;

/**
 * Typy operatorów krzyżowania.
 * @version 1.0 2011-04-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public enum CrossoverOperatorType {

    /** Krzyżowanie z porządkowaniem. OrderCrossover (OX1). */
    OrderCrossover,

    /**
     * Krzyżowanie z częściowym odwzorowaniem.
     * PartiallyMatchedCrossover (PMX).
     */
    PartiallyMatchedCrossover,

    /** Krzyżowanie wielopunktowe. k-Point Crossover (k-PX). */
    KPointCrossover,

    /** Krzyżowanie równomierne. Uniform Crossover (UX). */
    UniformCrossover;
}
