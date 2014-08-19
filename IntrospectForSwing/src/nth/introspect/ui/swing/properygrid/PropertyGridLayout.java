package nth.introspect.ui.swing.properygrid;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import nth.introspect.ui.swing.properygrid.PropertyRow.FieldWidth;

public class PropertyGridLayout implements LayoutManager {

	// private static final int MAX_LABEL_WIDTH = 100;
	private static final int SPACING = 3;
	// public static final int DEFAULT_LOW_FIELD_HEIGHT = 14;
	public static final int DEFAULT_HIGH_FIELD_HEIGHT = (int) (10 * new JTextField().getPreferredSize().getHeight());
	// public static final int DEFAULT_ROW_HEIGHT = DEFAULT_LOW_FIELD_HEIGHT + 2 * SPACING;

	private int maxLabelWith;
	private int y;

	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void layoutContainer(Container container) {
		int rowWidth = container.getWidth();
		int x = 0;
		y = 0;
		maxLabelWith = 0;
		int rowHeight = 0;

		// set size and location of property rows
		for (Component propertyPanel : container.getComponents()) {
			rowHeight = (int) propertyPanel.getMaximumSize().getHeight();
			// rowHeight = DEFAULT_ROW_HEIGHT;
			if (propertyPanel instanceof PropertyRow) {
				PropertyRow propertyRow = (PropertyRow) propertyPanel;
				rowHeight = (int) (SPACING + propertyRow.getField().getPreferredSize().getHeight() + SPACING);
				// make a temporary label and replace spaces with underscores to prevent line breaks
				JLabel tempLabel = new JLabel();
				tempLabel.setText(propertyRow.getLabel().getText().replace(" ", "_"));
				tempLabel.setFont(propertyRow.getLabel().getFont());
				maxLabelWith = (int) Math.max(tempLabel.getMinimumSize().getWidth(), maxLabelWith);
			}
			propertyPanel.setLocation(new Point(x, y));
			propertyPanel.setSize(new Dimension(rowWidth, rowHeight));
			// TODO set component border;
			y += rowHeight;
		}
		maxLabelWith += 3 * SPACING;
		// if (maxLabelWith > MAX_LABEL_WIDTH) {
		// maxLabelWith = MAX_LABEL_WIDTH;
		// }

		// set size of lables fields and validators
		x = 0;
		y = 0;
		for (Component propertyPanel : container.getComponents()) {
			rowHeight = propertyPanel.getHeight();
			if (propertyPanel instanceof PropertyRow) {
				PropertyRow propertyRow = (PropertyRow) propertyPanel;
				JLabel label = propertyRow.getLabel();
				label.setLocation(SPACING, SPACING);
				label.setSize(maxLabelWith - 3 * SPACING, rowHeight - 2 * SPACING);
				label.setVerticalAlignment(SwingConstants.TOP);
				// label.setHorizontalAlignment(SwingConstants.RIGHT);

				JPanel fieldAndValidatorPanel = propertyRow.getFieldAndValidatorPanel();
				fieldAndValidatorPanel.setLocation(maxLabelWith, 0);
				fieldAndValidatorPanel.setSize(new Dimension(rowWidth - maxLabelWith, rowHeight));

				Component field = propertyRow.getField();
				Component validator = propertyRow.getValidator();
				if (FieldWidth.half.equals(propertyRow.getFieldWidth())) {
					int halfPanelWidth = fieldAndValidatorPanel.getWidth() / 2;
					field.setLocation(SPACING, SPACING);
					Dimension size = new Dimension(halfPanelWidth - 2 * SPACING, fieldAndValidatorPanel.getHeight() - 2 * SPACING);
					// field.setMinimumSize(size);
					// field.setPreferredSize(size);
					field.setSize(size);
					validator.setLocation(halfPanelWidth + SPACING, SPACING);
					validator.setSize(size);
				} else {
					int panelWidth = fieldAndValidatorPanel.getWidth();
					int minValidatorWidth = (int) validator.getMinimumSize().getWidth();
					int validatorXPos = panelWidth - minValidatorWidth - 2 * SPACING;
					validator.setLocation(validatorXPos, SPACING);
					validator.setSize(new Dimension(minValidatorWidth, fieldAndValidatorPanel.getHeight() - 2 * SPACING));

					field.setLocation(SPACING, SPACING);
					field.setSize(new Dimension(validatorXPos - 2 * SPACING, fieldAndValidatorPanel.getHeight() - 2 * SPACING));
				}
			}
			y += rowHeight;
		}

		// TODO set size of labels,

	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return new Dimension(maxLabelWith * 3, 100);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return new Dimension(maxLabelWith * 3, y);
	}

	@Override
	public void removeLayoutComponent(Component comp) {
	}

}
