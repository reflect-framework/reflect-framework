package nth.reflect.fw.layer5provider.reflection.info.property.exception;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CouldNotSetDisabledPropertyValueException extends TranslatableException {

	private static final long serialVersionUID = 239120696202492473L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			CouldNotSetDisabledPropertyValueException.class.getCanonicalName() + ".message",
			"Could not set value of property: %s when it is disabled or read only");

	public CouldNotSetDisabledPropertyValueException(String conicalName) {
		super(MESSAGE.withParameters(conicalName));
	}

}
