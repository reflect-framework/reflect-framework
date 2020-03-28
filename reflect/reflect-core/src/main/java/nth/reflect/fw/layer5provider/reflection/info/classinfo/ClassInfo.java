package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.behavior.description.TranslatedClassDescription;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.TranslatedClassDisplayName;
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
	private final TranslatedString displayName;
	private final TranslatedString description;
	private final Class<?> type;

	public ClassInfo(ProviderContainer providerContainer, Class<?> type) {
		LanguageProvider languageProvider = providerContainer.get(LanguageProvider.class);
		this.simpleName = type.getSimpleName();
		this.canonicalName = type.getCanonicalName();
		this.type = type;
		this.displayName = new TranslatedClassDisplayName(languageProvider, type, this);
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

	public TranslatedString getDisplayName() {
		return displayName;
	}

	public TranslatedString getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return canonicalName;
	}

}
