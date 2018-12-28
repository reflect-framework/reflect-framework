package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.table.Table;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.tab.form.FormTab;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class ManyToOneOrManyField extends Table implements PropertyField {


	private static final String READ_ONLY_STYLE = "read-only";
	private static final String EDITABLE_STYLE = "editable";

	public ManyToOneOrManyField(FormTab formTab, PropertyValueModel propertyValueModel) {
		super(formTab, propertyValueModel);
		getStyleClass().add(RfxStyleSheet.createStyleClassName(ManyToOneOrManyField.class));
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
		// TODO Auto-generated method stub
		
	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		appendStyleGroups( styleSheet,ManyToOneOrManyField.class, ReflectColorName.CONTENT.BACKGROUND_20(), ReflectColorName.CONTENT.BACKGROUND());
	}

}
