package nth.introspect.ui.swing.view.menu.item;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.NoParameterOrParameterFactoryFilter;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;

public class ServiceObjectItem implements Item {

	private final Object serviceObject;
	private final ClassInfo serviceObjectInfo;
	private final List<Item> actionMethodItems;

	public ServiceObjectItem(UserInterfaceController userInterfaceController, Object serviceObject, ClassInfo serviceObjectInfo) {
		this.serviceObject = serviceObject;
		this.serviceObjectInfo = serviceObjectInfo;
		this.actionMethodItems = createActionMethodItems(userInterfaceController);
	}

	@Override
	public String getText() {
		return serviceObjectInfo.getDisplayName();
	}

	@Override
	public String getDescription() {
		return serviceObjectInfo.getDescription();
	}

	@Override
	public URI getIcon() {
		return serviceObjectInfo.getIconURI(serviceObject);
	}

	@Override
	public boolean isVisible() {
		return hasVisibleActionMethod();
	}

	private boolean hasVisibleActionMethod() {
		for (Item actionMethodItem : actionMethodItems) {
			if (actionMethodItem.isVisible()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<Item> createActionMethodItems(UserInterfaceController userInterfaceController) {
		Filter<ActionMethodInfo> filter = new NoParameterOrParameterFactoryFilter();
		List<ActionMethodInfo> actionMethodInfos = serviceObjectInfo.getActionMethodInfos(filter);
		List<Item> actionMethodItems = new ArrayList<>();
		for (ActionMethodInfo methodInfo : actionMethodInfos) {
			ActionMethodItem actionMethodItem = new ActionMethodItem(userInterfaceController, serviceObject, methodInfo);
			actionMethodItems.add(actionMethodItem);
		}
		return actionMethodItems;
	}

	public List<Item> getActionMethodItems() {
		return actionMethodItems;
	}

	@Override
	public void onAction() {
		// TODO invoke MenuList so that it can decide which ServiceObjectItems need to be collapsed by calling ServiceObjectItem onCollapse()
	}

}
