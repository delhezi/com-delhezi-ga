package com.delhezi.ga.genes;

public class EnumGene implements com.delhezi.ga.genes.IGene {

    /**
     * @param object xxx
     * @return boolean Porównanie objektów.
     */
    @Override
    public final boolean equals(final Object object) {
        return true;
    }
    
    /**
     * @return int.
     */
      @Override
      public final int hashCode() {
       return 11;
      }
      
      /**
       * String charakteryzujący gen.
       * @return String charakteryzujący gen.
       * @since 1.0
       */
      @Override
      public final String toString() {
          return "ytrew";
      }
}
