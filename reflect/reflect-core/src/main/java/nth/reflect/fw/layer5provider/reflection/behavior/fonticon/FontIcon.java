package nth.reflect.fw.layer5provider.reflection.behavior.fonticon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrl;

/**
 * <p>
 * You can define the {@link FontIcon} by placing an {@link FontIcon} annotation before
 * the class key word of the {@link ServiceObject} or the {@link DomainObject}
 * or before the {@link ActionMethod}.
 * </p>
 * <p>
 * Syntax: {@link FontIcon}(String fontIconUrl)
 * </p>
 * <p>
 * Parameter fontIconUrl: See {@link FontIconUrl}
 * </p>
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface FontIcon {
	/**
	 * @return {@link FontIconUrl}
	 */
	public String fontIconUrl();
}
