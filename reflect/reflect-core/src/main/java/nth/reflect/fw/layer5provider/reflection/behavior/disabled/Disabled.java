package nth.reflect.fw.layer5provider.reflection.behavior.disabled;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * You can disable a {@link DomainObjectProperty} by putting the
 * {@link Disabled} annotation before the getter method or you can disable an
 * {@link ActionMethod} by putting the {@link Disabled} annotation before the
 * {@link ActionMethod}.
 * </p>
 * <p>
 * Syntax: @Disabled(exceptForRoles)
 * </p>
 * <p>
 * Parameter: {@link #exceptForRoleNames()}: optional comma separated string of
 * user roles that are allowed to edit the {@link DomainObjectProperty} or
 * invoke a {@link ActionMethod}
 * </p>
 * TODO EXAMPLE
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Disabled {

	public String exceptForRoleNames() default "";
}
