package nth.reflect.fw.gui.component.tab.form.propertypanel;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.component.ReflectGuiComponent;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.DomainObject;

/**
 * <p>
 * {@link PropertyIconButton} are part of a {@link PropertyPanel} which is part
 * of a {@link FormTab}. A {@link PropertyIconButton} is normally located after
 * the {@link PropertyField}. Examples of {@link PropertyIconButton}s are:
 * <ul>
 * <li>Open a drop down menu to select something from a list (e.g. a
 * {@link Enum} property or a property that supports auto completion)</li>
 * <li>Open a drop down menu to select a action method (see
 * {@link PropertyActionMethodMenu})</li>
 * <li>Toggle the visibility of a password</li>
 * <li>Etc...</li>
 * </ul>
 * <p>
 * 
 * <p>
 * The state (e.g. visibility) of {@link PropertyIconButton} may change
 * depending on values of the {@link DomainObject}s or if the {@link FormMode}.
 * This is done when the {@link #onRefresh()} method is called by the
 * {@link ReflectFramework}
 * <p>
 * 
 * <p>
 * {@link PropertyIconButton}s usually extends some kind of <a href=
 * "https://en.wikipedia.org/wiki/List_of_graphical_user_interface_elements">user
 * interface element (also known as component or element)</a>. The type of the
 * user interface element depends on the framework used (e.g. JavaFx Component,
 * Vaadin Component or Android Component).
 * </p>
 * 
 * 
 * 
 * @author nilsth
 *
 */
public interface PropertyIconButton extends ReflectGuiComponent {

	public void onRefresh();
}
