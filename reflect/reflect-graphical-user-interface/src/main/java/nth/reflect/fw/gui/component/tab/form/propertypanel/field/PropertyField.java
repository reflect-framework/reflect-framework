package nth.reflect.fw.gui.component.tab.form.propertypanel.field;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Optional;

import nth.reflect.fw.gui.component.ReflectGuiComponent;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanel;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer3domain.DomainObjectProperty;

/**
 * <p>
 * A {@link PropertyField} is part of a {@link PropertyPanel} which is part of a
 * {@link FormTab}. A {@link PropertyField} is a representation of a
 * {@link DomainObjectProperty} value, e.g. a text or a table. This value is
 * either read-only or could be edited (manipulated), depending on the
 * {@link PropertyValueModel} and the {@link FormMode}
 * <p>
 * 
 * <p>
 * A {@link PropertyField} usually extends some kind of <a href=
 * "https://en.wikipedia.org/wiki/List_of_graphical_user_interface_elements">user
 * interface element (also known as component or element)</a>. The type of the
 * user interface element depends on the framework used (e.g. JavaFx Component,
 * Vaadin Component or Android Component).
 * </p>
 * 
 * <p>
 * The implementations of the {@link PropertyField} interface must update the
 * {@link PropertyValueModel} by calling the
 * {@link PropertyValueModel#setValue(Object)} every time when the user edits
 * (manipulates) the {@link PropertyField} value. The {@link PropertyValueModel}
 * is normally passed to a {@link PropertyField} as a {@link Constructor}
 * {@link Parameter}.
 * </p>
 * 
 * 
 * @author nilsth
 *
 */
public interface PropertyField extends ReflectGuiComponent {

	public PropertyFieldWidth getPropertyFieldWidth();

	public void setEnabled(boolean enabled);

	public void setValueFromDomainProperty(Object propertyValue);

	/**
	 * Some {@link PropertyField} have a selection button e.g. for the
	 * following:
	 * <ul>
	 * <li>CheckBox</li>
	 * <li>Open a Drop down list for a combo box</li>
	 * <li>Open a Color picker</li>
	 * <li>Open a Date and or time picker</li>
	 * </ul>
	 * 
	 * @return
	 */
	public Optional<Item> getSelectionItem();

}
