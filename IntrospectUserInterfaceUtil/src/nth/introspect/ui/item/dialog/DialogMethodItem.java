package nth.introspect.ui.item.dialog;

import java.net.URI;

import nth.introspect.Introspect;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;

public class DialogMethodItem extends Item {

	private final Object methodOwner;
	private final MethodInfo methodInfo;
	private final Object methodParameterValue;

	/**
	 * Wraps a {@link MethodInfo} in a {@link Item} by overwriting the getters
	 * 
	 * @param methodOwner
	 * @param methodInfo
	 */
	public DialogMethodItem(UserInterfaceContainer userInterfaceContainer , final Object methodOwner, final MethodInfo methodInfo, final Object methodParameterValue) {
		super(userInterfaceContainer.getLanguageProvider());
		this.methodOwner = methodOwner;
		this.methodInfo = methodInfo;
		this.methodParameterValue = methodParameterValue;
final UserInterfaceController<?> userInterfaceController=userInterfaceContainer.getUserInterfaceController();
		setAction(new Action() {
			@Override
			public void run() {
				userInterfaceController.excuteMethod(methodOwner, methodInfo, methodParameterValue);
				//TODO refresh form!!!!
			}

		});
	}


	@Override
	public URI getIconURI() {
		return methodInfo.getIconURI(methodOwner);
	}

	@Override
	public String getText() {
		return methodInfo.getDisplayName();
	}

	@Override
	public boolean isVisible() {
		return methodInfo.isVisible(methodOwner);
	}

	@Override
	public boolean isEnabled() {
		return methodInfo.isEnabled(methodOwner) ;
	}

	@Override
	public String getDescription() {
		return super.getDescriptionWithHotKey( methodInfo.getDescription());
	}


}
