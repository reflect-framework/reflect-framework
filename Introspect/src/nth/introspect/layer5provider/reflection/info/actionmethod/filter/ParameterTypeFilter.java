package nth.introspect.layer5provider.reflection.info.actionmethod.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ParameterTypeFilter implements Filter<ActionMethodInfo>{

	private final Class<?> parameterType;

	public ParameterTypeFilter(Class<?> parameterType) {
		this.parameterType = parameterType;
	}


	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		Class<?> methodParameterClass = actionMethodInfo.getParameterType().getType();
		return methodParameterClass!=null && parameterType.isAssignableFrom(methodParameterClass);
	}

}
