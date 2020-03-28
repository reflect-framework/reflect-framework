package nth.reflect.ui.vaadin.tab.treegrid;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.ui.vaadin.tab.Tab;

public class TreeTableTab extends Tab {

	private static final long serialVersionUID = -4778137603063800299L;
	private final ActionMethodInfo actionMethodInfo;

	public TreeTableTab(UserInterfaceContainer userInterfaceContainer, Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		this.actionMethodInfo = actionMethodInfo;
	}

	@Override
	public String getDisplayName() {
		return actionMethodInfo.getDisplayName().getTranslation();
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.getDescription().getTranslation();
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
	}

}
