package nth.reflect.fw.ui.swing.properygrid;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldStyle;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyPanelStyle;
import nth.reflect.fw.ui.swing.tab.form.proppanel.PropertyPanel;

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

		// set SIZE and location of property rows
		for (Component component : container.getComponents()) {
			rowHeight = (int) component.getMaximumSize().getHeight();
			// rowHeight = DEFAULT_ROW_HEIGHT;
			if (component instanceof PropertyPanel) {
				PropertyPanel propertyPanel = (PropertyPanel) component;
				Component propertyField = (Component) propertyPanel.getPropertyField();
				rowHeight = (int) (SPACING + propertyField.getPreferredSize().getHeight() + SPACING);
//				// make a temporary label and replace spaces with underscores to prevent line breaks
//				JLabel tempLabel = new JLabel();
//				tempLabel.setText(propertyPanel.getPropertyLabel().getText().replace(" ", "_"));
//				tempLabel.setFont(propertyPanel.getPropertyLabel().getFont());
//				maxLabelWith = (int) Math.max(tempLabel.getMinimumSize().getWidth(), maxLabelWith);
			}
			component.setLocation(new Point(x, y));
			component.setSize(new Dimension(rowWidth, rowHeight));
			// TODO set component border;
			y += rowHeight;
		}
		maxLabelWith =150+ 3 * SPACING;
		// if (maxLabelWith > MAX_LABEL_WIDTH) {
		// maxLabelWith = MAX_LABEL_WIDTH;
		// }

		// set SIZE of lables fields and validators
		x = 0;
		y = 0;
		for (Component component : container.getComponents()) {
			rowHeight = component.getHeight();
			if (component instanceof PropertyPanel) {
				PropertyPanel propertyPanel = (PropertyPanel) component;
				JLabel label = propertyPanel.getPropertyLabel();
				label.setLocation(SPACING, SPACING);
				label.setSize(maxLabelWith - 3 * SPACING, rowHeight - 2 * SPACING);
				label.setVerticalAlignment(SwingConstants.TOP);
				// label.setHorizontalAlignment(SwingConstants.RIGHT);

				PropertyField field = propertyPanel.getPropertyField();
				Component fieldComponent = (Component) field;
				Component validator = propertyPanel.getPropertyValidationLabel();
				if (PropertyFieldWidth.FULL==field.getPropertyFieldWidth()) {
					int propertyPanelWidth = propertyPanel.getWidth();
					int fieldXPos = maxLabelWith+SPACING;
					int fieldWidth= propertyPanelWidth-SPACING-fieldXPos;

					fieldComponent.setLocation(fieldXPos, SPACING);
					fieldComponent.setSize(new Dimension(fieldWidth, ((Component)field).getPreferredSize().height));
					
					validator.setLocation(fieldXPos, ((Component)field).getPreferredSize().height+SPACING);
					validator.setSize(new Dimension(fieldWidth, validator.getPreferredSize().height));

				} else {
					int fieldXPos = maxLabelWith+SPACING;
					int fieldWidth= PropertyPanelStyle.getMaxSmallWidth();
					
					fieldComponent.setLocation(fieldXPos, SPACING);
					fieldComponent.setSize(new Dimension(fieldWidth,((Component)field).getPreferredSize().height ));
					
					validator.setLocation(fieldXPos, ((Component)field).getPreferredSize().height+SPACING);
					validator.setSize(new Dimension(fieldWidth,validator.getPreferredSize().height ));
									}
			}
			y += rowHeight;
		}

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
