package nth.introspect.ui.item.method;

import java.net.URI;

import nth.introspect.Introspect;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.type.TypeCategory;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class MethodItem extends Item {

	private final Object methodOwner;
	private final MethodInfo methodInfo;
	private final ReadOnlyValueModel methodParameterValueModel;

	/**
	 * Wraps a {@link MethodInfo} in a {@link Item} by overwriting the getters
	 * 
	 * @param methodOwner
	 * @param methodInfo
	 */
	public MethodItem(final UserInterfaceContainer userInterfaceContainer,
			final Object methodOwner, final MethodInfo methodInfo,
			final ReadOnlyValueModel methodParameterValueModel) {
		super(userInterfaceContainer.getLanguageProvider());
		this.methodOwner = methodOwner;
		this.methodInfo = methodInfo;
		this.methodParameterValueModel = methodParameterValueModel;
		final UserInterfaceProvider<?> userInterfaceProvider = userInterfaceContainer
				.getUserInterfaceProvider();
		setAction(new Action() {
			@Override
			public void run() {
				Object methodParameterValue = (methodParameterValueModel == null) ? null
						: methodParameterValueModel.getValue();
				userInterfaceProvider.startExecution(methodOwner, methodInfo,
						methodParameterValue);
			}

		});
	}

	@Override
	public URI getIconURI() {
		return methodInfo.getIconURI(methodOwner);
	}

	@Override
	public String getText() {
		return methodInfo.getText();
	}

	@Override
	public boolean isVisible() {
		return methodInfo.isVisible(methodOwner);
	}

	@Override
	public boolean isEnabled() {
		return methodInfo.isEnabled(methodOwner) && (isMethodInvokable());
	}

	@Override
	public String getDescription() {
		return super.getDescriptionWithHotKey(methodInfo.getDescription());
	}

	private boolean isMethodInvokable() {
		TypeCategory parameterType = methodInfo.getParameterType()
				.getTypeCategory();
		switch (parameterType) {
		case NONE:
			return true;// no parameter needed
		case DOMAIN_TYPE:
			// return true if the methodParameter not null or when there is a
			// parameter factory (because we do not need a field value than)
			return methodInfo.hasParameterFactory()
					|| methodParameterValueModel != null
					&& methodParameterValueModel.getValue() != null;
		}
		// TODO parameter type = collection
		return false;
	}

}
