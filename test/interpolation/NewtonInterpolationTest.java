package interpolation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class NewtonInterpolationTest {
    final private double DELTA = 1e-15;

    @Test
    public void ConstructorTest() {
        double a = 0.0;
        double b = 1.0;
        double[] y = new double[]{0., 1., 2., 3., 4.};
        NewtonInterpolation newtonInterpolation = new NewtonInterpolation();
        newtonInterpolation.init(a,b, y);

        double nExpected = 4;
        double hExpected = 0.25;
        ArrayList<Double> yExpected = new ArrayList<>(Arrays.stream(y).boxed().toList());

        double aObserved = newtonInterpolation.a;
        double bObserved = newtonInterpolation.b;
        double nObserved = newtonInterpolation.n;
        double hObserved = newtonInterpolation.h;
        ArrayList<Double> yObserved = newtonInterpolation.getY();

        assertEquals(a, aObserved, DELTA);
        assertEquals(b, bObserved, DELTA);
        assertEquals(nExpected, nObserved, DELTA);
        assertEquals(hExpected, hObserved, DELTA);
        assertTrue(yExpected.equals(yObserved));
    }
}