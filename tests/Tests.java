package tests;
import static org.junit.Assert.*;
import org.junit.Test;
import math.Exponential;
import math.MathMethods;
import math.Polynomial;
import math.Sine;
/** Tests for this project.
 * @author Ryan Forsyth
 */
public class Tests {
	/** Tests of the functions. */
	@Test
	public void testFunctions() {
		Exponential e = new Exponential(2);
		double expected = 1024;
		double result = e.evaluate(10);
		assertTrue(doubleEquals(expected, result));
		
		double[] coefficients = {-1, 0, 1}; // -1 + 0x + 1x^2 <=> x^2 - 1
		Polynomial p = new Polynomial(coefficients);
		expected = 0;
		result = p.evaluate(-1);
		assertTrue(doubleEquals(expected, result));
		expected = -1;
		result = p.evaluate(0);
		assertTrue(doubleEquals(expected, result));
		expected = 0;
		result = p.evaluate(1);
		assertTrue(doubleEquals(expected, result));
		expected = 3;
		result = p.evaluate(2);
		assertTrue(doubleEquals(expected, result));
		expected = 15;
		result = p.evaluate(4);
		assertTrue(doubleEquals(expected, result));
		
		Sine s = new Sine();
		expected = 0;
		result = s.evaluate(0);
		assertTrue(doubleEquals(expected, result));
		expected = 1;
		result = s.evaluate(Math.PI / 2);
		assertTrue(doubleEquals(expected, result));
		expected = 0;
		result = s.evaluate(Math.PI);
		assertTrue(doubleEquals(expected, result));
		expected = -1;
		result = s.evaluate(3 * Math.PI / 2);
		assertTrue(doubleEquals(expected, result));
	}
	/** Tests the numerical derivatives. */
	@Test
	public void testNumericalDerivatives() {
		double h = Math.pow(2, -10);
		
		Exponential e = new Exponential(2);
		// d(2^x)/dx = 2^x * ln(2)
		double x = 0;
		double expected = Math.pow(2, x) * Math.log(2);
		double result = e.forwardDifference(x, h);
		assertTrue(doubleEquals(expected, result, .01));
		
		double[] coefficients = {-1, 0, 1}; // -1 + 0x + 1x^2 <=> x^2 - 1
		Polynomial p = new Polynomial(coefficients);
		// d(x^2 - 1)/dx = 2x
		x = -1;
		expected = 2*x;
		result = p.forwardDifference(x, h);
		assertTrue(doubleEquals(expected, result, .01));
		x = 0;
		expected = 2*x;
		result = p.forwardDifference(x, h);
		assertTrue(doubleEquals(expected, result, .01));
		x = 1;
		expected = 2*x;
		result = p.forwardDifference(x, h);
		assertTrue(doubleEquals(expected, result, .01));
		
		Sine s = new Sine();
		// d(sin(x))/dx = cos(x)
		x = 0;
		expected = Math.cos(x);
		result = s.forwardDifference(x, h);
		assertTrue(doubleEquals(expected, result, .01));
		x = Math.PI / 2;
		expected = Math.cos(x);
		result = s.forwardDifference(x, h);
		assertTrue(doubleEquals(expected, result, .01));
		x = Math.PI;
		expected = Math.cos(x);
		result = s.forwardDifference(x, h);
		assertTrue(doubleEquals(expected, result, .01));
		x = 3 * Math.PI / 2;
		expected = Math.cos(x);
		result = s.forwardDifference(x, h);
		assertTrue(doubleEquals(expected, result, .01));
	}
	/** Tests the numerical integrals. */
	@Test
	public void testNumericalIntegrals() {
		double a = 0;
		double b = 1;
		int numIntervals = 1000;
		
		Exponential e = new Exponential(2);
		// int(2^x) = (2^x) / ln(2) + C
		double expected = Math.pow(2, b) / Math.log(2);
		expected -= Math.pow(2, a) / Math.log(2);
		assertTrue(doubleEquals(expected, 1.4427, .01));
		double result = e.leftRiemannSum(a, b, numIntervals);
		assertTrue(doubleEquals(expected, result, .01));
		
		double[] coefficients = {-1, 0, 1}; // -1 + 0x + 1x^2 <=> x^2 - 1
		Polynomial p = new Polynomial(coefficients);
		// int(x^2 - 1) = (1/3)*x^3 - x + C
		expected = (1.0 / 3.0) * Math.pow(b, 3) - b;
		expected -= (1.0 / 3.0) * Math.pow(a, 3) - a;
		assertTrue(doubleEquals(expected, -2.0 / 3.0, .01));
		result = p.leftRiemannSum(a, b, numIntervals);
		assertTrue(doubleEquals(expected, result, .01));
		
		Sine s = new Sine();
		// int(sin(x)) = -cos(x) + C
		expected = -Math.cos(b);
		expected -= -Math.cos(a);
		assertTrue(doubleEquals(expected, 0.45970, .01));
		result = s.leftRiemannSum(a, b, numIntervals);
		assertTrue(doubleEquals(expected, result, .01));
		
	}
	/** Tests math tools */
	@Test
	public void testMathTools() {
		assertTrue(MathMethods.factorial(0) == 1);
		assertTrue(MathMethods.factorial(1) == 1);
		assertTrue(MathMethods.factorial(4) == 24);
		
		assertTrue(MathMethods.fibonacci(0) == 1);
		assertTrue(MathMethods.fibonacci(1) == 1);
		assertTrue(MathMethods.fibonacci(2) == 2);
		assertTrue(MathMethods.fibonacci(3) == 3);
		assertTrue(MathMethods.fibonacci(4) == 5);
		assertTrue(MathMethods.fibonacci(5) == 8);
		
		assertTrue(doubleEquals(MathMethods.eApproximation(Integer.MAX_VALUE), Math.E, .001));
	}
	/** Using == with doubles can be dangerous due to floating point arithmetic. Therefore, we implement our own method
	 * to determine if two double values are equal.
	 * @param d1 the first double
	 * @param d2 the second double
	 * @return true if the values are within machine epsilon of one another and false otherwise.
	 */
	private static boolean doubleEquals(double d1, double d2) {
		return doubleEquals(d1, d2, getMachineEpsilon());
	}
	/** Using == with doubles can be dangerous due to floating point arithmetic. Therefore, we implement our own method
	 * to determine if two double values are equal.
	 * @param d1 the first double
	 * @param d2 the second double
	 * @param epsilon the epsilon value
	 * @return true if the values are within machine epsilon of one another and false otherwise.
	 */
	private static boolean doubleEquals(double d1, double d2, double epsilon) {
		return (Math.abs(d1 - d2) < epsilon);
	}
	/** Returns the epsilon value for this machine. Code taken directly from the Wikipedia page "Machine epsilon"
	 * @return the double value for machine epsilon
	 */
	private static double getMachineEpsilon() {
		double machineEpsilon = 1.0;
		while ((1.0 + (machineEpsilon / 2.0)) != 1.0)
			machineEpsilon /= 2.0;
		return machineEpsilon;
	}
}
