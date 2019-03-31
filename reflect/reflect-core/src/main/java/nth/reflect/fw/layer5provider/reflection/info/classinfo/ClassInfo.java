package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.DescriptionModel;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayNameModel;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;

/**
 * <p>
 * Provides information on a {@link Class}
 * </p>
 * 
 * @author nilsth
 * 
 */
public abstract class ClassInfo implements NameInfo {

	private final String simpleName;
	private final String canonicalName;
	private final DescriptionModel descriptionModel;
	private final Class<?> objectClass;
	private final DisplayNameModel displayNameModel;

	public ClassInfo(ProviderContainer providerContainer, Class<?> objectClass) {
		LanguageProvider languageProvider = providerContainer.get(LanguageProvider.class);
		this.simpleName = objectClass.getSimpleName();
		this.canonicalName = objectClass.getCanonicalName();
		this.objectClass = objectClass;
		this.displayNameModel = new DisplayNameModel(languageProvider, objectClass, simpleName, canonicalName);
		this.descriptionModel = new DescriptionModel(languageProvider, objectClass, simpleName, canonicalName);
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

	@Override
	public String toString() {
		return canonicalName;
	}

}
