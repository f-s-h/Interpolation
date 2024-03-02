package interpolation;

public abstract class InterpolationMethod {
    /**
     * Leftmost x-value of the grid
     */
    private double a;
    /**
     * Rightmost x-value of the grid
     */
    private double b;
    /**
     * Distance between two supporting points in x direction
     */
    private double h;
    /**
     * Set of given y-Values, used to create the interpolation function
     */
    private double[] y;

    /**
     * Evaluates the interpolation function in the point z
     * @param z: x-Value, in which the interpolation function should be applied
     * @return y-Value: the result of the interpolation in point z
     */
    public abstract double evaluate(double z);

    /**
     * Checks whether all initialization-values are legal
     */
    private void checkInitParams(double a, double b, double h, double[] y) {
        // Left bound a must be smaller than b
        assert a < b;
        // If the distance between values is 0 or smaller than 0, y is never and the program would not be deterministic
        assert h > 0;
    }

    /**
     * Initializes the interpolation method
     * @param a: Leftmost x-value of the grid
     * @param b: Rightmost x-value of the grid
     * @param h: Distance between supporting points
     * @param y: Set of y values of the supporting points
     */
    public void init(double a, double b, double h, double[] y) {
        checkInitParams(a, b, h, y);
        this.a = a;
        this.b = b;
        this.h = h;
        this.y = y;
    }
}
