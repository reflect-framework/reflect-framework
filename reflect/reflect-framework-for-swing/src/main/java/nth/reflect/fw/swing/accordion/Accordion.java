package nth.reflect.fw.swing.accordion;

// Import the GUI classes
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A Accordion provides a component that is similar to a JTabbedPane, but
 * instead of maintaining tabs, it uses Outlook-style bars to control the
 * visible component
 */
@SuppressWarnings("serial")
public class Accordion extends JPanel implements ActionListener {
	/**
	 * The top panel: contains the buttons displayed on the top of the Accordion
	 */
	private JPanel topPanel = new JPanel(new GridLayout(1, 1));

	/**
	 * The bottom panel: contains the buttons displayed on the bottom of the
	 * Accordion
	 */
	private JPanel bottomPanel = new JPanel(new GridLayout(1, 1));

	/**
	 * A LinkedHashMap of bars: we use a linked hash map to preserve the order
	 * of the bars
	 */
	private java.util.List<BarInfo> bars = new ArrayList<BarInfo>();

	/**
	 * The currently visible bar
	 */
	private BarInfo visibleBar = null;

	/**
	 * A place-holder for the currently visible component
	 */
	private JComponent visibleComponent = null;

	/**
	 * Creates a new Accordion; after which you should make repeated calls to
	 * addBar() for each bar
	 */
	public Accordion() {
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	public Collection<BarInfo> getBars() {
		return Collections.unmodifiableCollection(bars);
	}

	/**
	 * Adds the specified Bar to the Accordion
	 * 
	 * @param name
	 *            The name of the outlook bar
	 * @param componenet
	 *            The component to add to the bar
	 */
	public void addBar(BarInfo bar) {
		this.bars.add(bar);
		bar.getButton().addActionListener(this);
		if (visibleBar == null) {
			setVisibleBar(bar);
		} else {
			render();
		}
	}

	/**
	 * Adds the specified component to the Accordion and sets the bar's name
	 * 
	 * @param name
	 *            The name of the outlook bar
	 * @param componenet
	 *            The component to add to the bar
	 */
	public void addBar(String name, JComponent component) {
		BarInfo bar = new BarInfo(name, component);
		addBar(bar);
	}

	/**
	 * Adds the specified component to the Accordion and sets the bar's name
	 * 
	 * @param name
	 *            The name of the outlook bar
	 * @param MaterialAppBarIcon
	 *            An MaterialAppBarIcon to display in the outlook bar
	 * @param componenet
	 *            The component to add to the bar
	 */
	public void addBar(String name, Icon icon, JComponent component) {
		BarInfo bar = new BarInfo(name, icon, component);
		addBar(bar);
	}

	/**
	 * Removes the specified bar from the Accordion
	 */
	public void removeBar(BarInfo bar) {
		this.bars.remove(bar);
		render();
	}

	/**
	 * Returns the currently visible bar
	 * 
	 * @return The currently visible bar
	 */
	public BarInfo getVisibleBar() {
		// validate visible bar
		if ((visibleBar == null || !bars.contains(visibleBar))
				&& bars.size() > 0) {
			visibleBar = bars.get(0);
		}
		return visibleBar;
	}

	/**
	 * Programmatically sets the currently visible bar; the visible bar index
	 * must be in the range of 0 to SIZE() - 1
	 * 
	 * @param visibleBar
	 */

	public void setVisibleBar(int index) {
		if (index >= 0 && index < bars.size()) {
			BarInfo bar = bars.get(index);
			setVisibleBar(bar);
		}
	}

	/**
	 * Programmatically sets the currently visible bar; the visible bar index
	 * must be in the range of 0 to SIZE() - 1
	 * 
	 * @param visibleBar
	 */
	public void setVisibleBar(BarInfo visibleBar) {
		if (visibleBar != null) {
			if (!bars.contains(visibleBar)) {
				addBar(visibleBar);
			}
			this.visibleBar = visibleBar;
			render();
			// set focus to visible component
			JComponent component = visibleBar.getComponent();
			component.requestFocus();
		}

	}

	/**
	 * Causes the outlook bar component to rebuild itself; this means that it
	 * rebuilds the top and bottom panels of bars as well as making the
	 * currently selected bar's panel visible
	 */
	public void render() {
		if (visibleBar == null) {
			setVisibleBar(getVisibleBar());// TODO supposed to set focus to
											// visiblebar.getcomponent bus
											// somehow it does not
		} else {
			// get a valid visible bar
			getVisibleBar();
		}

		// Compute how many bars we are going to have where
		int totalBars = this.bars.size();
		int topBars = bars.indexOf(visibleBar) + 1;
		int bottomBars = totalBars - topBars;

		// Render the top bars: remove all components, reset the GridLayout to
		// hold to correct number of bars, add the bars, and "validate" it to
		// cause it to re-layout its components
		this.topPanel.removeAll();
		GridLayout topLayout = (GridLayout) this.topPanel.getLayout();
		topLayout.setRows(topBars);
		for (int i = 0; i < topBars; i++) {
			BarInfo bar = bars.get(i);
			this.topPanel.add(bar.getButton());
		}
		this.topPanel.validate();

		// Render the center component: remove the current component (if there
		// is one) and then put the visible component in the center of this
		// panel
		if (this.visibleComponent != null) {
			this.remove(this.visibleComponent);
		}
		this.visibleComponent = visibleBar.getComponent();
		this.add(visibleComponent, BorderLayout.CENTER);

		// Render the bottom bars: remove all components, reset the GridLayout
		// to
		// hold to correct number of bars, add the bars, and "validate" it to
		// cause it to re-layout its components
		this.bottomPanel.removeAll();
		GridLayout bottomLayout = (GridLayout) this.bottomPanel.getLayout();
		bottomLayout.setRows(bottomBars);
		for (int i = topBars; i < totalBars; i++) {
			BarInfo bar = this.bars.get(i);
			this.bottomPanel.add(bar.getButton());
		}
		this.bottomPanel.validate();

		// Validate all of our components: cause this container to re-layout its
		// subcomponents
		this.validate();
	}

	/**
	 * Invoked when one of our bars is selected
	 */
	public void actionPerformed(ActionEvent e) {
		for (BarInfo barInfo : bars) {
			if (barInfo.getButton() == e.getSource()) {
				setVisibleBar(barInfo);
				return;
			}
		}
	}

	/**
	 * Debug, dummy method
	 */
	public static JPanel getDummyPanel(String name) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel(name, JLabel.CENTER));
		return panel;
	}

	/**
	 * Debug test...
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Accordion Test");
		Accordion outlookBar = new Accordion();
		outlookBar.addBar("One", getDummyPanel("One"));
		outlookBar.addBar("Two", getDummyPanel("Two"));
		outlookBar.addBar("Three", getDummyPanel("Three"));
		outlookBar.addBar("Four", getDummyPanel("Four"));
		outlookBar.addBar("Five", getDummyPanel("Five"));
		outlookBar.setVisibleBar(2);
		frame.getContentPane().add(outlookBar);

		frame.setSize(800, 600);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width / 2 - 400, d.height / 2 - 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Internal class that maintains information about individual Outlook bars;
	 * specifically it maintains the following information:
	 * 
	 * name The name of the bar button The associated JButton for the bar
	 * component The component maintained in the Outlook bar
	 */
	class BarInfo {
		/**
		 * The name of this bar
		 */
		private String name;

		/**
		 * The JButton that implements the Outlook bar itself
		 */
		private JButton button;

		/**
		 * The component that is the body of the Outlook bar
		 */
		private JComponent component;

		/**
		 * Creates a new BarInfo
		 * 
		 * @param name
		 *            The name of the bar
		 * @param component
		 *            The component that is the body of the Outlook Bar
		 */
		public BarInfo(String name, JComponent component) {
			this.name = name;
			this.component = component;
			this.button = new JButton(name);
		}

		/**
		 * Creates a new BarInfo
		 * 
		 * @param name
		 *            The name of the bar
		 * @param MaterialAppBarIcon
		 *            JButton MaterialAppBarIcon
		 * @param component
		 *            The component that is the body of the Outlook Bar
		 */
		public BarInfo(String name, Icon icon, JComponent component) {
			this.name = name;
			this.component = component;
			this.button = new JButton(name, icon);
		}

		/**
		 * Returns the name of the bar
		 * 
		 * @return The name of the bar
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * Sets the name of the bar
		 * 
		 * @param The
		 *            name of the bar
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * Returns the outlook bar JButton implementation
		 * 
		 * @return The Outlook Bar JButton implementation
		 */
		public JButton getButton() {
			return this.button;
		}

		/**
		 * Returns the component that implements the body of this Outlook Bar
		 * 
		 * @return The component that implements the body of this Outlook Bar
		 */
		public JComponent getComponent() {
			return this.component;
		}
	}

}