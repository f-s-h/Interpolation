package interpolation;

public class NewtonInterpolation extends InterpolationMethod {
    /**
     * Coefficients used to evaluate the
     */
    double[] coefficients;
    /**
     * Grid points
     */
    double[] x;

    /**
     * @param a: Leftmost x-value of the grid
     * @param b: Rightmost x-value of the grid
     * @param y: Set of y values of the supporting points
     */
    @Override
    public void init(double a, double b, double[] y) {
        super.init(a, b, y);
        coefficients = new double[n + 1];
        x = new double[n];
        setGridPoints();
        computeCoefficients();
    }

    /**
     * Sets the grid points
     */
    private void setGridPoints() {
        for (int i = 0; i < n; i++) {
            x[i] = a + i * h;
        }
    }

    /**
     * computes the coefficients, using the triangular scheme
     */
    private void computeCoefficients() {
        double[][] c = new double[n + 1][n + 1];

        // set c_i_0 = y_i
        for (int i = 0; i < n + 1; i++) {
            c[i][0] = y[i];
        }
        // set c_i_k = (c_i+1_k-1 - c_i_k-1)/(x_i+k - x_i)
        // fill the triangular scheme, the coefficients are in c[0][k]
        for (int k = 1; k < n + 1; k++) {
            for (int i = 0; i < n + 1 - k; i++) {
                c[i][k] = (c[i + 1][k - 1] - c[i][k - 1]) / (x[i + k] - x[i]);
            }
        }
        // set the coefficients (coefficients are in c[0][k]
        coefficients = c[0];
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
            double temp_res = coefficients[i];
            for (int j = 0; j < i; j++) {
                temp_res *= (z - x[j]);
            }
            result += temp_res;
        }

        return result;
    }
}
