package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldFactory;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;

public class ComboBoxFieldFactory implements PropertyFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		UserInterfaceContainer userInterfaceContainer=formTab.getUserInterfaceContainer();
		ReflectionProvider reflectionProvider=userInterfaceContainer.get(ReflectionProvider.class);
		LanguageProvider languageProvider=userInterfaceContainer.get(LanguageProvider.class);
		return new ComboBoxField(propertyValueModel, reflectionProvider, languageProvider);
	}

	@Override
	public boolean canCreateFor(PropertyValueModel propertyValueModel) {
		return propertyValueModel.getPropertyInfo().getFieldMode()==FieldModeType.COMBO_BOX;
	}

}
