package nth.reflect.fw.javafx.control.tab.form.proppanel;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.javafx.control.tab.form.proppanel.field.CharFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.CheckBoxFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.ComboBoxFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.DateFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.DateTimeFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.ManyToOneOrManyFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.NumericFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.OneToOneOrManyFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.PasswordFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.TextAreaFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.TextFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.TimeFieldFactory;
import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldFactory;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class PropertyPanelFactory extends nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyPanelFactory<PropertyPanel> {

	@Override
	public List<PropertyFieldFactory> createFieldFactories() {
		List<PropertyFieldFactory> fieldFactories = new ArrayList<>();
		fieldFactories.add(new TextFieldFactory());
		fieldFactories.add(new PasswordFieldFactory());
		fieldFactories.add(new TextAreaFieldFactory());
		fieldFactories.add(new CheckBoxFieldFactory());
		fieldFactories.add(new NumericFieldFactory());
		fieldFactories.add(new CharFieldFactory());
		fieldFactories.add(new TimeFieldFactory());
		fieldFactories.add(new DateFieldFactory());
		fieldFactories.add(new DateTimeFieldFactory());
		fieldFactories.add(new ComboBoxFieldFactory());
		fieldFactories.add(new OneToOneOrManyFieldFactory());
		fieldFactories.add(new ManyToOneOrManyFieldFactory());
		return fieldFactories;
	}

	@Override
	public PropertyPanel createPropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyField propertyField = createPropertyField(formTab, propertyValueModel);
		return new PropertyPanel(propertyValueModel, propertyField);
	}

}
