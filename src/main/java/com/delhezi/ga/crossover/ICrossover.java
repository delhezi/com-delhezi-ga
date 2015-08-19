/**
 * @(#)ICrossover.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.crossover;

import com.delhezi.ga.Chromosome;

/**
 * Klasa <code>ICrossover</code>: Interfejs Crossover.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public interface ICrossover<GENE_TYPE> {

  /**
   * Krzyżowanie.
   * @param chromosome1 Chromosom.
   * @param chromosome2 Chromosom.
   * @since 1.0
   */
  void crossover(Chromosome<GENE_TYPE> chromosome1, Chromosome<GENE_TYPE> chromosome2);
}
