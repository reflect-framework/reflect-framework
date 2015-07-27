package nth.introspect.layer5provider.reflection.info.method.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;

public class ParameterTypeFilter implements Filter<MethodInfo>{

	private final Class<?> parameterType;

	public ParameterTypeFilter(Class<?> parameterType) {
		this.parameterType = parameterType;
	}


	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		Class<?> methodParameterClass = methodInfo.getParameterType().getType();
		return methodParameterClass!=null && parameterType.isAssignableFrom(methodParameterClass);
	}

}
