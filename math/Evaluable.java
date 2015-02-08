package math;
/** Interface that should be implemented if something is capable of being evaluated at certain points along the real line.
 * @author Ryan Forsyth
 */
public interface Evaluable {
	/** Evaluate at x. 
	 * @param x The value in the domain
	 * @return The value in the range that x is mapped to.
	 */
	public double evaluate(double x);
}
