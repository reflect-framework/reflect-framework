package nth.reflect.fw.layer5provider.reflection.info.type;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collection;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.generic.util.TypeUtil;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;

/**
 * FIXME: remove this class (see how we did the userinterface
 * editActionMethodParameter confirmActionMethod and showActionMethodResult
 * methods and do the same for field creation)
 */
public enum TypeCategory {
	UNKNOWN, NONE, JAVA_TYPE, DOMAIN_TYPE, HIERARCHICAL_DOMAIN_TYPE, ENUM_TYPE, COLLECTION_TYPE, DOWNLOAD_STREAM_TYPE, UPLOAD_STREAM_TYPE;

	public static TypeCategory getFor(Class<?> type) {
		if (type == null || isVoidType(type)) {
			return NONE;
		} else if (isEnum(type)) {
			return ENUM_TYPE;
		} else if (isColection(type)) {
			return COLLECTION_TYPE;
		} else if (isURI(type)) {
			return DOWNLOAD_STREAM_TYPE;
		} else if (isUploadStream(type)) {
			return UPLOAD_STREAM_TYPE;
		} else if (isJavaType(type)) {
			return JAVA_TYPE;
		} else if (isHierarchicalDomainType(type)) {
			return HIERARCHICAL_DOMAIN_TYPE;
		} else if (isDomainType(type)) {
			return DOMAIN_TYPE;
		} else {
			return UNKNOWN;
		}
	}

	public static boolean isVoidType(Class<?> type) {
		return type == Void.TYPE;
	}

	public static boolean isColection(Class<?> type) {
		return Collection.class.isAssignableFrom(type);
	}

	public static boolean isJavaType(Class<?> type) {
		return TypeUtil.getComplexType(type).getCanonicalName().startsWith("java");
	}

	public static boolean isURI(Class<?> type) {
		return type.isAssignableFrom(URI.class);
	}

	public static boolean isDowloadStream(Class<?> type) {
		return type.isAssignableFrom(DownloadStream.class);
	}

	public static boolean isUploadStream(Class<?> type) {
		return type.isAssignableFrom(UploadStream.class);
	}

	/**
	 * 
	 * @param type
	 * @return true if type is a domain type and has a method <Collection>
	 *         getChildren()
	 */
	public static boolean isHierarchicalDomainType(Class<?> type) {
		if (TypeCategory.isDomainType(type)) {
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

	public static boolean isEnum(Class<?> type) {
		return type.isEnum();
	}

	public static boolean isDomainType(Class<?> type) {
		return !isJavaType(type) && !isEnum(type);
	}

	public static boolean isDomainType(Class<?> type, ReflectApplication reflectApplication) {
		return !isJavaType(type) && !isEnum(type) && !isReflectApplication(type)
				&& !isServiceClass(type, reflectApplication) && !isInfrastructureClass(type, reflectApplication);
	}

	private static boolean isReflectApplication(Class<?> type) {
		return ReflectApplication.class.isAssignableFrom(type);
	}

	private static boolean isServiceClass(Class<?> classToFind, ReflectApplication reflectApplication) {
		List<Class<?>> serviceClasses = reflectApplication.getServiceClasses();
		if (serviceClasses == null) {
			return false;
		} else {
			return serviceClasses.contains(classToFind);
		}
	}

	public static boolean isInfrastructureClass(Class<?> classToFind, ReflectApplication reflectApplication) {
		List<Class<?>> infrastructureClasses = reflectApplication.getInfrastructureClasses();
		if (infrastructureClasses == null) {
			return false;
		} else {
			return infrastructureClasses.contains(classToFind);
		}
	}

}
