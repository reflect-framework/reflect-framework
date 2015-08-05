package nth.introspect.layer5provider.reflection.behavior.hidden;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.swing.text.TableView;
import javax.swing.text.html.FormView;

import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * You can hide a {@link DomainObjectProperty} by putting the {@link Hidden}
 * annotation before the getter method or you can hide an {@link ActionMethod}
 * by putting the {@link Hidden} annotation before the {@link ActionMethod}.
 * </p>
 * <p>
 * Syntax: @Hidden(hiddenFor, exceptForUsers, exceptForRoles)
 * </p>
 * <p>
 * Parameters:
 * <ul>
 * <li>hiddenFor: optional and for properties only: to indicate if the property
 * should be hidden in {@link FormView}s, {@link TableView}s or both.</li>
 * <li>ExceptForUsers: optional comma separated string of users names that are
 * allowed to see the {@link DomainObjectProperty} or {@link ActionMethod}</li>
 * <li>exceptForRoles: optional comma separated string of user roles that are
 * allowed to see the {@link DomainObjectProperty} or {@link ActionMethod}</li>
 * </ul>
 * </p>
 * TODO EXAMPLE
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Hidden {
	public HiddenFor hiddenFor();

	public String exceptForUsers() default "";

	public String exceptForRoles() default "";
}
