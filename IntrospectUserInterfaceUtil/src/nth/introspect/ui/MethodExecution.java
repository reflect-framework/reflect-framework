package nth.introspect.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nth.introspect.Introspect;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.type.TypeCategory;
import nth.introspect.util.TitleUtil;
import nth.introspect.valuemodel.ReadWriteValueModel;

public class MethodExecution implements Runnable {

	private static final int PERCENTAGE_0 = 0;
	private static final int PERCENTAGE_100 = 100;
	private MethodInfo methodInfo;
	private Object methodParameterValue;
	private ReadWriteValueModel valueModel;

	public MethodExecution(MethodInfo methodInfo, Object methodParameterValue, ReadWriteValueModel valueModel) {
		this.methodInfo = methodInfo;
		this.methodParameterValue = methodParameterValue;
		this.valueModel = valueModel;
	}

	@Override
	public void run() {
		String title = TitleUtil.createTitle(methodInfo, methodParameterValue, true);

		showProgressDialog(title);

		Object methodReturnValue;
		try {
			methodReturnValue = executeMethod(methodInfo, methodParameterValue);

			valueModel.setValue(methodReturnValue);
			
		} catch (Exception e) {
			showErrorDialog(title, e);
		}

		hideProgressDialog();
	}

	private void showErrorDialog(String title, Exception exception) {
		String message = Introspect.getLanguageProvider().getText("Failed to execute.");
		Introspect.getUserInterfaceProvider().showErrorDialog(title, message, exception);
	}

	private void hideProgressDialog() {
		Introspect.getUserInterfaceProvider().closeProgressDialog();
	}

	private Object executeMethod(MethodInfo methodInfo, Object methodParameterValue) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = methodInfo.getMethod();
		Object[] methodArguments = null;
		if (TypeCategory.NONE == methodInfo.getParameterType().getTypeCategory()) {
			methodArguments = new Object[0];
		} else {
			// domain of collection
			methodArguments = new Object[] {methodParameterValue};
		}

		Class<?> methodOwnerClass = method.getDeclaringClass();
		IntrospectContainer introspectContainer=Introspect.getIntrospectContainer();
		Object serviceObject = introspectContainer.get(methodOwnerClass);
		return method.invoke(serviceObject, methodArguments);
	}

	public void showProgressDialog(String title) {
		Introspect.getUserInterfaceProvider().showProgressDialog(title, PERCENTAGE_0, PERCENTAGE_100);
	}

}
