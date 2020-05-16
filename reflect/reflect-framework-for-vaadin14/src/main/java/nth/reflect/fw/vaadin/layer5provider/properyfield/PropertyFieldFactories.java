package nth.reflect.fw.vaadin.layer5provider.properyfield;

import java.util.ArrayList;

import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.vaadin.layer5provider.properyfield.factory.CheckBoxFieldFactory;
import nth.reflect.fw.vaadin.layer5provider.properyfield.factory.TextFieldFactory;
import nth.reflect.fw.vaadin.layer5provider.properyfield.factory.ToDoFieldFactory;

public class PropertyFieldFactories extends ArrayList<Class<? extends PropertyFieldFactory>> {

	private static final long serialVersionUID = -8654029784136770594L;

	public PropertyFieldFactories() {
		add(TextFieldFactory.class);
		add(CheckBoxFieldFactory.class);
		add(ToDoFieldFactory.class);
		// TODO add(DateTimeFieldFactory.class);
		// TODO add(ComboBoxFieldFactory.class);
		// TODO add(TableFieldFactory.class);
		// TODO add(ManyToOneOrManyFieldFactory.class);
		// TODO add(OneToOneOrManyFieldFactory.class);
	}
}