package nth.reflect.fw.layer5provider.reflection.behavior.option;

import java.lang.reflect.Method;
import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.BehaviorMethodInvokeException;

public class OptionsMethodModel implements OptionsModel {

	private final Method optionsMethod;

	public OptionsMethodModel(Method optionsMethod) {
		this.optionsMethod = optionsMethod;
	}

	@Override
	public boolean hasOptions() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getOptions(Object domainObject) {
		try {
			return (List<Object>) optionsMethod.invoke(domainObject);
		} catch (Exception exception) {
			throw new BehaviorMethodInvokeException(optionsMethod, exception);
		}
	}

}
