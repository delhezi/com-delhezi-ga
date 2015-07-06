/**
 * @(#)IMutation.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation;

import com.delhezi.ga.Chromosome;

/**
 * Klasa <code>IMutation</code>: Interfejs klasy Mutation.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public interface IMutation {

  /**
   * Funkcja mutacji.
   * @param chromosome Chromosom podlegający mutacji.
   * @since 1.0
   */
  void mutation(Chromosome chromosome);
}
