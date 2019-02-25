package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectActionMethod;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;

/**
 * <p>
 * {@link DomainObject}s may have {@link ActionMethod}s that do something with
 * the value of a {@link DomainObjectProperty}, e.g. modify it or display it or
 * open it in a browser. In this case the we need to annotate it with
 * the @{@link PropertyActionMethod} annotation to indicated that the
 * {@link ActionMethod} is an {@link DomainObjectPropertyActionMethod} and needs
 * to be added to a {@link PropertyPanelMenu}. You therefore must specify the
 * {@link DomainObjectProperty} name in the @{@link PropertyActionMethod}
 * annotation.
 * </p>
 * <p>
 * An {@link ActionMethod} in a {@link DomainObject} will be an
 * {@link DomainObjectActionMethod} and displayed in the {@link FormTabMenu} if
 * it does not have a @{@link PropertyActionMethod} annotation.
 * </p>
 * 
 * TODO example
 * 
 * @author nilsth
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PropertyActionMethod {
	/**
	 * @return name of the {@link DomainObjectProperty} that the
	 *         {@link ActionMethod} belongs to.
	 */
	public String value();

}
