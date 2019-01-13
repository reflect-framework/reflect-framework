package nth.reflect.fw.layer5provider.reflection.info.type;

import nth.reflect.fw.generic.util.JavaTypeConverter;

/**
 * @deprecated use {@link TypeInfo} FIXME: remove this class (see how we did the
 *             userinterface editActionMethodParameter confirmActionMethod and
 *             showActionMethodResult methods and do the same for field
 *             creation)
 */
@Deprecated
public class TypeCategory {

	private static boolean isJavaType(Class<?> type) {
		return JavaTypeConverter.getComplexType(type).getCanonicalName().startsWith("java");
	}

	public static boolean isEnum(Class<?> type) {
		return type.isEnum();
	}

	public static boolean isDomainType(Class<?> type) {
		return !isJavaType(type) && !isEnum(type);
	}

}
