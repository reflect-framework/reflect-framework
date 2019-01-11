package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;

public class TextAreaField extends RfxToDoField {

	public TextAreaField(PropertyValueModel propertyValueModel) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

}
