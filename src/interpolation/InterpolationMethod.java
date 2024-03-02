package interpolation;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class InterpolationMethod {
    /**
     * Leftmost x-value of the grid
     */
    protected double a;
    /**
     * Rightmost x-value of the grid
     */
    protected double b;
    /**
     * Number of intervals
     */
    protected int n;
    /**
     * Distance between two supporting points in x direction (width of intervals)
     */
    protected double h;
    /**
     * Set of given y-Values, used to create the interpolation function
     */
    protected double[] y;

    /**
     * Evaluates the interpolation function in the point z
     *
     * @param z: x-Value, in which the interpolation function should be applied
     * @return y-Value: the result of the interpolation in point z
     */
    public abstract double evaluate(double z);

    /**
     * Checks whether all initialization-values are legal
     */
    private void checkInitParams(double a, double b, double[] y) {
        // Left bound a must be smaller than b
        assert a < b;
    }

    /**
     * Initializes the interpolation method
     *
     * @param a: Leftmost x-value of the grid
     * @param b: Rightmost x-value of the grid
     * @param y: Set of y values of the supporting points
     */
    public void init(double a, double b, double[] y) {
        checkInitParams(a, b, y);
        this.a = a;
        this.b = b;
        this.y = Arrays.copyOf(y, y.length);
        this.n = y.length - 1;
        this.h = (b - a) / n;
    }
}
