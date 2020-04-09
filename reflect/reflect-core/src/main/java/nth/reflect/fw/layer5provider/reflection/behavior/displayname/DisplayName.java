package nth.reflect.fw.layer5provider.reflection.behavior.displayname;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * The {@link ReflectFramework} will automatically convert the names used in the
 * program code to a human readable English format by default. In some cases the
 * default {@link DisplayName} does not suffice, in example when:
 * <ul>
 * <li>A different use of capital case is needed</li>
 * <li>Special characters are needed that can not be used in the code</li>
 * <li>The plural form of the default displayName of a ServiceObject is
 * incorrect</li>
 * </ul>
 * <p>
 * In these cases you can use a {@link DisplayName} annotation. The
 * {@link DisplayName} annotation is placed:
 * <ul>
 * <li>before the class keyword</li>
 * <li>before the getter method of a {@link DomainObjectProperty}</li>
 * <li>or before a {@link ActionMethod}</li>
 * </ul>
 * </p>
 * TODO EXAMPLE ACMEWebShop
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface DisplayName {
	public String defaultEnglish();
}
