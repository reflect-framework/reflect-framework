package nth.reflect.fw.layer5provider.language;

import java.util.Locale;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.ConstructionInjection;
import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * The {@link LanguageProvider} provides support multiple languages. The
 * {@link ReflectFramework} supports the English language by default because it
 * uses the ClassNames, {@link DomainObjectProperty}Names and
 * {@link ActionMethod}names that are used in the code as part of the
 * <a href="http://martinfowler.com/bliki/UbiquitousLanguage.html">Ubiquitous
 * Language</a> (it does not make sense to mix an other language into your
 * code).
 * </p>
 * <h3>LanguageFiles</h3>{@insert LanguageFiles}
 * <p>
 * <h3>Getting the translated text</h3>
 * <p>
 * The {@link UserInterfaceController} will call the {@link LanguageProvider} to
 * try and get the appropriate text from the language property files (depending
 * on the selected language). If it can’t find this value it will display the
 * default values (in English)
 * </p>
 * <p>
 * An other way to get a translated text is to inject the
 * {@link LanguageProvider} into the object that needs it and use it in your
 * code (see {@link ConstructionInjection})
 * </p>
 * <ul>
 * <li>TODO Default English</li>
 * <li>TODO Annotations</li>
 * <li>TODO The use of {@link TranslatableString} and
 * {@link TranslatableException}</li>
 * <li>TODO EXPLAIN HOW TO GENERATE OR UPDATE LANGUAGE PROPERTY FILES WITH
 * {@link ReflectUtilMavenPlugin}:{@link UpdateLanguageFiles} goal</li>
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
