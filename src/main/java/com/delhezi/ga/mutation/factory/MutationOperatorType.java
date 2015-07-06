/**
 * @(#)MutationOperatorType.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.mutation.factory;

/**
 * Typy operatorów mutacji.
 */
public enum MutationOperatorType {
    /** Mutacja przez inwersję. Inversion Mutation (IVM). */
    InversionMutation,

    /** Mutacja przez zamianę miejscami. Swap Mutation (SM). */
    SwapMutation,

    /** Algorytm 2-opt. */
    _2Opt,

    /** Algorytm 3-opt. */
    _3Opt,

    /** Algorytm Lina-Kernighana. LinKernighan (LK). */
    LinKernighan;
}
