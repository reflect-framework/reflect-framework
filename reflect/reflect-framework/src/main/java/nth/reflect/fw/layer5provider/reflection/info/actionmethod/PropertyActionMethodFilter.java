package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.Method;
import java.util.function.Predicate;

public class PropertyActionMethodFilter implements Predicate<Method> {

	private final String propertyName;

	public PropertyActionMethodFilter(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public boolean test(Method method) {
		return method.getName().startsWith(propertyName);
	}

	public String getPropertyName() {
		return propertyName;
	}

};