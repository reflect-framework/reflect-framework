package nth.introspect.ui.item.dialog;

import java.net.URL;

import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class DialogMethodItem extends Item {

	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;

	/**
	 * Wraps a {@link ActionMethodInfo} in a {@link Item} by overwriting the
	 * getters
	 * 
	 * @param methodOwner
	 * @param actionMethodInfo
	 */
	public DialogMethodItem(UserInterfaceContainer userInterfaceContainer,
			final Object methodOwner, final ActionMethodInfo actionMethodInfo,
			final Object methodParameterValue) {
		super(userInterfaceContainer.get(LanguageProvider.class));
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;
		final UserInterfaceController userInterfaceController = userInterfaceContainer
				.get(UserInterfaceController.class);
		setAction(new Action() {
			@Override
			public void run() {
				userInterfaceController.processActionMethodExecution(
						methodOwner, actionMethodInfo, methodParameterValue);
				// TODO refresh form!!!!
			}

		});
	}

	@Override
	public URL getIconURL() {
		return actionMethodInfo.getIconURL(methodOwner);
	}

	@Override
	public String getText() {
		return actionMethodInfo.getDisplayName();
	}

	@Override
	public boolean isVisible() {
		return actionMethodInfo.isVisible(methodOwner);
	}

	@Override
	public boolean isEnabled() {
		return actionMethodInfo.isEnabled(methodOwner);
	}

	@Override
	public String getDescription() {
		return super
				.getDescriptionWithHotKey(actionMethodInfo.getDescription());
	}

}
