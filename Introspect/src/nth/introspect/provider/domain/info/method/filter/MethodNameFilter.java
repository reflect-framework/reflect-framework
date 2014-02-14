package nth.introspect.provider.domain.info.method.filter;

import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.method.MethodInfo;

public class MethodNameFilter implements Filter<MethodInfo> {

	private final String name;
	public MethodNameFilter(String name) {
		this.name = name;
	}

	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		return methodInfo.getName().equals(name);
	}

}
