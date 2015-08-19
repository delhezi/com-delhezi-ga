/**
 * @(#)ISelect.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.selection;

import com.delhezi.ga.Chromosome;
import com.delhezi.ga.exception.GeneticAlgorithmException;
import java.util.LinkedList;

/**
 * Klasa <code>ISelect</code>: Interfesj klasy Select.
 * @version 1.0 2009-12-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public interface ISelect<GENE_TYPE> {

    /**
     * xxx.
     * @param chromosomes Lista chromosomów.
     * @return xxx
     * @throws GeneticAlgorithmException xxx
     * @since 1.0
     */
    LinkedList<Chromosome<GENE_TYPE>> select(final LinkedList<Chromosome<GENE_TYPE>> chromosomes)
            throws GeneticAlgorithmException;
}
