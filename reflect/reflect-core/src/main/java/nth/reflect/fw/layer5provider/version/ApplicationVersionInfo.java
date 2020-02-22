package nth.reflect.fw.layer5provider.version;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.ProviderContainer;

public class ApplicationVersionInfo extends VersionInfo {


	private final List<VersionInfo> providerInfos;

	public ApplicationVersionInfo(ProviderContainer providerContainer) {
		super(getApplicationClass(providerContainer));
		this.providerInfos=createProviderInfos(providerContainer);
	}

	private static Class<?> getApplicationClass(ProviderContainer providerContainer) {
		ReflectApplication reflectApplication = providerContainer.get(ReflectApplication.class);
		return reflectApplication.getClass();
	}

	private List<VersionInfo> createProviderInfos(ProviderContainer providerContainer) {
		List<VersionInfo> providerInfos = new ArrayList();
		List<Class<?>> classesInProviderContainer = providerContainer.getAllClasses();
		for (Class<?> classInProviderContainer : classesInProviderContainer) {
			if (Provider.class.isAssignableFrom(classInProviderContainer)) {
				VersionInfo providerInfo = new VersionInfo(classInProviderContainer);
				providerInfos.add(providerInfo);
			}
		}
		return providerInfos;
	}

	public List<VersionInfo> getProviderInfos() {
		return providerInfos;
	}


}
