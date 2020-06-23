package nth.reflect.fw.gui.item.method;

import java.net.URL;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer2service.ServiceObjectActionMethod;
import nth.reflect.fw.layer3domain.DomainObjectActionMethod;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * {@link DomainObjectActionMethod}s and {@link ServiceObjectActionMethod}s can
 * be wrapped in {@link MethodItem}s, e.g. so that they can be used as menu item
 * 
 * @author nilsth
 *
 */
public class MethodItem extends Item {

	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final ReadOnlyValueModel methodParameterValueModel;

	/**
	 * Wraps a {@link ActionMethodInfo} in a {@link Item} by overwriting the getters
	 * 
	 * @param methodOwner
	 * @param methodInfo
	 */
	public MethodItem(final UserInterfaceContainer container, final Object methodOwner,
			final ActionMethodInfo methodInfo, final ReadOnlyValueModel methodParameterValueModel) {
		super(container.get(LanguageProvider.class));
		this.methodOwner = methodOwner;
		this.actionMethodInfo = methodInfo;
		this.methodParameterValueModel = methodParameterValueModel;
		ActionMethodExecutionProvider executionProvider = container.get(ActionMethodExecutionProvider.class);
		setAction(new Action() {
			@Override
			public void run() {
				Object methodParameterValue = (methodParameterValueModel == null) ? null
						: methodParameterValueModel.getValue();
				executionProvider.process(container, methodInfo, methodOwner, methodParameterValue);
			}

		});
	}

	@Override
	public URL getIconURL() {
		return actionMethodInfo.getFontIconUrl(methodOwner);
	}

	@Override
	public String getText() {
		return actionMethodInfo.getDisplayName().getTranslation();
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
		return actionMethodInfo.getDescription().getTranslation();
	}

	private boolean isMethodInvokable() {
		if (actionMethodInfo.hasParameter()) {
			return actionMethodInfo.hasParameterFactory()
					|| methodParameterValueModel != null && methodParameterValueModel.getValue() != null;
		} else {
			return true;// no parameter needed
		}
	}

}
