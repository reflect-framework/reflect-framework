package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectActionMethod;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;
import nth.reflect.fw.layer3domain.DomainObjectPropertyMenu;

/**
 * <p>
 * Most {@link ActionMethod}s can modify values in a {@link DomainObject}, e.g.
 * change a {@link DomainObjectProperty} value. In this case the
 * {@link DomainObjectActionMethod} or {@link DomainObjectPropertyActionMethod}
 * is only visible in the {@link DomainObjectPropertyMenu} when the
 * {@link DomainObject} is edited in a {@link FormTab} ({@link FormMode#EDIT}).
 * </p>
 * <p>
 * You can annotate an {@link ActionMethod} with
 * the @{@link ReadOnlyActionMethod} annotation if the {@link ActionMethod} does
 * not not modify the {@link DomainObject}. In this case the
 * {@link DomainObjectActionMethod} or {@link DomainObjectPropertyActionMethod}
 * is also visible when the {@link DomainObject} is viewed in a {@link FormTab}
 * ({@link FormMode#READ_ONLY})
 * </p>
 * 
 * @author nilsth
 *
 */

// TODO include this in DomainObjectPropertyActionMethod documentation

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReadOnlyActionMethod {
}
