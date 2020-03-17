package nth.reflect.fw.container.exception;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ClassAlreadyRegisteredInContainerException extends ReflectContainerException {

	private static final long serialVersionUID = -2198987457083284820L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ClassAlreadyRegisteredInContainerException.class.getCanonicalName() + ".message",
			"%s: Cannot register class: %s  because it was already registered.");

	public ClassAlreadyRegisteredInContainerException(DependencyInjectionContainer container, Class<?> type) {
		super(MESSAGE.withParameters(DependencyInjectionContainer.class.getSimpleName(), container.getName(),
				type.getCanonicalName()));
	}

}
