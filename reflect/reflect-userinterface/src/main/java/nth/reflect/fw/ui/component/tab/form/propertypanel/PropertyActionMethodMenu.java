package nth.reflect.fw.ui.component.tab.form.propertypanel;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;
import nth.reflect.fw.ui.component.ReflectGuiComponent;
import nth.reflect.fw.ui.component.tab.form.FormMode;
import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.component.tab.form.valuemodel.PropertyValueModel;

/**
 * <p>
 * A {@link PropertyActionMethodMenu} is part of a {@link PropertyPanel} which
 * is part of a {@link FormTab}. A {@link PropertyActionMethodMenu} is a drop
 * down menu that is opened with a menu button. This menu contains all related
 * {@link DomainObjectPropertyActionMethod}s.The menu button is only visible
 * when the {@link DomainObjectProperty} is can be edited (manipulated),
 * depending on the {@link PropertyValueModel} and the {@link FormMode}.
 * <p>
 * 
 * <p>
 * A {@link PropertyActionMethodMenu} usually extends some kind of <a href=
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
public interface PropertyActionMethodMenu extends ReflectGuiComponent {

}
