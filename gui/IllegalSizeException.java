package gui;
/** Defines an exception for something being an illegal size.
 * @author Ryan Forsyth
 */
public class IllegalSizeException extends Exception {
	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;
	public IllegalSizeException(String message) {
		super(message);
	}
}
