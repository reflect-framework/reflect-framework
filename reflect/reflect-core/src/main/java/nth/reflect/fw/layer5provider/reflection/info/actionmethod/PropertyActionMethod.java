package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectActionMethod;
import nth.reflect.fw.layer3domain.DomainObjectMenu;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;
import nth.reflect.fw.layer3domain.DomainObjectPropertyMenu;

/**
 * <p>
 * {@link DomainObject}s may have {@link ActionMethod}s that do something with
 * the value of a {@link DomainObjectProperty}.
 * </p>
 * <p>
 * If the {@link ActionMethod} is not annotated with
 * the @{@link PropertyActionMethod} than the {@link ActionMethod} is an
 * {@link DomainObjectActionMethod} and is displayed in a
 * {@link DomainObjectMenu}.
 * </p>
 * <p>
 * If the {@link ActionMethod} is annotated with
 * the @{@link PropertyActionMethod} than the {@link ActionMethod} is an
 * {@link DomainObjectPropertyActionMethod} and is displayed in a
 * {@link DomainObjectPropertyMenu}. The {@link PropertyActionMethod} will
 * contain the {@link DomainObjectProperty} name.
 * </p>
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
