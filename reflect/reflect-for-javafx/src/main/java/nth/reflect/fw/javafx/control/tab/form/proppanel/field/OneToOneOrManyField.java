package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;

public class OneToOneOrManyField extends RfxToDoField {

	public OneToOneOrManyField(FormTab formTab, PropertyValueModel propertyValueModel) {

	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.FULL;
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}
}
