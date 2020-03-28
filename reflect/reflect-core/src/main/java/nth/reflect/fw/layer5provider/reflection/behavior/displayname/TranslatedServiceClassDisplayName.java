package nth.reflect.fw.layer5provider.reflection.behavior.displayname;

import nth.reflect.fw.generic.plural.EnglishPlural;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;

public class TranslatedServiceClassDisplayName extends TranslatedDisplayName {

	private static final String SERVICE = "Service";
	private static final String REG_EXP_ENDING_WITH_SERVICE = SERVICE + "$";

	public TranslatedServiceClassDisplayName(LanguageProvider languageProvider, Class<?> objectType,
			NameInfo nameInfo) {
		super(languageProvider, nameInfo, createDefaultEnglishDisplayName(objectType, nameInfo));
	}

	private static String createDefaultEnglishDisplayName(Class<?> objectType, NameInfo nameInfo) {
		DisplayName annotation = objectType.getAnnotation(DisplayName.class);
		if (annotation == null) {
			String simpleName = nameInfo.getSimpleName();
			simpleName = removeServiceSuffix(simpleName);
			simpleName = EnglishPlural.fromSingularNoun(simpleName);
			String defaultEnglishDisplayName = StringUtil.convertToNormalCase(simpleName);
			return defaultEnglishDisplayName;
		} else {
			String defaultEnglishDisplayName = annotation.defaultEnglish();
			return defaultEnglishDisplayName;
		}
	}

	private static String removeServiceSuffix(String simpleName) {
		return simpleName.replaceAll(REG_EXP_ENDING_WITH_SERVICE, "");
	}

}
