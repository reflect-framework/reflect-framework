package nth.introspect.layer5provider.reflection.behavior.displayname;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * In some cases the default {@link DisplayName} does not suffice, in example when:
 * <ul>
 * <li>A different use of capital case is needed</li>
 * <li>Special characters are needed that can not be used in the code</li>
 * <li>The plural form of the default displayName of a ServiceObject is
 * incorrect</li>
 * </ul>
 * <p>
 * In these cases you can use the {@link DisplayName} annotation before the class
 * keyword, before the getter method of a property or before the ActionMethod.
 * </p>
 * TODO EXAMPLE ACMEWebShop
 * <p>
 * Note that the {@link DisplayName} annotation is intended for the English language only.
 * Use the {@link DisplayName} default if you want to use multiple languages.
 * </p>
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DisplayName {
	public String englishName();
}
