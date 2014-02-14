package nth.introspect.provider.domain.info.method.filter;

import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.method.MethodInfo;

public class MethodsFilter implements Filter<MethodInfo> {

	private MethodInfo methodInfo;
	
	public MethodsFilter(MethodInfo methodInfo) {
		this.methodInfo = methodInfo;
	}

	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		return this.methodInfo==methodInfo;
	}

}
