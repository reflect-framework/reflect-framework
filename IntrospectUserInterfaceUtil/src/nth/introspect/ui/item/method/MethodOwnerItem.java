package nth.introspect.ui.item.method;

import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.item.HierarchicalItem;

public class MethodOwnerItem extends HierarchicalItem {

	private ClassInfo methodOwnerInfo;
	private final Object methodOwner;

	public MethodOwnerItem(UserInterfaceContainer userInterfaceContainer,
			Object methodOwner, Filter<ActionMethodInfo> methodFilter,
			ReadOnlyValueModel methodParameterValueModel) {
		super(userInterfaceContainer.get(LanguageProvider.class));
		this.methodOwner = methodOwner;
		ReflectionProvider reflectionProvider = userInterfaceContainer
				.get(ReflectionProvider.class);
		methodOwnerInfo = reflectionProvider.getClassInfo(methodOwner
				.getClass());

		setText(methodOwnerInfo.getDisplayName());
		setDescription(methodOwnerInfo.getDescription());
		List<ActionMethodInfo> actionMethodInfos = reflectionProvider
				.getMethodInfos(methodOwner.getClass(), methodFilter);

		UserInterfaceController<?> userInterfaceController = userInterfaceContainer
				.get(UserInterfaceController.class);
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			MethodItem methodItem = new MethodItem(userInterfaceContainer,
					methodOwner, actionMethodInfo, methodParameterValueModel);
			addItem(methodItem);
		}
	}

	@Override
	public boolean isVisible() {
		// check visibility of children
		Item lastVisibleItem = null;
		for (Item child : getChildren()) {
			if (child.isSeparator()) {
				// set visibility of separator
				if (child.isVisible()) {
					if (lastVisibleItem == null
							|| lastVisibleItem.isSeparator()) {
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
	}

}
