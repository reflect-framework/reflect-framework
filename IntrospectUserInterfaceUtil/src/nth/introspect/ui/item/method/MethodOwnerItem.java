package nth.introspect.ui.item.method;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.ui.item.HierarchicalItem;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class MethodOwnerItem extends HierarchicalItem {

	private ClassInfo methodOwnerInfo;
	private final Object methodOwner;

	public MethodOwnerItem( UserInterfaceContainer userInterfaceContainer, Object methodOwner, Filter<MethodInfo> methodFilter, ReadOnlyValueModel methodParameterValueModel) {
		super(userInterfaceContainer.getLanguageProvider());
		this.methodOwner = methodOwner;
		DomainInfoProvider domainInfoProvider = userInterfaceContainer.getDomainInfoProvider();
		methodOwnerInfo = domainInfoProvider.getClassInfo(methodOwner.getClass());
		
		setText(methodOwnerInfo.getText());
		setDescription(methodOwnerInfo.getDescription());
		List<MethodInfo> methodInfos = domainInfoProvider.getMethodInfos(methodOwner.getClass(), methodFilter);

		UserInterfaceProvider<?> userInterfaceProvider=userInterfaceContainer.getUserInterfaceProvider();
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
