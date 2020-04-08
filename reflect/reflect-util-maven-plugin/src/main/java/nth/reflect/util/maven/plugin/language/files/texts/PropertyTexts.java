package nth.reflect.util.maven.plugin.language.files.texts;

import java.util.Properties;

public class PropertyTexts extends Texts {

	private static final long serialVersionUID = 7331727744942392597L;

	public PropertyTexts(Properties properties) {
		for (final String key : properties.stringPropertyNames()) {
			put(key, properties.getProperty(key));
		}
	}

}
