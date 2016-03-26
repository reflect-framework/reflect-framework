package nth.introspect.layer5provider.language;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import nth.introspect.generic.util.StringUtil;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.info.NameInfo;

public class DefaultLanguageProvider implements LanguageProvider {
	private static final String PREFIX_LANGUAGE_FILE = "language";
	private static final String DOT = ".";
	private ResourceBundleClassLoader resourceBundleClassLoader;
	private Locale defaultLocale;

	public DefaultLanguageProvider(PathProvider pathProvider) {
		defaultLocale = Locale.ENGLISH;
		resourceBundleClassLoader = new ResourceBundleClassLoader(pathProvider);
	}

	private String getLanguageFileComments() {
		StringBuffer MESSAGE = new StringBuffer();
		MESSAGE.append("This file contains information for a specific language. Follow the following rules when modifying this file:\n");
		MESSAGE.append("#- DO NOT modify or remove lines that start with a # character. These lines contain important rules\n");
		MESSAGE.append("#- DO NOT remove lines! Each line contains information that is used by the applications and may not be removed\n");
		MESSAGE.append("#- DO NOT change the text before the = character. These textst contain reference keys for the applications and may not be modified or removed\n");
		MESSAGE.append("#- Prevent that the modified texts after the = character contain much more characters then the original texts. Use abbreviations to shorten the text when required.\n");
		MESSAGE.append("#- Use unicode for special characters. In example: replace the ö character in löschen with \\u00F6. so: clear=l\\u00F6schen");
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
		String key = null;
		if (obj instanceof NameInfo) {
			NameInfo introspectionInfo = (NameInfo) obj;
			key = introspectionInfo.getCanonicalName();
		} else if (obj instanceof Class<?>) {
			Class<?> claz = (Class<?>) obj;
			key = claz.getCanonicalName();
		} else if (obj.getClass().isEnum()) {
			String enumName = ((Enum<?>)obj).name();
			key = obj.getClass().getCanonicalName() + "." + enumName.toString();
		} else {
			key = obj.toString();
		}
		if (!key.matches("[a-zA-Z0-9._]+")) {
			throw new IllegalKeyFormat(key);
		}
		return key;
	}

	@Override
	public String getText(String defaultText) {
		return getText(defaultLocale, null, defaultText);
	}

	@Override
	public String getText(String key, String defaultText) {
		return getText(defaultLocale, key, defaultText);
	}

	public String getText(Locale locale, String key, String defaultText) {
		String text = null;
		// check default text
		if (defaultText == null || defaultText.length() == 0) {
			throw new IllegalArgumentException("Invalid default text");
		}
		// check key
		if (key == null || key.length() == 0 || key.contains(" ")) {
			key = StringUtil.convertToCamelCase(defaultText, false);
		} else if (key.contains(" ")) {
			throw new IllegalArgumentException("Invalid key");
		}
		// try to get text
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(
					PREFIX_LANGUAGE_FILE, locale, resourceBundleClassLoader);
			text = resourceBundle.getString(key);
		} catch (Throwable e) {
		}
		// found it?
		if (text == null) {
			// TODO reactivate the following 2 lines
			// store new key with value in language file(s)
			// appendToLanguageFile(locale, key, defaultText);
			// reload all language files
			// ResourceBundle.clearCache();
			// return default value
			return defaultText;
		} else {
			// return found value
			return text;
		}
	}

	public void appendToLanguageFile(Locale locale, String key,
			String defaultValue) {
		File file = getLanguageFile(locale);
		SortedProperties properties = new SortedProperties();
		try {
			properties.load(new FileInputStream(file));
		} catch (Exception e) {
			// File did not exist. We will create a new file when needed. No big
			// deal.
		}
		// add property
		properties.put(key, defaultValue);

		// store modified language file
		try {
			properties.store(new FileOutputStream(file),
					getLanguageFileComments());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// also store for default locale
		Locale defaultLocale = getDefaultLocale();
		if (locale != defaultLocale) {
			appendToLanguageFile(defaultLocale, key, defaultValue);
		}

	}

	private File getLanguageFile(Locale locale) {
		StringBuffer resourceName = new StringBuffer(PREFIX_LANGUAGE_FILE);
		resourceName.append("_");
		resourceName.append(locale.getLanguage());
		resourceName.append(".properties");
		URL propertyFileURL = resourceBundleClassLoader
				.findResource(resourceName.toString());
		return new File(propertyFileURL.getFile());
	}

	@Override
	public String getDefaultValue(String key) {
		String value = null;
		if (key.contains(DOT)) {
			value = key.substring(key.lastIndexOf(DOT) + 1);
		} else {
			value = key;
		}
		return StringUtil.convertToNormalCase(value);
	}
}
