package nth.introspect.layer5provider.domain.info.method.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.domain.info.method.MethodInfo;

public class ReturnTypeFilter implements Filter<MethodInfo>{

	private final Class<?> returnType;

	public ReturnTypeFilter(Class<?> returnType) {
		this.returnType = returnType;
	}


	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		Class<?> methodReturnClass = methodInfo.getReturnType().getTypeOrGenericCollectionType();
		return methodReturnClass!=null && returnType.isAssignableFrom(methodReturnClass);
	}

}
