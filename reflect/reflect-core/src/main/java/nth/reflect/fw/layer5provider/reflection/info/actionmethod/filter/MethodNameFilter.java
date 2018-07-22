package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import nth.reflect.fw.generic.filter.Filter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class MethodNameFilter implements Filter<ActionMethodInfo> {

	private final String name;
	public MethodNameFilter(String name) {
		this.name = name;
	}

	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		return actionMethodInfo.getSimpleName().equals(name);
	}

}
