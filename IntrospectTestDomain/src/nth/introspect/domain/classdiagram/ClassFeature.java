package nth.introspect.domain.classdiagram;

import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;
import nth.introspect.layer5provider.reflection.info.valuemodel.annotations.VisibleInTable;

public class ClassFeature {

	private final String representation;
	private final Class<?> type;

	public ClassFeature(String representation, Class<?> type) {
		this.representation = representation;
		this.type = type;
	}

	public ClassFeature(Class<?> type) {
		this(getClassRepresentation(type), type);
	}

	public ClassFeature(PropertyInfo propertyInfo) {
		this(getPropertyRepresentation(propertyInfo), propertyInfo.getPropertyType().getType());
	}

	public ClassFeature(MethodInfo methodInfo) {
		this(getMethodRepresentation(methodInfo), getMethodType(methodInfo));
	}

	private static Class<?> getMethodType(MethodInfo methodInfo) {
		Class<?> returnType = methodInfo.getReturnType().getTypeOrGenericCollectionType();
		if (returnType == Void.TYPE) {
			// if method is of type void, try to get the type of the parameter
			Class<?> parameterType = methodInfo.getParameterType().getTypeOrGenericCollectionType();
			return parameterType;
		} else {
			return returnType;
		}
	}

	private static String getMethodRepresentation(MethodInfo methodInfo) {
		StringBuffer representation = new StringBuffer();
		representation.append(methodInfo.getSimpleName());
		representation.append("(");
		if (methodInfo.getParameterType().getTypeCategory() != TypeCategory.NONE) {
			representation.append(methodInfo.getParameterType().toString());
		}
		representation.append(")");
		if (methodInfo.getReturnType().getTypeCategory() != TypeCategory.NONE) {
			representation.append(" : ");
			representation.append(methodInfo.getReturnType().toString());
		}
		return representation.toString();
	}

	private static String getPropertyRepresentation(PropertyInfo propertyInfo) {
		StringBuffer representation = new StringBuffer();
		representation.append(propertyInfo.getSimpleName());
		representation.append(" ");
		representation.append(propertyInfo.getPropertyType().toString());
		if (propertyInfo.isReadOnly()) {
			representation.append(" (Read-only)");
		}
		return representation.toString();
	}

	private static String getClassRepresentation(Class<?> type) {
		StringBuffer representation = new StringBuffer();
		representation.append("<");
		representation.append(type.getSimpleName());
		representation.append(">");
		return representation.toString();
	}

	public String getItem() {
		return representation;
	}

	@VisibleInTable(false)
	public Class<?> getType() {
		return type;
	}

	@Override
	public String toString() {
		return representation;
	}
}
