package nth.introspect.layer5provider.reflection.info.type;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import nth.introspect.generic.util.TypeUtil;

public abstract class ValueType {

	private final Class<?> type;
	private final TypeCategory typeCategory;
	private final Class<?> typeOrGenericCollectionType;

	public ValueType(Class<?> type, Method method,
			TypeCategory[] noneSupportedCategories) {
		this.type = TypeUtil.getComplexType(type);
		typeCategory = TypeUtil.getTypeCategory(type);

		validateTypeCategory(typeCategory, noneSupportedCategories, method);

		typeOrGenericCollectionType = getTypeOrGenericCollectionType(this.type,
				typeCategory, method);
	}

	private Class<?> getTypeOrGenericCollectionType(Class<?> type,
			TypeCategory typeCategory, Method method) {
		// get typeOrGenericCollectionType.
		if (type == null || typeCategory != TypeCategory.COLLECTION_TYPE) {
			return type;
		} else {
			Class<?> genericType = getGenericTypeOfReturnTypeOfCollection(method);
			return TypeUtil.getComplexType(genericType);
		}
	}

	private Class<?> getGenericTypeOfReturnTypeOfCollection(Method method) {
		Type returnType = method.getGenericReturnType();
		ParameterizedType pType = (ParameterizedType) returnType;
		Type actualType = pType.getActualTypeArguments()[0];
		if (actualType.toString().equals("java.lang.Class<?>")) {
			return Class.class;
		}
		Class<?> genericType =  (Class<?>) actualType;
		return genericType;
	}

	/**
	 * 
	 * @param typeCategory
	 * @param method
	 * @param noneSupportedCategories
	 * @throws RuntimeException
	 *             if typeCategory matches one of the noneSupportedCategories
	 */
	private void validateTypeCategory(TypeCategory typeCategory,
			TypeCategory[] noneSupportedCategories, Method method)
			throws RuntimeException {
		for (TypeCategory noneSupportedCategory : noneSupportedCategories) {
			if (typeCategory == noneSupportedCategory) {
				// throw error message
				StringBuffer message = new StringBuffer();
				message.append(this.getClass().getSimpleName());
				message.append(" for method: ");
				message.append(method.getDeclaringClass().getCanonicalName());
				message.append(".");
				message.append(method.getName());
				message.append(" may not be of type: ");
				message.append(noneSupportedCategory.toString());
				throw new RuntimeException(message.toString());
			}
		}
	}

	public Class<?> getType() {
		return type;
	}

	public TypeCategory getTypeCategory() {
		return typeCategory;
	}

	public Class<?> getTypeOrGenericCollectionType() {
		return typeOrGenericCollectionType;
	}

	@Override
	public String toString() {
		StringBuffer text = new StringBuffer();
		if (typeCategory == TypeCategory.COLLECTION_TYPE) {
			text.append(type.getSimpleName());
			text.append("(");
		}
		text.append("<");
		text.append(typeOrGenericCollectionType.getSimpleName());
		text.append(">");
		if (typeCategory == TypeCategory.COLLECTION_TYPE) {
			text.append(")");
		}
		return text.toString();
	}
}
