package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import java.net.URL;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.behavior.applicationicon.ApplicationIconModel;
import nth.reflect.fw.layer5provider.reflection.behavior.applicationicon.ApplicationIconModelFactory;

/**
 * <p>
 * Provides information on a {@link ReflectApplication}
 * </p>
 * 
 * @author nilsth
 * 
 */
public class ApplicationClassInfo extends ClassInfo {

	private final ApplicationIconModel iconModel;

	public ApplicationClassInfo(ProviderContainer providerContainer, ReflectApplication reflectApplication) {
		super(providerContainer, reflectApplication.getClass());
		this.iconModel = ApplicationIconModelFactory.create(reflectApplication);

	}

	public URL getIcon() {
		return iconModel.getUrl();
	}

}
