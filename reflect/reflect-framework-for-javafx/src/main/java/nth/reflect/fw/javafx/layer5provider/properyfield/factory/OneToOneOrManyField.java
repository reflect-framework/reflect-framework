package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import java.util.Optional;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;

public class OneToOneOrManyField extends TextField {

	public OneToOneOrManyField(PropertyValueModel propertyValueModel) {
		super(propertyValueModel);
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
