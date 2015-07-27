package nth.introspect.generic.util;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer5provider.domain.info.valuemodel.impl.SimpleValue;

public class AnnotationUtil {

	private static final Class<?>[] SIGNATURE = new Class[0];
	private static final Object[] ARGUMENTS = new Object[0];
	private static final String VALUE = "value";

	
	public static boolean hasAnnotation(PropertyDescriptor propertyDescriptor, Class<? extends Annotation> annotationClass) {
		return hasAnnotation(propertyDescriptor.getReadMethod(), annotationClass);
	}

	private static boolean hasAnnotation(Method method, Class<? extends Annotation> annotationClass) {
		return method.isAnnotationPresent(annotationClass);
	}

	public static ReadOnlyValueModel createValueModel(PropertyDescriptor propertyDescriptor, Class<? extends Annotation> annotationClass, String annotationMethodName) {
		Object value=getValue(propertyDescriptor.getReadMethod(), annotationClass, annotationMethodName);
		return new SimpleValue(value);
	}

	private static Object getValue(Method method, Class<? extends Annotation> annotationClass, String annotationMethodName) {
		try {
			Annotation annotation = method.getAnnotation(annotationClass);
			Class<?>[] signature = new Class[0];
			Method annotationMethod = annotation.getClass().getMethod(annotationMethodName, signature);
			Object[] arguments = new Object[0];
			return annotationMethod.invoke(annotation, arguments);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Tries to find a method annotation. It will also look in its interfaces 
	 * @param method
	 * @param annotationClassToFind
	 * @return
	 */
	public static Annotation findAnnotation(Method method,
			Class<? extends Annotation> annotationClassToFind) {
		Annotation annotation = method.getAnnotation(annotationClassToFind);
		if (annotation==null) {
			annotation=findAnnotationInInterfaces(method, annotationClassToFind);
		} 
		if (annotation==null) {
			annotation=findAnnotationInSuperClass(method, annotationClassToFind);
		}
		
		return annotation;
		
	}

	private static Annotation findAnnotationInSuperClass(Method method,
			Class<? extends Annotation> annotationClassToFind) {
		Class<?> owner = method.getDeclaringClass();
		Class<?> superClass = owner.getSuperclass();
		Class<?>[] parameterTypes = method.getParameterTypes();
		String name = method.getName();
		try {
			method=superClass.getMethod(name, parameterTypes);
			Annotation annotation = findAnnotation(method, annotationClassToFind);
			if (annotation!=null) {
				return annotation;
			}
		} catch (Exception e) {
			//Could not find it, no problem.
		}
		return null;
	}

	private static Annotation findAnnotationInInterfaces(Method method,
			Class<? extends Annotation> annotationClassToFind) {
		Class<?> owner = method.getDeclaringClass();
		Class<?>[] parameterTypes = method.getParameterTypes();
		String name = method.getName();
		Class<?>[] interfaces = owner.getInterfaces();
		for (Class<?> interf:interfaces) {
			try {
				method = interf.getMethod(name, parameterTypes);
				Annotation annotation = findAnnotation(method, annotationClassToFind);
				if (annotation!=null) {
					return annotation;
				}
			} catch (Exception exception) {
				//Could not find it, no problem.
			}
		}
		return null;
		
	}

	public static Object getAnnotationValue(Annotation  annotation) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method annotationValueMethod = annotation.getClass().getMethod(VALUE, SIGNATURE);
		return annotationValueMethod.invoke(annotation, ARGUMENTS);
	}

}
