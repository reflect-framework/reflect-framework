package nth.introspect.provider.language;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.IntrospectionInfo;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.valuemodel.impl.TextValue;
import nth.introspect.util.StringUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;
import nth.introspect.valuemodel.ValueModels;

public class DefaultLanguageProvider implements LanguageProvider {
	private static final String PREFIX_LANGUAGE_FILE = "language";
	private static final String DOT = ".";
	private ResourceBundleClassLoader resourceBundleClassLoader;
	private Locale defaultLocale = Locale.ENGLISH;// TODO move to constructor?
	private final String LANGUAGE_FILE_COMMENTS;
	private final DomainInfoProvider domainInfoProvider;

	public DefaultLanguageProvider(DomainInfoProvider domainInfoProvider) {
		this.domainInfoProvider = domainInfoProvider;
		resourceBundleClassLoader = new ResourceBundleClassLoader();
		LANGUAGE_FILE_COMMENTS = getLanguageFileComments();
	}

	// TODO remove the following lines when not needed
	// **
	// * This method can be used to create or update a language configuration file to support multiple language. Follow the following procedure:<br>
	// * - call this method for each new or existing language file and dor each domain and service class <br>
	// * - open this file and translate the texts that are marked with the a * behind the = sign to the required language.<br>
	// *
	// * @param locale
	// * The locale is used to determain the property file
	// * @param domainOrServiceClass
	// * a source of default information to merge into a language property file.
	// */
	// public void mergePropertyFile(Locale locale, Class<?> domainOrServiceClass) throws IOException {
	// File languageFile = getLanguageFile(locale);
	// mergePropertyFile(languageFile, domainOrServiceClass);
	// }

	// /**
	// * This method can be used to create or update a language configuration file to support multiple language. Follow the following procedure:<br>
	// * - call this method for each new or existing language file and dor each domain and service class <br>
	// * - open this file and translate the texts that are marked with the a * behind the = sign to the required language.<br>
	// *
	// * @param propertiesFile
	// * name and path of the new or to be merged properties file. The file name is normaly language_X.properties (replace X with the country code)<br>
	// * @param domainOrServiceClass
	// * a source of default information to merge into a language property file.
	// */
	// public void mergePropertyFile(File propertiesFile, Class<?> domainOrServiceClass) throws IOException {
	// // read existing properties
	// SortedProperties properties = new SortedProperties();
	// try {
	// properties.load(new FileInputStream(propertiesFile));
	// } catch (FileNotFoundException e) {
	// // File did not exist, maybe we have to create a new file. No big deal.
	// }
	// // get default properties
	// Properties defaultProperties = getDefaultProperties(domainOrServiceClass);
	// // if the propertyFile in none english then mark new properties with a * to indicate that these still need to be translated
	// String mark = "";
	// if (!propertiesFile.getName().contains("_en")) {
	// mark = "*";
	// }
	// // merge properies
	// for (Object key : defaultProperties.keySet()) {
	// if (!properties.containsKey(key)) {
	// properties.put(key, mark + defaultProperties.get(key));
	// }
	// }
	// // create set of rules for property file
	// String MESSAGE = getLanguageFileComments();
	// // store modified language file
	// properties.store(new FileOutputStream(propertiesFile), MESSAGE.toString());
	// }

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

	/**
	 * This method collects the default texts that can be used to create a language configuration file to support multi language.
	 * 
	 * @param domainOrServiceClass
	 *            a source of default information to merge into a language property file.
	 */
	public Properties getDefaultProperties(Class<?> domainOrServiceClass) {
		Properties properties = new Properties();
		for (PropertyInfo propertyInfo : domainInfoProvider.getPropertyInfos(domainOrServiceClass)) {
			ValueModels valueModels = propertyInfo.getValueModels();
			for (String name : valueModels.keySet()) {
				ReadOnlyValueModel valueModel = valueModels.get(name);
				if (valueModel instanceof TextValue) {
					TextValue textValueModel = (TextValue) valueModel;
					properties.put(textValueModel.getKey(), textValueModel.getDefaultValue());
				}
			}
		}
		return properties;
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
		if (obj instanceof IntrospectionInfo) {
			IntrospectionInfo introspectionInfo = (IntrospectionInfo) obj;
			return introspectionInfo.getNamePath();
		} else if (obj instanceof Class<?>) {
			Class<?> claz = (Class<?>) obj;
			return claz.getCanonicalName();
		} else if (obj instanceof String) {
			String string = (String) obj;
			StringUtil.convertToCamelCase(string, false);
		} else if (obj.getClass().isEnum()) {
			return obj.getClass().getCanonicalName() + "." + obj.toString();
		}
		return StringUtil.convertToCamelCase(obj.toString(), false);
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
			ResourceBundle resourceBundle = ResourceBundle.getBundle(PREFIX_LANGUAGE_FILE, locale, resourceBundleClassLoader);
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

	public void appendToLanguageFile(Locale locale, String key, String defaultValue) {
		File file = getLanguageFile(locale);
		SortedProperties properties = new SortedProperties();
		try {
			properties.load(new FileInputStream(file));
		} catch (Exception e) {
			// File did not exist. We will create a new file when needed. No big deal.
		}
		// add property
		properties.put(key, defaultValue);

		// store modified language file
		try {
			properties.store(new FileOutputStream(file), LANGUAGE_FILE_COMMENTS);
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
		URL propertyFileURL = resourceBundleClassLoader.findResource(resourceName.toString());
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
