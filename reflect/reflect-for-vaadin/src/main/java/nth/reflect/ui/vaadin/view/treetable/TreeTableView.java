package nth.reflect.ui.vaadin.view.treetable;

import java.net.URL;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.ui.vaadin.view.container.TabView;

public class TreeTableView extends TabView {

	private static final long serialVersionUID = -4778137603063800299L;
	private final UserInterfaceContainer userInterfaceContainer;
	private final Object actionMethodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;

	public TreeTableView(UserInterfaceContainer userInterfaceContainer, Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
				this.userInterfaceContainer = userInterfaceContainer;
				this.actionMethodOwner = actionMethodOwner;
				this.actionMethodInfo = actionMethodInfo;
				this.methodParameterValue = methodParameterValue;
		// TODO Auto-generated constructor stub
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
		return actionMethodInfo.getFontIconUrl(actionMethodOwner);
	}

	@Override
	public void onViewActivate() {
		// TODO Auto-generated method stub

	}

}
