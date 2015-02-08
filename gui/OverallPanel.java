package gui;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import math.Polynomial;
/** Defines an overall panel.
 * @author Ryan Forsyth
 */
public class OverallPanel extends JPanel {
	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** Constructor for an overall panel. */
	public OverallPanel() {
		super();
		int graphPanelWidth = 1200;
		int graphPanelHeight = 600;
		int controlPanelWidth = graphPanelWidth;
		int controlPanelHeight = 100;
		int overallWidth = graphPanelWidth;
		int overallHeight = graphPanelHeight + controlPanelHeight;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(overallWidth, overallHeight));
		double[] zero = {0};
		try {
			GraphPanel graphPanel = new GraphPanel(-20, 20, -10, 10, 
				graphPanelWidth, graphPanelHeight, new Polynomial(zero));
			ControlPanel controlPanel = new ControlPanel(graphPanel, 
					controlPanelWidth, controlPanelHeight);
			add(controlPanel);
			add(graphPanel);
		} catch (IllegalSizeException e) {
			e.printStackTrace();
		}
	}
}
