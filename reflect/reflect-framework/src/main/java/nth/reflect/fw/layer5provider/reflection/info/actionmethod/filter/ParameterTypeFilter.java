package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import java.util.function.Predicate;

import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class ParameterTypeFilter implements Predicate<ActionMethodInfo> {

	private final Class<?> parameterType;

	public ParameterTypeFilter(Class<?> parameterType) {
		this.parameterType = parameterType;
	}

	@Override
	public boolean test(ActionMethodInfo actionMethodInfo) {
		TypeInfo typeInfo = actionMethodInfo.getFirstParameterTypeInfo();
		return !typeInfo.isVoid() && parameterType.isAssignableFrom(typeInfo.getType());
	}

}
