package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.ui.component.tab.form.DateTimeMode;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class DateTimeField extends RfxToDoField {

	public DateTimeField(PropertyValueModel propertyValueModel, DateTimeMode mode) {
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
