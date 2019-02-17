package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyMenu;
import nth.reflect.fw.layer3domain.FormMenu;

/**
 * <p>
 * {@link DomainObject}s may have {@link ActionMethod}s that do something with
 * the value of a {@link DomainObjectProperty} (e.g. manipulate it).
 * </p>
 * <p>
 * These action methods are displayed as a menu item on the {@link FormMenu} or
 * on the {@link DomainObjectPropertyMenu}. You need to annotate the
 * {@link ActionMethod} with the @{@link PropertyActionMethod} annotation if it
 * needs to be displayed in the {@link DomainObjectPropertyMenu}.
 * </p>
 * 
 * 
 * 
 * 
 * @author nilsth
 *
 */

// TODO include this in DomainObjectPropertyActionMethod documentation

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PropertyActionMethod {
	/**
	 * @return name of the {@link DomainObjectProperty} that the
	 *         {@link ActionMethod} belongs to.
	 */
	public String value();

}
