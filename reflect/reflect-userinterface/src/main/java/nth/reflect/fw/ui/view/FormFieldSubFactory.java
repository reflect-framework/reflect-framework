package nth.reflect.fw.ui.view;

import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

/**
 * Creates a {@link FormField} for a {@link FormView}. This class is used by the {@link FormFieldFactory}
 * 
 * @author nilsth
 *
 * @param <T>
 *            Some kind of <a href=
 *            "https://en.wikipedia.org/wiki/List_of_graphical_user_interface_elements">user
 *            interface element (also known as component or element)</a>. The
 *            type of the user interface element depends on the framework used
 *            (e.g. JavaFx Component, Vaadin Component or Android Component).
 */
public interface FormFieldSubFactory<T> {

	public T create(PropertyValueModel propertyValueModel);
	
	public boolean canCreate(PropertyValueModel propertyValueModel);

}
