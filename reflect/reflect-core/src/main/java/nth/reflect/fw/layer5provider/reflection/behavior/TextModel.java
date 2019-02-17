package nth.reflect.fw.layer5provider.reflection.behavior;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public abstract class TextModel {

	private final String defaultText;
	private final String key;
	private final LanguageProvider languageProvider;

	private static final String SERVICE = "Service";
	private static final String REG_EXP_ENDING_WITH_SERVICE = SERVICE + "$";
	private static final String REG_EXP_ENDING_WITH_Y = "y$";

	public TextModel(LanguageProvider languageProvider, Class<?> objectType, String simpleName, String canonicalName) {

		this.languageProvider = languageProvider;
		this.defaultText = createDefaultTextForClass(objectType, simpleName);
		this.key = createKey(canonicalName);
	}

	public TextModel(LanguageProvider languageProvider, Method getterMethod, String simpleName, String canonicalName) {
		this.languageProvider = languageProvider;
		this.defaultText = createDefaultTextForProperty(getterMethod, simpleName);
		this.key = createKey(canonicalName);
	}

	public abstract String createKey(String canonicalName);

	private String createDefaultTextForClass(Class<?> objectType, String simpleName) {
		String defaultTextFromAnnotation = getDefaultTextFromAnnotation(objectType);
		if (defaultTextFromAnnotation == null) {
			return createDefaultTextForClass(simpleName);
		} else {
			return defaultTextFromAnnotation;
		}
	}

	public abstract String getDefaultTextFromAnnotation(Class<?> objectType);

	private static String createDefaultTextForClass(String simpleName) {
		if (isServiceClassName(simpleName)) {
			return createServiceClassText(simpleName);
		} else {
			return StringUtil.convertToNormalCase(simpleName);
		}
	}

	private static String createServiceClassText(String simpleName) {
		String domainObjectName = removeServiceSuffix(simpleName);
		String plural = plural(domainObjectName);
		String text = StringUtil.convertToNormalCase(plural);
		return text;
	}

	private static String plural(String name) {
		String plural = name.replaceAll(REG_EXP_ENDING_WITH_Y, "ies");
		if (!plural.toLowerCase().endsWith("s")) {
			plural = plural.concat("s");
		}
		return plural;
	}

	private static String removeServiceSuffix(String simpleName) {
		return simpleName.replaceAll(REG_EXP_ENDING_WITH_SERVICE, "");
	}

	private static boolean isServiceClassName(String simpleName) {
		return simpleName.endsWith(SERVICE) && simpleName.length() > SERVICE.length();
	}

	private String createDefaultTextForProperty(Method getterMethod, String simpleName) {
		String defaultTextFromAnnotation = getDefaultTextFromAnnotation(getterMethod);
		if (defaultTextFromAnnotation == null) {
			return StringUtil.convertToNormalCase(simpleName);
		} else {
			return defaultTextFromAnnotation;
		}
	}

	public abstract String getDefaultTextFromAnnotation(Method method);

	public String getText() {
		return languageProvider.getText(key, defaultText);
	}

}
