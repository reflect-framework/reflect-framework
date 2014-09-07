package nth.introspect.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nth.introspect.application.IntrospectApplication;

public class ReflectContainer {

	private Map<Class<?>, Object> cashedObjects;
	private List<Class<?>> types;

	public ReflectContainer(IntrospectApplication application) {
		types = new ArrayList<Class<?>>();
		cashedObjects = new HashMap<Class<?>, Object>();
	}

	public void add(Object object) {
		Class<?> type = object.getClass();
		types.add(type);
		cashedObjects.put(object.getClass(), object);
	}

	public void add(Class<?> type) {
		types.add(type);
	}
	
	public void add(Collection<Class<?>> types) {
		for (Class<?> type : types) {
			add(type);
		}
		
	}

}
