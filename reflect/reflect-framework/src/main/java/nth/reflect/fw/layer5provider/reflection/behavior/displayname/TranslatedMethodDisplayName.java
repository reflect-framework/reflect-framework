package nth.reflect.fw.layer5provider.reflection.behavior.displayname;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;

public class TranslatedMethodDisplayName extends TranslatedDisplayName {

	public TranslatedMethodDisplayName(LanguageProvider languageProvider, Method method, NameInfo nameInfo) {
		super(languageProvider, nameInfo, createDefaultEnglishDisplayName(method, nameInfo));
	}

	private static String createDefaultEnglishDisplayName(Method method, NameInfo nameInfo) {
		DisplayName annotation = method.getAnnotation(DisplayName.class);
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
