package nth.introspect.ui.item.method;

import java.net.URI;

import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

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
	public URI getIconURI() {
		return actionMethodInfo.getIconURI(methodOwner);
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
		return super.getDescriptionWithHotKey(actionMethodInfo.getDescription());
	}

	private boolean isMethodInvokable() {
		TypeCategory parameterType = actionMethodInfo.getParameterType()
				.getTypeCategory();
		switch (parameterType) {
		case NONE:
			return true;// no parameter needed
		case DOMAIN_TYPE:
			// return true if the methodParameter not null or when there is a
			// parameter factory (because we do not need a field value than)
			return actionMethodInfo.hasParameterFactory()
					|| methodParameterValueModel != null
					&& methodParameterValueModel.getValue() != null;
		}
		// TODO parameter type = collection
		return false;
	}

}
