package nth.introspect.ui.item.dialog;

import java.net.URI;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;

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
	public DialogMethodItem(final UserInterfaceProvider<?> userInterfaceProvider , final Object methodOwner, final MethodInfo methodInfo, final Object methodParameterValue) {
		this.methodOwner = methodOwner;
		this.methodInfo = methodInfo;
		this.methodParameterValue = methodParameterValue;

		setAction(new Action() {
			@Override
			public void run() {
				userInterfaceProvider.excuteMethod(methodOwner, methodInfo, methodParameterValue);
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
		return methodInfo.getText();
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
