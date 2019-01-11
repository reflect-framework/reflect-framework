package nth.reflect.fw.generic.util;

public class TypeUtil {
	public static Class<?> getComplexType(Class<?> type) {
		if (type == boolean.class) {
			return Boolean.class;
		} else if (type == byte.class) {
			return Byte.class;
		} else if (type == short.class) {
			return Short.class;
		} else if (type == int.class) {
			return Integer.class;
		} else if (type == long.class) {
			return Long.class;
		} else if (type == float.class) {
			return Float.class;
		} else if (type == double.class) {
			return Double.class;
		} else if (type == char.class) {
			return Character.class;
		}
		return type;
	}

	public static Class<?> getSimpleType(Class<?> type) {
		if (type == Boolean.class) {
			return boolean.class;
		} else if (type == Byte.class) {
			return byte.class;
		} else if (type == Short.class) {
			return short.class;
		} else if (type == Integer.class) {
			return int.class;
		} else if (type == Long.class) {
			return long.class;
		} else if (type == Float.class) {
			return float.class;
		} else if (type == Double.class) {
			return double.class;
		} else if (type == Character.class) {
			return char.class;
		}
		return type;
	}

}
