package nth.reflect.fw.generic.util;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class CloneException extends TranslatableException {

	private static final long serialVersionUID = -8142108326137197016L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			CloneException.class.getCanonicalName() + ".message", "%s: Error cloning object: %s");

	public CloneException(Object object, Throwable cause) {
		super(MESSAGE.withParameters(CloneUtil.class.getSimpleName(), object), cause);
	}

}
