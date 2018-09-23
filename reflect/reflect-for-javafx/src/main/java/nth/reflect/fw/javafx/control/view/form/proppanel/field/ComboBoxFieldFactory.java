package nth.reflect.fw.javafx.control.view.form.proppanel.field;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldFactory;

public class ComboBoxFieldFactory implements PropertyFieldFactory {

	@Override
	public PropertyField create(FormView formView, PropertyValueModel propertyValueModel) {
		UserInterfaceContainer userInterfaceContainer=formView.getUserInterfaceContainer();
		ReflectionProvider reflectionProvider=userInterfaceContainer.get(ReflectionProvider.class);
		LanguageProvider languageProvider=userInterfaceContainer.get(LanguageProvider.class);
		return new ComboBoxField(propertyValueModel, reflectionProvider, languageProvider);
	}

	@Override
	public boolean canCreateFor(PropertyValueModel propertyValueModel) {
		return propertyValueModel.getPropertyInfo().getFieldMode()==FieldModeType.COMBO_BOX;
	}

}
