package nth.reflect.fw.layer1userinterface.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.documentation.ReflectApplicationProjects;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.generic.util.TitleUtil;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.NotificationListener;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

//FIXME: test all methods in this class

/**
 *
 * <p>
 * The {@link UserInterfaceController} is responsible for showing information to
 * the user and processing the information from the user using the objects in
 * the lower layers (see {@link ReflectArchitecture}).
 * </p>
 * 
 * <p>
 * See the {@link ReflectApplicationProjects} section to learn what types of
 * user interfaces are available, or download all the sources of the
 * {@link ReflectFramework} projects and see the class hierarchy of the
 * {@link UserInterfaceController} class to find all the different user
 * interface implementations. 
 * </p> 
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
			String actionMethod = TitleUtil.createTitle(reflectionProvider, methodInfo, methodParameter);
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
			String actionMethod = TitleUtil.createTitle(reflectionProvider, methodInfo, methodParameter);
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
	 * {@link ReflectApplication}
	 */
	public abstract void launch();
}
