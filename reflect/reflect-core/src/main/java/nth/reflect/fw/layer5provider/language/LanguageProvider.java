package nth.reflect.fw.layer5provider.language;

import java.util.Locale;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.ConstructionInjection;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.TranslatedDisplayName;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * The {@link LanguageProvider} provides support multiple languages.
 * <h3>English by default</h3>
 * <p>
 * The {@link ReflectFramework} supports the English language by default because
 * it uses the Class names, {@link DomainObjectProperty} names and
 * {@link ActionMethod} names that are used in the code as part of the
 * <a href="http://martinfowler.com/bliki/UbiquitousLanguage.html">Ubiquitous
 * Language</a> (in terms understood by both users and software developers). For
 * more information see {@link TranslatedDisplayName}.
 * <p>
 * <p>
 * <h3>DisplayName Annotation</h3>
 * <p>
 * In some cases the default English text is incorrect because the {@link Class}
 * name, {@link DomainObjectProperty} name or {@link ActionMethod} name is
 * incorrectly translated to the default English text by the
 * {@link ReflectFramework}. In this case you can correct this using a
 * {@link DisplayName} annotation.
 * </p>
 * <h3>How texts are translated to other languages</h3>
 * <p>
 * The {@link UserInterfaceController} will call the {@link LanguageProvider} to
 * try and get the appropriate text from the {@link LanguagePropertyFile}s
 * (depending on the selected language). If it can’t find this value it will
 * display the default values (in English)
 * <p>
 * <h3>Language property files</h3>{@insert LanguagePropertyFile}
 * <p>
 * <h3>Creating and updating {@link LanguagePropertyFile}s</h3>
 * <p>
 * See the {@link ReflectUtilMavenPlugin}:{@link UpdateLanguageFiles} goal, to
 * learn how you can create and update {@link LanguagePropertyFile}s
 * <p>
 * <h3>Using translations in your application</h3>
 * <p>
 * There are multiple ways to use translatable texts in your application:
 * <ul>
 * <li>{@insert TranslatableString}</li>
 * <li>{@insert TranslatableException}</li>
 * <li>By inject the {@link LanguageProvider} into the object that needs it and
 * use it in your code (see {@link ConstructionInjection})</li>
 * </ul>
 * 
 * @author nilsth
 *
 */

public interface LanguageProvider extends Provider {
	Locale getDefaultLocale();

	String getKey(String text);

	String getKey(Enum<?> enumeration);

	String getKey(Class<?> clasz);

	String getKey(NameInfo nameInfo);

	String getKey(Object obj);

	String getDefaultValueFromKey(String key);

	String getText(String defaultText);

	String getText(String key, String defaultText);

	String getText(TranslatableString translatableString);

	String getText(Locale locale, String key, String defaultText);

}
