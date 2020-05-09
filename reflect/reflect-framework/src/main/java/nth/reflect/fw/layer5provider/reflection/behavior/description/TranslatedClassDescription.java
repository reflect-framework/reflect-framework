package nth.reflect.fw.layer5provider.reflection.behavior.description;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;

public class TranslatedClassDescription extends TranslatedDescription {

	public TranslatedClassDescription(LanguageProvider languageProvider, Class<?> type, NameInfo nameInfo) {
		super(languageProvider, nameInfo, createDefaultEnglishDescription(type, nameInfo));
	}

	private static String createDefaultEnglishDescription(Class<?> objectType, NameInfo nameInfo) {
		Description annotation = objectType.getAnnotation(Description.class);
		if (annotation == null) {
			String simpleName = nameInfo.getSimpleName();
			String defaultEnglishDescription = StringUtil.convertToNormalCase(simpleName);
			return defaultEnglishDescription;
		} else {
			String defaultEnglishDescription = annotation.defaultEnglish();
			return defaultEnglishDescription;
		}
	}

}
