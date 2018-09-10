package nth.reflect.fw.layer5provider.reflection.behavior.applicationicon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.url.classresource.ClassResourceUrl;

/**
 * <p>
 * You can define the {@link ApplicationIcon} by placing an {@link FontIcon}
 * annotation before the “class” of the {@link ReflectApplication}.
 * </p>
 * <p>
 * Syntax: {@link ApplicationIcon}(String iconURL)
 * </p>
 * <p>
 * Parameter iconURI: a URL to a image file. Best practice is to put the icon
 * file in the package of the {@link ReflectApplication} class and refer to it
 * using a {@link ClassResourceUrl}. TODO: example.
 * </p>
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface ApplicationIcon {
	public String iconURL();
}
