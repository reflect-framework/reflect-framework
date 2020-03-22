package nth.reflect.infra.generic.xml;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class PropertyTypeNotSupportedException extends TranslatableException {

	private static final long serialVersionUID = -7363817044156427010L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			PropertyTypeNotSupportedException.class.getCanonicalName() + ".message",
			"Type: %s for property: %s  is not supported in: %s");

	public PropertyTypeNotSupportedException(Class<?> propertyType, PropertyInfo propertyInfo) {
		super(MESSAGE.withParameters(propertyType.getCanonicalName(), propertyInfo,
				XmlConverter.class.getCanonicalName()));
	}

}
