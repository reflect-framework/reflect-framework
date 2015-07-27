package nth.introspect.layer5provider.domain.info.method.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.domain.info.method.MethodInfo;
import nth.introspect.layer5provider.domain.info.type.TypeCategory;

public class NoParameterOrParameterFactoryFilter implements Filter<MethodInfo> {

	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		return TypeCategory.NONE == methodInfo.getParameterType().getTypeCategory() || methodInfo.hasParameterFactory();
	}

}
