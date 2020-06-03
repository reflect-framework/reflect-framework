package nth.reflect.fw.gui.layer5provider.properyfield;

import java.util.ArrayList;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.CheckBoxFieldFactory;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.ComboBoxFieldFactory;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.DateTimeFieldFactory;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.DomainObjectFieldFactory;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.TableFieldFactory;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.TextFieldFactory;

/**
 * The {@link ReflectFramework} supports the following {@link PropertyField}s:
 * <p>
 * {@insert CheckBoxFieldFactory}
 * <p>
 * {@insert ComboBoxFieldFactory}
 * <p>
 * {@insert DateTimeFieldFactory}
 * <p>
 * {@insert DomainObjectFieldFactory}
 * <p>
 * {@insert TableFieldFactory}
 * <p>
 * {@insert TextFieldFactory}
 * 
 * 
 * @author nilsth
 *
 */
public abstract class PropertyFieldFactoryClasses extends ArrayList<Class<? extends PropertyFieldFactory>> {

	private static final long serialVersionUID = -5769546680785517489L;

	public PropertyFieldFactoryClasses() {
		add(getCheckBoxFieldFactoryClass());
		add(getComboBoxFieldFactoryClass());
		add(getDateTimeFieldFactoryClass());
		add(getDomainObjectFieldFactoryClass());
		add(getTableFieldFactoryClass());
		add(getTextFieldFactoryClass());
	}

	public abstract Class<? extends CheckBoxFieldFactory> getCheckBoxFieldFactoryClass();

	public abstract Class<? extends ComboBoxFieldFactory> getComboBoxFieldFactoryClass();

	public abstract Class<? extends DateTimeFieldFactory> getDateTimeFieldFactoryClass();

	public abstract Class<? extends DomainObjectFieldFactory> getDomainObjectFieldFactoryClass();

	public abstract Class<? extends TableFieldFactory> getTableFieldFactoryClass();

	public abstract Class<? extends TextFieldFactory> getTextFieldFactoryClass();

}
