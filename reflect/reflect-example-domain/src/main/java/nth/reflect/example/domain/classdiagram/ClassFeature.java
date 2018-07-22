package nth.reflect.example.domain.classdiagram;

import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

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

	public ClassFeature(ActionMethodInfo actionMethodInfo) {
		this(getMethodRepresentation(actionMethodInfo), getMethodType(actionMethodInfo));
	}

	private static Class<?> getMethodType(ActionMethodInfo actionMethodInfo) {
		Class<?> returnType = actionMethodInfo.getGenericReturnType();
		if (returnType == Void.TYPE) {
			// if method is of type void, try to get the type of the parameter
			Class<?> parameterType = actionMethodInfo.getGenericParameterType();
			return parameterType;
		} else {
			return returnType;
		}
	}

	private static String getMethodRepresentation(ActionMethodInfo actionMethodInfo) {
		StringBuffer representation = new StringBuffer();
		representation.append(actionMethodInfo.getSimpleName());
		representation.append("(");
		if (actionMethodInfo.hasParameter()) {
			representation.append(actionMethodInfo.getParameterType().toString());
		}
		representation.append(")");
		if (actionMethodInfo.hasParameter()) {
			representation.append(" : ");
			representation.append(actionMethodInfo.getReturnType().toString());
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

	@Hidden(propertyHiddenFor = HiddenFor.TABLES)
	public Class<?> getType() {
		return type;
	}

	@Override
	public String toString() {
		return representation;
	}
}
