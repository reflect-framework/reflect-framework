package nth.reflect.fw.ui.view;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.validation.ValidationViolations;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

/**
 * A {@link FormView} has one or more {@link FormField}s. Each {@link FormField}
 * represents a {@link DomainObjectProperty} and contains:
 * <ul>
 * <li>a label with the {@link DomainObjectProperty} {@link DisplayName}</li>
 * <li>a representation of the {@link DomainObjectProperty} value, e.g. a text
 * or a table. This value is either read-only or could be edited, depending on
 * the {@link PropertyValueModel}.</li>
 * <li>{@link ValidationViolations} if any</li>
 * </ul>
 * 
 * A {@link FormField} implements this interface and usually extends some kind
 * of <a href=
 * "https://en.wikipedia.org/wiki/List_of_graphical_user_interface_elements">user
 * interface element (also known as component or element)</a>. The type of the
 * user interface element depends on the framework used (e.g. JavaFx Component,
 * Vaadin Component or Android Component).
 * 
 * The constructor must take a {@link PropertyValueModel} as parameter!
 * 
 * @author nilsth
 *
 */
public interface FormField {

	default void updateAll() {
		updateVisibility();
		updateEnabled();
		updateLabel();
		updateValue();
	}
	
	public void updateLabel();
	public void updateVisibility();
	public void updateEnabled();
	public void updateValue();
}
