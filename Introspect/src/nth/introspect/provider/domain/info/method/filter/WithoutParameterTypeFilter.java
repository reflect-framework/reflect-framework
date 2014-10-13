package nth.introspect.provider.domain.info.method.filter;

import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.method.MethodInfo;

public class WithoutParameterTypeFilter implements Filter<MethodInfo> {

	private final Class<?> parameterType;

	public WithoutParameterTypeFilter(Class<?> parameterType) {
		this.parameterType = parameterType;
	}

	/**
	 * @param methodInfo
	 *            The value that will be matched
	 * @return True if the method does not require a given parameter to be invoked. In other words: If the methods has no parameter or if the parameter has a parameter factory method
	 */
	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		Class<?> methodParameterClass = methodInfo.getParameterType().getType();
		return methodParameterClass == null || !methodInfo.getParameterType().getType().isAssignableFrom(parameterType);
	}

}
