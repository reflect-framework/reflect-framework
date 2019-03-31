package nth.reflect.fw.gui.component.tab.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.gui.item.method.MethodItem;
import nth.reflect.fw.gui.util.collection.UnmodifiableCollection;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

/**
 * Menu {@link Item}s for a {@link FormTabMenu}
 * 
 * @author nilsth
 *
 */
public class FormTabMenuItems extends UnmodifiableCollection<MethodItem> {

	private static final long serialVersionUID = -7940254102480175383L;

	public FormTabMenuItems(FormTab formTab) {
		super(createFormTabMenuItems(formTab));
	}

	private static Collection<MethodItem> createFormTabMenuItems(FormTab formTab) {
		FormMode formMode = formTab.getFormMode();
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		Object domainObject = formTab.getDomainObject();
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainObject.getClass());
		List<ActionMethodInfo> actionMethodInfos = domainClassInfo
				.getActionMethodInfos(createActionMethodFilter(formMode));

		List<MethodItem> methodItems = new ArrayList<>();
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			MethodItem methodItem = new MethodItem(userInterfaceContainer, domainObject, actionMethodInfo, null);
			methodItems.add(methodItem);
		}
		return methodItems;
	}

	private static Predicate<ActionMethodInfo> createActionMethodFilter(FormMode formMode) {
		return new Predicate<ActionMethodInfo>() {

			@Override
			public boolean test(ActionMethodInfo actionMethodInfo) {
				boolean mayChangeDomainOnject = FormMode.EDIT == formMode;
				boolean wontChangeDomainObject = FormMode.READ_ONLY == formMode && actionMethodInfo.isReadOnly();
				boolean hasValidParameter = !actionMethodInfo.hasParameter() || actionMethodInfo.hasParameterFactory();
				boolean ok = hasValidParameter && (mayChangeDomainOnject || wontChangeDomainObject);
				return ok;
			}
		};
	}

}