package math;
/** Defines a polynomial.
 * @author Ryan Forsyth
 */
public class Polynomial extends Function {
	/** The coefficients. */
	private final double[] COEFFICIENTS;
	/** Constructor for a polynomial.
	 * @param coefficients The coefficients c0, c1, c2 in sum(cn*x^n) - i.e. c2 is the coefficient of the x^2 term
	 */
	public Polynomial(double[] coefficients) {
		COEFFICIENTS = coefficients;
	}
	public double evaluate(double x) {
		double value = 0;
		for (int degree = 0; degree < COEFFICIENTS.length; degree++) {
			value += COEFFICIENTS[degree] * Math.pow(x, degree);
		}
		return value;
	}
}
