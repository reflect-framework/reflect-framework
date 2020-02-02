package nth.reflect.ui.vaadin.tab.form.row;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyIconButton;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

@SuppressWarnings("serial")
public class PropertyPanel extends VerticalLayout implements
		nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanel<PropertyLabel, PropertyField, PropertyValidationLabel> {

	private final PropertyValueModel propertyValueModel;
	private final PropertyField propertyField;

	public PropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel, PropertyField propertyField) {
		this.propertyValueModel = propertyValueModel;
		this.propertyField = propertyField;

		getThemeList().clear();// remove theme "padding spacing"

		add(getPropertyLabel());
		add((Component) propertyField);// FIXME YUK!!!
		add(getPropertyValidationLabel());

	}

	@Override
	public PropertyLabel getPropertyLabel() {
		return new PropertyLabel();
	}

	@Override
	public PropertyField getPropertyField() {
		return propertyField;
	}

	@Override
	public PropertyValidationLabel getPropertyValidationLabel() {
		return new PropertyValidationLabel();
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
		// TODO Auto-generated method stub
		return null;
	}

}
