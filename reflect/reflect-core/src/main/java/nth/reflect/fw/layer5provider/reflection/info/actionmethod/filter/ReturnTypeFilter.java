package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import nth.reflect.fw.generic.filter.Filter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ReturnTypeFilter implements Filter<ActionMethodInfo>{

	private final Class<?> returnType;

	public ReturnTypeFilter(Class<?> returnType) {
		this.returnType = returnType;
	}


	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		Class<?> methodReturnClass = actionMethodInfo.getGenericReturnType();
		return methodReturnClass!=null && returnType.isAssignableFrom(methodReturnClass);
	}

}
