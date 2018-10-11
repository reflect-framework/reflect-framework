package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import java.util.function.Predicate;

import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ParameterTypeFilter implements Predicate<ActionMethodInfo>{

	private final Class<?> parameterType;

	public ParameterTypeFilter(Class<?> parameterType) {
		this.parameterType = parameterType;
	}


	@Override
	public boolean test(ActionMethodInfo actionMethodInfo) {
		Class<?> methodParameterClass = actionMethodInfo.getParameterType();
		return methodParameterClass!=null && parameterType.isAssignableFrom(methodParameterClass);
	}

}
