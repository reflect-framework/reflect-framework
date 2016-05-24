package nth.introspect.ui.item.method;

import java.net.URL;
import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.item.HierarchicalItem;

public class MethodOwnerItem extends HierarchicalItem {

	private ClassInfo methodOwnerInfo;
	private final Object methodOwner;

	public MethodOwnerItem(UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			Filter<ActionMethodInfo> methodFilter, ReadOnlyValueModel methodParameterValueModel) {
		super(userInterfaceContainer.get(LanguageProvider.class));
		this.methodOwner = methodOwner;
		ReflectionProvider reflectionProvider = userInterfaceContainer
				.get(ReflectionProvider.class);
		methodOwnerInfo = reflectionProvider.getClassInfo(methodOwner.getClass());

		ClassInfo classInfo = reflectionProvider.getClassInfo(methodOwner.getClass());
		List<ActionMethodInfo> actionMethodInfos = classInfo.getActionMethodInfos(methodFilter);

		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			MethodItem methodItem = new MethodItem(userInterfaceContainer, methodOwner,
					actionMethodInfo, methodParameterValueModel);
			addItem(methodItem);
		}
	}

	@Override
	public boolean isVisible() {
		for (Item child : getChildren()) {
			if (child.isVisible()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getText() {
		return methodOwnerInfo.getDisplayName();
	}

	@Override
	public String getDescription() {
		return methodOwnerInfo.getDescription();
	}

	@Override
	public URL getIconURL() {
		return methodOwnerInfo.getIconURL(methodOwner);
	}

}
