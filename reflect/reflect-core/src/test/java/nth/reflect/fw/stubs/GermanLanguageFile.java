package nth.reflect.fw.stubs;

import java.util.Locale;
import java.util.ResourceBundle;

import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.ResourceBundleClassLoader;

/**
 * Gets values from language_ge.properties file for junit testing
 * 
 * @author nilsth
 *
 */
public class GermanLanguageFile {

	static ResourceBundle resourceBundle;

	static {
		ResourceBundleClassLoader resourceBundleClassLoader = new ResourceBundleClassLoader();
		resourceBundle = ResourceBundle.getBundle(DefaultLanguageProvider.PREFIX_LANGUAGE_FILE, Locale.GERMAN,
				resourceBundleClassLoader);

	}

	public static String get(String key) {
		if (!resourceBundle.containsKey(key)) {
			throw new RuntimeException("Could not find key: " + key + " in German language file.");
		}
		return resourceBundle.getString(key);
	}

}
