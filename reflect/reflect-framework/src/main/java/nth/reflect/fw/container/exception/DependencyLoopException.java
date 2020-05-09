package nth.reflect.fw.container.exception;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class DependencyLoopException extends ReflectContainerException {

	private static final long serialVersionUID = 1332521278671440853L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			DependencyLoopException.class.getCanonicalName() + ".message",
			"Dependency loop with class: %s and class: %s");

	public DependencyLoopException(Class<?> type1, Class<?> type2) {
		super(MESSAGE.withParameters(type1.getCanonicalName(), type2.getCanonicalName()));
	}

}
