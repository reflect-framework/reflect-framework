package nth.introspect.layer5provider.reflection.info.actionmethod.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

public class NoParameterOrParameterFactoryFilter implements Filter<ActionMethodInfo> {

	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		return TypeCategory.NONE == actionMethodInfo.getParameterType().getTypeCategory() || actionMethodInfo.hasParameterFactory();
	}

}
