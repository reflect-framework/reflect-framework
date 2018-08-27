package nth.reflect.fw.layer5provider.reflection.info.appinfo;

import java.net.URL;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.applicationicon.ApplicationIconModel;
import nth.reflect.fw.layer5provider.reflection.behavior.applicationicon.ApplicationIconModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.description.DescriptionModel;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayNameModel;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;

/**
 * <p>
 * Provides information on a {@link ReflectApplication}
 * </p>
 * 
 * @author nilsth
 * 
 */
public class ApplicationInfo implements NameInfo {

	private final String simpleName;
	private final String canonicalName;
	private final DescriptionModel descriptionModel;
	private final Class<?> objectClass;
	private final DisplayNameModel displayNameModel;
	private final ApplicationIconModel iconModel;

	public ApplicationInfo(ProviderContainer providerContainer,
			ReflectApplication reflectApplication) {
		LanguageProvider languageProvider = providerContainer.get(LanguageProvider.class);
		Class<? extends ReflectApplication> reflectApplicationClass = reflectApplication.getClass();
		this.simpleName = reflectApplicationClass.getSimpleName();
		this.canonicalName = reflectApplicationClass.getCanonicalName();
		this.objectClass = reflectApplicationClass;
		this.displayNameModel = new DisplayNameModel(languageProvider, reflectApplicationClass, simpleName,
				canonicalName);
		this.descriptionModel = new DescriptionModel(languageProvider, reflectApplicationClass, simpleName,
				canonicalName);
		this.iconModel=ApplicationIconModelFactory.create(reflectApplication);
		
	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	@Override
	public String getCanonicalName() {
		return canonicalName;
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}

	public String getDisplayName() {
		return displayNameModel.getText();
	}

	public String getDescription() {
		return descriptionModel.getText();
	}

	public URL getIcon() {
		return iconModel.getUrl();
	}
	
	@Override
	public String toString() {
		return canonicalName;
	}

}
