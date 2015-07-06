/**
 * @(#)GeneticAlgorithmException.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.exception;

/**
 * GeneticAlgorithmEJBException: Klasa wyjątku.
 * @version 1.0 2009-06-10
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public class GeneticAlgorithmException extends Exception {

    /**
     * Serial id for this class.
     */
    private static final long serialVersionUID = 5432228727933122229L;

    /**
     * Construct a message exception.
     */
    public GeneticAlgorithmException() {
        super();
        }

    /**
     * Construct a message exception.
     * @param msg The exception message.
     */
    public GeneticAlgorithmException(final String msg) {
        super(msg);
        }

    /**
     * Construct a message exception.
     * @param msg The exception message.
     * @param t The other exception.
     */
    public GeneticAlgorithmException(final String msg, final Throwable t) {
        super(msg, t);
        }

    /**
     * Construct an exception that holds another exception.
     * @param t The other exception.
     */
    public GeneticAlgorithmException(final Throwable t) {
        super(t);
        }
    }
