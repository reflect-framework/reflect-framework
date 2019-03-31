package nth.reflect.fw.layer5provider.reflection;

import java.util.HashMap;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfoFactory;

public class DefaultReflectionProvider implements ReflectionProvider {

	private final ProviderContainer providerContainer;
	private final ApplicationClassInfo applicationClassInfo;
	private final HashMap<Class<?>, ServiceClassInfo> serviceClassInfos;
	private final HashMap<Class<?>, DomainClassInfo> domainClassInfos;
	private final ReflectApplication reflectApplication;

	public DefaultReflectionProvider(ProviderContainer providerContainer) {
		this.providerContainer = providerContainer;
		reflectApplication = providerContainer.get(ReflectApplication.class);
		applicationClassInfo = new ApplicationClassInfo(providerContainer, reflectApplication);
		serviceClassInfos = new HashMap<>();
		domainClassInfos = new HashMap<>();
	}

	@Override
	public ApplicationClassInfo getApplicationClassInfo() {
		return applicationClassInfo;
	}

	@Override
	public DomainClassInfo getDomainClassInfo(Class<?> objectClass) {
		if (!domainClassInfos.containsKey(objectClass)) {
			DomainClassInfo domainClassInfo = new DomainClassInfo(providerContainer, objectClass);
			domainClassInfos.put(objectClass, domainClassInfo);
		}
		return domainClassInfos.get(objectClass);
	}

	@Override
	public ServiceClassInfo getServiceClassInfo(Class<?> serviceClass) {
		if (serviceClassInfos.size() == 0) {
			serviceClassInfos.putAll(ServiceClassInfoFactory.createAll(providerContainer, reflectApplication));
		}
		return serviceClassInfos.get(serviceClass);
	}

}
