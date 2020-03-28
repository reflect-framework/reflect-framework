package nth.reflect.fw.layer5provider.reflection.behavior.description;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * A {@link DomainObject}, {link DomainObjectProperty} or {@link ActionMethod}
 * can have a text to explain the class member in more detail. This
 * {@link TranslatedDescription} is often displayed in a graphical user
 * interface as <a href="https://en.wikipedia.org/wiki/Tooltip">tooltip</a> or
 * can be used by most
 * <a href="https://en.wikipedia.org/wiki/Computer_accessibility">accessibility
 * tools (e.g. for blind people)</a> when the user hovers over the
 * {@link DomainObjectProperty} or {@link ActionMethod} (menu item) or icon
 * </p>
 * 
 * <h3>Description Default</h3>
 * <p>
 * By default the description is the same as the default {@link DisplayName},
 * therefore it is recommended to override the default value where needed. You
 * can override the default value by by adding the description in the language
 * property files (see {@link LanguageProvider}) or by adding a
 * {@link Description} annotation.
 * </p>
 * 
 * <h3>Description Annotation</h3>
 * <p>
 * {@insert Description}
 * </p>
 * 
 * @author nilsth
 *
 */
public abstract class TranslatedDescription extends TranslatedString {

	public final static String DESCRIPTION_KEY_SUFFIX = ".description";

	public TranslatedDescription(LanguageProvider languageProvider, NameInfo nameInfo, String defaultEnglish) {
		super(languageProvider, createKey(nameInfo), defaultEnglish);
	}

	private static String createKey(NameInfo nameInfo) {
		return nameInfo.getCanonicalName() + DESCRIPTION_KEY_SUFFIX;
	}

}
