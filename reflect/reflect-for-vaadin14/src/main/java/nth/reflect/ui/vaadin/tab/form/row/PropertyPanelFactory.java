package nth.reflect.ui.vaadin.tab.form.row;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldFactory;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.ui.vaadin.tab.form.row.field.CheckBoxFieldFactory;
import nth.reflect.ui.vaadin.tab.form.row.field.TextFieldFactory;
import nth.reflect.ui.vaadin.tab.form.row.field.ToDoFieldFactory;

public class PropertyPanelFactory extends nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory<PropertyPanel> {

	@Override
	public List<PropertyFieldFactory> createFieldFactories() {
		List<PropertyFieldFactory> fieldFactories=new ArrayList<>();
		fieldFactories.add(new TextFieldFactory());
		fieldFactories.add(new CheckBoxFieldFactory());
		fieldFactories.add(new ToDoFieldFactory());
		return fieldFactories;
	}

	@Override
	public PropertyPanel createPropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyField propertyField = createPropertyField(formTab, propertyValueModel);
		return new PropertyPanel(formTab, propertyValueModel, propertyField);
	}

	

}
