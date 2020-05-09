package nth.reflect.fw.container.exception;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ReflectContainerInitializationException extends ReflectContainerException {

	private static final long serialVersionUID = -5257751247046976112L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ReflectContainerInitializationException.class.getCanonicalName() + ".message",
			"The %s failed to initialize.");

	public ReflectContainerInitializationException(Exception exception) {
		super(MESSAGE.withParameters(ReflectFramework.class.getSimpleName()), exception);
	}
}
