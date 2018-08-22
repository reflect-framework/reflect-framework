package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import nth.reflect.fw.generic.filter.Filter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

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
		Class<?> methodParameterClass = actionMethodInfo.getParameterType();
		return methodParameterClass == null || !actionMethodInfo.getParameterType().isAssignableFrom(parameterType);
	}

}