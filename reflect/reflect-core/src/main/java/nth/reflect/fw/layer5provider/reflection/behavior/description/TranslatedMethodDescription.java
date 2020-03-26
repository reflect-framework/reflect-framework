package nth.reflect.fw.layer5provider.reflection.behavior.description;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;

public class TranslatedMethodDescription extends TranslatedDescription {

	public TranslatedMethodDescription(LanguageProvider languageProvider, Method method, NameInfo nameInfo) {
		super(languageProvider, nameInfo, createDefaultEnglishDescription(method, nameInfo));
	}

	private static String createDefaultEnglishDescription(Method method, NameInfo nameInfo) {
		Description annotation = method.getAnnotation(Description.class);
		if (annotation == null) {
			String simpleName = nameInfo.getSimpleName();
			String defaultEnglishDescription = StringUtil.convertToNormalCase(simpleName);
			return defaultEnglishDescription;
		} else {
			String defaultEnglishDescription = annotation.englishDescription();
			return defaultEnglishDescription;
		}
	}

}
