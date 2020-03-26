package nth.reflect.fw.layer5provider.language;

import java.util.Locale;
import java.util.ResourceBundle;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;

public class DefaultLanguageProvider implements LanguageProvider {
	private static final String PREFIX_LANGUAGE_FILE = "language";
	private static final String DOT = ".";
	private ResourceBundleClassLoader resourceBundleClassLoader;
	private Locale defaultLocale;

	public DefaultLanguageProvider() {
		defaultLocale = Locale.ENGLISH;
		resourceBundleClassLoader = new ResourceBundleClassLoader();
	}

	// TODO use when generating language files
	public String getLanguageFileComments() {
		StringBuffer MESSAGE = new StringBuffer();
		MESSAGE.append(
				"This file contains information for a specific language. Follow the following rules when modifying this file:\n");
		MESSAGE.append(
				"#- DO NOT modify or remove lines that start with a # character. These lines contain important rules\n");
		MESSAGE.append(
				"#- DO NOT remove lines! Each line contains information that is used by the applications and may not be removed\n");
		MESSAGE.append(
				"#- DO NOT change the text before the = character. These textst contain reference keys for the applications and may not be modified or removed\n");
		MESSAGE.append(
				"#- Prevent that the modified texts after the = character contain much more characters then the original texts. Use abbreviations to shorten the text when required.\n");
		MESSAGE.append(
				"#- Use unicode for special characters. In example: replace the ö character in löschen with \\u00F6. so: clear=l\\u00F6schen");
		return MESSAGE.toString();
	}

	@Override
	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	@Override
	public String getKey(Object obj) {
		return getKey(obj.toString());
	}

	@Override
	public String getKey(NameInfo nameInfo) {
		return nameInfo.getCanonicalName();
	}

	@Override
	public String getKey(Class<?> clasz) {
		return clasz.getCanonicalName();
	}

	@Override
	public String getKey(Enum<?> enumeration) {
		String enumName = enumeration.name();
		return enumeration.getClass().getCanonicalName() + "." + enumName.toString();
	}

	@Override
	public String getKey(String text) {
		StringBuilder key = new StringBuilder();
		boolean lastCharIsDot = false;
		for (int i = 0; i < text.length(); i++) {
			boolean isLastChar = i == text.length();
			char ch = text.charAt(i);
			if (isValidKeyChar(ch)) {
				key.append(ch);
				lastCharIsDot = ch == '.';
			} else if (!lastCharIsDot && !isLastChar) {
				key.append('.');
			}
		}
		return text;
	}

	private boolean isValidKeyChar(char ch) {
		return Character.isLetter(ch) || Character.isDigit(ch);
	}

	@Override
	public String getText(String defaultText) {
		return getText(defaultLocale, null, defaultText);
	}

	@Override
	public String getText(String key, String defaultText) {
		return getText(defaultLocale, key, defaultText);
	}

	@Override
	public String getText(Locale locale, String key, String defaultText) {
		String text = null;
		// check key
		if (key == null || key.length() == 0 || key.contains(" ")) {
			key = getKey(defaultText);
		}

		// try to get text
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(PREFIX_LANGUAGE_FILE, locale,
					resourceBundleClassLoader);
			text = resourceBundle.getString(key);
		} catch (Throwable e) {
		}
		// found it?
		if (text == null) {
			return defaultText;
		} else {
			return text;
		}
	}

//	public void appendToLanguageFile(Locale locale, String key,
//			String defaultValue) {
//		File file = getLanguageFile(locale);
//		SortedProperties properties = new SortedProperties();
//		try {
//			properties.load(new FileInputStream(file));
//		} catch (Exception e) {
//			// File did not exist. We will create a new file when needed. No big
//			// deal.
//		}
//		// add property
//		properties.put(key, defaultValue);
//
//		// store modified language file
//		try {
//			properties.store(new FileOutputStream(file),
//					getLanguageFileComments());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// also store for default locale
//		Locale defaultLocale = getDefaultLocale();
//		if (locale != defaultLocale) {
//			appendToLanguageFile(defaultLocale, key, defaultValue);
//		}
//
//	}

//	private File getLanguageFile(Locale locale) {
//		StringBuffer resourceName = new StringBuffer(PREFIX_LANGUAGE_FILE);
//		resourceName.append("_");
//		resourceName.append(locale.getLanguage());
//		resourceName.append(".properties");
//		URL propertyFileURL = resourceBundleClassLoader
//				.findResource(resourceName.toString());
//		return new File(propertyFileURL.getFile());
//	}

	@Override
	public String getDefaultValueFromKey(String key) {
		String value = null;
		if (key.contains(DOT)) {
			value = key.substring(key.lastIndexOf(DOT) + 1);
		} else {
			value = key;
		}
		return StringUtil.convertToNormalCase(value);
	}

	@Override
	public String getText(TranslatableString translatableString) {
		return getText(translatableString.getKey(), translatableString.getDefaultEnglish());
	}
}
