package nth.introspect.layer1userinterface.controller;

import java.net.URI;
import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.documentation.IntrospectArchitecture;
import nth.introspect.documentation.IntrospectGettingStarted;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.notification.NotificationListener;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * <p>
 * The {@link UserInterfaceController} is responsible for showing information to
 * the user and processing the information from the user using the objects in
 * the lower layers (see {@link IntrospectArchitecture}).
 * </p>
 * <p>
 * An user interface could be any type of user interface, in example:
 * </p>
 * <h3>
 * Command line interface</h3>
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Command-line_interface">Command
 * line interface</a> is a text-based user interface where the user (or client)
 * issues commands in the form of successive lines of text (command lines).</li>
 * <li>The user can be a person or another computer application (using a shell).
 * </li>
 * <li>See {@link IntrospectApplicationForCommandLine}.</li>
 * </ul>
 * 
 * <h3>Desktop interface</h3>
 * <ul>
 * <li>A desktop interface is a <a
 * href="http://en.wikipedia.org/wiki/Graphical_user_interface"> graphical user
 * interface</a> for a computer with an <a
 * href="https://en.wikipedia.org/wiki/Desktop_environment">desktop
 * environment</a></li>
 * <li>The user is likely to be a person</li>
 * <li>Introspect has an desktop implementation based on <a href="">Swing</a>.
 * See {@link IntrospectApplicationForSwing}</li>
 * </ul>
 * 
 * <h3>Mobile interface</h3>
 * <ul>
 * <li>
 * A mobile interface is a <a
 * href="http://en.wikipedia.org/wiki/Graphical_user_interface"> graphical user
 * interface</a> for mobile devices such as smart phones and tablets.</li>
 * <li>The user is likely to be a person</li>
 * <li>Introspect will have a mobile implementation using Android. See
 * {@link IntrospectApplicationForAndroid}</li>
 * </ul>
 * 
 * <h3>Web interface</h3>
 * <ul>
 * <li>
 * A <a href="https://en.wikipedia.org/wiki/Web_application">web application</a>
 * is a <a
 * href="http://en.wikipedia.org/wiki/Graphical_user_interface">graphical user
 * interface</a> for <a href="https://en.wikipedia.org/wiki/Web_browser">web
 * browsers</a></li>
 * <li>The user is likely to be a person</li>
 * <li>
 * Introspect will have a mobile implementation using Vaadin. See
 * {@link IntrospectApplicationForVaadin}</li>
 * </ul>
 * 
 * <h3>SOAP interface</h3>
 * <ul>
 * <li>The SOAP interface is a <a
 * href="https://en.wikipedia.org/wiki/Web_service">web service</a> that allows
 * other computer applications to interact via the <a
 * href="http://en.wikipedia.org/wiki/SOAP">Simple Object Access Protocol
 * (SOAP)</a></li>
 * <li>The user is likely to be another computer application</li>
 * <li>Introspect might have a SOAP implementation in the future</li>
 * </ul>
 * 
 * <h3>RESTfull XML interface</h3>
 * <ul>
 * <li>The RESTfull XML interface is a <a
 * href="https://en.wikipedia.org/wiki/Web_service">web service</a> that allows
 * other computer applications to interact using <a
 * href="http://en.wikipedia.org/wiki/Representational_state_transfer"
 * >Representational State Transfer (RESTfull)</a> with <a
 * href="https://en.wikipedia.org/wiki/XML">XML</a></li>
 * <li>The user is likely to be another computer application</li>
 * <li>Introspect might have a RESTfull XML implementation in the future</li>
 * </ul>
 * 
 * <h3>RESTfull JSON interface</h3>
 * <ul>
 * <li>The RESTfull SJON interface is a <a
 * href="https://en.wikipedia.org/wiki/Web_service">web service</a> that allows
 * other computer applications to interact using <a
 * href="http://en.wikipedia.org/wiki/Representational_state_transfer"
 * >Representational State Transfer (RESTfull)</a> with <a
 * href="https://en.wikipedia.org/wiki/JSON">JSON</a></li>
 * <li>The user is likely to be another computer application</li>
 * <li>Introspect might have a RESTfull XML implementation in the future</li>
 * </ul>
 * 
 * <h3>JUnit interface</h3>
 * <ul>
 * <li>The JUnit interface is an interface to test objects in the lower layers
 * (see {@link IntrospectArchitecture}), using the <a
 * href="https://en.wikipedia.org/wiki/JUnit">JUnit test framework</a></li>
 * <li>The user is the JUnit test framework</li>
 * <li>See {@link IntrospectApplicationForJUnit}.</li>
 * </ul>
 * 
 * Please see the class hierarchy of the {@link IntrospectApplication} class to
 * find all the different user interface implementations. See the
 * {@link IntrospectGettingStarted} section with specific instructions for each
 * type of {@link IntrospectApplication}
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface UserInterfaceController<T> extends NotificationListener {

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
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 */

	public void startExecution(Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue);

	/**
	 * This method is called from
	 * {@link #startExecution(Object, ActionMethodInfo, Object)} or from the
	 * {@link FormOkItem} linked to the OK button <br>
	 * It needs the check if the method is enabled before the method is executed<br>
	 * It needs to validate the method parameter value before the method is
	 * executed
	 * 
	 * @param serviceObject
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 */

	void excuteMethod(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue);

	/**
	 * This method is called from
	 * {@link #startMethodExecutionThread(Object, ActionMethodInfo, Object)}
	 * when the thread is completed.<br>
	 * It will open a new view or InfoDialog to show the method return value
	 * 
	 * @param serviceObject
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 * @param methodReturnValue
	 */

	void showMethodReturnValue(Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue);

	void start();

}
