package nth.reflect.fw.layer5provider.reflection.info.property.exception;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CouldNotSetPropertyValueWithNullException extends TranslatableException {
	private static final long serialVersionUID = -198664327917355938L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			CouldNotSetPropertyValueWithNullException.class.getCanonicalName() + ".message",
			"Could not set value of property: %s with value: null");

	public CouldNotSetPropertyValueWithNullException(String canonicalName, Exception e) {
		super(MESSAGE.withParameters(canonicalName), e);
	}

}
