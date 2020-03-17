package nth.reflect.fw.container.exception;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ClassHasNoUsableConstructorException extends ReflectContainerException {

	private static final long serialVersionUID = -8120551659126072243L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ClassHasNoUsableConstructorException.class.getCanonicalName() + ".message",
			"%s has no public constructor that can be used by the %s. "
					+ "Make sure that the constructor parameter types are all registered in the %s.getServiceClasses() "
					+ "or %s.getInfrastructureClasses() methods.");

	public ClassHasNoUsableConstructorException(Class<?> classToInstantiate) {
		super(MESSAGE.withParameters(classToInstantiate.getCanonicalName(), ReflectFramework.class.getSimpleName(),
				ReflectApplication.class.getSimpleName(), ReflectApplication.class.getSimpleName()));
	}

}
