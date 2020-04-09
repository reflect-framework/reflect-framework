package nth.reflect.fw.ui.commandline.domain.command;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class PropertyTypeNotSupported extends TranslatableException {

	private static final long serialVersionUID = 1666511695642836783L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			PropertyTypeNotSupported.class.getCanonicalName(), "Property type: %s  is not supported for property: %s");

	public PropertyTypeNotSupported(PropertyInfo propertyInfo) {
		super(MESSAGE.withParameters(propertyInfo.getTypeInfo(), propertyInfo.getSimpleName()));
	}
}
