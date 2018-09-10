package nth.reflect.fw.ui.view;

import java.util.List;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.generic.translatablestring.ReflectTranslatable;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public abstract class FormFieldFactory<FORM_FIELD extends FormField> {

	@ReflectTranslatable
	private static final String COULD_NOT_FIND_A_S_FOR_DOMAIN_OBJECT_PROPERTY_S = "%s could not find a %s for domain object property: %s";

	public abstract List<FormFieldSubFactory<FORM_FIELD>> getFormFieldSubFactories();

	public FORM_FIELD createAndUpdate(UserInterfaceContainer userInterfaceContainer, PropertyValueModel propertyValueModel) {
		List<FormFieldSubFactory<FORM_FIELD>> subFactories = getFormFieldSubFactories();
		for (FormFieldSubFactory<FORM_FIELD> formFieldSubFactory : subFactories) {
			if (formFieldSubFactory.canCreate(propertyValueModel)) {
				FORM_FIELD formField = formFieldSubFactory.create(propertyValueModel);
				formField.updateAll();
				return formField;
			}
		}
		throw new ReflectTranslatableException(userInterfaceContainer, COULD_NOT_FIND_A_S_FOR_DOMAIN_OBJECT_PROPERTY_S,
				this.getClass().getCanonicalName(), FormFieldSubFactory.class.getName(), propertyValueModel.getPropertyInfo().getCanonicalName());
	}

}
