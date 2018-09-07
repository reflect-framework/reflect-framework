package nth.reflect.fw.junit;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.generic.exception.ReflectException;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.notification.Task;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class UserInterfaceControllerForJUnit extends UserInterfaceController {

	private final List<String> events;

	public UserInterfaceControllerForJUnit(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		events = new ArrayList<>();
	}

	@Override
	public void processActionMethod(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		events.add(String.format("processActionMethod(%s, %s, %s)", methodOwner, methodInfo,
				methodParameter));
		super.processActionMethod(methodOwner, methodInfo, methodParameter);
	}

	@Override
	public void processActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		events.add(String.format("processActionMethod(%s, %s, %s)", methodOwner, methodInfo,
				methodParameter));
		super.processActionMethodResult(methodOwner, methodInfo, methodParameter, methodResult);
	}

	@Override
	public void onTaskChange(Task task) {
		events.add(String.format("onTaskChange(%s)", task));
	}

	@Override
	public void onRefresh() {
		events.add("onRefresh()");
	}

	@Override
	public void onNewMessage(String title, String message) {
		events.add(String.format("onTaskChange(%s, %s)", title, message));
	}

	@Override
	public void launch() {
		events.add("start()");
	}

	@Override
	public void showErrorDialog(String title, String message, Throwable throwable) {
		throw new ReflectException(title + "-" + message, throwable);
	}

	
	public void editActionMethodParameter(Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object actionMethodParameterValue) {
		events.add(String.format("editActionMethodParameter(%s, %s, %s)", actionMethodOwner,
				actionMethodInfo, actionMethodParameterValue));

	}

	public void editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo,
			UploadStream uploadStream){
		events.add(String.format("editActionMethodParameter(%s, %s, %s)", methodOwner,
				methodInfo, uploadStream));
	}

	@Override
	public void processActionMethodExecution(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		events.add(String.format("processActionMethodExecution(%s, %s, %s)", methodOwner,
				methodInfo, methodParameter));
	}

	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		events.add(String.format("showActionMethodResult(%s, %s, %s)", methodOwner, methodInfo,
				methodParameter));
	}

	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		events.add(String.format("showActionMethodResult(%s, %s, %s, (DomainObject) %s)",
				methodOwner, methodInfo, methodParameter, methodResult));
	}

	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, List<?> methodResult) {
		events.add(String.format("showActionMethodResult(%s, %s, %s, (List<?>) %s)", methodOwner,
				methodInfo, methodParameter, methodResult));
	}

	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, String methodResult) {
		events.add(String.format("showActionMethodResult(%s, %s, %s, (String) %s)", methodOwner,
				methodInfo, methodParameter, methodResult));
	}

	
	public void confirmActionMethod(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		events.add(String.format("confirmActionMethod(%s, %s, %s, %s)", methodOwner,
				methodInfo, methodParameter));
	} 
	
	public List<String> getEvents() {
		List<String> eventsClone=new ArrayList<>();
		for (String event : events) {
			eventsClone.add(new String(event));
		}
		events.clear();
		return eventsClone;
	}

}
