package nth.reflect.fw.ui.item.method;

import java.net.URL;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class MethodItem extends Item {

	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final ReadOnlyValueModel methodParameterValueModel;

	/**
	 * Wraps a {@link ActionMethodInfo} in a {@link Item} by overwriting the getters
	 * 
	 * @param methodOwner
	 * @param actionMethodInfo
	 */
	public MethodItem(final UserInterfaceContainer userInterfaceContainer,
			final Object methodOwner, final ActionMethodInfo actionMethodInfo,
			final ReadOnlyValueModel methodParameterValueModel) {
		super(userInterfaceContainer.get(LanguageProvider.class));
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValueModel = methodParameterValueModel;
		final UserInterfaceController userInterfaceController = userInterfaceContainer
				.get(UserInterfaceController.class);
		setAction(new Action() {
			@Override
			public void run() {
				Object methodParameterValue = (methodParameterValueModel == null) ? null
						: methodParameterValueModel.getValue();
				userInterfaceController.processActionMethod(methodOwner, actionMethodInfo,
						methodParameterValue);
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
		return actionMethodInfo.isEnabled(methodOwner) && (isMethodInvokable());
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.getDescription();
	}

	private boolean isMethodInvokable() {
		if (actionMethodInfo.hasParameter()) {
			return actionMethodInfo.hasParameterFactory()
					|| methodParameterValueModel != null
					&& methodParameterValueModel.getValue() != null;
		} else {
			return true;// no parameter needed
		}
	}

}
