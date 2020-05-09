package nth.reflect.fw.gui.item.method;

import java.net.URL;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.item.HierarchicalItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

/**
 * This {@link Item} represents a {@link DomainObject} or {@link ServiceObject}
 * that has {@link MethodItem}s
 * 
 * @author nilsth
 *
 */
public class MethodOwnerItem extends HierarchicalItem {

	private final DomainClassInfo methodOwnerInfo;
	private final Object methodOwner;

	public MethodOwnerItem(UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			Predicate<ActionMethodInfo> methodFilter, ReadOnlyValueModel methodParameterValueModel) {
		super(userInterfaceContainer.get(LanguageProvider.class));
		this.methodOwner = methodOwner;
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		methodOwnerInfo = reflectionProvider.getDomainClassInfo(methodOwner.getClass());

		addActionMethods(userInterfaceContainer, methodOwner, methodFilter, methodParameterValueModel,
				reflectionProvider);

	}

	private void addActionMethods(UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			Predicate<ActionMethodInfo> methodFilter, ReadOnlyValueModel methodParameterValueModel,
			ReflectionProvider reflectionProvider) {
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(methodOwner.getClass());
		List<ActionMethodInfo> actionMethodInfos = domainClassInfo.getActionMethodInfos(methodFilter);
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			MethodItem methodItem = new MethodItem(userInterfaceContainer, methodOwner, actionMethodInfo,
					methodParameterValueModel);
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
		return methodOwnerInfo.getDisplayName().getTranslation();
	}

	@Override
	public String getDescription() {
		return methodOwnerInfo.getDescription().getTranslation();
	}

	@Override
	public URL getIconURL() {
		return methodOwnerInfo.getFontIconUrl(methodOwner);
	}

}
