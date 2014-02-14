package nth.introspect.provider.domain.info.method.filter;

import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.method.MethodInfo;

public class AllMethods implements Filter<MethodInfo> {

	@Override
	public boolean isMatch(MethodInfo t) {
		return true;
	}

}
