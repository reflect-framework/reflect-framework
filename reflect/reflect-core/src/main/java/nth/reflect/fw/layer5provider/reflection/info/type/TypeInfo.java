package nth.reflect.fw.layer5provider.reflection.info.type;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.generic.util.PrimitiveType;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * Provides information on a type, e.g. a {@link DomainObjectProperty} type, a
 * {@link ActionMethod} parameter type or a {@link ActionMethod} return type.
 * 
 * @author nilsth
 *
 */
public class TypeInfo {

	private final Class<?> type;
	private final Optional<TypeInfo> arrayGenericTypeInfo;
	private final boolean isVoid;
	private final boolean isCollection;
	private final boolean hasMultipleValues;
	private final boolean isJavaType;
	private final boolean isDomainClass;
	private final boolean isArray;
	private final boolean isMap;
	private final boolean isEnum;
	private final boolean isHierarchicalDomainType;

	public TypeInfo(ReflectApplication reflectApplication, Class<?> type, Type genericType) {
		this.type = type;
		this.arrayGenericTypeInfo = getGenericTypeInfo(reflectApplication, genericType);
		this.isVoid = isVoid(type);
		this.isCollection = Collection.class.isAssignableFrom(this.type);
		this.isMap = Map.class.isAssignableFrom(this.type);
		this.isArray = this.type.isArray();
		this.isEnum = this.type.isEnum();
		this.isJavaType = isJavaType(type);
		this.hasMultipleValues = isArray || isCollection || isMap;
		boolean isReflectApplication = ReflectApplication.class.isAssignableFrom(this.type);
		boolean isServiceClass = isServiceClass(this.type, reflectApplication);
		boolean isInfrastructureClass = isInfrastructureClass(this.type, reflectApplication);
		this.isDomainClass = !isVoid && !isJavaType && !hasMultipleValues && !isReflectApplication && !isServiceClass
				&& !isInfrastructureClass;
		this.isHierarchicalDomainType = isHierarchicalDomainType(type);
	}

	/**
	 * @param type        e.g. the generic parameter or the generic return type of a
	 *                    method
	 * @param application
	 * @return If type is an {@link Array} it returns its type<br>
	 *         If type is an {@link ParameterizedType} (e.g. the parameter or the
	 *         return type of a method) it returns the generic type of a
	 *         {@link Collection} or {@link Stream}<br>
	 *         Otherwise it returns {@link Optional#empty()}
	 */
	public static Optional<TypeInfo> getGenericTypeInfo(ReflectApplication application, Type type) {
		if (type == null) {
			return Optional.empty();
		}
		if (type instanceof Class<?>) {
			Class<?> cls = (Class<?>) type;
			if (cls.isArray()) {
				Class<?> arrayType = cls.getComponentType();
				TypeInfo arrayTypeInfo = new TypeInfo(application, arrayType, null);
				return Optional.of(arrayTypeInfo);
			} else {
				return Optional.empty();
			}
		} else if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			if (actualTypeArguments.length != 1) {
				Class<?> genericParameterType = (Class<?>) parameterizedType.getRawType();
				TypeInfo genericParameterTypeInfo = new TypeInfo(application, genericParameterType, null);
				return Optional.of(genericParameterTypeInfo);
			}
			Type actualType = actualTypeArguments[0];
			if (actualType.toString().equals("java.lang.Class<?>")) {
				TypeInfo classTypeInfo = new TypeInfo(application, Class.class, null);
				return Optional.of(classTypeInfo);
			}
			Class<?> genericType = (Class<?>) actualType;
			TypeInfo genericTypeInfo = new TypeInfo(application, genericType, null);
			return Optional.of(genericTypeInfo);
		}
		return Optional.empty();
	}

	private static boolean isVoid(Class<?> type) {
		return type == Void.TYPE || type == Void.class;
	}

	/**
	 * See {@link #getGenericTypeInfo(ReflectApplication, Type)}
	 */
	public Optional<TypeInfo> getGenericTypeInfo() {
		return arrayGenericTypeInfo;
	}

	public Class<?> getType() {
		return type;
	}

	/**
	 * @return true if the type can contain multiple values (can be displayed as a
	 *         table or grid) at a given time. Note that a {@link Enum} only
	 *         contains one value at a time.
	 */
	public boolean hasMultipleValues() {
		return hasMultipleValues;
	}

	public boolean isArray() {
		return isArray;
	}

	public boolean isCollection() {
		return isCollection;
	}

	public boolean isArrayOrCollection() {
		return isArray || isCollection;
	}

	/**
	 * 
	 * @return true if the type is a custom made class {@link DomainObject} class
	 *         (not part of java language or {@link ReflectFramework}) that can be
	 *         displayed in a form. Note that an custom {@link Enum} can contain
	 *         getter methods and is therefore a (none editable) domain object that
	 *         could be displayed in a form
	 */
	public boolean isDomainClass() {
		return isDomainClass;
	}

	/**
	 *
	 * @param type
	 * @return true if type is a domain type and has a method <Collection>
	 *         getChildren()
	 */
	private boolean isHierarchicalDomainType(Class<?> type) {
		if (isDomainClass) {
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

	/**
	 * @return See {@link #isHierarchicalDomainType(Class)}
	 */
	public boolean isHierarchicalDomainType() {
		return isHierarchicalDomainType;
	}

	public boolean isEnum() {
		return isEnum;
	}

	private boolean isInfrastructureClass(Class<?> classToFind, ReflectApplication reflectApplication) {
		List<Class<?>> infrastructureClasses = reflectApplication.getInfrastructureClasses();
		if (infrastructureClasses == null) {
			return false;
		} else {
			return infrastructureClasses.contains(classToFind);
		}
	}

	public boolean isJavaType() {
		return isJavaType;
	}

	public boolean isJavaType(Class<?> type) {
		if (PrimitiveType.isPrimitive(type) || isVoid || (isArray && arrayGenericTypeInfo.get().isJavaType)) {
			return true;
		}
		String canonicalName = type.getCanonicalName();
		boolean startsWithJavaPackageName = canonicalName.startsWith("java.") || canonicalName.startsWith("javax.");
		return startsWithJavaPackageName;
	}

	public boolean isMap() {
		return isMap;
	}

	private boolean isServiceClass(Class<?> classToFind, ReflectApplication reflectApplication) {
		List<Class<?>> serviceClasses = reflectApplication.getServiceClasses();
		if (serviceClasses == null) {
			return false;
		} else {
			return serviceClasses.contains(classToFind);
		}
	}

	public boolean isVoid() {
		return isVoid;
	}

	public boolean isHasMultipleValues() {
		return hasMultipleValues;
	}

	@Override
	public String toString() {
		return type.getName();
	}
}
