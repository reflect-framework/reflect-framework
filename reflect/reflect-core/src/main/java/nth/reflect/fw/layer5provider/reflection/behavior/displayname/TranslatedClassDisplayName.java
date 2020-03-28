package nth.reflect.fw.layer5provider.reflection.behavior.displayname;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;

public class TranslatedClassDisplayName extends TranslatedDisplayName {

	public TranslatedClassDisplayName(LanguageProvider languageProvider, Class<?> type, NameInfo nameInfo) {
		super(languageProvider, nameInfo, createDefaultEnglishDisplayName(type, nameInfo));
	}

	private static String createDefaultEnglishDisplayName(Class<?> objectType, NameInfo nameInfo) {
		DisplayName annotation = objectType.getAnnotation(DisplayName.class);
		if (annotation == null) {
			String simpleName = nameInfo.getSimpleName();
			String defaultEnglishDisplayName = StringUtil.convertToNormalCase(simpleName);
			return defaultEnglishDisplayName;
		} else {
			String defaultEnglishDisplayName = annotation.defaultEnglish();
			return defaultEnglishDisplayName;
		}
	}

}
