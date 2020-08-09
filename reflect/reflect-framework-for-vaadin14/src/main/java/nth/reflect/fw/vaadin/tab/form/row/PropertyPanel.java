package nth.reflect.fw.vaadin.tab.form.row;

import java.util.Collections;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyIconButton;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelStyle;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class PropertyPanel extends VerticalLayout implements
		nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanel<PropertyLabel, PropertyField, PropertyValidationLabel> {

	private final PropertyValueModel propertyValueModel;
	private final PropertyField propertyField;
	private final PropertyLabel propertyLabel;
	private final PropertyValidationLabel propertyValidationLabel;

	public PropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel, PropertyField propertyField) {
		this.propertyValueModel = propertyValueModel;
		this.propertyLabel = new PropertyLabel();
		this.propertyField = propertyField;
		this.propertyValidationLabel = new PropertyValidationLabel();

		getThemeList().clear();// remove theme "padding spacing"
		new StyleBuilder().setPadding(0, 0, PropertyPanelStyle.BOTTOM_PADDING, 0).setFor(this);

		add(getPropertyLabel());
		add((Component) propertyField);// FIXME YUK!!!
		add(getPropertyValidationLabel());
	}

	@Override
	public PropertyLabel getPropertyLabel() {
		return propertyLabel;
	}

	@Override
	public PropertyField getPropertyField() {
		return propertyField;
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
		// setTitle(description);
	}

	@Override
	public void setEnabled(Boolean enabled) {
		// TODO update background color (see javadoc overridden method)

	}

	@Override
	public List<PropertyIconButton> getPropertyIconButtons() {
		return Collections.EMPTY_LIST;
	}

}
