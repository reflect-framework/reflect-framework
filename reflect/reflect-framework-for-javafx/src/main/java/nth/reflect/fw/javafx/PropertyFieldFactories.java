package nth.reflect.fw.javafx;

import java.util.ArrayList;

import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.CheckBoxFieldFactory;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.ComboBoxFieldFactory;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.DateTimeFieldFactory;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.ManyToOneOrManyFieldFactory;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.OneToOneOrManyFieldFactory;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.TableFieldFactory;
import nth.reflect.fw.javafx.layer5provider.properyfield.factory.TextFieldFactory;

public class PropertyFieldFactories extends ArrayList<Class<? extends PropertyFieldFactory>> {

	private static final long serialVersionUID = -7261182053881227214L;

	public PropertyFieldFactories() {
		add(TextFieldFactory.class);
		add(CheckBoxFieldFactory.class);
		add(DateTimeFieldFactory.class);
		add(ComboBoxFieldFactory.class);
		add(TableFieldFactory.class);
		add(ManyToOneOrManyFieldFactory.class);
		add(OneToOneOrManyFieldFactory.class);
	}
}
