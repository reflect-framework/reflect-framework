package nth.reflect.fw.container.exception;

import java.lang.reflect.Constructor;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class InstantiationException extends ReflectContainerException {

	private static final long serialVersionUID = -7371949978849872639L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			InstantiationException.class.getCanonicalName() + ".message",
			"%s: Could not create a new instance for: %s(%s)");

	public InstantiationException(DependencyInjectionContainer container, Constructor<?> constructor,
			Throwable exception) {
		super(MESSAGE
				.withParameters(container.getName(), constructor.getDeclaringClass().getCanonicalName(),
						constructor.getParameters()),
				exception);
	}

}
