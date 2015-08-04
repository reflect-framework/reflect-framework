package nth.introspect.layer5provider.reflection.behavior.displayname;

import java.lang.reflect.Method;

import oracle.jrockit.jfr.tools.ConCatRepository;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.generic.util.StringUtil;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

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
 * such as “Orders” “Order lines” and “Add order line”. The
 * {@link IntrospectFramework} will therefore convert these names to a human
 * readable format when needed.
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
public class DisplayNameModel {

	private static final String SERVICE = "Service";
	private static final String REG_EXP_ENDING_WITH_SERVICE = SERVICE+"$";
	private static final String REG_EXP_ENDING_WITH_Y = "y$";
	private final String defaultDisplayName;
	private final String key;
	private final LanguageProvider languageProvider;



	public DisplayNameModel(LanguageProvider languageProvider,
			Class<?> objectType, String simpleName, String canonicalName) {

		this(languageProvider,
				createDefaultDisplayNameForClass(objectType, simpleName), canonicalName);
	}

	public DisplayNameModel(LanguageProvider languageProvider,
			Method getterMethod, String simpleName, String canonicalName) {
		this(languageProvider, createDefaultDisplayNameForProperty(getterMethod,
				simpleName), canonicalName);
	}
	public DisplayNameModel(LanguageProvider languageProvider,
			Method getterMethod, String simpleName, String canonicalName,String linkedPropertyName) {
		this(languageProvider, createDefaultDisplayNameForActionMethod(getterMethod,
				simpleName, linkedPropertyName), canonicalName);
	}
	private DisplayNameModel(LanguageProvider languageProvider,
			String defaultDisplayName, String canonicalName) {
		this.languageProvider = languageProvider;
		this.defaultDisplayName = defaultDisplayName;
		this.key = createKey(canonicalName);
	}

	private String createKey(String canonicalName) {
		return canonicalName.concat(".displayName");
	}

	private static String createDefaultDisplayNameForClass(Class<?> objectType,
			String simpleName) {
		DisplayName annotation = objectType.getAnnotation(DisplayName.class);
		if (annotation == null) {
			return createDefaultDisplayNameFromSimpleName(simpleName);
		} else {
			return annotation.englishName();
		}
	}

	private static String createDefaultDisplayNameFromSimpleName(
			String simpleName) {
		if (isServiceClassName(simpleName)) {
			return createServiceClassDisplayName(simpleName);
		} else {
			return StringUtil.convertToNormalCase(simpleName);
		}
	}

	private static String createServiceClassDisplayName(String simpleName) {
		String domainObjectName=removeServiceSuffix(simpleName);
		String displayName= plural(domainObjectName);
		return displayName;
	}

	private static String plural(String name) {
		String plural = name.replaceAll(REG_EXP_ENDING_WITH_Y, "ies");
		if (!plural.toLowerCase().endsWith("s")) {
			plural=plural.concat("s");
		}
		return plural;
	}

	private static String removeServiceSuffix(String simpleName) {
		return simpleName.replaceAll(REG_EXP_ENDING_WITH_SERVICE, "");
	}

	private static boolean isServiceClassName(String simpleName) {
		return simpleName.endsWith(SERVICE) && simpleName.length()>SERVICE.length();
	}

	private static String createDefaultDisplayNameForProperty(Method getterMethod,
			String simpleName) {
		DisplayName annotation = getterMethod.getAnnotation(DisplayName.class);
		if (annotation == null) {
			return StringUtil.convertToNormalCase(simpleName);
		} else {
			return annotation.englishName();
		}
	}
	
	private static String createDefaultDisplayNameForActionMethod(Method method,
			String simpleName, String linkedPropertyName) {
		DisplayName annotation = method.getAnnotation(DisplayName.class);
		if (annotation == null) {
			simpleName=removeProperyNameSuffixIfNeeded(simpleName, linkedPropertyName);
			return StringUtil.convertToNormalCase(simpleName);
		} else {
			return annotation.englishName();
		}
	}

	private static String removeProperyNameSuffixIfNeeded(
			String displayName, String propertyName) {
		if (propertyName!=null && displayName.endsWith(propertyName)) {
			return displayName.substring(0,displayName.length()-propertyName.length());
		}	else {
			return displayName;
		}
		
	}

	public String getDisplayName() {
		return languageProvider.getText(key, defaultDisplayName);
	}
}
