package math;
/** Defines a random walk.
 * @author Ryan Forsyth
 */
public class RandomWalk implements Evaluable {
	/** The current value. */
	private double value;
	/** The current maximum step size. */
	private double maxStepSize;
	/** Typical constructor for a Random Walk.
	 * @param seed The initial starting point for the random walk
	 */
	public RandomWalk(double seed) {
		this(seed, 1);
	}
	/** General constructor for a Random Walk.
	 * @param seed The initial starting point for the random walk
	 * @param maxStepSize The maximum step size in terms of absolute value
	 */
	public RandomWalk(double seed, double maxStepSize) {
		value = seed;
		this.maxStepSize = maxStepSize;
	}
	/** Change the maximum step size.
	 * @param maxStepSize The new maximum step size
	 */
	public void setMaxStepSize(double maxStepSize) {
		this.maxStepSize = maxStepSize;
	}
	public double evaluate(double x) { // Here we use x simply as a time step - it's not even used in the calculation
		value += (Math.random()*(2*maxStepSize) - maxStepSize); // [-maxStepSize, maxStepSize)
		return value;
	}
}
