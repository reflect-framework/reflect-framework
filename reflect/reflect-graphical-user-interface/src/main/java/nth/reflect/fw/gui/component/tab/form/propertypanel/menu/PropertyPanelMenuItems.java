package nth.reflect.fw.gui.component.tab.form.propertypanel.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.item.ItemCollection;
import nth.reflect.fw.gui.item.method.PropertyMethodItem;
import nth.reflect.fw.gui.item.method.ServiceObjectItems;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterTypeFilter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ReturnTypeFilter;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * Menu {@link Item}s for a {@link PropertyPanelMenu}
 * 
 * @author nilsth
 *
 */
public class PropertyPanelMenuItems extends ItemCollection {

	private static final long serialVersionUID = -8380826298117283745L;

	public PropertyPanelMenuItems(FormTab formTab, ReadOnlyValueModel parameterModel, PropertyInfo propertyInfo) {
		super(create(formTab, parameterModel, propertyInfo));
	}

	private static Collection<? extends Item> create(FormTab formTab, ReadOnlyValueModel parameterModel,
			PropertyInfo propertyInfo) {
		List<Item> items = new ArrayList<Item>();

		// get info from form tab
		ActionMethodInfo methodInfoToExclude = formTab.getMethodInfo();
		Class<?> domainType = formTab.getDomainValueModel().getValueType();
		Class<?> parameterType = parameterModel.getValueType();
		Object methodOwner = formTab.getMethodOwner();

		// add property methods
		ReflectionProvider reflectionProvider = formTab.getUserInterfaceContainer().get(ReflectionProvider.class);
		// TODO does methodOwner needs to be a value model??? We now assume the
		// menu will be created when a field is selected.

		// TODO remove???? following code was replaced by
		// propertyInfo.getActionMethodInfos();

		// Predicate<ActionMethodInfo> filter = new
		// NoParameterOrParameterFactoryFilter()
		// .or(new ParameterTypeFilter(parameterType)).and(new
		// LinkedToPropertyFilter(propertyInfo));
		// DomainClassInfo classInfo =
		// reflectionProvider.getClassInfo(domainType);
		// List<ActionMethodInfo> actionMethodInfos =
		// classInfo.getActionMethodInfos(filter);

		List<ActionMethodInfo> actionMethodInfos = propertyInfo.getActionMethodInfos();

		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			PropertyMethodItem item = new PropertyMethodItem(formTab, propertyInfo, actionMethodInfo, parameterModel,
					false);
			// MethodItem item = new MethodItem(methodOwner,
			// methodInfo,parameterModel);
			items.add(item);
		}

		// @SuppressWarnings("rawtypes")
		// GraphicalUserinterfaceController graphicalUserinterfaceController =
		// formTab.getUserInterfaceContainer()
		// .get(GraphicalUserinterfaceController.class);
		// @SuppressWarnings("unchecked")
		// Tabs<Tab> tabs = graphicalUserinterfaceController.getTabs();
		// items.addAll(new PropertyMethodOwnerItems(tabs, parameterModel,
		// propertyInfo));

		// service object methods
		Predicate<ActionMethodInfo> filter = new ParameterTypeFilter(parameterType)
				.or(new ReturnTypeFilter(parameterType)).and(actionMethod -> !actionMethod.equals(methodInfoToExclude));
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		items.addAll(new ServiceObjectItems(userInterfaceContainer, methodOwner, parameterModel, filter));

		return items;

	}

}
