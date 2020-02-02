package nth.reflect.fw.javafx.control.tab.form.proppanel;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyIconButton;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelStyle;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;

public class PropertyLabelAndFieldPanel extends BorderPane {

	private static final String ENABLED = "enabled";
	private static final String DISABLED = "disabled";
	private final PropertyLabel propertyLabel;
	private final PropertyField propertyField;
	private PropertyButtonBar propertyButtonBar;

	public PropertyLabelAndFieldPanel(FormTab formTab, PropertyValueModel propertyValueModel,
			PropertyField propertyField) {
		propertyLabel = new PropertyLabel();
		setTop(propertyLabel);

		this.propertyField = propertyField;
		setCenter((Node) propertyField);

		propertyButtonBar = new PropertyButtonBar(formTab, propertyValueModel, propertyField);
		setRight(propertyButtonBar);

		setWidth(propertyField.getPropertyFieldWidth());
	}

	private void setWidth(PropertyFieldWidth propertyFieldWidth) {
		switch (propertyFieldWidth) {
		case FULL:
			setWidth(USE_COMPUTED_SIZE);
			break;
		case SMALL:
		default:
			setMaxWidth(PropertyPanelStyle.MAX_SMALL_WIDTH);
			setMinWidth(PropertyPanelStyle.MIN_SMALL_WIDTH);
			break;
		}

	}

	public PropertyLabel getPropertyLabel() {
		return propertyLabel;
	}

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(PropertyLabelAndFieldPanel.class, ENABLED)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND_20())
				.setProperty("-fx-background-radius", PropertyPanelStyle.BACKGROUND_RADIUS + "px")
				.setPadding(0, PropertyPanelStyle.PADDING_LEFT_RIGHT, 0, PropertyPanelStyle.PADDING_LEFT_RIGHT);

		styleSheet.addStyleGroup(StyleSelector.createFor(PropertyLabelAndFieldPanel.class, DISABLED)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND()).setProperty("-fx-background-radius", "0px")
				.setPadding(0, PropertyPanelStyle.PADDING_LEFT_RIGHT, 0, PropertyPanelStyle.PADDING_LEFT_RIGHT);

	}

	public void setEnabled(Boolean enabled) {
		getStyleClass().clear();
		if (enabled) {
			getStyleClass().add(StyleSheet.createStyleClassName(PropertyLabelAndFieldPanel.class, ENABLED));
		} else {
			getStyleClass().add(StyleSheet.createStyleClassName(PropertyLabelAndFieldPanel.class, DISABLED));
		}

	}

	public List<PropertyIconButton> getPropertyIconButtons() {
		List<PropertyIconButton> buttons = new ArrayList();
		ObservableList<Node> children = propertyButtonBar.getChildren();
		for (Node node : children) {
			if (node instanceof PropertyIconButton) {
				buttons.add((PropertyIconButton) node);
			}
		}
		return buttons;
	}

}
