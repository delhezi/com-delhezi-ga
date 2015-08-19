/**
 * @(#)BitGene.java
 * Copyright (C) 2008-2011 delhezi.com
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL) version 3 or later.
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.delhezi.ga.genes;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Implementation of a BitGene.
 *
 * @author <a href="mailto:wojciech.wolszczak@delhezi.com">Wojciech Wolszczak</a>
 * @since 1.0
 * @version 1.0 2008-11-02
 */
@XmlJavaTypeAdapter(BitGene.Model.Adapter.class)
public class BitGene implements com.delhezi.ga.genes.IGene, Comparable<BitGene>, Serializable {


    private static final long serialVersionUID = 3L;
    
    private final boolean value;

    private BitGene(final boolean value) {
        this.value = value;
    }

    /**
     * Return the value of the BitGene.
     * @return The value of the BitGene.
     */
    public final boolean getValue() {
        return this.value;
    }
    
    /**
     * Create a new gene from the given {@code value}.
     * @param value the value of the new gene.
     * @return a new gene with the given value.
     */
    public BitGene newInstance(final Boolean value) {
        return new BitGene(value);
    }
    
    @Override
    public int compareTo(BitGene o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public String toString() {
        return Boolean.toString(this.value);
    }
    
    /**
     * Return the corresponding {@code BitGene} for the given {@code boolean}
     * value.
     *
     * @param value the value of the returned {@code BitGene}.
     * @return the {@code BitGene} for the given {@code boolean} value.
     */
    public static BitGene of(final boolean value) {
        return new BitGene(value);
    }
    
    /*
     * *************************************************************************
     * JAXB object serialization
     ************************************************************************/

    @XmlRootElement(name = "bit-gene")
    @XmlType(name = "com.delhezi.ga.genes.BitGene")
    @XmlAccessorType(XmlAccessType.FIELD)
    final static class Model {

        @XmlValue
        public boolean value;

        public final static class Adapter extends XmlAdapter<Model, BitGene> {
            @Override
            public Model marshal(final BitGene value) {
                final Model m = new Model();
                m.value = value.getValue();
                return m;
            }

            @Override
            public BitGene unmarshal(final Model m) {
                return BitGene.of(m.value);
            }
        }
    }
}
