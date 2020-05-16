package nth.reflect.fw.gui.layer5provider.properyfield;

import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class PropertyFieldFactoryNotFoundException extends TranslatableException {

	private static final long serialVersionUID = 4720848439949333460L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			PropertyFieldFactoryNotFoundException.class.getCanonicalName() + ".message",
			"%s: Could not find a %s for domain object property: %s of type %s");

	public PropertyFieldFactoryNotFoundException(PropertyValueModel propertyValueModel) {
		super(MESSAGE
				.withParameters(PropertyFieldProvider.class.getSimpleName(), PropertyFieldFactory.class.getSimpleName(),
						propertyValueModel.getPropertyInfo(),
						propertyValueModel.getPropertyInfo().getTypeInfo().getType().getCanonicalName()));
	}

}
