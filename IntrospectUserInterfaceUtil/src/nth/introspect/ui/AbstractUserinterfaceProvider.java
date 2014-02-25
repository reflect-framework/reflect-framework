package nth.introspect.ui;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.method.filter.MethodNameFilter;
import nth.introspect.provider.domain.info.type.TypeCategory;
import nth.introspect.provider.userinterface.DownloadStream;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.util.TitleUtil;

/**
 * 
 * @author nilsth
 * 
 * @param <T>
 *            A user interface specific class (often a component container/
 *            layout) that implements {@link View}
 */
public abstract class AbstractUserinterfaceProvider<T> implements
		UserInterfaceProvider<T> {

	private static final int PERCENT_0 = 0;
	private static final int PERCENT_100 = 100;

	@Override
	public void startExecution(Object serviceObject, MethodInfo methodInfo,
			Object methodParameterValue) {
		try {
			FormModeType executionMode = methodInfo.getFormMode();
			switch (executionMode) {
			case showParameterThenClose:
			case showParameterThenExecuteMethodOrCancel:
			case editParameterThenExecuteMethodOrCancel:
			case executeMethodAfterConformation:
				Object domainObject = methodParameterValue;
				if (methodInfo.hasParameterFactory()) {
					domainObject = methodInfo
							.createMethodParameter(serviceObject);
				}
				T formView = createFormView(serviceObject, methodInfo,
						methodParameterValue, domainObject, executionMode);
				getViewContainer().addView(formView);
				break;
			case executeMethodDirectly:
				excuteMethod(serviceObject, methodInfo, methodParameterValue);
				break;
			}
		} catch (Exception exception) {
			String title = TitleUtil.createTitle(methodInfo,
					methodParameterValue, true);
			String message = Introspect.getLanguageProvider().getText(
					"Failed to execute.");
			showErrorDialog(title, message, exception);
		}
	}

	@Override
	public void excuteMethod(Object serviceObject, MethodInfo methodInfo,
			Object methodParameterValue) {
		// TODO check if the method is enabled before the method is executed
		// (otherwise throw exception)
		// TODO validate the method parameter value before the method is
		// executed (if invalid: throw exception)

		// show ProgressDialog
		String title = TitleUtil.createTitle(methodInfo, methodParameterValue,
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
					Introspect.getUserInterfaceProvider()
							.showMethodReturnValue(serviceObject, methodInfo,
									methodParameterValue, methodReturnValue);
				} catch (Exception exception) {
					String title = TitleUtil.createTitle(methodInfo,
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
		String title = TitleUtil.createTitle(methodInfo, methodParameterValue,
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
			T formView = createFormView(serviceObject, methodInfo,
					methodParameterValue, domainObject,
					FormModeType.showParameterThenClose);
			getViewContainer().addView(formView);
			break;
		case COLLECTION_TYPE:
			// TODO case tableModel:
			T tableView = createTableView(serviceObject, methodInfo,
					methodParameterValue, methodReturnValue);
			getViewContainer().addView(tableView);
			break;
		case HIERARCHICAL_DOMAIN_TYPE:
			T treeTableView = createTreeTableView(serviceObject, methodInfo,
					methodParameterValue, methodReturnValue);
			getViewContainer().addView(treeTableView);
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
					DomainProvider domainAdapter = Introspect
							.getDomainProvider();
					Object serviceObject2 = domainAdapter
							.getServiceObject(serviceClass);
					List<MethodInfo> methodInfos = domainAdapter
							.getMethodInfos(serviceClass, new MethodNameFilter(
									methodName));
					startExecution(serviceObject2, methodInfos.get(0), null);
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

	// TODO view factory?
	/**
	 * NOTE that the FormOkItem linked to the OK button of the FormView will
	 * need to call {@link #excuteMethod(Object, MethodInfo, Object)};
	 * 
	 * @param serviceObject
	 * @param methodInfo
	 * @param methodParameterValue
	 * @param domainObject
	 * @param executionMode
	 * @return
	 */
	public abstract T createFormView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object domainObject, FormModeType executionMode);

	public abstract T createTableView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object methodReturnValue);

	public abstract T createTreeTableView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object methodReturnValue);

	// TODO public abstract T createMenuView();

}
