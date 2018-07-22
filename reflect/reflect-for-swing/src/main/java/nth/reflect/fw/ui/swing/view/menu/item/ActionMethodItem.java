package nth.reflect.fw.ui.swing.view.menu.item;

import java.net.URL;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;

/**
 * @deprecated use {@link MethodItem}
 * @author nilsth
 *
 */
public class ActionMethodItem implements Item {

	private final UserInterfaceController userInterfaceController;
	private final Object serviceObject;
	private final ActionMethodInfo methodInfo;

	public ActionMethodItem(UserInterfaceController userInterfaceController, Object serviceObject, ActionMethodInfo methodInfo) {
		this.userInterfaceController = userInterfaceController;
		this.serviceObject = serviceObject;
		this.methodInfo = methodInfo;
	}

	@Override
	public String getText() {
		return methodInfo.getDisplayName();
	}

	@Override
	public String getDescription() {
		return methodInfo.getDescription();
	}

	@Override
	public URL getIcon() {
		return methodInfo.getIconURL(serviceObject);
	}

	@Override
	public boolean isVisible() {
		return methodInfo.isVisible(serviceObject);
	}

	@Override
	public boolean isEnabled() {
		return methodInfo.isEnabled(serviceObject);
	}

	@Override
	public void onAction() {
		userInterfaceController.processActionMethod(serviceObject, methodInfo, null);
	}

}
