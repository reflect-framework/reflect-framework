package nth.reflect.fw.gui.item.dialog;

import java.net.URL;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodInvoker;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class DialogMethodItem extends Item {

	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;

	/**
	 * Wraps a {@link ActionMethodInfo} in a {@link Item} by overwriting the getters
	 * 
	 * @param methodOwner
	 * @param methodInfo
	 */
	public DialogMethodItem(UserInterfaceContainer container, final Object methodOwner,
			final ActionMethodInfo methodInfo, final Object methodParameter) {
		super(container.get(LanguageProvider.class));
		this.methodOwner = methodOwner;
		this.actionMethodInfo = methodInfo;

		Action action = createAction(container, methodOwner, methodInfo, methodParameter);
		setAction(action);
	}

	private Action createAction(UserInterfaceContainer container, final Object methodOwner,
			final ActionMethodInfo methodInfo, final Object methodParameter) {
		return new ActionMethodInvoker(container, methodInfo, methodOwner, methodParameter);
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
		return actionMethodInfo.isEnabled(methodOwner);
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.getDescription().getTranslation();
	}

}
