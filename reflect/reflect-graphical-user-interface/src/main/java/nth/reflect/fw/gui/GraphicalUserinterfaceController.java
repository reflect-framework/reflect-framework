package nth.reflect.fw.gui;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.component.mainwindow.MainWindow;
import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory;
import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.gui.item.dialog.DialogCancelItem;
import nth.reflect.fw.gui.item.dialog.DialogCloseItem;
import nth.reflect.fw.gui.item.dialog.DialogMethodItem;
import nth.reflect.fw.gui.item.dialog.DialogShowStackTraceItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.DialogType;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer1userinterface.item.Item;
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
		String title = languageProvider.getText("Confirmation");
		StringBuilder message = new StringBuilder();
		message.append(languageProvider.getText("Do you want to execute: "));
		message.append(methodInfo.createTitle(methodParameter));
		message.append("?");

		// show the dialog
		showDialog(DialogType.QUESTION, title, message.toString(), items);

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
		openFormTab(actionMethodOwner, actionMethodInfo, actionMethodParameterValue, actionMethodParameterValue,
				FormMode.EDIT);
	}

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
		String title = actionMethodInfo.createTitle(methodParameterValue);
		showProgressDialog(title, PERCENT_0, PERCENT_100);

		// start method execution thread
		try {
			startMethodExecutionThread(serviceObject, actionMethodInfo, methodParameterValue);
		} catch (Exception exception) {
			String message = languageProvider.getText("Failed to execute.");
			showErrorDialog(title, message, exception);
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
	 * {@link #showErrorDialog(String, String, Throwable)} if needed<br>
	 * - invoke
	 * {@link GraphicalUserinterfaceController#processActionMethodReturnValue(Object, ActionMethodInfo, Object, Object)}
	 * <br>
	 * <br>
	 * This method can be overridden if the framework of the user interface
	 * implementation requires a specific threading mechanism (i.e. Android)
	 * 
	 * @param methodOwner
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 * 
	 */

	public void startMethodExecutionThread(final Object methodOwner, final ActionMethodInfo actionMethodInfo,
			final Object methodParameterValue) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Object methodReturnValue = actionMethodInfo.invoke(methodOwner, methodParameterValue);
					// update current tab (calling a method on a object is most
					// likely to change its state
					Tab selectedTab = getTabs().getSelected();
					if (selectedTab != null) {
						selectedTab.onRefresh();
					}
					// show method result
					processActionMethodResult(methodOwner, actionMethodInfo, methodParameterValue, methodReturnValue);
				} catch (Exception exception) {
					String title = actionMethodInfo.createTitle(methodParameterValue);
					String message = languageProvider.getText("Failed to execute.");
					showErrorDialog(title, message, exception);
				}

			}
		};
		executeInThread(runnable);
	}

	/**
	 * Hook so that each type of user interface can execute the method using
	 * threading in they way preferred by user interface
	 * 
	 * @param methodExecutionRunnable
	 */
	public abstract void executeInThread(Runnable methodExecutionRunnable);

	@SuppressWarnings("unchecked")
	public void openFormTab(Object methodOwner, ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object domainObject, FormMode formMode) {
		Tabs<TAB> tabs = getTabs();
		for (Tab tab : tabs) {
			if (tab instanceof FormTab) {
				FormTab formTab = (FormTab) tab;
				// identical FormTab?
				if (methodOwner == formTab.getMethodOwner() && actionMethodInfo == formTab.getMethodInfo()
						&& methodParameterValue == formTab.getMethodParameter()
						&& domainObject == formTab.getDomainObject() && formMode == formTab.getFormMode()) {
					// activate identical FormTab
					tabs.setSelected((TAB) formTab);
					return;
				}
			}
		}
		// FormTab not found so create and show a new FormTab
		TAB formTab = createFormTab(methodOwner, actionMethodInfo, methodParameterValue, domainObject, formMode);
		tabs.add(formTab);
	}

	@SuppressWarnings("unchecked")
	public void openTableTab(Object methodOwner, ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		Tabs<TAB> tabs = getTabs();
		for (Tab tab : tabs) {
			if (tab instanceof GridTab) {
				GridTab gridTab = (GridTab) tab;
				// identical GridTab?
				if (methodOwner == gridTab.getMethodOwner() && actionMethodInfo == gridTab.getMethodInfo()
						&& methodParameterValue == gridTab.getMethodParameter()) {
					// activate identical GridTab
					tabs.setSelected((TAB) gridTab);
					return;
				}
			}
		}
		// GridTab not found so create and show a new GridTab
		TAB tableTab = createTableTab(methodOwner, actionMethodInfo, methodParameterValue, methodReturnValue);
		tabs.add(tableTab);
	}

	@SuppressWarnings("unchecked")
	public void openTreeTableTab(Object methodOwner, ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		Tabs<TAB> tabs = getTabs();
		for (Tab tab : tabs) {
			if (tab instanceof GridTab) {
				GridTab gridTab = (GridTab) tab;
				// identical GridTab?
				if (methodOwner == gridTab.getMethodOwner() && actionMethodInfo == gridTab.getMethodInfo()
						&& methodParameterValue == gridTab.getMethodParameter()) {
					// activate identical GridTab
					tabs.setSelected((TAB) gridTab);
					return;
				}
			}
		}
		// GridTab not found so create and show a new GridTab
		TAB treeTableTab = createTreeTableTab(methodOwner, actionMethodInfo, methodParameterValue, methodReturnValue);
		tabs.add(treeTableTab);
	}

	/**
	 * NOTE that the FormOkItem linked to the OK button of the FormTab will need to
	 * call {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)};
	 * 
	 * @param actionMethodOwner
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 * @param domainObject
	 * @return
	 */
	public abstract TAB createFormTab(Object actionMethodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode);

	public abstract TAB createTableTab(Object actionMethodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue);

	public abstract TAB createTreeTableTab(Object actionMethodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue);

	@Override
	public void showErrorDialog(String title, String message, Throwable throwable) {

		throwable.printStackTrace();
		// to help debugging (stack trace in eclipse console has hyper links to
		// code)

		List<Item> items = new ArrayList<Item>();
		DialogShowStackTraceItem showStackTraceItem = new DialogShowStackTraceItem(userInterfaceContainer, title,
				message, throwable);
		items.add(showStackTraceItem);
		DialogCloseItem closeItem = new DialogCloseItem(languageProvider);
		items.add(closeItem);

		showDialog(DialogType.ERROR, title, message, items);
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
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
		String title = methodInfo.createTitle(methodParameter);

		StringBuffer message = new StringBuffer(title);
		message.append(languageProvider.getText(" was successfully executed"));
		showInfoMessage(message.toString());
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
			Object methodResult) {
		Object domainObject = methodResult;
		openFormTab(methodOwner, methodInfo, methodParameter, domainObject, FormMode.READ_ONLY);

	}

	/**
	 * Process method to show the result of an {@link ActionMethod} with return type
	 * {@link List}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			List<?> methodResult) {
		openTableTab(methodOwner, methodInfo, methodParameter, methodResult);
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
				throw new RuntimeException(
						"Illegal Reflect URI. Format must be a standard URI like http://www.google.com or of format: Reflect:<service class package>.<service class name>.<service class method>",
						exception);
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
		String title = methodInfo.createTitle(methodParameter);

		StringBuilder message = new StringBuilder(title);
		message.append(": ");
		message.append(languageProvider.getText("Result is: "));
		message.append(methodResult);
		showInfoMessage(message.toString());

	}

	/**
	 * Provides simple feedback about an operation in a small popup. It only fills
	 * the amount of space required for the message and the current activity remains
	 * visible and interactive. The message popup will automatically disappear after
	 * a timeout
	 * 
	 * @param message
	 */
	public abstract void showInfoMessage(String message);

	public abstract void showDialog(DialogType dialogType, String title, String message, List<Item> items);

	/**
	 * TODO refactor so that progress dialog shows multiple thread monitors, while
	 * listening to updates
	 * 
	 * @param taskDescription
	 * @param currentValue
	 * @param maxValue
	 */
	public abstract void showProgressDialog(String taskDescription, int currentValue, int maxValue);

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
