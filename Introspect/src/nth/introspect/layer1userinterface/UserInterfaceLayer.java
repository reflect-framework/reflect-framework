package nth.introspect.layer1userinterface;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.documentation.Documentation;
import nth.introspect.documentation.IntrospectArchitecture;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;

/**
 * 
 * The {@link UserInterfaceLayer} is also know as presentation layer (although I
 * think that 'presentation layer' is a poor name, because it is responsible for
 * so much more). The {@link UserInterfaceLayer} contains the
 * {@link UserInterfaceController}, which is responsible for showing information to the user and processing the information from the user using the objects in the lower layers (see {@link IntrospectArchitecture}).
 * <p>
 * The {@link UserInterfaceContainer} is an {@link IntrospectContainer} that
 * represents the {@link UserInterfaceLayer} and holds and manages the
 * {@link UserInterfaceController}.
 * </p>
 *
 * Note that this layer is the top layer, which means it may know all the
 * objects in the lower layers but not visa versa! See
 * {@link IntrospectArchitecture}
 * 
 * <h2>UserInterfaceController</h2> {@insert UserInterfaceController}
 * 
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface UserInterfaceLayer extends Documentation {
	/**
	 * TODO A future feature could be that the {@link UserInterfaceLayer} also
	 * manages custom views (e.g. for using google maps, charts, etc)
	 * <ul>
	 * <li>A custom view can represent an {@link ActionMethod} parameter or
	 * return value.</li>
	 * <li>A custom view must indicate if it can display an {@link ActionMethod}
	 * parameter or return value with a canHandleAsActionMethodParameter(method)
	 * canHandleAsActionMethodReturnValie(method) methods in the {@link View}
	 * interface</li>
	 * <li>A custom view is registered in the {@link IntrospectApplication}
	 * List<T> getViewClasses() method. (a UI component) should be given by the
	 * ApplicationInterfaceFor..., depending on the userinterface type used</li>
	 * <li>Default views (formview, tableview, etc) should be handled by the
	 * same mechanism. E.G. the IntrospectApplicationFor... could have a default
	 * implementation of the getViews method, that returns formview, tableview,
	 * etc, which could be extended or modified by the user.</li>
	 * </ul>
	 */

	/**
	 * TODO A future feature could be that the {@link UserInterfaceLayer} also
	 * manages custom form fields (e.g. for editing custom data types).
	 * <ul>
	 * <li>A custom FormField can display or edit a data type.</li>
	 * <li>A custom FormField must indicate if it can display and edit an data
	 * type with a boolean canHandleDatatType(Class<?> type) in the FormField
	 * interface</li>
	 * <li>A custom FormField is registered in the {@link IntrospectApplication}
	 * List<T> getFormFieldClasses() method. T (a UI component) should be given
	 * by the ApplicationInterfaceFor..., depending on the userinterface type
	 * used</li>
	 * <li>Default FormFields should be handled by the same mechanism. E.G. the
	 * IntrospectApplicationFor... could have a default implementation of the
	 * getFormField method. that returns all default FormFields(date, time,
	 * string, numbers, domain object, domain objects, etc), which could be
	 * extended or modified by the user.</li>
	 * </ul>
	 */
}
