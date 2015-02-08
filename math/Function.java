package math;
/** Abstract class that defines what a function is.
 * @author Ryan Forsyth
 */
public abstract class Function implements Evaluable {
	
	// Numerical Integration
	// See http://en.wikipedia.org/wiki/Riemann_sum
	
	/** Numerical Integration using a Left Riemann Sum.
	 * @param a The left end point
	 * @param b The right end point
	 * @param numIntervals The number of subintervals
	 * @return An approximation of the definite integral of the function from a to b
	 */
	public double leftRiemannSum(double a, double b, int numIntervals) {
		double intervalLength = (b - a) / numIntervals;
		double leftRiemannSum = 0;
		for (int interval = 0; interval < numIntervals; interval++) {
			leftRiemannSum += evaluate(a) * intervalLength;
			a += intervalLength;
		}
		return leftRiemannSum;
	}
	/** Numerical Integration using a Right Riemann Sum.
	 * @param a The left end point
	 * @param b The right end point
	 * @param numIntervals The number of subintervals
	 * @return An approximation of the definite integral of the function from a to b
	 */
	public double rightRiemannSum(double a, double b, int numIntervals) {
		double intervalLength = (b - a) / numIntervals;
		double rightRiemannSum = 0;
		for (int interval = 0; interval < numIntervals; interval++) {
			a += intervalLength;
			rightRiemannSum += evaluate(a) * intervalLength;
		}
		return rightRiemannSum;
	}
	/** Numerical Integration using a Middle Riemann Sum.
	 * @param a The left end point
	 * @param b The right end point
	 * @param numIntervals The number of subintervals
	 * @return An approximation of the definite integral of the function from a to b
	 */
	public double middleRiemannSum(double a, double b, int numIntervals) {
		double intervalLength = (b - a) / numIntervals;
		double leftRiemannSum = 0;
		double i = a;
		double j = a + intervalLength;
		for (int interval = 0; interval < numIntervals; interval++) {
			double x = (1.0 / 2.0) * (i + j);
			leftRiemannSum += evaluate(x) * intervalLength;
			i += intervalLength;
			j += intervalLength;
		}
		return leftRiemannSum;
	}
	/** Numerical Integration using a Trapezoidal Riemann Sum.
	 * @param a The left end point
	 * @param b The right end point
	 * @param numIntervals The number of subintervals
	 * @return An approximation of the definite integral of the function from a to b
	 */
	public double trapezoidalRiemannSum(double a, double b, int numIntervals) {
		return (leftRiemannSum(a, b, numIntervals) + 
				rightRiemannSum(a, b, numIntervals)) / 2.0;
	}
	
	// Numerical Differentiation
	// See http://en.wikipedia.org/wiki/Difference_quotient
	
	/** Numerical differentiation using a forward difference
	 * @param x The point the function is being differentiated at
	 * @param h The approximation to 0
	 * @return An approximation of the derivative of the function at x
	 */
	public double forwardDifference(double x, double h) {
		double f1 = evaluate(x);
		double f2 = evaluate(x + h);
		return (f2 - f1) / h;
	}
	/** Numerical differentiation using a central difference
	 * @param x The point the function is being differentiated at
	 * @param h The approximation to 0
	 * @return An approximation of the derivative of the function at x
	 */
	public double centralDifference(double x, double h) {
		double f1 = evaluate(x + (h / 2.0));
		double f2 = evaluate(x - (h / 2.0));
		return (f2 - f1) / h;
	}
	/** Numerical differentiation using a backward difference
	 * @param x The point the function is being differentiated at
	 * @param h The approximation to 0
	 * @return An approximation of the derivative of the function at x
	 */
	public double backwardDifference(double x, double h) {
		double f1 = evaluate(x);
		double f2 = evaluate(x - h);
		return (f2 - f1) / h;
	}
	
}
