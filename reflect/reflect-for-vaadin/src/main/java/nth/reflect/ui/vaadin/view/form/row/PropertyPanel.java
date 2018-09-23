package nth.reflect.ui.vaadin.view.form.row;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;

@SuppressWarnings("serial")
public class PropertyPanel extends VerticalLayout implements nth.reflect.fw.ui.view.form.propertypanel.PropertyPanel<PropertyLabel, PropertyField, PropertyValidationMessages> {

	private final PropertyValueModel propertyValueModel;
	private final PropertyField propertyField;

	public PropertyPanel(FormView formView, PropertyValueModel propertyValueModel, PropertyField propertyField) {
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
