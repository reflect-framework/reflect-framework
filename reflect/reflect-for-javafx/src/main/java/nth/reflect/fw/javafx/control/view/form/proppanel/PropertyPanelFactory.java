package nth.reflect.fw.javafx.control.view.form.proppanel;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.javafx.control.view.form.proppanel.field.CharFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.CheckBoxFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.ComboBoxFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.DateFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.DateTimeFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.ManyToOneOrManyFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.NumericFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.OneToOneOrManyFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.PasswordFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.TextAreaFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.TextFieldFactory;
import nth.reflect.fw.javafx.control.view.form.proppanel.field.TimeFieldFactory;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldFactory;

public class PropertyPanelFactory extends nth.reflect.fw.ui.view.form.propertypanel.PropertyPanelFactory<PropertyPanel> {

	@Override
	public List<PropertyFieldFactory> createFieldFactories() {
		List<PropertyFieldFactory> fieldFactories=new ArrayList<>();
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
	public PropertyPanel createPropertyPanel(FormView formView, PropertyValueModel propertyValueModel) {
		PropertyField propertyField = createPropertyField(formView, propertyValueModel);
		return new PropertyPanel((nth.reflect.fw.javafx.control.view.form.FormView) formView,propertyValueModel, propertyField);
	}

}
