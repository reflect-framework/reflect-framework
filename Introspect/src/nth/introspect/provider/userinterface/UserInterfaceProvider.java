package nth.introspect.provider.userinterface;

import java.net.URI;
import java.util.List;

import nth.introspect.definition.UserInterfaceLayer;
import nth.introspect.provider.Provider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.view.ViewContainer;

/**
 * The {@link UserInterfaceProvider} is responsible for displaying and
 * controlling the user interface. An user interface could be any type of user
 * interface, in example:
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Command-line_interface">Command
 * line User interface</a></li>
 * <li><a href="http://en.wikipedia.org/wiki/Graphical_user_interface">
 * Graphical User interface</a> for a computer using <a href="">Swing</a></li>
 * <li><a href="http://en.wikipedia.org/wiki/Graphical_user_interface">
 * Graphical User interface</a> for a mobile device using <a
 * href="http://en.wikipedia.org/wiki/Android_(operating_system)">Android</a></li>
 * <li><a href="http://en.wikipedia.org/wiki/Graphical_user_interface">
 * Graphical User interface</a> for a web bowser using <a
 * href="http://en.wikipedia.org/wiki/Vaadin">Vaandin</a></li>
 * <li>A inter-application interface with a <a
 * href="http://en.wikipedia.org/wiki/SOAP">SOAP</a> Service using ...</li>
 * <li>A inter-application interface with a <a
 * href="http://en.wikipedia.org/wiki/Representational_state_transfer"
 * >restfull</a> Service using ...</li>
 * </ul>
 * 
 * Please see the class hierarchy to find all the different user interface
 * implementations.<br>
 * 
 * @author Nils ten Hoeve
 * 
 * @param <T>
 */
public interface UserInterfaceProvider<T> extends Provider, Refreshable, UserInterfaceLayer {

	/**
	 * Provides simple feedback about an operation in a small popup. It only
	 * fills the amount of space required for the message and the current
	 * activity remains visible and interactive. The message popup will
	 * automatically disappear after a timeout
	 * 
	 * @param message
	 */
	public void showInfoMessage(String message);

	public void showDialog(DialogType dialogType, String title, String message,
			List<Item> items);

	public void showErrorDialog(String title, String message,
			Throwable throwable);

	public void showProgressDialog(String taskDescription, int currentValue,
			int maxValue);// TODO refactor parameters to: taskName, int
							// percentageCompleted

	public void closeProgressDialog();// TODO remove. progress dialog should
										// close automatically when
										// percentageCompleted=100

	public void openURI(URI uri);

	public void downloadFile(DownloadStream downloadStream);

	@SuppressWarnings("rawtypes")
	public ViewContainer getViewContainer();

	/**
	 * This method is called from a {@link MethodItem} and starts the process of
	 * invoking a method
	 * 
	 * @param methodOwner
	 *            Domain or service object that owns the method
	 * @param methodInfo
	 * @param methodParameterValue
	 */

	public void startExecution(Object methodOwner, MethodInfo methodInfo,
			Object methodParameterValue);

	/**
	 * This method is called from
	 * {@link #startExecution(Object, MethodInfo, Object)} or from the
	 * {@link FormOkItem} linked to the OK button <br>
	 * It needs the check if the method is enabled before the method is executed<br>
	 * It needs to validate the method parameter value before the method is
	 * executed
	 * 
	 * @param serviceObject
	 * @param methodInfo
	 * @param methodParameterValue
	 */

	void excuteMethod(Object serviceObject, MethodInfo methodInfo,
			Object methodParameterValue);

	/**
	 * This method is called from
	 * {@link #startMethodExecutionThread(Object, MethodInfo, Object)} when the
	 * thread is completed.<br>
	 * It will open a new view or InfoDialog to show the method return value
	 * 
	 * @param serviceObject
	 * @param methodInfo
	 * @param methodParameterValue
	 * @param methodReturnValue
	 */

	void showMethodReturnValue(Object serviceObject, MethodInfo methodInfo,
			Object methodParameterValue, Object methodReturnValue);

}
