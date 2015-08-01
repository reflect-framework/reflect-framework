package nth.introspect.layer5provider.reflection.behavior.disabled;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.swing.text.TableView;
import javax.swing.text.html.FormView;

import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

/**
 * <p>
 * You can disable a {@link DomainObjectProperty} by putting the
 * {@link Disabled} annotation before the getter method or you can disable an
 * {@link ActionMethod} by putting the {@link Disabled} annotation before the
 * {@link ActionMethod}.
 * </p>
 * <p>
 * Syntax: @Disabled(exceptForUsers, exceptForRoles)
 * </p>
 * <p>
 * Parameters:
 * <ul>
 * <li>ExceptForUsers: optional comma separated string of users names that are
 * allowed to edit the {@link DomainObjectProperty} or invoke
 * {@link ActionMethod}</li>
 * <li>exceptForRoles: optional comma separated string of user roles that are
 * allowed to edit the {@link DomainObjectProperty} or invoke
 * {@link ActionMethod}</li>
 * </ul>
 * </p>
 * TODO EXAMPLE
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Disabled {
	public String exceptForUsers();

	public String exceptForRoles();
}
