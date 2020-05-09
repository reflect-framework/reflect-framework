package nth.reflect.fw.generic.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class PrimitiveType {

	/**
	 * A collection of primitive types that represent a number. In other words:
	 * primitive types that have a primitive wrapper that implements {@link Number}.
	 */
	private static final Set<Class<?>> primitiveNumberTypes = new HashSet<Class<?>>();
	static {
		primitiveNumberTypes.add(Byte.TYPE);
		primitiveNumberTypes.add(Short.TYPE);
		primitiveNumberTypes.add(Integer.TYPE);
		primitiveNumberTypes.add(Long.TYPE);
		primitiveNumberTypes.add(Double.TYPE);
		primitiveNumberTypes.add(Float.TYPE);
	}

	/**
	 * Maps primitive {@code Class}es to their corresponding wrapper {@code Class}.
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
		primitiveToWrapperMap.put(Void.TYPE, void.class);
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
	 * @param primitiveType the class to convert, may be null
	 * @return {@link Optional} the wrapper class for {@code primitiveType} or
	 *         {@link Optional#empty()} otherwise.
	 * @See {@link #wrapperToPrimitive(Class)}
	 */
	public static Optional<Class<?>> primitiveToWrapper(Class<?> primitiveType) {
		if (primitiveToWrapperMap.containsKey(primitiveType)) {
			Class<?> wrapperType = primitiveToWrapperMap.get(primitiveType);
			return Optional.of(wrapperType);
		} else {
			return Optional.empty();
		}

	}

	public static boolean isPrimitive(Class<?> type) {
		return primitiveToWrapperMap.containsKey(type);
	}

	public static boolean isPrimitiveNumber(Class<?> type) {
		return primitiveNumberTypes.contains(type);
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
	 * @param wrapperType the class to convert, may be <b>null</b>
	 * @return {@link Optional#of(Object)} the corresponding primitive type if
	 *         {@code wrapperType} is a wrapper class, {@link Optional#empty()}
	 *         otherwise
	 * @see #primitiveToWrapper(Class)
	 */
	public static Optional<Class<?>> wrapperToPrimitive(Class<?> wrapperType) {
		if (wrapperToPrimitiveMap.containsKey(wrapperType)) {
			Class<?> primitiveType = wrapperToPrimitiveMap.get(wrapperType);
			return Optional.of(primitiveType);
		} else {
			return Optional.empty();
		}
	}

	public static boolean isWrapper(Class<?> type) {
		return wrapperToPrimitiveMap.containsKey(type);
	}


}
