package nth.introspect.ui;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DialogType;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.notification.Task;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;
import nth.introspect.ui.item.dialog.DialogCancelItem;
import nth.introspect.ui.item.dialog.DialogCloseItem;
import nth.introspect.ui.item.dialog.DialogMethodItem;
import nth.introspect.ui.item.dialog.DialogShowStackTraceItem;
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
public abstract class GraphicalUserinterfaceController<T extends View> implements
		UserInterfaceController<T> {

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
	protected final UserInterfaceContainer userInterfaceContainer;
	protected final ReflectionProvider reflectionProvider;
	protected final LanguageProvider languageProvider;

	public GraphicalUserinterfaceController(UserInterfaceContainer userInterfaceContainer) {
		this.userInterfaceContainer=userInterfaceContainer;
		this.reflectionProvider=userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider=userInterfaceContainer.get(LanguageProvider.class);
		NotificationProvider notificationProvider = userInterfaceContainer.get(NotificationProvider.class);
		notificationProvider.addListener(this);
	}
	
	@Override
	public void startExecution(Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue) {
		try {
			ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
			switch (executionMode) {
			case EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL:
				createAndShowParameterForm(methodOwner, actionMethodInfo,
						methodParameterValue, FormMode.EDIT_MODE);
				break;
			case EXECUTE_METHOD_AFTER_CONFORMATION:
				createAndShowConformationDialog(methodOwner, actionMethodInfo,
						methodParameterValue);
				break;
			case EXECUTE_METHOD_DIRECTLY:
				excuteMethod(methodOwner, actionMethodInfo, methodParameterValue);
				break;
			}
		} catch (Exception exception) {
			String title = TitleUtil.createTitle(reflectionProvider, actionMethodInfo,
					methodParameterValue, true);
			String message = languageProvider.getText(
					"Failed to execute.");
			showErrorDialog(title, message, exception);
		}
	}

	private void createAndShowParameterForm(Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			FormMode formMode) throws InstantiationException,
			IllegalAccessException {
		Object domainObject = methodParameterValue;
		if (actionMethodInfo.hasParameterFactory()) {
			domainObject = actionMethodInfo.createMethodParameter(methodOwner);
		}
		openFormView(methodOwner, actionMethodInfo, methodParameterValue,
				domainObject, formMode);
	}

	private void createAndShowConformationDialog(Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		// create the dialog items/ buttons
		List<Item> items = new ArrayList<Item>();
		DialogMethodItem methodExecuteItem = new DialogMethodItem(userInterfaceContainer,  methodOwner,
				actionMethodInfo, methodParameterValue);
		items.add(methodExecuteItem);
		DialogCancelItem cancelItem = new DialogCancelItem(languageProvider);
		items.add(cancelItem);

		// create the confirmation title and message
		String title = languageProvider.getText("Confirmation");
		StringBuilder message = new StringBuilder();
		message.append(languageProvider.getText("Do you want to: "));
		message.append(TitleUtil.createTitle(reflectionProvider, actionMethodInfo, methodParameterValue,
				false));
		message.append("?");

		// show the dialog
		showDialog(DialogType.QUESTION, title, message.toString(), items);
	}

	@Override
	public void excuteMethod(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue) {
		// TODO check if the method is enabled before the method is executed
		// (otherwise throw exception)
		// TODO validate the method parameter value before the method is
		// executed (if invalid: throw exception)

		// show ProgressDialog
		String title = TitleUtil.createTitle(reflectionProvider, actionMethodInfo, methodParameterValue,
				true);
		showProgressDialog(title, PERCENT_0, PERCENT_100);

		// start method execution thread
		try {
			startMethodExecutionThread(serviceObject, actionMethodInfo,
					methodParameterValue);
		} catch (Exception exception) {
			String message = languageProvider.getText(
					"Failed to execute.");
			showErrorDialog(title, message, exception);
		}
	}

	/**
	 * This method is called from
	 * {@link #excuteMethod(Object, ActionMethodInfo, Object)} <br>
	 * This method must do 3 things<br>
	 * - invoke Object methodReturnValue=
	 * {@link ActionMethodInfo#invoke(Object, Object)} in a separate thread (may be
	 * time consuming). This will need to be implemented per user interface
	 * because threading may need to be differently implemented for each user
	 * interface framework. <br>
	 * - catch errors during the execution of the thread and call
	 * {@link #showErrorDialog(String, String, Throwable)} if needed<br>
	 * - invoke
	 * {@link GraphicalUserinterfaceController#showMethodReturnValue(Object, ActionMethodInfo, Object, Object)}
	 * <br>
	 * <br>
	 * This method can be overridden if the framework of the user interface
	 * implementation requires a specific threading mechanism (i.e. Android)
	 * 
	 * @param serviceObject
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 * 
	 */

	public void startMethodExecutionThread(final Object serviceObject,
			final ActionMethodInfo actionMethodInfo, final Object methodParameterValue) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Object methodReturnValue = null;
					// skip getting the method return value if it returns a
					// collection
					if (actionMethodInfo.getReturnType().getTypeCategory() != TypeCategory.COLLECTION_TYPE
							&& actionMethodInfo.getReturnType().getTypeCategory() != TypeCategory.HIERARCHICAL_DOMAIN_TYPE) {
						// not a collection so get method return value
						Method method = actionMethodInfo.getActionMethod();
						Object[] methodArguments = null;
						if (TypeCategory.NONE == actionMethodInfo.getParameterType()
								.getTypeCategory()) {
							methodArguments = new Object[0];
						} else {
							// domain of collection
							methodArguments = new Object[] { methodParameterValue };
						}
						methodReturnValue = method.invoke(serviceObject,
								methodArguments);
					}

					// update current view (calling a method on a object is most
					// likely to change its state
					View selectedView = getViewContainer().getSelectedView();
					if (selectedView != null) {
						selectedView.onViewActivate();
					}
					// show method result
					showMethodReturnValue(serviceObject, actionMethodInfo,
									methodParameterValue, methodReturnValue);
				} catch (Exception exception) {
					String title = TitleUtil.createTitle(reflectionProvider, actionMethodInfo,
							methodParameterValue, true);
					String message = languageProvider.getText(
							"Failed to execute.");
					showErrorDialog(title, message, exception);
				}

			}
		};
		Thread methodExecutionThread = new Thread(runnable);
		methodExecutionThread.start();
	}

	@Override
	public void showMethodReturnValue(Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		String title = TitleUtil.createTitle(reflectionProvider, actionMethodInfo, methodParameterValue,
				true);
		switch (actionMethodInfo.getReturnType().getTypeCategory()) {
		case NONE:// void
			StringBuffer message = new StringBuffer(actionMethodInfo.getDisplayName());
			message.append(languageProvider.getText(
					" was successfully executed"));
			showInfoMessage(message.toString());
			break;
		case DOMAIN_TYPE:
			Object domainObject = methodReturnValue;
			openFormView(serviceObject, actionMethodInfo, methodParameterValue,
					domainObject, FormMode.READ_ONLY_MODE);
			break;
		case COLLECTION_TYPE:
			// TODO case tableModel:
			openTableView(serviceObject, actionMethodInfo, methodParameterValue,
					methodReturnValue);
			break;
		case HIERARCHICAL_DOMAIN_TYPE:
			openTreeTableView(serviceObject, actionMethodInfo, methodParameterValue,
					methodReturnValue);
			break;
		case URI_TYPE:
			URI uri = (URI) methodReturnValue;
			String uriString = uri.toString();
			if (uriString.toLowerCase().startsWith(
					Introspect.class.getSimpleName().toLowerCase() + ":")) {
				try {
					int positionColon = uriString.indexOf(":");
					int positionLastDot = uriString.lastIndexOf(".");
					String serviceClassName = uriString.substring(
							positionColon + 1, positionLastDot);
					String methodName = uriString
							.substring(positionLastDot + 1);
					Class<?> serviceClass = Class.forName(serviceClassName);
					Object serviceObject2 =userInterfaceContainer.get(serviceClass);
					List<ActionMethodInfo> actionMethodInfos = reflectionProvider
							.getMethodInfos(serviceClass, new MethodNameFilter(
									methodName));
					startExecution(serviceObject, actionMethodInfos.get(0), null);
				} catch (Exception exception) {
					throw new RuntimeException(
							"Illegal Introspect URI. Format must be a standard URI like http://www.google.com or of format: Introspect:<service class package>.<service class name>.<service class method>",
							exception);
				}
			} else {
				openURI(uri);
			}
			break;
		case DOWNLOAD_STREAM_TYPE:
			downloadFile((DownloadStream) methodReturnValue);
			break;
		case JAVA_TYPE:
			message = new StringBuffer(title);
			message.append(": ");
			message.append(languageProvider.getText(
					"Result is: "));
			message.append(methodReturnValue.toString());
			showInfoMessage(message.toString());
			break;
		}

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
						&& methodParameterValue == formView
								.getMethodParameter()
						&& domainObject == formView.getDomainObject()
						&& formMode == formView.getFormMode()) {
					// activate identical formView
					viewContainer.setSelectedView((T) formView);
					return;
				}
			}
		}
		// formView not found so create and show a new formView
		T formView = createFormView(methodOwner, actionMethodInfo,
				methodParameterValue, domainObject, formMode);
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
						&& methodParameterValue == tableView
								.getMethodParameter()) {
					// activate identical tableView
					viewContainer.setSelectedView((T) tableView);
					return;
				}
			}
		}
		// tableView not found so create and show a new tableView
		T tableView = createTableView(methodOwner, actionMethodInfo,
				methodParameterValue, methodReturnValue);
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
						&& methodParameterValue == tableView
								.getMethodParameter()) {
					// activate identical tableView
					viewContainer.setSelectedView((T) tableView);
					return;
				}
			}
		}
		// tableView not found so create and show a new tableView
		T treeTableView = createTreeTableView(methodOwner, actionMethodInfo,
				methodParameterValue, methodReturnValue);
		viewContainer.addView(treeTableView);
	}

	// TODO view factory?
	/**
	 * NOTE that the FormOkItem linked to the OK button of the FormView will
	 * need to call {@link #excuteMethod(Object, ActionMethodInfo, Object)};
	 * 
	 * @param serviceObject
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 * @param domainObject
	 * @return
	 */
	public abstract T createFormView(Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object domainObject, FormMode formMode);

	public abstract T createTableView(Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue);

	public abstract T createTreeTableView(Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue);

	// TODO public abstract T createMenuView();

	@Override
	public void showErrorDialog(String title, String message,
			Throwable throwable) {

		throwable.printStackTrace(); // to help debugging (stack trace in
										// eclipse console has hyper links to
										// code)

		List<Item> items = new ArrayList<Item>();
		DialogShowStackTraceItem showStackTraceItem = new DialogShowStackTraceItem(userInterfaceContainer,
				title, message, throwable);
		items.add(showStackTraceItem);
		DialogCloseItem closeItem = new DialogCloseItem(languageProvider);
		items.add(closeItem);

		showDialog(DialogType.ERROR, title, message, items);
	}

	@Override
	public void refresh() {
		// refresh current view
		View view = getViewContainer().getSelectedView();
		if (view != null) {
			view.onViewActivate();
		}
	}

	//TODO we might want to remove this: We want the UserInterfaceContainer to know the UserInterfaceController. Not visa versa
	public UserInterfaceContainer getUserInterfaceContainer() {
		return userInterfaceContainer;
	}
	

}
