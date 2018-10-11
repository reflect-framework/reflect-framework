package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import java.util.function.Predicate;

import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class NoParameterOrParameterFactoryFilter implements Predicate<ActionMethodInfo> {

	@Override
	public boolean test(ActionMethodInfo actionMethodInfo) {
		return !actionMethodInfo.hasParameter() || actionMethodInfo.hasParameterFactory();
	}

}
