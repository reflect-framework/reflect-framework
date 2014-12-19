package nth.introspect.ui;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.method.filter.MethodNameFilter;
import nth.introspect.provider.domain.info.type.TypeCategory;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.userinterface.DialogType;
import nth.introspect.provider.userinterface.DownloadStream;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.item.dialog.DialogCancelItem;
import nth.introspect.ui.item.dialog.DialogCloseItem;
import nth.introspect.ui.item.dialog.DialogMethodItem;
import nth.introspect.ui.item.dialog.DialogShowStackTraceItem;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;
import nth.introspect.ui.view.TableView;
import nth.introspect.util.TitleUtil;

/**
 * 
 * @author nilsth
 * 
 * @param <T>
 *            A user interface specific class (often a component container/
 *            layout) that implements {@link View}
 */
public abstract class AbstractUserinterfaceProvider<T extends View> implements
		UserInterfaceProvider<T> {

	private static final int PERCENT_0 = 0;
	private static final int PERCENT_100 = 100;
	private final UserInterfaceContainer introspectOuterContainer;
	private final DomainInfoProvider domainInfoProvider;

	public AbstractUserinterfaceProvider(UserInterfaceContainer introspectOuterContainer) {
		this.introspectOuterContainer = introspectOuterContainer;
		this.domainInfoProvider = introspectOuterContainer.getDomainInfoProvider();
	}
	
	@Override
	public void startExecution(Object methodOwner, MethodInfo methodInfo,
			Object methodParameterValue) {
		try {
			ExecutionModeType executionMode = methodInfo.getExecutionMode();
			switch (executionMode) {
			case EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL:
				createAndShowParameterForm(methodOwner, methodInfo,
						methodParameterValue, FormMode.EDIT_MODE);
				break;
			case EXECUTE_METHOD_AFTER_CONFORMATION:
				createAndShowConformationDialog(methodOwner, methodInfo,
						methodParameterValue);
				break;
			case EXECUTE_METHOD_DIRECTLY:
				excuteMethod(methodOwner, methodInfo, methodParameterValue);
				break;
			}
		} catch (Exception exception) {
			String title = TitleUtil.createTitle(domainInfoProvider, methodInfo,
					methodParameterValue, true);
			String message = Introspect.getLanguageProvider().getText(
					"Failed to execute.");
			showErrorDialog(title, message, exception);
		}
	}

	private void createAndShowParameterForm(Object methodOwner,
			MethodInfo methodInfo, Object methodParameterValue,
			FormMode formMode) throws InstantiationException,
			IllegalAccessException {
		Object domainObject = methodParameterValue;
		if (methodInfo.hasParameterFactory()) {
			domainObject = methodInfo.createMethodParameter(methodOwner);
		}
		openFormView(methodOwner, methodInfo, methodParameterValue,
				domainObject, formMode);
	}

	private void createAndShowConformationDialog(Object methodOwner,
			MethodInfo methodInfo, Object methodParameterValue) {
		// create the dialog items/ buttons
		List<Item> items = new ArrayList<Item>();
		DialogMethodItem methodExecuteItem = new DialogMethodItem(methodOwner,
				methodInfo, methodParameterValue);
		items.add(methodExecuteItem);
		DialogCancelItem cancelItem = new DialogCancelItem();
		items.add(cancelItem);

		// create the confirmation title and message
		LanguageProvider languageProvider = Introspect.getLanguageProvider();
		String title = languageProvider.getText("Confirmation");
		StringBuilder message = new StringBuilder();
		message.append(languageProvider.getText("Do you want to: "));
		message.append(TitleUtil.createTitle(domainInfoProvider, methodInfo, methodParameterValue,
				false));
		message.append("?");

		// show the dialog
		showDialog(DialogType.QUESTION, title, message.toString(), items);
	}

	@Override
	public void excuteMethod(Object serviceObject, MethodInfo methodInfo,
			Object methodParameterValue) {
		// TODO check if the method is enabled before the method is executed
		// (otherwise throw exception)
		// TODO validate the method parameter value before the method is
		// executed (if invalid: throw exception)

		// show ProgressDialog
		String title = TitleUtil.createTitle(domainInfoProvider, methodInfo, methodParameterValue,
				true);
		showProgressDialog(title, PERCENT_0, PERCENT_100);

		// start method execution thread
		try {
			startMethodExecutionThread(serviceObject, methodInfo,
					methodParameterValue);
		} catch (Exception exception) {
			String message = Introspect.getLanguageProvider().getText(
					"Failed to execute.");
			showErrorDialog(title, message, exception);
		}
	}

	/**
	 * This method is called from
	 * {@link #excuteMethod(Object, MethodInfo, Object)} <br>
	 * This method must do 3 things<br>
	 * - invoke Object methodReturnValue=
	 * {@link MethodInfo#invoke(Object, Object)} in a separate thread (may be
	 * time consuming). This will need to be implemented per user interface
	 * because threading may need to be differently implemented for each user
	 * interface framework. <br>
	 * - catch errors during the execution of the thread and call
	 * {@link #showErrorDialog(String, String, Throwable)} if needed<br>
	 * - invoke
	 * {@link AbstractUserinterfaceProvider#showMethodReturnValue(Object, MethodInfo, Object, Object)}
	 * <br>
	 * <br>
	 * This method can be overridden if the framework of the user interface
	 * implementation requires a specific threading mechanism (i.e. Android)
	 * 
	 * @param serviceObject
	 * @param methodInfo
	 * @param methodParameterValue
	 * 
	 */

	public void startMethodExecutionThread(final Object serviceObject,
			final MethodInfo methodInfo, final Object methodParameterValue) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Object methodReturnValue = null;
					// skip getting the method return value if it returns a
					// collection
					if (methodInfo.getReturnType().getTypeCategory() != TypeCategory.COLLECTION_TYPE
							&& methodInfo.getReturnType().getTypeCategory() != TypeCategory.HIERARCHICAL_DOMAIN_TYPE) {
						// not a collection so get method return value
						Method method = methodInfo.getMethod();
						Object[] methodArguments = null;
						if (TypeCategory.NONE == methodInfo.getParameterType()
								.getTypeCategory()) {
							methodArguments = new Object[0];
						} else {
							// domain of collection
							methodArguments = new Object[] { methodParameterValue };
						}
						methodReturnValue = method.invoke(serviceObject,
								methodArguments);
					}

					UserInterfaceProvider<?> userInterfaceProvider = Introspect
							.getUserInterfaceProvider();
					// update current view (calling a method on a object is most
					// likely to change its state
					View selectedView = userInterfaceProvider
							.getViewContainer().getSelectedView();
					if (selectedView != null) {
						selectedView.onViewActivate();
					}
					// show method result
					userInterfaceProvider
							.showMethodReturnValue(serviceObject, methodInfo,
									methodParameterValue, methodReturnValue);
				} catch (Exception exception) {
					String title = TitleUtil.createTitle(domainInfoProvider, methodInfo,
							methodParameterValue, true);
					String message = Introspect.getLanguageProvider().getText(
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
			MethodInfo methodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		String title = TitleUtil.createTitle(domainInfoProvider, methodInfo, methodParameterValue,
				true);
		switch (methodInfo.getReturnType().getTypeCategory()) {
		case NONE:// void
			StringBuffer message = new StringBuffer(methodInfo.getText());
			message.append(Introspect.getLanguageProvider().getText(
					" was successfully executed"));
			showInfoMessage(message.toString());
			break;
		case DOMAIN_TYPE:
			Object domainObject = methodReturnValue;
			openFormView(serviceObject, methodInfo, methodParameterValue,
					domainObject, FormMode.READ_ONLY_MODE);
			break;
		case COLLECTION_TYPE:
			// TODO case tableModel:
			openTableView(serviceObject, methodInfo, methodParameterValue,
					methodReturnValue);
			break;
		case HIERARCHICAL_DOMAIN_TYPE:
			openTreeTableView(serviceObject, methodInfo, methodParameterValue,
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
					Object serviceObject2 =getIntrospectOuterContainer().get(serviceClass);
					List<MethodInfo> methodInfos = domainInfoProvider
							.getMethodInfos(serviceClass, new MethodNameFilter(
									methodName));
					startExecution(serviceObject, methodInfos.get(0), null);
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
			message.append(Introspect.getLanguageProvider().getText(
					"Result is: "));
			message.append(methodReturnValue.toString());
			showInfoMessage(message.toString());
			break;
		}

	}

	public void openFormView(Object methodOwner, MethodInfo methodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode) {
		ViewContainer<T> viewContainer = Introspect.getUserInterfaceProvider()
				.getViewContainer();

		for (int i = 0; i < viewContainer.getViewCount(); i++) {
			T view = viewContainer.getView(i);
			if (view instanceof FormView) {
				FormView formView = (FormView) view;
				// identical formView?
				if (methodOwner == formView.getMethodOwner()
						&& methodInfo == formView.getMethodInfo()
						&& methodParameterValue == formView
								.getMethodParameter()
						&& domainObject == formView.getDomainObject()
						&& formMode == formView.getFormMode()) {
					// activate identical formView
					viewContainer.setSelectView((T) formView);
					return;
				}
			}
		}
		// formView not found so create and show a new formView
		T formView = createFormView(methodOwner, methodInfo,
				methodParameterValue, domainObject, formMode);
		viewContainer.addView(formView);
	}

	public void openTableView(Object methodOwner, MethodInfo methodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		ViewContainer<T> viewContainer = Introspect.getUserInterfaceProvider()
				.getViewContainer();

		for (int i = 0; i < viewContainer.getViewCount(); i++) {
			T view = viewContainer.getView(i);
			if (view instanceof TableView) {
				TableView tableView = (TableView) view;
				// identical tableView?
				if (methodOwner == tableView.getMethodOwner()
						&& methodInfo == tableView.getMethodInfo()
						&& methodParameterValue == tableView
								.getMethodParameter()) {
					// activate identical tableView
					viewContainer.setSelectView((T) tableView);
					return;
				}
			}
		}
		// tableView not found so create and show a new tableView
		T tableView = createTableView(methodOwner, methodInfo,
				methodParameterValue, methodReturnValue);
		viewContainer.addView(tableView);
	}

	public void openTreeTableView(Object methodOwner, MethodInfo methodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		ViewContainer<T> viewContainer = Introspect.getUserInterfaceProvider()
				.getViewContainer();

		for (int i = 0; i < viewContainer.getViewCount(); i++) {
			T view = viewContainer.getView(i);
			if (view instanceof TableView) {
				TableView tableView = (TableView) view;
				// identical tableView?
				if (methodOwner == tableView.getMethodOwner()
						&& methodInfo == tableView.getMethodInfo()
						&& methodParameterValue == tableView
								.getMethodParameter()) {
					// activate identical tableView
					viewContainer.setSelectView((T) tableView);
					return;
				}
			}
		}
		// tableView not found so create and show a new tableView
		T treeTableView = createTreeTableView(methodOwner, methodInfo,
				methodParameterValue, methodReturnValue);
		viewContainer.addView(treeTableView);
	}

	// TODO view factory?
	/**
	 * NOTE that the FormOkItem linked to the OK button of the FormView will
	 * need to call {@link #excuteMethod(Object, MethodInfo, Object)};
	 * 
	 * @param serviceObject
	 * @param methodInfo
	 * @param methodParameterValue
	 * @param domainObject
	 * @return
	 */
	public abstract T createFormView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object domainObject, FormMode formMode);

	public abstract T createTableView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object methodReturnValue);

	public abstract T createTreeTableView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object methodReturnValue);

	// TODO public abstract T createMenuView();

	@Override
	public void showErrorDialog(String title, String message,
			Throwable throwable) {

		throwable.printStackTrace(); // to help debugging (stack trace in
										// eclipse console has hyper links to
										// code)

		List<Item> items = new ArrayList<Item>();
		DialogShowStackTraceItem showStackTraceItem = new DialogShowStackTraceItem(
				title, message, throwable);
		items.add(showStackTraceItem);
		DialogCloseItem closeItem = new DialogCloseItem();
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

	public UserInterfaceContainer getIntrospectOuterContainer() {
		return introspectOuterContainer;
	}


}
