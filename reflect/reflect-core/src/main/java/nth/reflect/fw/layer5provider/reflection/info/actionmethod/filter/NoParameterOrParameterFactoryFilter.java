package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import nth.reflect.fw.generic.filter.Filter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class NoParameterOrParameterFactoryFilter implements Filter<ActionMethodInfo> {

	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		return !actionMethodInfo.hasParameter() || actionMethodInfo.hasParameterFactory();
	}

}
