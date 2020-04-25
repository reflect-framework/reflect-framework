package nth.reflect.fw.gui.component.table.info.dataprovider;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class FellowshipGridTab implements GridTab {

	private static final Object NO_PARAMETER = null;
	private final UserInterfaceContainer userInterfaceContainer;
	private final FellowshipService fellowshipService;
	private final ActionMethodInfo actionMethodInfo;

	public FellowshipGridTab(UserInterfaceContainer userInterfaceContainer, String methodName) {
		this.userInterfaceContainer = userInterfaceContainer;
		fellowshipService = userInterfaceContainer.get(FellowshipService.class);
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(FellowshipService.class);
		actionMethodInfo = serviceClassInfo.getActionMethodInfo(methodName);
	}

	@Override
	public Object getMethodOwner() {
		return fellowshipService;
	}

	@Override
	public ActionMethodInfo getMethodInfo() {
		return actionMethodInfo;
	}

	@Override
	public Object getMethodParameter() {
		return NO_PARAMETER;
	}

	@Override
	public String getDisplayName() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public void onRefresh() {
	}

	@Override
	public ReadOnlyValueModel getSelectedRowModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInterfaceContainer getUserInterfaceContainer() {
		return userInterfaceContainer;
	}

}
