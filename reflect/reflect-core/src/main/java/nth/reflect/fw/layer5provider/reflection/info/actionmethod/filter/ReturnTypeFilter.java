package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import java.util.Optional;
import java.util.function.Predicate;

import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class ReturnTypeFilter implements Predicate<ActionMethodInfo> {

	private final Class<?> returnType;

	public ReturnTypeFilter(Class<?> returnType) {
		this.returnType = returnType;
	}

	@Override
	public boolean test(ActionMethodInfo actionMethodInfo) {
		TypeInfo typeInfo = actionMethodInfo.getReturnTypeInfo();
		if (returnType.isAssignableFrom(typeInfo.getType())) {
			return true;
		}
		Optional<TypeInfo> arrayOrCollectionTypeInfo = typeInfo.getGenericTypeInfo();
		if (arrayOrCollectionTypeInfo.isPresent()) {
			return returnType.isAssignableFrom(arrayOrCollectionTypeInfo.get().getType());
		}
		return false;
	}

}
