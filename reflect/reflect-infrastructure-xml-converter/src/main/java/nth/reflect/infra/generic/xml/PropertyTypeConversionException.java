package nth.reflect.infra.generic.xml;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class PropertyTypeConversionException extends TranslatableException {

	private static final long serialVersionUID = -3006858160489147731L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			PropertyTypeConversionException.class.getCanonicalName() + ".message",
			"Error converting type: %s for property: %s in: %s");

	public PropertyTypeConversionException(Class<?> propertyType, PropertyInfo propertyInfo) {
		super(MESSAGE.withParameters(propertyType.getCanonicalName(), propertyInfo.getCanonicalName(),
				XmlConverter.class.getCanonicalName()));
	}

}
