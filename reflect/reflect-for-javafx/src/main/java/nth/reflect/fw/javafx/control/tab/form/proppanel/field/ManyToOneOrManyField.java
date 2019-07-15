package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.javafx.control.table.Table;
import nth.reflect.fw.layer1userinterface.item.Item;

public class ManyToOneOrManyField extends Table implements PropertyField {

	private static final String READ_ONLY_STYLE = "read-only";
	private static final String EDITABLE_STYLE = "editable";

	public ManyToOneOrManyField(FormTab formTab, PropertyValueModel propertyValueModel) {
		super(formTab, propertyValueModel);

		getStyleClass().add(StyleSheet.createStyleClassName(ManyToOneOrManyField.class));
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.FULL;
	}

	@Override
	public void setEnabled(boolean enabled) {
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		this.updateData();
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		appendStyleGroups(styleSheet, ManyToOneOrManyField.class, ReflectColorName.CONTENT.BACKGROUND_20(),
				ReflectColorName.CONTENT.BACKGROUND());
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

}
