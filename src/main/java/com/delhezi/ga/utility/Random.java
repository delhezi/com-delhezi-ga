package com.delhezi.ga.utility;

/**
 * <code>Random/code>: Klasa pomocnicza.
 * @version 1.0 2015-08-07
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">
 * Wojciech Wolszczak</a>
 */
public final class Random extends java.util.Random {

    /** */
    private static final long serialVersionUID = 1L;
    /** */
  private static java.util.Random random = new java.util.Random();
  
  public static double nextDoubleInRange(double min, double max) {
      double range = max - min;
      double scaled = random.nextDouble() * range;
      double shifted = scaled + min;
      return shifted; // == (rand.nextDouble() * (max-min)) + min;
    }
}
