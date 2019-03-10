package nth.reflect.fw.gui.component.tab.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.item.method.PropertyMethodOwnerItems;
import nth.reflect.fw.gui.item.method.ServiceObjectItems;
import nth.reflect.fw.gui.util.collection.UnmodifiableCollection;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterModelFilter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterTypeFilter;

/**
 * Menu {@link Item}s for a {@link GridTabMenu}
 */
public class GridTabMenuItems extends UnmodifiableCollection<Item> {

	private static final long serialVersionUID = 6211256666484535772L;

	public GridTabMenuItems(GridTab gridTab) {
		super(create(gridTab));
	}

	public GridTabMenuItems(GridTab gridTab, ReadOnlyValueModel actionMethodParameterModel) {
		super(create(gridTab, actionMethodParameterModel));
	}

	@SuppressWarnings("unchecked")
	private static Collection<? extends Item> create(GridTab gridTab) {
		List<Item> items = new ArrayList<Item>();

		// get info from table tab
		ActionMethodInfo methodInfoToExclude = gridTab.getMethodInfo();
		ReadOnlyValueModel parameterModel = gridTab.getSelectedRowModel();
		Object serviceObject = gridTab.getMethodOwner();

		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userinterfaceController = gridTab.getUserInterfaceContainer()
				.get(GraphicalUserinterfaceController.class);
		Tabs<Tab> tabs = userinterfaceController.getTabs();
		items.addAll(new PropertyMethodOwnerItems(tabs, parameterModel, null));

		// create filter for service object items
		Class<?> domainType = parameterModel.getValueType();
		Predicate<ActionMethodInfo> filter = new ParameterTypeFilter(domainType)
				.and(actionMethod -> !actionMethod.equals(methodInfoToExclude));
		UserInterfaceContainer userInterfaceContainer = gridTab.getUserInterfaceContainer();
		items.addAll(new ServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel, filter));

		return items;
	}

	private static Collection<? extends Item> create(GridTab gridTab, ReadOnlyValueModel actionMethodParameterModel) {
		List<Item> items = new ArrayList<>();

		// get info from table tab
		ReadOnlyValueModel parameterModel = gridTab.getSelectedRowModel();
		Object serviceObject = gridTab.getMethodOwner();
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
		UserInterfaceContainer userInterfaceContainer = gridTab.getUserInterfaceContainer();
		items.addAll(new ServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel, filter));

		return items;

	}
}
