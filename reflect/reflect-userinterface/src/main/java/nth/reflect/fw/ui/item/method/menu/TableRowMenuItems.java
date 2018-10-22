package nth.reflect.fw.ui.item.method.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterTypeFilter;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.tab.Tab;
import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.table.TableTab;

public class TableRowMenuItems extends UnmodifiableCollection<Item> {

	private static final long serialVersionUID = 6211256666484535772L;

	public TableRowMenuItems(TableTab tableTab) {
		super(createTableMenuItems(tableTab));
	}

	public TableRowMenuItems(TableTab tableTab, Object domainObject) {
		super(createTableMenuItems(tableTab, domainObject));
	}

	@SuppressWarnings("unchecked")
	private static Collection<? extends Item> createTableMenuItems(TableTab tableTab) {
		List<Item> items = new ArrayList<Item>();

		// get info from table tab
		ActionMethodInfo methodInfoToExclude = tableTab.getMethodInfo();
		ReadOnlyValueModel parameterModel = tableTab.getSelectedRowModel();
		Object serviceObject = tableTab.getMethodOwner();

		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userinterfaceController = tableTab.getUserInterfaceContainer()
				.get(GraphicalUserinterfaceController.class);
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

	@SuppressWarnings("unchecked")
	private static Collection<? extends Item> createTableMenuItems(TableTab tableTab, Object domainObject) {
		List<Item> items = new ArrayList<>();

		// get info from table tab
		ReadOnlyValueModel parameterModel = tableTab.getSelectedRowModel();
		Object serviceObject = tableTab.getMethodOwner();

		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userinterfaceController = tableTab.getUserInterfaceContainer()
				.get(GraphicalUserinterfaceController.class);
		@SuppressWarnings("rawtypes")
		Tabs tab = userinterfaceController.getTabs();
		items.addAll(new PropertyMethodOwnerItems(tab, parameterModel, null));

		// create filter for service object items
		Class<?> domainType = domainObject.getClass();
		Predicate<ActionMethodInfo> filter = new ParameterTypeFilter(domainType);
		UserInterfaceContainer userInterfaceContainer = tableTab.getUserInterfaceContainer();
		items.addAll(new ServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel, filter));

		return items;

	}
}
