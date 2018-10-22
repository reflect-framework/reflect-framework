package nth.reflect.ui.vaadin.tab.form.row;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import nth.reflect.fw.ui.tab.form.FormTab;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

@SuppressWarnings("serial")
public class PropertyPanel extends VerticalLayout implements nth.reflect.fw.ui.tab.form.propertypanel.PropertyPanel<PropertyLabel, PropertyField, PropertyValidationMessages> {

	private final PropertyValueModel propertyValueModel;
	private final PropertyField propertyField;

	public PropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel, PropertyField propertyField) {
		this.propertyValueModel = propertyValueModel;
		this.propertyField = propertyField;

		getThemeList().clear();//remove theme "padding spacing"
		
		add(getPropertyLabel());
		add((Component)propertyField);//FIXME YUK!!!
		add(getPropertyValidationMessages());

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
	public PropertyValidationMessages getPropertyValidationMessages() {
		return new PropertyValidationMessages();
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
		//setTitle(description);
	}



}
