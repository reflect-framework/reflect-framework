package nth.introspect.layer5provider.reflection.info.actionmethod.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class NoParameterOrParameterFactoryFilter implements Filter<ActionMethodInfo> {

	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		return !actionMethodInfo.hasParameter() || actionMethodInfo.hasParameterFactory();
	}

}
