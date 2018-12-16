package nth.reflect.fw.javafx.control.tab.form.proppanel;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.BorderPane;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.tab.form.FormTab;
import nth.reflect.fw.javafx.control.tab.form.PropertiesPanel;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.style.component.PropertyPanelStyle;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class PropertyPanel extends BorderPane implements
		nth.reflect.fw.ui.tab.form.propertypanel.PropertyPanel<PropertyLabel, PropertyField, PropertyValidationLabel> {

	private final PropertyValidationLabel propertyValidationLabel;
	private final PropertyValueModel propertyValueModel;
	private final FormTab formTab;
	private final PropertyLabelAndFieldPanel labelAndFieldPanel;

	public PropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel, PropertyField propertyField) {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(PropertyPanel.class));

		labelAndFieldPanel = new PropertyLabelAndFieldPanel(formTab, propertyValueModel, propertyField);
		setCenter(labelAndFieldPanel);

		this.formTab = formTab;
		this.propertyValueModel = propertyValueModel;

		propertyValidationLabel = new PropertyValidationLabel();
		setBottom(propertyValidationLabel);

		setWidth(propertyField.getPropertyFieldWidth());
	}

	private void setWidth(PropertyFieldWidth propertyFieldWidth) {
		switch (propertyFieldWidth) {
		case FULL:
			DoubleBinding fullWidth = formTab.widthProperty().subtract(2 * PropertiesPanel.HORIZONTAL_GAP);
			prefWidthProperty().bind(fullWidth);
			break;
		case SMALL:
		default:
			setMaxWidth(PropertyPanelStyle.getMaxSmallWidth());
			setMinWidth(PropertyPanelStyle.getMinSmallWidth());
			break;
		}

	}

	@Override
	public PropertyLabel getPropertyLabel() {
		return labelAndFieldPanel.getPropertyLabel();
	}

	@Override
	public PropertyField getPropertyField() {
		return labelAndFieldPanel.getPropertyField();
	}

	@Override
	public PropertyValidationLabel getPropertyValidationLabel() {
		return propertyValidationLabel;
	}

	@Override
	public PropertyValueModel getPropertyValueModel() {
		return propertyValueModel;
	}

	@Override
	public void setVisible(Boolean visible) {
		super.setVisible(visible);
	}

	@Override
	public void setDescription(String description) {
		// Does nothing: Borderpanel does not have a setToolTip method
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(PropertyPanel.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND());
	}

}
