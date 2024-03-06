package interpolation;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class NewtonInterpolationTest {
    final private double DELTA = 1e-15;

    @Test
    public void constructorTest() {
        double a = 0.0;
        double b = 1.0;
        double[] y = new double[]{0., 1., 2., 3., 4.};
        double[] x = new double[]{0, 0.25, 0.5, 0.75, 1.};

        NewtonInterpolation newtonInterpolation = new NewtonInterpolation();
        newtonInterpolation.init(a,b, y);

        double nExpected = 4;
        double hExpected = 0.25;
        ArrayList<Double> yExpected = new ArrayList<>(Arrays.stream(y).boxed().toList());
        ArrayList<Double> xExpected = new ArrayList<>(Arrays.stream(x).boxed().toList());

        double aObserved = newtonInterpolation.a;
        double bObserved = newtonInterpolation.b;
        double nObserved = newtonInterpolation.n;
        double hObserved = newtonInterpolation.h;
        ArrayList<Double> yObserved = newtonInterpolation.getY();
        ArrayList<Double> xObserved = newtonInterpolation.getX();

        assertEquals(a, aObserved, DELTA);
        assertEquals(b, bObserved, DELTA);
        assertEquals(nExpected, nObserved, DELTA);
        assertEquals(hExpected, hObserved, DELTA);
        assertTrue(yExpected.equals(yObserved));
        assertTrue(xExpected.equals(xObserved));
    }




}