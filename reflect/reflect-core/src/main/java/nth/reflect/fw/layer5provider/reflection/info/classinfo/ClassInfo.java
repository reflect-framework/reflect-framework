package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.behavior.description.TranslatedClassDescription;
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
	private final TranslatedString description;
	private final Class<?> type;
	private final DisplayNameModel displayNameModel;

	public ClassInfo(ProviderContainer providerContainer, Class<?> type) {
		LanguageProvider languageProvider = providerContainer.get(LanguageProvider.class);
		this.simpleName = type.getSimpleName();
		this.canonicalName = type.getCanonicalName();
		this.type = type;
		this.displayNameModel = new DisplayNameModel(languageProvider, type, simpleName, canonicalName);
		this.description = new TranslatedClassDescription(languageProvider, type, this);
	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	@Override
	public String getCanonicalName() {
		return canonicalName;
	}

	public Class<?> getType() {
		return type;
	}

	public String getDisplayName() {
		return displayNameModel.getText();
	}

	public TranslatedString getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return canonicalName;
	}

}
