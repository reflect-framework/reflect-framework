package nth.reflect.fw.layer5provider.reflection;

import java.util.HashMap;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.info.appinfo.ApplicationInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfoFactory;

public class DefaultReflectionProvider implements ReflectionProvider {

	private final HashMap<Class<?>, ClassInfo> classInfos;
	private final ProviderContainer providerContainer;
	private final ApplicationInfo applicationInfo;

	public DefaultReflectionProvider(ProviderContainer providerContainer) {
		this.providerContainer = providerContainer;
		ReflectApplication reflectApplication=providerContainer.get(ReflectApplication.class);
		applicationInfo=new ApplicationInfo(providerContainer, reflectApplication);
		classInfos = new HashMap<>();
	}

	public ClassInfo getClassInfo(Class<?> objectClass) {
		if (!classInfos.containsKey(objectClass)) {
			classInfos.put(objectClass,
					ClassInfoFactory.create(providerContainer, objectClass));
		}
		return classInfos.get(objectClass);
	}

	@Override
	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

}
