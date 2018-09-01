package nth.reflect.ui.vaadin10.view.table;

import java.net.URL;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.ui.vaadin10.view.TabView;

public class TableView extends TabView {

	private final UserInterfaceContainer userInterfaceContainer;
	private final Object serviceObject;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;

	public TableView(UserInterfaceContainer userInterfaceContainer, Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
				this.userInterfaceContainer = userInterfaceContainer;
				this.serviceObject = serviceObject;
				this.actionMethodInfo = actionMethodInfo;
				this.methodParameterValue = methodParameterValue;
	}

	@Override
	public String getViewTitle() {
		return actionMethodInfo.getDisplayName();
	}

	@Override
	public String getViewDescription() {
		return actionMethodInfo.getDescription();
	}

	@Override
	public URL getViewIconURL() {
		return actionMethodInfo.getFontIconUrl(serviceObject);
	}

	@Override
	public void onViewActivate() {
		// TODO Auto-generated method stub

	}

}
