package nth.introspect.documentation;

import nth.introspect.controller.userinterface.DownloadStream;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.domain.format.DomainObjectFormat;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

/**
 * <h3>Action Methods Convention</h3>
 * <p>
 * Any method in a {@link DomainObject} or {@link ServiceObject} can be an
 * Action method, provided that it complies with the following convention:
 * </p>
 * <ul>
 * <li>The method has no parameter or a single {@link DomainObject} parameter</li>
 * <li>Its return type (if any) are types recognized by the Introspect framework
 * (see below)</li>
 * <li>The method is not a getter method or a setter method (see
 * {@link DomainObjectProperty})</li>
 * <li>The method is public (not private)</li>
 * <li>The method is NOT static</li>
 * </ul>
 * <h3>Action method names</h3><p> The name of an {@link ActionMethod} should
 * describe the action and match the <a
 * href="http://martinfowler.com/bliki/UbiquitousLanguage.html">Ubiquitous
 * Language</a> (in terms understood by both users and developers).
 *  Keep in mind that the goal of a user is almost never to create, update or
 * delete objects. Method names like: createPerson, updatePerson and removePerson
 * should therefore be avoided where possible. Method names like registerNewBirth, registerMarriage,
 * registerPersonDeceased would be better names.
 * <p>
 * <h3>Action method parameter</h3>
 * <p>
 * An action method either has no parameter or a single {@link DomainObject} as
 * a parameter. If not, the Introspect Framework will not recognize a method as
 * an {@link ActionMethod}.
 * </p>
 * <h3>Action method return value</h3>
 * <p>
 * The {@link UserInterfaceController} renders the output of a method, depending
 * on type of the action method return value:
 * <ul>
 * <li>No return value (void method): The {@link UserInterfaceController} will
 * display a short message when the method has been executed</li>
 * <li><a href=
 * "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html">A
 * primitive data type</a>: The {@link UserInterfaceController} will display a
 * message dialog that displays the return value after the method is been
 * executed</li>
 * <li>A {@link DomainObject}: The {@link UserInterfaceController} displays the
 * domain object in a form on a new tab.</li>
 * <li>A <a
 * href="http://en.wikipedia.org/wiki/Java_collections_framework">collection</a>
 * of {@link DomainObject}s: The {@link UserInterfaceController} displays the
 * domain objects in table on a new tab. Note that you will need to annotate the
 * Action method with a @{@link GenericReturnType} annotation in which you need
 * to define the {@link DomainObject} type.</li>
 * <li>A URI: The {@link UserInterfaceController} displays the contents of the
 * URI on a new tab.</li>
 * <li>A {@link DownloadStream}: The {@link UserInterfaceController} will open a
 * "Save as" dialog" so that the file can be down loaded.</li>
 * </ul>
 * </p>
 * <h3>Action execution modes</h3> {@link ActionMethod}s can be annotated, so
 * that the {@link UserInterfaceController} knows how the {@link ActionMethod}
 * needs to be invoked after the user has clicked on the corresponding menu
 * item.
 * <ul>
 * <li>@{@link ExecutionMode}({@link ExecutionModeType#EXECUTE_METHOD_DIRECTLY}
 * ): executes the method directly without user intervention</li>
 * <li>@{@link ExecutionMode}(
 * {@link ExecutionModeType#EXECUTE_METHOD_AFTER_CONFORMATION}): the
 * {@link UserInterfaceController} opens a confirmation dialog. The method is
 * executed after the user has confirmed by the user. The method is NOT executed
 * when the user cancels the confirmation dialog.</li>
 * <li>@{@link ExecutionMode}(
 * {@link ExecutionModeType#EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL}): the
 * {@link UserInterfaceController} opens a form on a new tab, so that the user
 * can modify (edit) the {@link DomainObject}. The method is executed with the
 * edited {@link DomainObject} as the method parameter, when the user clicks the
 * confirmation button on the button bar. The method is NOT executed when the
 * user clicks on cancel in the bottom bar.</li>
 * </ul>
 * <h3>Action behavior</h3> You can specify certain things about both the
 * behavior and presentation of actions methods, such as their display name,
 * icon, visibility, enabled state, etc with help of annotations or behavior
 * methods. See chapter on {@link ObjectBehavior}.
 * 
 * 
 * 
 * @author nilsth
 *
 */
public interface ActionMethod extends Documentation {

}
