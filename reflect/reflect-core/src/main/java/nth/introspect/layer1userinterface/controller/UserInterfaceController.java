package nth.introspect.layer1userinterface.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.IntrospectFramework;
import nth.introspect.documentation.IntrospectApplicationProjects;
import nth.introspect.documentation.IntrospectArchitecture;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.junit.IntrospectApplicationForJUnit;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.NotificationListener;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

//FIXME: test all methods in this class

/**
 *
 * <p>
 * The {@link UserInterfaceController} is responsible for showing information to
 * the user and processing the information from the user using the objects in
 * the lower layers (see {@link IntrospectArchitecture}).
 * </p>
 * 
 * <p>
 * See the {@link IntrospectApplicationProjects} section to learn what types of
 * user interfaces are available, or download all the sources of the
 * {@link IntrospectFramework} projects and see the class hierarchy of the
 * {@link UserInterfaceController} class to find all the different user
 * interface implementations.
 * </p>
 * 
 * TODO make reference to IntrospectProjectsDemosAndDownloads and move the sub
 * parts to the javadoc IntrospectApplication inplementations, containting a
 * description of the type of application, How to download a demo, How to create
 * a new project
 * <p>
 * An user interface could be any type of user interface, in example:
 * </p>
 * <h3>Command line interface</h3>
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
 * <li>A desktop interface is a
 * <a href="http://en.wikipedia.org/wiki/Graphical_user_interface"> graphical
 * user interface</a> for a computer with an
 * <a href="https://en.wikipedia.org/wiki/Desktop_environment">desktop
 * environment</a></li>
 * <li>The user is likely to be a person</li>
 * <li>Introspect has an desktop implementation based on <a href="">Swing</a>.
 * See {@link IntrospectApplicationForSwing}</li>
 * </ul>
 * 
 * <h3>Mobile interface</h3>
 * <ul>
 * <li>A mobile interface is a
 * <a href="http://en.wikipedia.org/wiki/Graphical_user_interface"> graphical
 * user interface</a> for mobile devices such as smart phones and tablets.</li>
 * <li>The user is likely to be a person</li>
 * <li>Introspect will have a mobile implementation using Android. See
 * {@link IntrospectApplicationForAndroid}</li>
 * </ul>
 * 
 * <h3>Web interface</h3>
 * <ul>
 * <li>A <a href="https://en.wikipedia.org/wiki/Web_application">web
 * application</a> is a
 * <a href="http://en.wikipedia.org/wiki/Graphical_user_interface">graphical
 * user interface</a> for
 * <a href="https://en.wikipedia.org/wiki/Web_browser">web browsers</a></li>
 * <li>The user is likely to be a person</li>
 * <li>Introspect will have a mobile implementation using Vaadin. See
 * {@link IntrospectApplicationForVaadin}</li>
 * </ul>
 * 
 * <h3>SOAP interface</h3>
 * <ul>
 * <li>The SOAP interface is a
 * <a href="https://en.wikipedia.org/wiki/Web_service">web service</a> that
 * allows other computer applications to interact via the
 * <a href="http://en.wikipedia.org/wiki/SOAP">Simple Object Access Protocol
 * (SOAP)</a></li>
 * <li>The user is likely to be another computer application</li>
 * <li>Introspect might have a SOAP implementation in the future</li>
 * </ul>
 * 
 * <h3>RESTfull XML interface</h3>
 * <ul>
 * <li>The RESTfull XML interface is a
 * <a href="https://en.wikipedia.org/wiki/Web_service">web service</a> that
 * allows other computer applications to interact using
 * <a href="http://en.wikipedia.org/wiki/Representational_state_transfer" >
 * Representational State Transfer (RESTfull)</a> with
 * <a href="https://en.wikipedia.org/wiki/XML">XML</a></li>
 * <li>The user is likely to be another computer application</li>
 * <li>Introspect might have a RESTfull XML implementation in the future</li>
 * </ul>
 * 
 * <h3>RESTfull JSON interface</h3>
 * <ul>
 * <li>The RESTfull SJON interface is a
 * <a href="https://en.wikipedia.org/wiki/Web_service">web service</a> that
 * allows other computer applications to interact using
 * <a href="http://en.wikipedia.org/wiki/Representational_state_transfer" >
 * Representational State Transfer (RESTfull)</a> with
 * <a href="https://en.wikipedia.org/wiki/JSON">JSON</a></li>
 * <li>The user is likely to be another computer application</li>
 * <li>Introspect might have a RESTfull XML implementation in the future</li>
 * </ul>
 * 
 * <h3>JUnit interface</h3>
 * <ul>
 * <li>The JUnit interface is an interface to test objects in the lower layers
 * (see {@link IntrospectArchitecture}), using the
 * <a href="https://en.wikipedia.org/wiki/JUnit">JUnit test framework</a></li>
 * <li>The user is the JUnit test framework</li>
 * <li>See {@link IntrospectApplicationForJUnit}.</li>
 * </ul>
 * 
 * 
 * @author Nils ten Hoeve
 * 
 */
public abstract class UserInterfaceController implements NotificationListener {

	protected final UserInterfaceContainer userInterfaceContainer;
	protected final ReflectionProvider reflectionProvider;
	protected final LanguageProvider languageProvider;

	public UserInterfaceController(UserInterfaceContainer userInterfaceContainer) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
	}

	public abstract void showErrorDialog(String title, String message, Throwable throwable);

	/**
	 * This method is called when a user sends an command to the
	 * {@link UserInterfaceController}. this can come from different sources
	 * such as:
	 * <ul>
	 * <li>command line</li>
	 * <li>graphical user interface (when the user activates a menu item)</li>
	 * <li>http request from a SOAP or Restfull client</li>
	 * <li>etc</li>
	 * </ul>
	 * This method will process the {@link ActionMethod} parameter (depending on
	 * how it is annotated):
	 * <ul>
	 * <li>{@link ExecutionModeType#EXECUTE_METHOD_DIRECTLY }: Will call
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)}
	 * directly (i.e. when there is no {@link ActionMethod} parameter)</li>
	 * <li>{@link ExecutionModeType#EXECUTE_METHOD_AFTER_CONFORMATION }: Will
	 * ask the user for confirmation before the {@link ActionMethod} is
	 * executed. To do this it will call one of the
	 * confirmActionMethodParameter(...) methods in the
	 * {@link UserInterfaceController} implementation. After the confirmation
	 * the
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)}
	 * needs to be called (i.e. by a OK button).</li>
	 * <li>
	 * {@link ExecutionModeType#EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL }:
	 * Will let the user edit the {@link ActionMethod} parameter before the
	 * {@link ActionMethod} is executed. To do this it will call one of the
	 * editActionMethodParameter(...) methods in the
	 * {@link UserInterfaceController} implementation. After the confirmation
	 * the
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)}
	 * needs to be called (i.e. by a OK button).</li>
	 * </ul>
	 * 
	 * @param methodOwner
	 *            Domain or service object that owns the method
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 */

	public void processActionMethod(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		try {

			if (methodParameter == null && methodInfo.hasParameter()) {
				methodParameter = methodInfo.createMethodParameter(methodOwner);
			}

			ExecutionModeType executionMode = methodInfo.getExecutionMode();
			switch (executionMode) {
			case EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL:
				methodInfo.invokeEditParameterMethod(this, methodOwner, methodParameter);
				break;
			case EXECUTE_METHOD_AFTER_CONFORMATION:
				methodInfo.invokeConfirmMethod(this, methodOwner, methodParameter);
				break;
			case EXECUTE_METHOD_DIRECTLY:
				processActionMethodExecution(methodOwner, methodInfo, methodParameter);
				break;
			}
		} catch (Exception exception) {
			String title = languageProvider.getText("Error while executing an action" );
			String messageFormat = languageProvider.getText("Action: %s" );
			String actionMethod = TitleUtil.createTitle(reflectionProvider, methodInfo, methodParameter,
					false);
			String message=String.format(messageFormat, actionMethod);
			showErrorDialog(title,  message, exception);
		}

	}

	/**
	 * This method is called from
	 * {@link #processActionMethod(Object, ActionMethodInfo, Object)} or from
	 * the {@link FormOkItem} linked to the OK button <br>
	 * It needs the check if the method is enabled before the method is executed
	 * <br>
	 * It needs to validate the method parameter value before the method is
	 * executed
	 * @throws Exception 
	 * 
	 */

	public abstract void processActionMethodExecution(Object methodOwner,
			ActionMethodInfo methodInfo, Object methodParameter) ;

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

	public void processActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		try {
			methodInfo.invokeShowResult(this, methodOwner, methodParameter, methodResult);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException exception) {
			String title = languageProvider.getText("Error while displaying an action result" );
			String messageFormat = languageProvider.getText("Action: %s" );
			String actionMethod = TitleUtil.createTitle(reflectionProvider, methodInfo, methodParameter,
					false);
			String message=String.format(messageFormat, actionMethod);
			showErrorDialog(title,  message, exception);
		}

	}

	/**
	 * Process method to show the result of an {@link ActionMethod} with return
	 * type {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
 	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public abstract void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter);

	/**
	 * Process method to show the result of an {@link ActionMethod} with return
	 * type {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
 	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public abstract void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult);

	/**
	 * Process method to show the result of an {@link ActionMethod} with return
	 * type {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
 	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public abstract void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, List<?> methodResult);

	
	/**
	 * Process method to show the result of an {@link ActionMethod} with return
	 * type {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
 	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public abstract void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, String methodResult);

	/**
	 * Allows the user interface objects to be build (i.e. the creation of a
	 * main window). All information needed to start the application (like the
	 * command line arguments or web ap URL) should be available in the
	 * {@link IntrospectApplication}
	 */
	public abstract void launch();
}
