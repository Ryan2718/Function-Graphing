package math;
/** Defines a Sine function.
 * @author Ryan Forsyth
 */
public class Sine extends Function {
	/** The outside coefficient of the sine function. */
	private final double a;
	/** The inside coefficient of the sine function. */
	private final double b;
	/** The added term inside the argument of the sine function. */
	private final double c;
	/** Constructor for typical sine function. */
	public Sine() {
		this(1, 1, 0);
	}
	/** General constructor for a sine function.
	 * @param a outside coefficient
	 * @param b inside coefficient
	 * @param c added term inside
	 */
	public Sine(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public double evaluate(double x) {
		return a * Math.sin(b * x + c);
	}
}
