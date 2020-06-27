package nth.reflect.fw.gui.component.tab.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.GraphicalUserInterfaceController;
import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.item.ItemCollection;
import nth.reflect.fw.gui.item.method.PropertyMethodOwnerItems;
import nth.reflect.fw.gui.item.method.ServiceObjectItems;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterModelFilter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterTypeFilter;

/**
 * Menu {@link Item}s for a {@link TableTabMenu}
 */
public class TableTabMenuItems extends ItemCollection {

	private static final long serialVersionUID = 6211256666484535772L;

	public TableTabMenuItems(TableTab tableTab) {
		super(create(tableTab));
	}

	public TableTabMenuItems(TableTab tableTab, ReadOnlyValueModel actionMethodParameterModel) {
		super(create(tableTab, actionMethodParameterModel));
	}

	@SuppressWarnings("unchecked")
	private static Collection<? extends Item> create(TableTab tableTab) {
		List<Item> items = new ArrayList<Item>();

		// get info from table tab
		ActionMethodInfo methodInfoToExclude = tableTab.getMethodInfo();
		ReadOnlyValueModel parameterModel = tableTab.getSelectedRowModel();
		Object serviceObject = tableTab.getMethodOwner();

		@SuppressWarnings("rawtypes")
		GraphicalUserInterfaceController userinterfaceController = tableTab.getUserInterfaceContainer()
				.get(GraphicalUserInterfaceController.class);
		Tabs<Tab> tabs = userinterfaceController.getTabs();
		items.addAll(new PropertyMethodOwnerItems(tabs, parameterModel, null));

		// create filter for service object items
		Class<?> domainType = parameterModel.getValueType();
		Predicate<ActionMethodInfo> filter = new ParameterTypeFilter(domainType)
				.and(actionMethod -> !actionMethod.equals(methodInfoToExclude));
		UserInterfaceContainer userInterfaceContainer = tableTab.getUserInterfaceContainer();
		items.addAll(new ServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel, filter));

		return items;
	}

	private static Collection<? extends Item> create(TableTab tableTab, ReadOnlyValueModel actionMethodParameterModel) {
		List<Item> items = new ArrayList<>();

		// get info from table tab
		ReadOnlyValueModel parameterModel = tableTab.getSelectedRowModel();
		Object serviceObject = tableTab.getMethodOwner();
		//
		// @SuppressWarnings("rawtypes")
		// GraphicalUserinterfaceController userinterfaceController =
		// gridTab.getUserInterfaceContainer()
		// .get(GraphicalUserinterfaceController.class);
		// @SuppressWarnings("rawtypes")
		// Tabs tab = userinterfaceController.getTabs();
		// items.addAll(new PropertyMethodOwnerItems(tab, parameterModel,
		// null));

		// create filter for service object items

		ParameterModelFilter filter = new ParameterModelFilter(actionMethodParameterModel);
		UserInterfaceContainer userInterfaceContainer = tableTab.getUserInterfaceContainer();
		items.addAll(new ServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel, filter));

		return items;

	}
}
