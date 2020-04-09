package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class MethodNotSupportedException extends TranslatableException {

	private static final long serialVersionUID = -8216136994547733822L;
	private static TranslatableString MESSAGE = new TranslatableString(
			MethodNotSupportedException.class.getCanonicalName() + ".message", "Method: %s is not supported");

	public MethodNotSupportedException(String classAndMethodName) {
		super(MESSAGE.withParameters(classAndMethodName));
	}

}
