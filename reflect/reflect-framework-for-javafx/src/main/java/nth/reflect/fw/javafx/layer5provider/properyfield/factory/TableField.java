package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.javafx.control.table.Table;
import nth.reflect.fw.layer1userinterface.item.Item;

public class TableField extends Table implements nth.reflect.fw.gui.layer5provider.properyfield.factory.TableField {

	public TableField(FormTab formTab, PropertyValueModel propertyValueModel) {
		super(formTab, propertyValueModel);

		getStyleClass().add(StyleSheet.createStyleClassName(TableField.class));
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
		appendStyleGroups(styleSheet, TableField.class, ReflectColorName.CONTENT.BACKGROUND_20(),
				ReflectColorName.CONTENT.BACKGROUND());
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

}
