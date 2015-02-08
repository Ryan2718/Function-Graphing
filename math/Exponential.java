package math;
/** Defines an exponential function.
 * @author Ryan Forsyth
 */
public class Exponential extends Function {
	/** The coefficient of the exponential function. */
	private final double COEFFICIENT;
	/** The base of the exponential function. */
	private final double BASE;
	/** Typical constructor for the exponential function.
	 * @param base The base
	 */
	public Exponential(double base) {
		this(1, base);
	}
	/** General constructor for the exponential function.
	 * @param coefficient The coefficient
	 * @param base The base
	 */
	public Exponential(double coefficient, double base) {
		COEFFICIENT = coefficient;
		BASE = base;
	}
	public double evaluate(double x) {
		return COEFFICIENT * Math.pow(BASE, x);
	}
}
