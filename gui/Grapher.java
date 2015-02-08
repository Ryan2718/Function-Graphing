package gui;
import javax.swing.JFrame;
/** Defines a Grapher.
 * @author Ryan Forsyth
 */
public class Grapher {
	/** The main method to run the program.
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Graph Paper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new OverallPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
