package nth.introspect.generic.util;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collection;
import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

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
	
	public static boolean isNumber(Class<?> type) {
		return Number.class.isAssignableFrom(getComplexType(type));
	}
	
	public static boolean isChar(Class<?> type) {
		return Character.class.isAssignableFrom(getComplexType(type));
	}

	
	public static boolean isColection(Class<?> type) {
		return Collection.class.isAssignableFrom(type);
	}
	
	public static boolean isJavaType(Class<?> type) {
		return getComplexType(type).getCanonicalName().startsWith("java");
	}
	
	public static boolean isDomainType(Class<?> type) {
		return  !isJavaType(type) && !isEnum(type);
	}

	public static boolean isDomainType(Class<?> type, IntrospectApplication introspectApplication) {
		return  !isJavaType(type) && !isEnum(type) && !isIntrospectApplication(type) &&  !isServiceClass(type, introspectApplication) && !isInfrastructureClass(type, introspectApplication);
	}
	

	private static boolean isIntrospectApplication(Class<?> type) {
		return IntrospectApplication.class.isAssignableFrom(type);
	}

	private static boolean isServiceClass(Class<?> classToFind,
			IntrospectApplication introspectApplication) {
		List<Class<?>> serviceClasses = introspectApplication.getServiceClasses();
		return serviceClasses.contains(classToFind);
	}

	private static boolean isInfrastructureClass(Class<?> classToFind,
			IntrospectApplication introspectApplication) {
		List<Class<?>> infrastructureClasses = introspectApplication.getInfrastructureClasses();
		return infrastructureClasses.contains(classToFind);
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

	public static boolean isURI(Class<?> type) {
		return type.isAssignableFrom(URI.class);
	}

	public static boolean isDowloadStream(Class<?> type) {
		return type.isAssignableFrom(DownloadStream.class);
	}

	/**
	 * 
	 * @param type
	 * @return true if type is a domain type and has a method <Collection> getChildren() 
	 */
	public static boolean isHierarchicalDomainType(Class<?> type) {
		if (isDomainType(type)) {
			Class<?>[] arguments = new Class[0];
			try {
				Method method = type.getMethod("getChildren", arguments);
				return method.getReturnType().isAssignableFrom(Collection.class);
			} catch (Exception e) {
				return false;
			}	
		} else {
			return false;
		}
		
	}

	public static boolean isVoidType(Class<?> type) {
		return type==Void.TYPE;
	}

	public static TypeCategory getTypeCategory(Class<?> type) {
		if (type == null || isVoidType(type)) {
			return TypeCategory.NONE;
		} else if (isColection(type)) {
			return TypeCategory.COLLECTION_TYPE;
		} else if (isURI(type)) {
			return TypeCategory.URI_TYPE;
		} else if (isDowloadStream(type)) {
			return TypeCategory.DOWNLOAD_STREAM_TYPE;
		} else if (isJavaType(type)) {
			return TypeCategory.JAVA_TYPE;
		} else if (isHierarchicalDomainType(type)) {
			return TypeCategory.HIERARCHICAL_DOMAIN_TYPE;
		} else {
			return TypeCategory.DOMAIN_TYPE;
		}
	}

	public static boolean isEnum(Class<?> type) {
		return type.isEnum();
	}

	public static boolean isShort(Class<?> type) {
		return type==Short.class;
	}


	


}
