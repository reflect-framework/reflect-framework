package nth.reflect.fw.junit;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.generic.util.ExceptionUtil;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.notification.Task;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class UserInterfaceControllerForJUnit extends UserInterfaceController {

	private final List<String> events;

	public UserInterfaceControllerForJUnit(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		events = new ArrayList<>();
	}

	@Override
	public void processActionMethod(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
		events.add(String.format("processActionMethod(%s, %s, %s)", methodOwner, methodInfo, methodParameter));
		super.processActionMethod(methodOwner, methodInfo, methodParameter);
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
	public void showError(TranslatableString title, TranslatableString message, Throwable throwable) {
		events.add(title.getTranslation(languageProvider));
		events.add(message.getTranslation(languageProvider));
		events.add(ExceptionUtil.getRootCauseStackTrace(throwable, languageProvider));
		throw new RuntimeException(throwable);
	}

	public void editActionMethodParameter(Object actionMethodOwner, ActionMethodInfo actionMethodInfo,
			Object actionMethodParameterValue) {
		events
				.add(String
						.format("editActionMethodParameter(%s, %s, %s)", actionMethodOwner, actionMethodInfo,
								actionMethodParameterValue));

	}

	@Override
	public void processActionMethodExecution(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
		events.add(String.format("processActionMethodExecution(%s, %s, %s)", methodOwner, methodInfo, methodParameter));
	}

	public void confirmActionMethod(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
		events.add(String.format("confirmActionMethod(%s, %s, %s, %s)", methodOwner, methodInfo, methodParameter));
	}

	public List<String> getEvents() {
		return events;
	}

	public List<String> getEventsAndClear() {
		List<String> eventsClone = new ArrayList<>();
		for (String event : events) {
			eventsClone.add(new String(event));
		}
		events.clear();
		return eventsClone;
	}

	@Override
	public void showMessage(TranslatableString message) {
		events.add(String.format("showMessage(%s)", message));
	}

}
