package nth.reflect.fw.generic.util;

import java.util.HashMap;
import java.util.Map;

import nth.reflect.fw.ReflectFramework;

public class PrimitiveType {

	/**
	 * Maps primitive {@code Class}es to their corresponding wrapper {@code Class}.
	 * 
	 * Note that the {@link ReflectFramework} does not see the {@link Void#TYPE} as
	 * a primitive type, because it can not be displayed in a user interface
	 */
	private static final Map<Class<?>, Class<?>> primitiveToWrapperMap = new HashMap<Class<?>, Class<?>>();
	static {
		primitiveToWrapperMap.put(Boolean.TYPE, Boolean.class);
		primitiveToWrapperMap.put(Byte.TYPE, Byte.class);
		primitiveToWrapperMap.put(Character.TYPE, Character.class);
		primitiveToWrapperMap.put(Short.TYPE, Short.class);
		primitiveToWrapperMap.put(Integer.TYPE, Integer.class);
		primitiveToWrapperMap.put(Long.TYPE, Long.class);
		primitiveToWrapperMap.put(Double.TYPE, Double.class);
		primitiveToWrapperMap.put(Float.TYPE, Float.class);
	}

	/**
	 * Maps wrapper {@code Class}es to their corresponding primitive types.
	 */
	private static final Map<Class<?>, Class<?>> wrapperToPrimitiveMap = new HashMap<Class<?>, Class<?>>();
	static {
		for (Class<?> primitiveClass : primitiveToWrapperMap.keySet()) {
			Class<?> wrapperClass = primitiveToWrapperMap.get(primitiveClass);
			if (!primitiveClass.equals(wrapperClass)) {
				wrapperToPrimitiveMap.put(wrapperClass, primitiveClass);
			}
		}
	}
	
	/**
	 * <p>
	 * Converts the specified primitive Class object to its corresponding wrapper
	 * Class object.
	 * </p>
	 *
	 * @param cls the class to convert, may be null
	 * @return the wrapper class for {@code cls} or {@code cls} if {@code cls} is
	 *         not a primitive. {@code null} if null input.
	 * @See {@link #wrapperToPrimitive(Class)}
	 */
	public static Class<?> primitiveToWrapper(Class<?> primitiveType) {
		return primitiveToWrapperMap.get(primitiveType);
	}

	public static boolean isPrimative(Class<?> type) {
		return primitiveToWrapperMap.keySet().contains(type);
	}

	
	/**
	 * <p>
	 * Converts the specified wrapper class to its corresponding primitive class.
	 * </p>
	 *
	 * <p>
	 * This method is the counter part of {@code primitiveToWrapper()}. If the
	 * passed in class is a wrapper class for a primitive type, this primitive type
	 * will be returned (e.g. {@code Integer.TYPE} for {@code Integer.class}). For
	 * other classes, or if the parameter is <b>null</b>, the return value is
	 * <b>null</b>.
	 * </p>
	 *
	 * @param cls the class to convert, may be <b>null</b>
	 * @return the corresponding primitive type if {@code cls} is a wrapper class,
	 *         <b>null</b> otherwise
	 * @see #primitiveToWrapper(Class)
	 */
	public static Class<?> wrapperToPrimitive(Class<?> wrapperType) {
		return wrapperToPrimitiveMap.get(wrapperType);
	}

	public static boolean isWrapper(Class<?> type) {
		return wrapperToPrimitiveMap.keySet().contains(type);
	}
	
	
}
