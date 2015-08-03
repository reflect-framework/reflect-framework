package nth.introspect.layer5provider.reflection.info.method.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;

public class MethodNameFilter implements Filter<MethodInfo> {

	private final String name;
	public MethodNameFilter(String name) {
		this.name = name;
	}

	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		return methodInfo.getSimpleName().equals(name);
	}

}
