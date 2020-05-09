package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import java.util.function.Predicate;

import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class MethodNameFilter implements Predicate<ActionMethodInfo> {

	private final String name;
	public MethodNameFilter(String name) {
		this.name = name;
	}

	@Override
	public boolean test(ActionMethodInfo  actionMethodInfo) {
		return actionMethodInfo.getSimpleName().equals(name);
	}

}
