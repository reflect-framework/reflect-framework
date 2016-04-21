package nth.introspect.ui;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.IntrospectFramework;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DialogType;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.notification.Task;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.item.dialog.DialogCancelItem;
import nth.introspect.ui.item.dialog.DialogCloseItem;
import nth.introspect.ui.item.dialog.DialogMethodItem;
import nth.introspect.ui.item.dialog.DialogShowStackTraceItem;
import nth.introspect.ui.style.DisplaySize;
import nth.introspect.ui.style.DisplayType;
import nth.introspect.ui.style.MaterialColorPalette;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.basic.Color;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;
import nth.introspect.ui.view.TableView;

/**
 * 
 * @author nilsth
 * 
 * @param <T>
 *            A user interface specific class (often a component container/
 *            layout) that implements {@link View}
 */
public abstract class GraphicalUserinterfaceController<T extends View>
		extends UserInterfaceController {

	public void confirmActionMethod(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		// create the dialog items/ buttons
		List<Item> items = new ArrayList<Item>();
		DialogMethodItem methodExecuteItem = new DialogMethodItem(userInterfaceContainer,
				methodOwner, methodInfo, methodParameter);
		items.add(methodExecuteItem);
		DialogCancelItem cancelItem = new DialogCancelItem(languageProvider);
		items.add(cancelItem);

		// create the confirmation MaterialAppBarTitle and message
		String title = languageProvider.getText("Confirmation");
		StringBuilder message = new StringBuilder();
		message.append(languageProvider.getText("Do you want to execute: "));
		message.append(
				TitleUtil.createTitle(reflectionProvider, methodInfo, methodParameter, false));
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
		refresh();
	}

	@Override
	public void onNewMessage(String title, String message) {
		// TODO Auto-generated method stub

	}

	private static final int PERCENT_0 = 0;
	private static final int PERCENT_100 = 100;

	public GraphicalUserinterfaceController(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		NotificationProvider notificationProvider = userInterfaceContainer
				.get(NotificationProvider.class);
		notificationProvider.addListener(this);
	}

	public void editActionMethodParameter(Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object actionMethodParameterValue) {
		openFormView(actionMethodOwner, actionMethodInfo, actionMethodParameterValue,
				actionMethodParameterValue, FormMode.EDIT_MODE);
	}

	@Override
	public void processActionMethodExecution(Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		// TODO check if the method is enabled before the method is executed
		// (otherwise throw exception)
		// TODO validate the method parameter value before the method is
		// executed (if invalid: throw exception)

		// show ProgressDialog
		String title = TitleUtil.createTitle(reflectionProvider, actionMethodInfo,
				methodParameterValue, true);
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
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)}
	 * <br>
	 * This method must do 3 things<br>
	 * - invoke Object methodReturnValue=
	 * {@link ActionMethodInfo#invoke(Object, Object)} in a separate thread (may
	 * be time consuming). This will need to be implemented per user interface
	 * because threading may need to be differently implemented for each user
	 * interface framework. <br>
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

	public void startMethodExecutionThread(final Object methodOwner,
			final ActionMethodInfo actionMethodInfo, final Object methodParameterValue) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Object methodReturnValue = actionMethodInfo.invoke(methodOwner,
							methodParameterValue);
					// update current view (calling a method on a object is most
					// likely to change its state
					View selectedView = getViewContainer().getSelectedView();
					if (selectedView != null) {
						selectedView.onViewActivate();
					}
					// show method result
					processActionMethodResult(methodOwner, actionMethodInfo, methodParameterValue,
							methodReturnValue);
				} catch (Exception exception) {
					String title = TitleUtil.createTitle(reflectionProvider, actionMethodInfo,
							methodParameterValue, false);
					String message = languageProvider.getText("Failed to execute.");
					showErrorDialog(title, message, exception);
				}

			}
		};
		Thread methodExecutionThread = new Thread(runnable);
		methodExecutionThread.start();
	}

	public void openFormView(Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode) {
		ViewContainer<T> viewContainer = getViewContainer();

		for (int i = 0; i < viewContainer.getViewCount(); i++) {
			T view = viewContainer.getView(i);
			if (view instanceof FormView) {
				FormView formView = (FormView) view;
				// identical formView?
				if (methodOwner == formView.getMethodOwner()
						&& actionMethodInfo == formView.getMethodInfo()
						&& methodParameterValue == formView.getMethodParameter()
						&& domainObject == formView.getDomainObject()
						&& formMode == formView.getFormMode()) {
					// activate identical formView
					viewContainer.setSelectedView((T) formView);
					return;
				}
			}
		}
		// formView not found so create and show a new formView
		T formView = createFormView(methodOwner, actionMethodInfo, methodParameterValue,
				domainObject, formMode);
		viewContainer.addView(formView);
	}

	public void openTableView(Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		ViewContainer<T> viewContainer = getViewContainer();

		for (int i = 0; i < viewContainer.getViewCount(); i++) {
			T view = viewContainer.getView(i);
			if (view instanceof TableView) {
				TableView tableView = (TableView) view;
				// identical tableView?
				if (methodOwner == tableView.getMethodOwner()
						&& actionMethodInfo == tableView.getMethodInfo()
						&& methodParameterValue == tableView.getMethodParameter()) {
					// activate identical tableView
					viewContainer.setSelectedView((T) tableView);
					return;
				}
			}
		}
		// tableView not found so create and show a new tableView
		T tableView = createTableView(methodOwner, actionMethodInfo, methodParameterValue,
				methodReturnValue);
		viewContainer.addView(tableView);
	}

	public void openTreeTableView(Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		ViewContainer<T> viewContainer = getViewContainer();

		for (int i = 0; i < viewContainer.getViewCount(); i++) {
			T view = viewContainer.getView(i);
			if (view instanceof TableView) {
				TableView tableView = (TableView) view;
				// identical tableView?
				if (methodOwner == tableView.getMethodOwner()
						&& actionMethodInfo == tableView.getMethodInfo()
						&& methodParameterValue == tableView.getMethodParameter()) {
					// activate identical tableView
					viewContainer.setSelectedView((T) tableView);
					return;
				}
			}
		}
		// tableView not found so create and show a new tableView
		T treeTableView = createTreeTableView(methodOwner, actionMethodInfo, methodParameterValue,
				methodReturnValue);
		viewContainer.addView(treeTableView);
	}

	/**
	 * NOTE that the FormOkItem linked to the OK button of the FormView will
	 * need to call
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)};
	 * 
	 * @param serviceObject
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 * @param domainObject
	 * @return
	 */
	public abstract T createFormView(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode);

	public abstract T createTableView(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue);

	public abstract T createTreeTableView(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue);

	// TODO public abstract T createMenuView();

	@Override
	public void showErrorDialog(String title, String message, Throwable throwable) {

		throwable.printStackTrace(); // to help debugging (stack trace in
										// eclipse console has hyper links to
										// code)

		List<Item> items = new ArrayList<Item>();
		DialogShowStackTraceItem showStackTraceItem = new DialogShowStackTraceItem(
				userInterfaceContainer, title, message, throwable);
		items.add(showStackTraceItem);
		DialogCloseItem closeItem = new DialogCloseItem(languageProvider);
		items.add(closeItem);

		showDialog(DialogType.ERROR, title, message, items);
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
	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		String title = TitleUtil.createTitle(reflectionProvider, methodInfo, methodParameter, true);

		StringBuffer message = new StringBuffer(title);
		message.append(languageProvider.getText(" was successfully executed"));
		showInfoMessage(message.toString());
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
	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		Object domainObject = methodResult;
		openFormView(methodOwner, methodInfo, methodParameter, domainObject,
				FormMode.READ_ONLY_MODE);

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
	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, List<?> methodResult) {
		openTableView(methodOwner, methodInfo, methodParameter, methodResult);
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
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, URI methodResult) {
		URI uri = methodResult;
		String uriString = uri.toString();
		if (uriString.toLowerCase()
				.startsWith(IntrospectFramework.class.getSimpleName().toLowerCase() + ":")) {
			try {
				int positionColon = uriString.indexOf(":");
				int positionLastDot = uriString.lastIndexOf(".");
				String serviceClassName = uriString.substring(positionColon + 1, positionLastDot);
				String methodName = uriString.substring(positionLastDot + 1);
				Class<?> serviceClass = Class.forName(serviceClassName);
				ClassInfo classInfo = reflectionProvider.getClassInfo(serviceClass);
				List<ActionMethodInfo> actionMethodInfos = classInfo
						.getActionMethodInfos(new MethodNameFilter(methodName));
				processActionMethod(methodOwner, actionMethodInfos.get(0), null);
			} catch (Exception exception) {
				throw new RuntimeException(
						"Illegal Introspect URI. Format must be a standard URI like http://www.google.com or of format: Introspect:<service class package>.<service class name>.<service class method>",
						exception);
			}
		} else {
			openURI(uri);
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
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, DownloadStream methodResult) {
		DownloadStream downloadStream = methodResult;
		downloadFile(downloadStream);
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
	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, String methodResult) {
		String title = TitleUtil.createTitle(reflectionProvider, methodInfo, methodParameter, true);

		StringBuilder message = new StringBuilder(title);
		message.append(": ");
		message.append(languageProvider.getText("Result is: "));
		message.append(methodResult);
		showInfoMessage(message.toString());

	}

	public void refresh() {
		// refresh current view
		View view = getViewContainer().getSelectedView();
		if (view != null) {
			view.onViewActivate();
		}
	}

	/**
	 * Provides simple feedback about an operation in a small popup. It only
	 * fills the amount of space required for the message and the current
	 * activity remains visible and interactive. The message popup will
	 * automatically disappear after a timeout
	 * 
	 * @param message
	 */
	public abstract void showInfoMessage(String message);

	public abstract void showDialog(DialogType dialogType, String title, String message,
			List<Item> items);

	/**
	 * TODO refactor so that progress dialog shows multiple thread monitors,
	 * while listening to updates
	 * 
	 * @param taskDescription
	 * @param currentValue
	 * @param maxValue
	 */
	public abstract void showProgressDialog(String taskDescription, int currentValue, int maxValue);

	/**
	 * TODO remove. progress dialog should close automatically when all tasks
	 * are completed
	 */
	public abstract void closeProgressDialog();

	public abstract void openURI(URI uri);

	public abstract void downloadFile(DownloadStream downloadStream);

	@SuppressWarnings("rawtypes")
	public abstract ViewContainer getViewContainer();

	public abstract DisplaySize getDisplaySize();

	public abstract int getDisplayWidthInInches();

	public MaterialStyle getMaterialStyle() {
		boolean hasKeyboardAndMouse = true;// assumption
		DisplayType displayType = new DisplayType(getDisplayWidthInInches(), hasKeyboardAndMouse);
		MaterialStyle materialStyle = new MaterialStyle(MaterialColorPalette.TEAL,
				MaterialColorPalette.ORANGE, Color.WHITE, displayType);
		return materialStyle;
	}

}
