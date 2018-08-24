package nth.reflect.fw.ui.item.method;

import java.net.URL;
import java.util.List;

import nth.reflect.fw.generic.filter.Filter;
import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer2service.ServiceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.ui.item.HierarchicalItem;

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
		boolean serviceObjectChildrenBeforeActionMethods = methodOwnerInfo
				.getServiceObjectChildrenBeforeActionMethods();

		if (serviceObjectChildrenBeforeActionMethods) {
			addServiceObjectChildren(userInterfaceContainer, methodOwner, methodFilter,
					methodParameterValueModel);
			addActionMethods(userInterfaceContainer, methodOwner, methodFilter,
					methodParameterValueModel, reflectionProvider);
		} else {
			addActionMethods(userInterfaceContainer, methodOwner, methodFilter,
					methodParameterValueModel, reflectionProvider);
			addServiceObjectChildren(userInterfaceContainer, methodOwner, methodFilter,
					methodParameterValueModel);
		}

	}

	private void addServiceObjectChildren(UserInterfaceContainer userInterfaceContainer,
			Object methodOwner, Filter<ActionMethodInfo> methodFilter,
			ReadOnlyValueModel methodParameterValueModel) {
		Class<?>[] serviceObjectChildren = methodOwnerInfo.getServiceObjectChildren();
		ServiceContainer serviceContainer = userInterfaceContainer.get(ServiceContainer.class);
		for (Class<?> serviceClass : serviceObjectChildren) {
			Object serviceObject = serviceContainer.get(serviceClass);
			MethodOwnerItem methodOwnerItem = new MethodOwnerItem(userInterfaceContainer,
					serviceObject, methodFilter, methodParameterValueModel);
			addItem(methodOwnerItem);
		}
	}

	private void addActionMethods(UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			Filter<ActionMethodInfo> methodFilter, ReadOnlyValueModel methodParameterValueModel,
			ReflectionProvider reflectionProvider) {
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
		return methodOwnerInfo.getFontIconUrl(methodOwner);
	}

}
