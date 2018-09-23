package nth.reflect.ui.vaadin.view.form.row;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldFactory;
import nth.reflect.ui.vaadin.view.form.row.field.TextFieldFactory;

public class PropertyPanelFactory extends nth.reflect.fw.ui.view.form.propertypanel.PropertyPanelFactory<PropertyPanel> {

	@Override
	public List<PropertyFieldFactory> createFieldFactories() {
		List<PropertyFieldFactory> fieldFactories=new ArrayList<>();
		fieldFactories.add(new TextFieldFactory());
		return fieldFactories;
	}

	@Override
	public PropertyPanel createPropertyPanel(FormView formView, PropertyValueModel propertyValueModel) {
		PropertyField propertyField = createPropertyField(formView, propertyValueModel);
		return new PropertyPanel(formView, propertyValueModel, propertyField);
	}

	

}
