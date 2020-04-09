package nth.reflect.infra.dataaccess.sql;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class CouldNotSetPropertyException extends TranslatableException {

	private static final long serialVersionUID = 7994004077722761350L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			CouldNotSetPropertyException.class.getCanonicalName() + ".message", "Could not set property: %s.%s");

	public CouldNotSetPropertyException(Class<?> domainClass, PropertyInfo propertyInfo, Exception e) {
		super(MESSAGE.withParameters(domainClass.getCanonicalName(), propertyInfo.getSimpleName()), e);
	}

}
