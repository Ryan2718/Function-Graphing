package math;
/** A few static methods that are math-related.
 * @author Ryan Forsyth
 */
public class MathMethods {
	/** factorial(n) = n! = n * (n-1) * (n-2) * ... * 3 * 2 * 1
	 * Note that 0! = 1
	 * @param n The argument
	 * @return n!
	 * @throws ArithmeticException if n < 0
	 */
	public static int factorial(int n) {
		if (n < 0) {
			throw new ArithmeticException();
		} else if (n == 0) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}
	/** Fibonacci Sequence = 1, 1, 2, 3, 5, 8, 13.
	 * Each term is the sum of the previous two, with the first two terms being 1
	 * Begins indexing with 0. 
	 * fibonacci(0) = 1
	 * fibonacci(1) = 1
	 * fibonacci(2) = 2;
	 * fibonnaci(3) = 3;
	 * And so on
	 * @param n The argument
	 * @return the n-th Fibonacci Number when indexed beginning at 0
	 */
	public static int fibonacci(int n) {
		if (n < 0) {
			throw new ArithmeticException();
		} else if (n == 0 || n == 1) {
			return 1;
		} else {
			return fibonacci(n - 2) + fibonacci(n - 1);
		}
	}
	/** The number e can be approximated by (1 + 1/n)^n for large n
	 * @param n The choice of n. The larger n is, the closer the approximation to e
	 * @return The approximation to Euler's Number e
	 */
	public static double eApproximation(int n) {
		double base = 1 + (1.0 / n);
		return Math.pow(base, n);
	}
}
