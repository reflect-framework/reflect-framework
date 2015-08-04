package nth.introspect.ui.item.method;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.ui.item.HierarchicalItem;

public class MethodOwnerItem extends HierarchicalItem {

	private ClassInfo methodOwnerInfo;
	private final Object methodOwner;

	public MethodOwnerItem( UserInterfaceContainer userInterfaceContainer, Object methodOwner, Filter<MethodInfo> methodFilter, ReadOnlyValueModel methodParameterValueModel) {
		super(userInterfaceContainer.getLanguageProvider());
		this.methodOwner = methodOwner;
		ReflectionProvider reflectionProvider = userInterfaceContainer.getReflectionProvider();
		methodOwnerInfo = reflectionProvider.getClassInfo(methodOwner.getClass());
		
		setText(methodOwnerInfo.getDisplayName());
		setDescription(methodOwnerInfo.getDescription());
		List<MethodInfo> methodInfos = reflectionProvider.getMethodInfos(methodOwner.getClass(), methodFilter);

		UserInterfaceController<?> userInterfaceController=userInterfaceContainer.getUserInterfaceController();
		for (MethodInfo methodInfo : methodInfos) {
			MethodItem methodItem = new MethodItem(userInterfaceContainer, methodOwner, methodInfo, methodParameterValueModel);
			addItem(methodItem);
		}
	}

	@Override
	public boolean isVisible() {
		if (methodOwnerInfo.isVisible(methodOwner)) {
			// check visibility of children
			Item lastVisibleItem = null;
			for (Item child : getChildren()) {
				if (child.isSeparator()) {
					// set visibility of separator
					if (child.isVisible()) {
						if (lastVisibleItem == null || lastVisibleItem.isSeparator()) {
							// hide separator
							child.setVisible(false);
						} else {
							// separator remains visible
							lastVisibleItem = child;
						}
					}
				} else {
					if (child.isVisible()) {
						lastVisibleItem = child;
					}
				}
			}

			if (lastVisibleItem != null && lastVisibleItem.isSeparator()) {
				// hide separator when it is the last visible item
				lastVisibleItem.setVisible(false);
			}

			// hide this item if this item does not contain a visible item
			return lastVisibleItem != null;
		} else {
			return false;
		}
	}


}
