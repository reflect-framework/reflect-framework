package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldModeType;

public class DateTimeField extends ToDoField
		implements nth.reflect.fw.gui.layer5provider.properyfield.factory.DateTimeField {

	public DateTimeField(PropertyValueModel propertyValueModel, DateTimeFieldModeType mode) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public Optional<Item> getSelectionItem() {
		Item itemOpenListBox = new Item(null);// TODO
		return Optional.of(itemOpenListBox);
	}

}
