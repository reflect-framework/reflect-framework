package nth.reflect.fw.ui.item.method.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.ViewController;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterTypeFilter;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.view.TableView;

public class TableRowMenuItems extends UnmodifiableCollection<Item> {

	private static final long serialVersionUID = 6211256666484535772L;

	public TableRowMenuItems(TableView tableView) {
		super(createTableMenuItems(tableView));
	}

	public TableRowMenuItems(TableView tableView, Object domainObject) {
		super(createTableMenuItems(tableView, domainObject));
	}

	@SuppressWarnings("unchecked")
	private static Collection<? extends Item> createTableMenuItems(TableView tableView) {
		List<Item> items = new ArrayList<Item>();

		// get info from table view
		ActionMethodInfo methodInfoToExclude = tableView.getMethodInfo();
		ReadOnlyValueModel parameterModel = tableView.getSelectedRowModel();
		Object serviceObject = tableView.getMethodOwner();

		@SuppressWarnings("rawtypes")
		ViewController viewController = tableView.getUserInterfaceContainer()
				.get(GraphicalUserinterfaceController.class).getViewController();
		items.addAll(new PropertyMethodOwnerItems(viewController, parameterModel, null));

		// create filter for service object items
		Class<?> domainType = parameterModel.getValueType();
		Predicate<ActionMethodInfo> filter = new ParameterTypeFilter(domainType)
				.and(actionMethod -> !actionMethod.equals(methodInfoToExclude));
		UserInterfaceContainer userInterfaceContainer = tableView.getUserInterfaceContainer();
		items.addAll(new ServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel, filter));

		return items;
	}

	@SuppressWarnings("unchecked")
	private static Collection<? extends Item> createTableMenuItems(TableView tableView, Object domainObject) {
		List<Item> items = new ArrayList<>();

		// get info from table view
		// ActionMethodInfo methodInfoToExclude = tableView.getMethodInfo();
		ReadOnlyValueModel parameterModel = tableView.getSelectedRowModel();
		Object serviceObject = tableView.getMethodOwner();

		@SuppressWarnings("rawtypes")
		ViewController viewController = tableView.getUserInterfaceContainer()
				.get(GraphicalUserinterfaceController.class).getViewController();
		items.addAll(new PropertyMethodOwnerItems(viewController, parameterModel, null));

		// create filter for service object items
		Class<?> domainType = domainObject.getClass();
		Predicate<ActionMethodInfo> filter = new ParameterTypeFilter(domainType);
		UserInterfaceContainer userInterfaceContainer = tableView.getUserInterfaceContainer();
		items.addAll(new ServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel, filter));

		return items;

	}
}
