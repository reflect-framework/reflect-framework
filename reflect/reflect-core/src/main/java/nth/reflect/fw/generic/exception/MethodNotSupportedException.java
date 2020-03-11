package nth.reflect.fw.generic.exception;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class MethodNotSupportedException extends ReflectTranslatableException {

	private static final long serialVersionUID = -8216136994547733822L;
	private static TranslatableString translatableString = new TranslatableString(
			MethodNotSupportedException.class.getCanonicalName() + ".message", "Method: %s is not supported");

	public MethodNotSupportedException(String classAndMethodName) {
		super(translatableString.withParameters(classAndMethodName));
	}

}
