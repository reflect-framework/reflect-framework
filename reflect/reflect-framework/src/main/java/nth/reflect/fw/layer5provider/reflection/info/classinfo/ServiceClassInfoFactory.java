package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import java.util.HashMap;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.ProviderContainer;

public class ServiceClassInfoFactory {

	public static HashMap<Class<?>, ServiceClassInfo> createAll(ProviderContainer providerContainer,
			ReflectApplication reflectApplication) {
		List<Class<?>> serviceClasses = reflectApplication.getServiceClasses();
		HashMap<Class<?>, ServiceClassInfo> serviceClassInfos = new HashMap<>();
		for (Class<?> serviceClass : serviceClasses) {
			ServiceClassInfo serviceClassInfo = new ServiceClassInfo(providerContainer, serviceClass);
			serviceClassInfos.put(serviceClass, serviceClassInfo);
		}
		return serviceClassInfos;
	}

}
