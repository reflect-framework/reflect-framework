package nth.reflect.fw.layer5provider.reflection.info.property.exception;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CouldNotSetPropertyValueException extends TranslatableException {

	private static final long serialVersionUID = 7783512995685282950L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			CouldNotSetPropertyValueException.class.getCanonicalName() + ".message",
			"Could not set value of property: %s with value: %s of type: %s");

	public CouldNotSetPropertyValueException(String canonicalName, Object value, Exception e) {
		super(MESSAGE.withParameters(canonicalName, value, value.getClass().getCanonicalName()), e);
	}

}
