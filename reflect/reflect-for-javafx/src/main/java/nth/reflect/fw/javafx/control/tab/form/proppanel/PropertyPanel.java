package nth.reflect.fw.javafx.control.tab.form.proppanel;

import javafx.scene.layout.BorderPane;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyPanelStyle;
import nth.reflect.fw.ui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.style.ReflectColorName;

/**
 * A {@link PropertyPanel} displays or edits a {@link DomainObjectProperty}. It
 * contains::<br>
 * <ul>
 * <li>a label that represents the {@link DomainObjectProperty} name (above
 * field for narrow window, left of field for wide window)</li>
 * <li>a field to display or edit the {@link DomainObjectProperty} value</li>
 * <li>a button to open a context menu with
 * {@link DomainObjectPropertyActionMethod}s</li>
 * </ul>
 * For styling see <a href=
 * "https://material.io/guidelines/components/text-fields.html#text-fields-layout">Google
 * Material Design Guidelines</a>
 * 
 */
public class PropertyPanel extends BorderPane implements
		nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyPanel<PropertyLabel, PropertyField, PropertyValidationLabel> {

	private final PropertyValidationLabel propertyValidationLabel;
	private final PropertyValueModel propertyValueModel;
	private final PropertyLabelAndFieldPanel labelAndFieldPanel;

	public PropertyPanel(nth.reflect.fw.ui.component.tab.form.FormTab formTab, PropertyValueModel propertyValueModel,
			PropertyField propertyField) {
		getStyleClass().add(StyleSheet.createStyleClassName(PropertyPanel.class));

		labelAndFieldPanel = new PropertyLabelAndFieldPanel(formTab, propertyValueModel, propertyField);
		setCenter(labelAndFieldPanel);

		this.propertyValueModel = propertyValueModel;

		propertyValidationLabel = new PropertyValidationLabel();
		setBottom(propertyValidationLabel);

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

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(PropertyPanel.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND());
	}

	@Override
	public void setEnabled(Boolean enabled) {
		labelAndFieldPanel.setEnabled(enabled);
		System.out.println(enabled);
	}

}
