package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;

public class ComboBoxFieldFactory extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.ComboBoxFieldFactory {

	@Override
	public Optional<PropertyField> create(PropertyFieldFactoryInfo info) {
		UserInterfaceContainer userInterfaceContainer=info.getUserInterfaceContainer();
		ReflectionProvider reflectionProvider=userInterfaceContainer.get(ReflectionProvider.class);
		LanguageProvider languageProvider=userInterfaceContainer.get(LanguageProvider.class);
		PropertyValueModel propertyValueModel=info.getPropertyValueModel();
		ComboBoxField comboBoxField = new ComboBoxField(propertyValueModel, reflectionProvider, languageProvider);
		return Optional.of(comboBoxField);
	}

}
