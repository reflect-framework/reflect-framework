package nth.reflect.fw.layer5provider.reflection.behavior.description;

import java.lang.reflect.Method;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.TextModel;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * Each {@link DomainObjectProperty} or {@link ActionMethod} can have a text to
 * explain the class member in more detail. This {@link DescriptionModel} is
 * often displayed in a graphical user interface as
 * <a href="https://en.wikipedia.org/wiki/Tooltip">tooltip</a> or can be used by
 * most
 * <a href="https://en.wikipedia.org/wiki/Computer_accessibility">accessibility
 * tools (e.g. for blind people)</a> when the user hovers over the
 * {@link DomainObjectProperty} or {@link ActionMethod} (menu item) or icon
 * </p>
 * 
 * <h3>Description Default</h3>
 * <p>
 * By default the description is the same as the default {@link DisplayName},
 * therefore it is recommended to override the default value where needed. You
 * can override the default value by by adding the {@link Description} in the
 * language property files (see {@link LanguageProvider}) or by adding a
 * description annotation.
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
public class DescriptionModel extends TextModel {

	public DescriptionModel(LanguageProvider languageProvider, Class<?> objectType, String simpleName,
			String canonicalName) {
		super(languageProvider, objectType, simpleName, canonicalName);
	}

	public DescriptionModel(LanguageProvider languageProvider, Method getterMethod, String simpleName,
			String canonicalName, String linkedPropertyName) {
		super(languageProvider, getterMethod, simpleName, canonicalName, linkedPropertyName);
	}

	public DescriptionModel(LanguageProvider languageProvider, Method getterMethod, String simpleName,
			String canonicalName) {
		super(languageProvider, getterMethod, simpleName, canonicalName);
	}

	@Override
	public String createKey(String canonicalName) {
		return canonicalName.concat(".description");
	}

	@Override
	public String getDefaultTextFromAnnotation(Class<?> objectType) {
		Description annotation = objectType.getAnnotation(Description.class);
		return (annotation == null) ? null : annotation.englishDescription();
	}

	@Override
	public String getDefaultTextFromAnnotation(Method method) {
		Description annotation = method.getAnnotation(Description.class);
		return (annotation == null) ? null : annotation.englishDescription();
	}

}
