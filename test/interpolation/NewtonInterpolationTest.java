package interpolation;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewtonInterpolationTest {
    final private double DELTA = 1e-15;

    @Test
    public void ConstructorTest() {
        double a = 0.0;
        double b = 1.0;
        double[] y = new double[]{0., 1., 2., 3.,};
        NewtonInterpolation newtonInterpolation = new NewtonInterpolation();
        newtonInterpolation.init(a,b, y);

        double nExpected = 4;
        double hExpected = 0.25;

        double aObserved = newtonInterpolation.a;
        double bObserved = newtonInterpolation.b;
        double nObserved = newtonInterpolation.n;
        double hObserved = newtonInterpolation.h;

        assertEquals(a, aObserved, DELTA);
        assertEquals(b, bObserved, DELTA);
        assertEquals(nExpected, nObserved, DELTA);
        assertEquals(hExpected, hObserved, DELTA);
    }
}