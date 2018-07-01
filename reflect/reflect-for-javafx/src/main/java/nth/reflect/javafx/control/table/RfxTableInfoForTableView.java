package nth.reflect.javafx.control.table;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.view.TableView;

public class RfxTableInfoForTableView extends RfxTableInfo{

	private TableView tableView;
	private Class<?> itemType;
	private ReflectionProvider reflectionProvider;
	private LanguageProvider languageProvider;

	public RfxTableInfoForTableView(TableView tableView) {
		this.tableView = tableView;
		UserInterfaceContainer userInterfaceContainer = tableView.getUserInterfaceContainer();
		this.reflectionProvider = userInterfaceContainer
				.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		ActionMethodInfo actionMethodInfo = tableView.getMethodInfo();
		this.itemType = actionMethodInfo.getGenericReturnType();

	}
	
	public ObservableList<Object> getItems() {
		try {
			Object methodOwner = tableView.getMethodOwner();
			ActionMethodInfo actionMethodInfo = tableView.getMethodInfo();
			Object methodParameterValue = tableView.getMethodParameter();
			Object result = actionMethodInfo.invoke(methodOwner, methodParameterValue);
			List<Object> list = (List<Object>) result;
			// TODO create a createObservableList for all types, and that can be
			// updated when needed
			return new ObservableListWrapper<Object>(list);
		} catch (Exception e) {
			UserInterfaceController userInterfaceController = tableView.getUserInterfaceContainer()
					.get(UserInterfaceController.class);
			userInterfaceController.showErrorDialog(tableView.getViewTitle(),
					"Error getting table values.", e);
			return new ObservableListWrapper<Object>(new ArrayList<>());
		}
	}


	@Override
	public Class<?> getItemType() {
		return itemType;
	}

	@Override
	public ReflectionProvider getReflectionProvider() {
		return reflectionProvider;
	}

	@Override
	public LanguageProvider getLanguageProvider() {
		return languageProvider;
	}

	@Override
	public List<MethodOwnerItem> getRowMenuItems(Object selectedObject) {
		List<MethodOwnerItem> items = ItemFactory.createTableViewRowMenuItems(tableView,
				selectedObject);
		return items;
	}
}
