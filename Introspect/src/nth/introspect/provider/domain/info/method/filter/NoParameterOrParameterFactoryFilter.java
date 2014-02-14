package nth.introspect.provider.domain.info.method.filter;

import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.type.TypeCategory;

public class NoParameterOrParameterFactoryFilter implements Filter<MethodInfo> {

	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		return TypeCategory.NONE == methodInfo.getParameterType().getTypeCategory() || methodInfo.hasParameterFactory();
	}

}
