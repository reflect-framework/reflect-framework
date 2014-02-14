package nth.introspect.provider.domain.info.method.filter;

import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.method.MethodInfo;

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
