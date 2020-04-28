package nth.reflect.fw.gui;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.component.mainwindow.MainWindow;
import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.FormTabFilter;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory;
import nth.reflect.fw.gui.item.dialog.DialogCancelItem;
import nth.reflect.fw.gui.item.dialog.DialogCloseItem;
import nth.reflect.fw.gui.item.dialog.DialogMethodItem;
import nth.reflect.fw.gui.item.dialog.DialogShowStackTraceItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodExecutionProvider;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.notification.Task;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

/**
 * <p>
 * The {@link GraphicalUserinterfaceController} extends the
 * {@link UserInterfaceController} and adds functionality needed for a
 * <a href="https://en.wikipedia.org/wiki/Graphical_user_interface">Graphical
 * User Interface</a>. The {@link GraphicalUserinterfaceController} creates and
 * manipulates a {@link MainWindow} for a person to interact with.
 * </p>
 * <p>
 * <h3>Reflect GUI Components
 * <h3>{@insert ReflectGuiComponent}
 * 
 * 
 * @author nilsth
 * 
 * @param <TAB> A user interface specific class (often a component container/
 *              layout) that implements {@link Tab}
 */
public abstract class GraphicalUserinterfaceController<TAB extends Tab, PROPERTY_PANEL>
		extends UserInterfaceController {

	private static final int PERCENT_0 = 0;
	private static final int PERCENT_100 = 100;
	private final Tabs<TAB> tabs;

	public GraphicalUserinterfaceController(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		tabs = new Tabs<TAB>();
		NotificationProvider notificationProvider = userInterfaceContainer.get(NotificationProvider.class);
		notificationProvider.addListener(this);
	}

	public void confirmActionMethod(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
		// create the dialog items/ buttons

		List<Item> items = new ArrayList<Item>();
		DialogMethodItem methodExecuteItem = new DialogMethodItem(userInterfaceContainer, methodOwner, methodInfo,
				methodParameter);
		items.add(methodExecuteItem);
		DialogCancelItem cancelItem = new DialogCancelItem(languageProvider);
		items.add(cancelItem);

		// create the confirmation MaterialAppBarTitle and message
		TranslatableString title = CONFIRMATION_DIALOG_TITLE;
		TranslatableString methodTitle = methodInfo.getTitle(methodParameter);
		TranslatableString question = CONFIRMATION_DIALOG_QUESTION.withParameters(methodTitle);

		// show the dialog
		showDialog(title, question, items);

	}

	@Override
	public void onTaskChange(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefresh() {
		// refresh current tab
		Tab tab = getTabs().getSelected();
		if (tab != null) {
			tab.onRefresh();
		}
	}

	@Override
	public void onNewMessage(String title, String message) {
		// TODO Auto-generated method stub

	}

	public void editActionMethodParameter(Object actionMethodOwner, ActionMethodInfo actionMethodInfo,
			Object actionMethodParameterValue) {
		showFormTab(actionMethodOwner, actionMethodInfo, actionMethodParameterValue, actionMethodParameterValue,
				FormMode.EDIT);
	}

	/**
	 * TODO move to {@link ActionMethodResultHandler}
	 * 
	 * @param methodOwner
	 * @param actionMethodInfo
	 * @param methodParameter
	 * @param methodResult
	 * @param formMode
	 */
	public void showFormTab(Object methodOwner, ActionMethodInfo actionMethodInfo, Object methodParameter,
			Object methodResult, FormMode formMode) {

		Tabs tabs = getTabs();

		FormTabFilter formTabFilter = new FormTabFilter(methodOwner, actionMethodInfo, methodParameter, methodResult,
				formMode);
		Optional<FormTab> result = tabs.stream().filter(formTabFilter).findFirst();

		if (result.isPresent()) {
			tabs.setSelected(result.get());
		} else {
			Tab formTab = createFormTab(methodOwner, actionMethodInfo, methodParameter, methodResult, formMode);
			tabs.setSelected(formTab);
		}
	}

	public abstract Tab createFormTab(Object methodOwner, ActionMethodInfo actionMethodInfo, Object methodParameter,
			Object methodResult, FormMode formMode);

	public abstract void editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo,
			UploadStream uploadStream);

	@Override
	public void processActionMethodExecution(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue) {
		// TODO check if the method is enabled before the method is executed
		// (otherwise throw exception)
		// TODO validate the method parameter value before the method is
		// executed (if invalid: throw exception)

		// show ProgressDialog
		TranslatableString title = actionMethodInfo.getTitle(methodParameterValue);
		showProgressDialog(title, PERCENT_0, PERCENT_100);

		// start method execution thread
		try {
			startMethodExecutionThread(serviceObject, actionMethodInfo, methodParameterValue);
		} catch (Exception exception) {
			TranslatableString message = ERROR_EXECUTE;
			showError(title, message, exception);
		}
	}

	/**
	 * This method is called from
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)} <br>
	 * This method must do 3 things<br>
	 * - invoke Object methodReturnValue=
	 * {@link ActionMethodInfo#invoke(Object, Object)} in a separate thread (may be
	 * time consuming). This will need to be implemented per user interface because
	 * threading may need to be differently implemented for each user interface
	 * framework. <br>
	 * - catch errors during the execution of the thread and call
	 * {@link #showError(String, String, Throwable)} if needed<br>
	 * - invoke
	 * {@link GraphicalUserinterfaceController#processActionMethodReturnValue(Object, ActionMethodInfo, Object, Object)}
	 * <br>
	 * <br>
	 * This method can be overridden if the framework of the user interface
	 * implementation requires a specific threading mechanism (i.e. Android)
	 * 
	 * @param methodOwner
	 * @param actionMethodInfo
	 * @param methodParameter
	 * 
	 */

	public void startMethodExecutionThread(final Object methodOwner, final ActionMethodInfo actionMethodInfo,
			final Object methodParameter) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Object methodReturnValue = actionMethodInfo.invoke(methodOwner, methodParameter);

					// Calling a method on a object can change its state
					// We therefore must update current tab
					Tab selectedTab = getTabs().getSelected();
					if (selectedTab != null) {
						selectedTab.onRefresh();
					}
					// show method result
					processActionMethodResult(methodOwner, actionMethodInfo, methodParameter, methodReturnValue);
				} catch (Exception exception) {
					Optional<Object> optionalMethodParameter = Optional.ofNullable(methodOwner);
					TranslatableString title = actionMethodInfo.getTitle(optionalMethodParameter);
					TranslatableString message = ERROR_EXECUTE;
					showError(title, message, exception);
				}

			}
		};
		executeInThread(runnable);
	}

	/**
	 * Hook so that each type of user interface can execute the method using
	 * threading in they way preferred by user interface TODO move to
	 * {@link ActionMethodExecutionProvider}
	 * 
	 * @param methodExecutionRunnable
	 */
	public abstract void executeInThread(Runnable methodExecutionRunnable);

	@Override
	public void showError(TranslatableString title, TranslatableString message, Throwable throwable) {

		throwable.printStackTrace();
		// to help debugging (stack trace in IDE console has hyper links to code)

		List<Item> items = new ArrayList<Item>();
		DialogShowStackTraceItem showStackTraceItem = new DialogShowStackTraceItem(userInterfaceContainer, title,
				message, throwable);
		items.add(showStackTraceItem);
		DialogCloseItem closeItem = new DialogCloseItem(languageProvider);
		items.add(closeItem);

		showDialog(title, message, items);
	}

	/**
	 * Process method to show the result of an {@link ActionMethod} with return type
	 * {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			URI methodResult) {
		URI uri = methodResult;
		String uriString = uri.toString();
		if (uriString.toLowerCase().startsWith(ReflectFramework.class.getSimpleName().toLowerCase() + ":")) {
			try {
				int positionColon = uriString.indexOf(":");
				int positionLastDot = uriString.lastIndexOf(".");
				String serviceClassName = uriString.substring(positionColon + 1, positionLastDot);
				String methodName = uriString.substring(positionLastDot + 1);
				Class<?> serviceClass = Class.forName(serviceClassName);
				DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(serviceClass);
				List<ActionMethodInfo> actionMethodInfos = domainClassInfo
						.getActionMethodInfos(new MethodNameFilter(methodName));
				processActionMethod(methodOwner, actionMethodInfos.get(0), null);
			} catch (Exception exception) {
				throw new IllegalReflectUriException(exception);
			}
		} else {
			openURI(uri);
		}

	}

	/**
	 * Process method to show the result of an {@link ActionMethod} with return type
	 * {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 * 
	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			DownloadStream methodResult) {
		DownloadStream downloadStream = methodResult;
		downloadFile(downloadStream);
	}

	/**
	 * Process method to show the result of an {@link ActionMethod} with return type
	 * {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			String methodResult) {
		TranslatableString actionMethodTitle = methodInfo.getTitle(methodParameter);
		TranslatableString message = RESULT_DIALOG_MESSAGE.withParameters(actionMethodTitle, methodResult);
		showMessage(message);

	}

	/**
	 * Provides simple feedback about an operation in a small popup. It only fills
	 * the amount of space required for the message and the current activity remains
	 * visible and interactive. The message popup will automatically disappear after
	 * a timeout
	 * 
	 * @param message
	 */
	@Override
	public abstract void showMessage(TranslatableString message);

	/**
	 * Shows a pop-up dialog containing a message and one or more buttons (items).
	 * Note that the dialog needs to scroll the message when it does not fit inside
	 * the dialog.
	 * 
	 * @param title
	 * @param message
	 * @param items
	 */
	public abstract void showDialog(TranslatableString title, TranslatableString message, List<Item> items);

	/**
	 * TODO refactor so that progress dialog shows multiple thread monitors, while
	 * listening to updates
	 * 
	 * @param taskDescription
	 * @param currentValue
	 * @param maxValue
	 */
	public abstract void showProgressDialog(TranslatableString taskDescription, int currentValue, int maxValue);

	/**
	 * TODO remove. progress dialog should close automatically when all tasks are
	 * completed
	 */
	public abstract void closeProgressDialog();

	public abstract void openURI(URI uri);

	public abstract void downloadFile(DownloadStream downloadStream);

	public Tabs<TAB> getTabs() {
		return tabs;
	}

	public abstract PropertyPanelFactory<PROPERTY_PANEL> getPropertyPanelFactory();
}
