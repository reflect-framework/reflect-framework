package nth.introspect.ui.junit;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.generic.exception.IntrospectException;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.notification.Task;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

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
	public void start() {
		events.add("start()");
	}

	@Override
	public void showErrorDialog(String title, String message, Throwable throwable) {
		throw new IntrospectException(title + "-" + message, throwable);
	}

	@Override
	public void editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		events.add(String.format("editActionMethodParameter(%s, %s, %s)", methodOwner, methodInfo,
				methodParameter));
	}

	@Override
	public void confirmActionMethod(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		events.add(String.format("confirmActionMethod(%s, %s, %s)", methodOwner, methodInfo,
				methodParameter));
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
			Object methodParameter, URI methodResult) {
		events.add(String.format("showActionMethodResult(%s, %s, %s, (URI) %s)", methodOwner,
				methodInfo, methodParameter, methodResult));
	}

	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, DownloadStream methodResult) {
		events.add(String.format("showActionMethodResult(%s, %s, %s, (DownloadStream) %s)",
				methodOwner, methodInfo, methodParameter, methodResult));
	}

	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, String methodResult) {
		events.add(String.format("showActionMethodResult(%s, %s, %s, (String) %s)", methodOwner,
				methodInfo, methodParameter, methodResult));
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
