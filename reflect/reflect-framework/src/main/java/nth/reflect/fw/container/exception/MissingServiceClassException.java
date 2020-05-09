package nth.reflect.fw.container.exception;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class MissingServiceClassException extends ReflectContainerException {

	private static final TranslatableString MESSAGE = new TranslatableString(
			MissingServiceClassException.class.getCanonicalName() + ".message",
			"%s.getServiceClasses method must return at least one service object class.");
	private static final long serialVersionUID = 8927372836123865304L;

	public MissingServiceClassException(ReflectApplication application) {
		super(MESSAGE.withParameters(application.getClass().getCanonicalName()));
	}

}
