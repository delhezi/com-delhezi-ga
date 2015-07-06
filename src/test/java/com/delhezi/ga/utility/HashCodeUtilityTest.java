package utility;

import com.delhezi.ga.utility.HashCodeUtil;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Rafal Gorzkowski
 * User: rafal
 * Date: 20.01.11
 * Time: 1:25
 */
public class HashCodeUtilityTest {


    @Test
    public void shouldHashBeHash() {
        //given
        String comDelheziGa = "com-delhezi-ga";
        int intVal = 124353;
        float floatVal = 3451232f;
        double doubleVal = 1232443d;
        InnerHashCodeTestClass inner = new InnerHashCodeTestClass(1, 2f, 3d, "inner");
        InnerHashCodeTestClass inner2 = new InnerHashCodeTestClass(1, 2.0f, 3.00000000000000009999d, "inner");

        //when
        int comDelheziGaHashCode = HashCodeUtil.hash(0, comDelheziGa);
        int intObjHashCode = HashCodeUtil.hash(0, intVal);
        int floatObjHashCode = HashCodeUtil.hash(0, floatVal);
        int doubleObjHashCode = HashCodeUtil.hash(0, doubleVal);
        int innerHashCode = HashCodeUtil.hash(0, inner);
        int innerHashCode2 = HashCodeUtil.hash(0, inner2);

        //then
        assertEquals(comDelheziGaHashCode, comDelheziGa.hashCode());
        assertEquals(intObjHashCode, Integer.valueOf(intVal).hashCode());
        assertEquals(floatObjHashCode, Float.valueOf(floatVal).hashCode());
        assertEquals(doubleObjHashCode, Double.valueOf(doubleVal).hashCode());
        assertEquals(innerHashCode, inner.hashCode());
        assertEquals(innerHashCode, innerHashCode2);
        assertEquals(inner, inner2);
    }

    private class InnerHashCodeTestClass {
        private int intVal;
        private float floatVal;
        private double doubleVal;
        private String stringObj;

        private InnerHashCodeTestClass(int intVal, float floatVal, double doubleVal, String stringObj) {
            this.intVal = intVal;
            this.floatVal = floatVal;
            this.doubleVal = doubleVal;
            this.stringObj = stringObj;
        }

        @Override
        public int hashCode() {
            int result = HashCodeUtil.SEED;
            result = HashCodeUtil.hash(result, intVal);
            result = HashCodeUtil.hash(result, floatVal);
            result = HashCodeUtil.hash(result, doubleVal);
            result = HashCodeUtil.hash(result, stringObj);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof InnerHashCodeTestClass) || obj == null) {
                return false;
            } else {
                InnerHashCodeTestClass that = (InnerHashCodeTestClass) obj;
                return (this.intVal == that.intVal) &&
                        (this.floatVal == that.floatVal) &&
                        (this.doubleVal == that.doubleVal) &&
                        (this.stringObj == null ? that.stringObj == null : this.stringObj.equals(that.stringObj));
            }
        }
    }
}
