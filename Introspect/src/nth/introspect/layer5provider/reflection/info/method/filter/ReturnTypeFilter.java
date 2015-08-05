package nth.introspect.layer5provider.reflection.info.method.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ReturnTypeFilter implements Filter<ActionMethodInfo>{

	private final Class<?> returnType;

	public ReturnTypeFilter(Class<?> returnType) {
		this.returnType = returnType;
	}


	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		Class<?> methodReturnClass = actionMethodInfo.getReturnType().getTypeOrGenericCollectionType();
		return methodReturnClass!=null && returnType.isAssignableFrom(methodReturnClass);
	}

}
