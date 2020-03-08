package nth.reflect.fw.layer5provider.language;

import java.util.Locale;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.ConstructionInjection;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * The {@link LanguageProvider} makes multilingual support possible. The
 * {@link ReflectFramework} supports the English language by default because it
 * uses the ClassNames, {@link DomainObjectProperty}Names and
 * {@link ActionMethod}names that are used in the code as part of the
 * <a href="http://martinfowler.com/bliki/UbiquitousLanguage.html">Ubiquitous
 * Language</a> (it does not make sense to mix an other language into your
 * code).
 * </p>
 * <h3>Language property files</h3>
 * <p>
 * Texts for other languages are stored in property files. The name of these
 * files need to be: &lt;application configuration
 * folder&gt;/language_&lt;language_code&gt;.properties
 * </p>
 * Where:
 * <ul>
 * <li>&lt;application configuration folder&gt; These files need to be located
 * at the configuration folder of the application. See PathProvider for more
 * information</li>
 * <li>&lt;Language_code&gt; two letter language code (see java
 * {@link Locale})</li>
 * </ul>
 * 
 * <p>
 * The language property files comply to the Java
 * <a href="https://en.wikipedia.org/wiki/.properties">.properties</a> standard
 * and thus contain key value pairs.
 * <ul>
 * <li>The key is an reference to part of the code. Keys have the following
 * format: &lt;packageName&gt;.&lt;classname&gt; or
 * &lt;packageName&gt;.&lt;classname&gt;.&lt;propertyName&gt; or
 * &lt;packageName&gt;.&lt;classname&gt;.&lt;actionMethodName&gt;, followed by
 * .displayname or.description (see examples below)</li>
 * <li>The value is the translated text. Special characters are supported with
 * Unicode (&#92;u<number> e.g. &#92;u20AC=€). There are several free tools
 * available to edit .properties file with good Unicode support.</li>
 * </ul>
 * </p>
 * <p>
 * Examples of key value lines in property files:
 * <ul>
 * <li>com.acme.order.ShoppingCart.displayname=Winkel wagen</li>
 * <li>com.acme.product.ProductService.displayname=Producten</li>
 * </ul>
 * 
 * <h3>Getting the translated text</h3>
 * <p>
 * The {@link UserInterfaceController} will call the {@link LanguageProvider} to
 * try and get the appropriate text from the language property files (depending
 * on the selected language in the graphical user interface). If it can’t find
 * this value it will display the default values (in English)
 * </p>
 * <p>
 * An other way to get a translated text is to inject the
 * {@link LanguageProvider} into the object that needs it and use it in your
 * code (see {@link ConstructionInjection})
 * </p>
 * <ul>
 * <li>TODO CODE EXAMPLE OF VALIDATION TEXTS OR EXCEPTION TEXTS</li>
 * <li>TODO EXPLAIN HOW THE LANGUAGE IS SELECTED</li>
 * <li>TODO HOW TO SET DEFAULT TEXT OTHER THAN DEFAULT</li>
 * <li>TODO EXPLAIN HOW TO GENERATE OR UPDATE LANGUAGE PROPERTY FILES WITH A
 * SPECIAL {@link ReflectApplication}</li>
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
