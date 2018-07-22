package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import nth.reflect.fw.layer5provider.ProviderContainer;


public class ClassInfoFactory {

	public static ClassInfo create(ProviderContainer providerContainer, Class<?> domainOrServiceClass) {
		return new ClassInfo(providerContainer, domainOrServiceClass);
	}
	
}
