package nth.reflect.fw.layer5provider.reflection.behavior.description;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;
import nth.reflect.util.english.plural.EnglishPlural;

public class TranslatedServiceClassDescription extends TranslatedDescription {

	private static final String SERVICE = "Service";
	private static final String REG_EXP_ENDING_WITH_SERVICE = SERVICE + "$";

	public TranslatedServiceClassDescription(LanguageProvider languageProvider, Class<?> objectType,
			NameInfo nameInfo) {
		super(languageProvider, nameInfo, createDefaultEnglishDescription(objectType, nameInfo));
	}

	private static String createDefaultEnglishDescription(Class<?> objectType, NameInfo nameInfo) {
		Description annotation = objectType.getAnnotation(Description.class);
		if (annotation == null) {
			String simpleName = nameInfo.getSimpleName();
			simpleName = removeServiceSuffix(simpleName);
			simpleName = EnglishPlural.fromSingularNoun(simpleName);
			String defaultEnglishDescription = StringUtil.convertToNormalCase(simpleName);
			return defaultEnglishDescription;
		} else {
			String defaultEnglishDescription = annotation.defaultEnglish();
			return defaultEnglishDescription;
		}
	}

	private static String removeServiceSuffix(String simpleName) {
		return simpleName.replaceAll(REG_EXP_ENDING_WITH_SERVICE, "");
	}

}
