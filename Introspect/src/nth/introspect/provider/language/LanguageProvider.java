package nth.introspect.provider.language;

import java.util.Locale;

import nth.introspect.provider.Provider;


public interface LanguageProvider extends Provider {
	public Locale getDefaultLocale();

	public String getKey(Object obj);

	public String getDefaultValue(String key);
	
	public String getText(String defaultText);

	public String getText(String key, String defaultText);

	public String getText(Locale locale, String key, String defaultText);

}
