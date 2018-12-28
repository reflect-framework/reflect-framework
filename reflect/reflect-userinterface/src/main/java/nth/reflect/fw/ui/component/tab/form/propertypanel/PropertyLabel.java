package nth.reflect.fw.ui.component.tab.form.propertypanel;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

/**
 * <p>
 * A {@link PropertyLabelStyle} is part of a {@link PropertyPanel} which is part of a
 * {@link FormTab}. A {@link PropertyLabelStyle} shows the name of the
 * {@link DomainObjectProperty} {@link DisplayName} in the language of the user.
 * The label text is set by the {@link PropertyPanel}, using the the the
 * {@link PropertyValueModel}.
 * <p>
 * 
 * <p>
 * A {@link PropertyLabelStyle} usually extends some kind of <a href=
 * "https://en.wikipedia.org/wiki/List_of_graphical_user_interface_elements">user
 * interface element (also known as component or element)</a>. The type of the
 * user interface element depends on the framework used (e.g. JavaFx Component,
 * Vaadin Component or Android Component).
 * </p>
 * 
 * @author nilsth
 *
 */

//TODO create PropertyLabelStyle class and refer to it in the javadoc

public interface PropertyLabel {

	public void setText(String text);

	public void setDescription(String description);
}
