package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import math.Evaluable;
/** Defines a piece of virtual graph paper.
 * @author Ryan Forsyth
 */
public class GraphPanel extends JPanel {
	/** Default serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** Minimum X value */
	private final int X_MIN;
	/** Maximum X value */
	private final int X_MAX;
	/** Size of 1 unit in the X direction */
	private final double X_UNIT;
	/** Minimum Y value */
	private final int Y_MIN;
	/** Maximum Y value */
	private final int Y_MAX;
	/** Size of 1 unit in the Y direction */
	private final double Y_UNIT;
	/** The height of the panel. */
	private final int PANEL_WIDTH;
	/** The width of the panel. */
	private final int PANEL_HEIGHT;
	/** The Evaluable to be evaluated. */
	private Evaluable evaluable;
	/** Constructor for a graph panel.
	 * @param xMin The minimum x value to be shown on the graph
	 * @param xMax The maximum x value to be shown on the graph
	 * @param yMin The minimum y value to be shown on the graph
	 * @param yMax The maximum y value to be shown on the graph
	 * @param evaluable The Evaluable to be evaluated
	 * @throws IllegalSizeException if xMin > xMax or yMin > yMax
	 */
	public GraphPanel(int xMin, int xMax, int yMin, int yMax, int panelWidth,
			int panelHeight, Evaluable evaluable) throws IllegalSizeException {
		super();
		this.X_MIN = xMin;
		this.X_MAX = xMax;
		int width = X_MAX - X_MIN;
		if (width < 0) {
			throw new IllegalSizeException("Width " + width + " is negative.");
		}
		this.Y_MIN = yMin;
		this.Y_MAX = yMax;
		int height = Y_MAX - Y_MIN;
		if (height < 0) {
			throw new IllegalSizeException("Height " + height + "is negative.");
		}
		PANEL_WIDTH = panelWidth;
		PANEL_HEIGHT = panelHeight;
		this.X_UNIT = (double) PANEL_WIDTH/width;
		this.Y_UNIT = (double) PANEL_HEIGHT/height;
		this.evaluable = evaluable;
		setBackground(Color.white);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}
	public void setEvaluable(Evaluable evaluable) {
		this.evaluable = evaluable;
		repaint();
	}
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		final double INTERVAL_WIDTH = 0.0001;
		double xCoordinate;
		double yCoordinate;
		page.setColor(Color.black);
		for (double xValue = X_MIN; xValue <= X_MAX; xValue += INTERVAL_WIDTH) {
			xCoordinate = xValue;
			yCoordinate = 0;
			drawPoint(page, xCoordinate, yCoordinate);	
		}
		for (double yValue = Y_MIN; yValue <= Y_MAX; yValue += INTERVAL_WIDTH) {
			yCoordinate = yValue;
			xCoordinate = 0;
			drawPoint(page, xCoordinate, yCoordinate);
		}
		page.setColor(Color.red);
		for (double xValue = X_MIN; xValue <= X_MAX; xValue += INTERVAL_WIDTH) {
			xCoordinate = xValue;
			yCoordinate = evaluable.evaluate(xCoordinate);
			drawPoint(page, xCoordinate, yCoordinate);	
		}
	}
	/** Draw a point on the panel.
	 * @param page The page object
	 * @param xCoordinate The x coordinate
	 * @param yCoordinate The y coordinate
	 */
	private void drawPoint(Graphics page, double xCoordinate, 
			double yCoordinate) {
		final double X_DISTANCE = xCoordinate - X_MIN;
		/* Columns are counted from the left */
		final double Y_DISTANCE = Y_MAX - yCoordinate;
		/* Rows are counted from the top */
		final int SIZE = 3;
		final int X_POSITION = (int) (X_DISTANCE*X_UNIT);
		final int Y_POSITION = (int) (Y_DISTANCE*Y_UNIT);
		page.fillRect(X_POSITION, Y_POSITION, SIZE, SIZE);
	}
}
