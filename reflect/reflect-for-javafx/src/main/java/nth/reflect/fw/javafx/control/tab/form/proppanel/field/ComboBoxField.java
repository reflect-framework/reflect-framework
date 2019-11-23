package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;

public class ComboBoxField extends RfxToDoField {
TODO fix#99

	public ComboBoxField(PropertyValueModel propertyValueModel, ReflectionProvider reflectionProvider,
			LanguageProvider languageProvider) {
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
