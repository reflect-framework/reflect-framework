package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldFactoryNotFoundException;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;

public class ComboBoxFieldFactory extends nth.reflect.fw.gui.layer5provider.properyfield.factory.ComboBoxFieldFactory {

	private final ProviderContainer container;

	public ComboBoxFieldFactory(ProviderContainer container) {
		this.container = container;
	}

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel)
			throws PropertyFieldFactoryNotFoundException {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		LanguageProvider languageProvider = container.get(LanguageProvider.class);
		ComboBoxField comboBoxField = new ComboBoxField(propertyValueModel, reflectionProvider, languageProvider);
		return comboBoxField;
	}

}
