package nth.reflect.fw.junit;

import nth.reflect.fw.generic.util.ExceptionUtil;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.notification.Task;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class UserInterfaceControllerForJUnit extends UserInterfaceController {

	private final LogProvider log;

	public UserInterfaceControllerForJUnit(UserInterfaceContainer userInterfaceContainer, LogProvider log) {
		super(userInterfaceContainer);
		this.log = log;
	}

	@Override
	public void onTaskChange(Task task) {
		log.add(String.format("onTaskChange(%s)", task));
	}

	@Override
	public void onRefresh() {
		log.add("onRefresh()");
	}

	@Override
	public void onNewMessage(String title, String message) {
		log.add(String.format("onTaskChange(%s, %s)", title, message));
	}

	@Override
	public void launch() {
		log.add("start()");
	}

	@Override
	public void showError(TranslatableString title, TranslatableString message, Throwable throwable) {
		log.add(title.getTranslation(languageProvider));
		log.add(message.getTranslation(languageProvider));
		log.add(ExceptionUtil.getRootCauseStackTrace(throwable, languageProvider));
		throw new RuntimeException(throwable);
	}

	public void editActionMethodParameter(Object actionMethodOwner, ActionMethodInfo actionMethodInfo,
			Object actionMethodParameterValue) {
		log
				.add(String
						.format("editActionMethodParameter(%s, %s, %s)", actionMethodOwner, actionMethodInfo,
								actionMethodParameterValue));

	}

	public void confirmActionMethod(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
		log.add(String.format("confirmActionMethod(%s, %s, %s, %s)", methodOwner, methodInfo, methodParameter));
	}

	@Override
	public void showMessage(TranslatableString message) {
		log.add(String.format("showMessage(%s)", message));
	}

}
