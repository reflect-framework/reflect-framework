package nth.reflect.fw.container.exception;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;

public class ClassHasNoUsableConstructorException extends
		ReflectContainerException {

	public ClassHasNoUsableConstructorException(Class<?> classToInstantiate) {
		super(createMessage(classToInstantiate));
	}

	private static String createMessage(Class<?> classToInstantiate) {
		StringBuffer message = new StringBuffer();
		message.append("Class ");
		message.append(classToInstantiate.getCanonicalName());
		message.append(" has no public constructor that can be used by the ");
		message.append(ReflectFramework.class.getSimpleName());
		message.append(". Make sure that the constructor parameter types are all registered in the ");
		message.append(ReflectApplication.class.getSimpleName());
		message.append(".getServiceClasses() or .getInfrastructureClasses() methods");
		return message.toString();
	}

	private static final long serialVersionUID = -8120551659126072243L;

	
}
