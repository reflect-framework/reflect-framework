package nth.introspect.layer5provider.reflection.info.actionmethod.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class WithoutParameterTypeFilter implements Filter<ActionMethodInfo> {

	private final Class<?> parameterType;

	public WithoutParameterTypeFilter(Class<?> parameterType) {
		this.parameterType = parameterType;
	}

	/**
	 * @param actionMethodInfo
	 *            The value that will be matched
	 * @return True if the method does not require a given parameter to be invoked. In other words: If the methods has no parameter or if the parameter has a parameter factory method
	 */
	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		Class<?> methodParameterClass = actionMethodInfo.getParameterType().getType();
		return methodParameterClass == null || !actionMethodInfo.getParameterType().getType().isAssignableFrom(parameterType);
	}

}
