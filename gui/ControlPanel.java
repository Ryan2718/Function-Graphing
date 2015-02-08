package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import math.Exponential;
import math.Polynomial;
import math.RandomWalk;
import math.Sine;
/** Defines a control panel.
 * @author Ryan Forsyth
 */
public class ControlPanel extends JPanel {
	// Self-explanatory variable names
	private static final long serialVersionUID = 1L;
	private final GraphPanel GRAPH;
	
	private final JButton POLYNOMIAL = new JButton("Graph Polynomial");
	private final JTextField C0 = new JTextField("c0");
	private final JTextField C1 = new JTextField("c1");
	private final JTextField C2 = new JTextField("c2");
	
	private final JButton EXPONENTIAL = new JButton("Graph Exponential");
	private final JTextField COEFFICIENT = new JTextField("coefficient");
	private final JTextField BASE = new JTextField("base");
	
	private final JButton SINE = new JButton("Graph Sine");
	private final JLabel SINE_LABEL = new JLabel("a*sin(bx + c)");
	private final JTextField A = new JTextField("a");
	private final JTextField B = new JTextField("b");
	private final JTextField C = new JTextField("c");
	
	private final JButton RANDOM_WALK = new JButton("Random Walk");
	private final JTextField SEED = new JTextField("Seed");
	private final JTextField STEP = new JTextField("Step");
	
	/** Constructor for a control panel.
	 * @param graph The graph being used
	 * @param panelWidth The width of the panel
	 * @param panelHeight The height of the panel
	 */
	public ControlPanel(GraphPanel graph, int panelWidth, int panelHeight) {
		super();
		GRAPH = graph;
		
		add(POLYNOMIAL);
		add(C0);
		add(C1);
		add(C2);
		add(EXPONENTIAL);
		add(COEFFICIENT);
		add(BASE);
		add(SINE);
		add(SINE_LABEL);
		add(A);
		add(B);
		add(C);
		add(RANDOM_WALK);
		add(SEED);
		add(STEP);
		
		ButtonListener buttonListener = new ButtonListener();
		POLYNOMIAL.addActionListener(buttonListener);
		EXPONENTIAL.addActionListener(buttonListener);
		SINE.addActionListener(buttonListener);
		RANDOM_WALK.addActionListener(buttonListener);
		
		setBackground(Color.ORANGE);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(panelWidth, panelHeight));
	}
	/** Defines a class that responds when buttons are pressed.
	 * @author Ryan Forsyth
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			GRAPH.setBackground(Color.WHITE);
			String errorMessage = "Please enter numeric values only";
			if (source == POLYNOMIAL) {
				try {
					double c0 = Double.valueOf(C0.getText());
					double c1 = Double.valueOf(C1.getText());
					double c2 = Double.valueOf(C2.getText());
					double[] coefficients = {c0, c1, c2};
					Polynomial p = new Polynomial(coefficients);
					GRAPH.setEvaluable(p);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, errorMessage);
				}
			} else if (source == EXPONENTIAL) {
				try {
					double coefficient = Double.valueOf(COEFFICIENT.getText());
					double base = Double.valueOf(BASE.getText());
					Exponential e = new Exponential(coefficient, base);
					GRAPH.setEvaluable(e);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, errorMessage);
				}
			} else if (source == SINE) {
				try {
					double a = Double.valueOf(A.getText());
					double b = Double.valueOf(B.getText());
					double c = Double.valueOf(C.getText());
					Sine s = new Sine(a, b, c);
					GRAPH.setEvaluable(s);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, errorMessage);
				}
			} else if (source == RANDOM_WALK) {
				try {
					double seed = Double.valueOf(SEED.getText());
					double step = Double.valueOf(STEP.getText()); // 0.025 is nice
					RandomWalk rw = new RandomWalk(seed, step);
					GRAPH.setEvaluable(rw);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, errorMessage);
				}
			}
		}
	}
}
