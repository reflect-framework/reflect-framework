package nth.reflect.fw.vaadin.layer5provider.properyfield;

import java.util.ArrayList;

import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.vaadin.layer5provider.properyfield.factory.CheckBoxFieldFactory;
import nth.reflect.fw.vaadin.layer5provider.properyfield.factory.TextFieldFactory;
import nth.reflect.fw.vaadin.layer5provider.properyfield.factory.ToDoFieldFactory;

public class PropertyFieldFactoryClasses extends ArrayList<Class<? extends PropertyFieldFactory>> {
	// TODO extends
	// nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldFactoryClasses {

	private static final long serialVersionUID = -8654029784136770594L;

	public PropertyFieldFactoryClasses() {
		add(TextFieldFactory.class);
		add(CheckBoxFieldFactory.class);
		add(ToDoFieldFactory.class);
	}

}