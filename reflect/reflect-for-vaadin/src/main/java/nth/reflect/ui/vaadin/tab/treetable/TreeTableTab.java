package nth.reflect.ui.vaadin.tab.treetable;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.ui.vaadin.tab.Tab;

public class TreeTableTab extends Tab {

	private static final long serialVersionUID = -4778137603063800299L;
	private final UserInterfaceContainer userInterfaceContainer;
	private final Object actionMethodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;

	public TreeTableTab(UserInterfaceContainer userInterfaceContainer, Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.actionMethodOwner = actionMethodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDisplayName() {
		return actionMethodInfo.getDisplayName();
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.getDescription();
	}

	@Override
	public void onSelected() {
		// TODO Auto-generated method stub
	}

}
