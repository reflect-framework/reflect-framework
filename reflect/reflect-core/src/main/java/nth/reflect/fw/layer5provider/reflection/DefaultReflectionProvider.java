package nth.reflect.fw.layer5provider.reflection;

import java.util.HashMap;

import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfoFactory;

public class DefaultReflectionProvider implements ReflectionProvider {

	// TODO Continue code review here
	private final HashMap<Class<?>, ClassInfo> classInfos;
	private final ProviderContainer providerContainer;

	public DefaultReflectionProvider(ProviderContainer providerContainer) {
		this.providerContainer = providerContainer;
		classInfos = new HashMap<>();
	}

	public ClassInfo getClassInfo(Class<?> objectClass) {
		if (!classInfos.containsKey(objectClass)) {
			classInfos.put(objectClass,
					ClassInfoFactory.create(providerContainer, objectClass));
		}
		return classInfos.get(objectClass);
	}

}
