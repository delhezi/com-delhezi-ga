/**
 * @(#)HashCodeUtil.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.utility;

import org.apache.log4j.Logger;
import java.lang.reflect.Array;

/**
 * Created by Rafal Gorzkowski
 * User: rafal
 * Date: 20.01.11
 * Time: 0:43
 */
public final class HashCodeUtil {

    private static final Logger log = Logger.getLogger(HashCodeUtil.class);
    private static final int ODD_PRIME_NUMBER = 31;

    public static final int SEED = 17;

    public static int hash(final int aSeed, final boolean aBoolean) {
        log.debug("hash for boolean");
        return firstElement(aSeed) + (aBoolean ? 1 : 0);
    }

    public static int hash(final int aSeed, final char aChar) {
        log.debug("hash for char");
        return firstElement(aSeed) + (int) aChar;
    }

    public static int hash(final int aSeed, final int aInt) {
        log.debug("hash for int");
        return firstElement(aSeed) + aInt;
    }

    public static int hash(final int aSeed, final long aLong) {
        log.debug("hash for long");
        return firstElement(aSeed) + (int) (aLong ^ (aLong >>> 32));
    }

    public static int hash(final int aSeed, final float aFloat) {
        log.debug("hash for float");
        return hash(aSeed, Float.floatToIntBits(aFloat));
    }

    public static int hash(final int aSeed, final double aDouble) {
        log.debug("hash for double");
        return hash(aSeed, Double.doubleToLongBits(aDouble));
    }

    public static int hash(final int aSeed, final Object aObject) {
        log.debug("hash for Object");
        int result = aSeed;
        if (aObject == null) {
            result = hash(result, 0);
        } else if (!isArray(aObject)) {
            result = hash(result, aObject.hashCode());
        } else {
            int length = Array.getLength(aObject);
            for (int index = 0; index < length; index++) {
                Object item = Array.get(aObject, index);
                result = hash(result, item);
            }
        }
        return result;
    }

    private static int firstElement(final int aSeed) {
        return ODD_PRIME_NUMBER * aSeed;
    }

    private static boolean isArray(final Object aObject) {
        return aObject.getClass().isArray();
    }
}
