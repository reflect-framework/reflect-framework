package nth.reflect.fw.swing.layer5provider.properyfield;

import java.util.ArrayList;

import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.CheckBoxFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.ComboBoxFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.DateTimeFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.ManyToOneOrManyFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.OneToOneOrManyFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.TextFieldFactory;

public class PropertyFieldFactories extends ArrayList<Class<? extends PropertyFieldFactory>> {

	private static final long serialVersionUID = -5838724372387169372L;

	public PropertyFieldFactories() {
		add(TextFieldFactory.class);
		add(CheckBoxFieldFactory.class);
		add(DateTimeFieldFactory.class);
		add(ComboBoxFieldFactory.class);
		add(ManyToOneOrManyFieldFactory.class);
		add(OneToOneOrManyFieldFactory.class);
	}
}
