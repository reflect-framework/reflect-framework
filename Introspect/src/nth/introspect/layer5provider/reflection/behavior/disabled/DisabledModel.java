package nth.introspect.layer5provider.reflection.behavior.disabled;

import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * A {@link DomainObjectProperty} or {@link ActionMethod} is enabled on the user
 * interface by default, but in some cases you may want to disable them. If
 * something is disabled they are grayed out on the user interface. Disabled
 * properties can not be edited (read only) and disabled {@link ActionMethod}s
 * can not be invoked.
 * </p>
 * <p>
 * You may want to disable items when:
 * <ul>
 * <li>Not all properties need to be editable on the user interface but the code
 * might need a setter method to set the {@link DomainObjectProperty}value when
 * the {@link DomainObject} is created from the database</li>
 * <li>Depending on the state of a {@link DomainObject} you do not want the user
 * to edit certain {@link DomainObjectProperty}</li>
 * <li>Depending on the state of a {@link DomainObject} you do not want a user
 * to invoke an {@link ActionMethod} (e.g. prevent invoking {@link ActionMethod}
 * “submit” once the {@link DomainObject} already is submitted)</li>
 * <li>Because a user is not authorized to edit a {@link DomainObjectProperty}
 * Value</li>
 * <li>Because a user is not authorized to invoke an {@link ActionMethod}</li>
 * </ul>
 * </p>
 * <p>
 * Note that if an user is not authorized to change a
 * {@link DomainObjectProperty} or call an {@link ActionMethod} it is best to
 * hide the method or property instead of disabling it. In general you do not
 * want to confuse users (clutter the user interface) with options that they are
 * not allowed to use anyway.
 * </p>
 * 
 * <h3>Disabled Annotation</h3>
 * <p>
 * {@insert Disabled}
 * </p>
 * 
 * <h3>Disabled Method</h3>
 * <p>
 * {@insert DisabledMethod}
 * </p>
 * 
 
 * 
 * @author nilsth
 *
 */
public class DisabledModel {

}
