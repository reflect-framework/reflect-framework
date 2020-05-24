package nth.reflect.fw.swing.layer5provider.properyfield;

import nth.reflect.fw.swing.layer5provider.properyfield.factory.CheckBoxFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.ComboBoxFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.DateTimeFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.DomainObjectFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.TableFieldFactory;
import nth.reflect.fw.swing.layer5provider.properyfield.factory.TextFieldFactory;

public class PropertyFieldFactoryClasses
		extends nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldFactoryClasses {

	private static final long serialVersionUID = -5838724372387169372L;

	@Override
	public Class<? extends CheckBoxFieldFactory> getCheckBoxFieldFactoryClass() {
		return CheckBoxFieldFactory.class;
	}

	@Override
	public Class<? extends ComboBoxFieldFactory> getComboBoxFieldFactoryClass() {
		return ComboBoxFieldFactory.class;
	}

	@Override
	public Class<? extends DateTimeFieldFactory> getDateTimeFieldFactoryClass() {
		return DateTimeFieldFactory.class;
	}

	@Override
	public Class<? extends DomainObjectFieldFactory> getDomainObjectFieldFactoryClass() {
		return DomainObjectFieldFactory.class;
	}

	@Override
	public Class<? extends TableFieldFactory> getTableFieldFactoryClass() {
		return TableFieldFactory.class;
	}

	@Override
	public Class<? extends TextFieldFactory> getTextFieldFactoryClass() {
		return TextFieldFactory.class;
	}
}
