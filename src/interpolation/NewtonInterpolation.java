package interpolation;

import java.util.ArrayList;
import java.util.Arrays;

public class NewtonInterpolation extends InterpolationMethod {
    /**
     * Coefficients used to evaluate the
     */
    private ArrayList<Double> coefficients;
    /**
     * Grid points
     */
    private ArrayList<Double> x;
    /**
     * y-Values at grid points
     */
    private ArrayList<Double> y;
    /**
     * Triangular scheme
     */
    private ArrayList<ArrayList<Double>> c;

    /**
     * @param a: Leftmost grid point
     * @param b: Rightmost grid point
     * @param y: Set of y values of the grid points
     */
    @Override
    public void init(double a, double b, double[] y) {
        super.init(a, b, y);
        coefficients = new ArrayList<>();
        this.y = new ArrayList<>();
        this.y.addAll(Arrays.stream(y).boxed().toList());
        setGridPoints();
        computeCoefficients();
    }

    /**
     * Sets the grid points
     */
    private void setGridPoints() {
        x = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            x.add(a + i * h);
        }
    }

    /**
     * computes the coefficients, using the triangular scheme
     */
    private void computeCoefficients() {
        c = new ArrayList<>();
        for(int i = 0; i < n + 1; i++) {
            c.add(new ArrayList<>());
        }

        // set c_i_0 = y_i
        for (int i = 0; i < n + 1; i++) {
            c.get(i).add(y.get(i));
        }
        // set c_i_k = (c_i+1_k-1 - c_i_k-1)/(x_i+k - x_i)
        // fill the triangular scheme, the coefficients are in c[0][k]
        for (int k = 1; k < n + 1; k++) {
            for (int i = 0; i < n + 1 - k; i++) {
                c.get(i).add((c.get(i + 1).get(k - 1) - c.get(i).get(k - 1)) / (x.get(i + k) - x.get(i)));
            }
        }
        // set the coefficients (coefficients are in c[0][k]
        coefficients = c.get(0);
    }

    /**
     * Extends the newton interpolation with a new grid point
     * @param newX new x-value
     * @param newY new y-value
     */
    public void extendNewtonInterpolation(double newX, double newY) {
        // Extend triangular scheme
        c.add(new ArrayList<>());
        // Add new grid point
        x.add(newX);
        y.add(newY);
        n++;

        // add new y value at c_n_0
        c.get(n).add(newY);

        // extend triangular scheme, only the values on the new diagonal need to be set
        for(int i = n - 1; n >= 0; n--) {
            c.get(n-i).add((c.get(i + 1).get(n - 1) - c.get(i).get(n - 1)) / (x.get(i + n) - x.get(i)));
        }

    }

    /**
     * Evaluation of the newton interpolation in the point z
     * p(z) = coefficient_0 + coefficient_1 * (x - x_0) + ... + coefficient_n * \prod\limits_{i = 0}^{n-1}(x - x_i)
     *
     * @param z: x-Value, in which the interpolation function should be applied
     * @return evaluation result of the point z
     */
    @Override
    public double evaluate(double z) {
        double result = 0.;

        for (int i = 0; i < n + 1; i++) {
            double temp_res = coefficients.get(i);
            for (int j = 0; j < i; j++) {
                temp_res *= (z - x.get(j));
            }
            result += temp_res;
        }

        return result;
    }

    public ArrayList<Double> getCoefficients() {
        return coefficients;
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public ArrayList<Double> getY() {
        return y;
    }

    public ArrayList<ArrayList<Double>> getC() {
        return c;
    }
}
