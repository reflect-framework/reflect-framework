package nth.reflect.fw.layer5provider.language;

import java.util.Locale;

import nth.reflect.fw.layer5provider.url.UrlProvider;

/**
 * Texts for other languages are stored in property files. The name of these
 * files need to be: &lt;application configuration
 * folder&gt;/language_&lt;language_code&gt;.properties
 * </p>
 * <ul>
 * <li>&lt;application configuration folder&gt; These files need to be located
 * at the configuration folder of the application. These files are often stored
 * in the src/main/resources folder. See {@link UrlProvider} for more
 * information</li>
 * <li>&lt;Language_code&gt; two letter language code (see java
 * {@link Locale})</li>
 * </ul>
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
 * @author nilsth
 *
 */
public interface LanguagePropertyFile {

}
