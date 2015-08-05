package nth.introspect.layer5provider.reflection.behavior.description;

import java.lang.reflect.Method;

import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.behavior.TextModel;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * Each {@link DomainObjectProperty} or {@link ActionMethod} can have a text to
 * explain the class member in more detail. This description is often displayed
 * in a graphical user interface when the user hovers over the property or
 * action menu.
 * </p>
 * 
 * <h3>Description Default</h3>
 * <p>
 * By default the description is the same as the Description, therefore it is
 * recommended to override the default value where needed. You can override the
 * default value by defining the description in the language property files (see
 * {@link LanguageProvider}) or by adding a description annotation.
 * </p>
 * 
 *  <h3>Description Annotation</h3>
 * <p>
 * {@insert Description}
 * </p>
 * 
 * @author nilsth
 *
 */
public class DescriptionModel extends TextModel {

	public DescriptionModel(LanguageProvider languageProvider,
			Class<?> objectType, String simpleName, String canonicalName) {
		super(languageProvider, objectType, simpleName, canonicalName);
	}

	public DescriptionModel(LanguageProvider languageProvider,
			Method getterMethod, String simpleName, String canonicalName,
			String linkedPropertyName) {
		super(languageProvider, getterMethod, simpleName, canonicalName,
				linkedPropertyName);
	}

	public DescriptionModel(LanguageProvider languageProvider,
			Method getterMethod, String simpleName, String canonicalName) {
		super(languageProvider, getterMethod, simpleName, canonicalName);
	}

	@Override
	public String createKey(String canonicalName) {
		return canonicalName.concat(".description");
	}

	@Override
	public String getDefaultTextFromAnnotation(Class<?> objectType) {
		Description annotation = objectType.getAnnotation(Description.class);
		return (annotation == null) ? null : annotation.englishName();
	}

	@Override
	public String getDefaultTextFromAnnotation(Method method) {
		Description annotation = method.getAnnotation(Description.class);
		return (annotation == null) ? null : annotation.englishName();
	}




}
