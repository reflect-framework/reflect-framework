package nth.introspect.layer5provider.reflection.behavior.displayname;

import java.lang.reflect.Method;

import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.behavior.TextModel;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * The {@link Class}Names, {@link DomainObjectProperty}Names and
 * {@link ActionMethod}Names are part of the <a
 * href="http://martinfowler.com/bliki/UbiquitousLanguage.html">ubiquitous
 * language</a> (in terms both understand by users and developers) and should
 * therefore match both the code and user interface.
 * </p>
 * 
 * <h3>DisplayName Default</h3>
 * <p>
 * {@link Class}Names, {@link DomainObjectProperty}Names and
 * {@link ActionMethod}Names in the <a
 * href="https://en.wikipedia.org/wiki/Codebase">codebase</a> use names such as
 * OrderService, orderLines, addOrderLine (using no spaces, <a
 * href="https://nl.wikipedia.org/wiki/CamelCase">camelCase</a> and no special
 * characters). If the user is a an human, more user friendly names are needed
 * such as “Orders” “Order lines” and “Add order line”. This format is called the {@link DisplayName}. The
 * {@link IntrospectFramework} will automatically convert the names used in the code  to a human
 * readable format ({@link DisplayName}) when needed.
 * </p>
 * <p>
 * The {@link IntrospectFramework} supports DisplayNames for multiple languages.
 * The {@link LanguageProvider} is used to get the default displayNames from a
 * language specific property files. For more information see
 * {@link LanguageProvider}.
 * </p>
 * 
 * <h3>DisplayName Annotation</h3>
 * <p>
 * {@insert DisplayName}
 * </p>
 * 
 * @author nilsth
 *
 */
public class DisplayNameModel extends TextModel {

	public DisplayNameModel(LanguageProvider languageProvider,
			Class<?> objectType, String simpleName, String canonicalName) {
		super(languageProvider, objectType, simpleName, canonicalName);
	}

	public DisplayNameModel(LanguageProvider languageProvider,
			Method getterMethod, String simpleName, String canonicalName,
			String linkedPropertyName) {
		super(languageProvider, getterMethod, simpleName, canonicalName,
				linkedPropertyName);
	}

	public DisplayNameModel(LanguageProvider languageProvider,
			Method getterMethod, String simpleName, String canonicalName) {
		super(languageProvider, getterMethod, simpleName, canonicalName);
	}


	@Override
	public String createKey(String canonicalName) {
		return canonicalName.concat(".displayname");
	}

	
	@Override
	public String getDefaultTextFromAnnotation(Class<?> objectType) {
		DisplayName annotation = objectType.getAnnotation(DisplayName.class);
		return (annotation == null) ? null : annotation.englishName();
	}

	@Override
	public String getDefaultTextFromAnnotation(Method method) {
		DisplayName annotation = method.getAnnotation(DisplayName.class);
		return (annotation == null) ? null : annotation.englishName();
	}

}
