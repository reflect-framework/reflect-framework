package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.util.Optional;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.layer1userinterface.item.Item;

public class OneToOneOrManyField extends TextField {

	public OneToOneOrManyField(PropertyFieldFactoryInfo info) {
		super(info.getPropertyValueModel());
		setEnabled(false);
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.FULL;
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

	/**
	 * {@link OneToOneOrManyField} is not editable for now (only via
	 * {@link PropertyActionMethod}s).
	 */
	@SuppressWarnings("restriction")
	@Override
	public void setEnabled(boolean enabled) {
		setEditable(false);
	}

	/**
	 * {@link OneToOneOrManyField} is not editable for now, so propertyValue is not
	 * set via onChangeListener (only via {@link PropertyActionMethod}s).
	 */
	@Override
	public void onChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	}

}
