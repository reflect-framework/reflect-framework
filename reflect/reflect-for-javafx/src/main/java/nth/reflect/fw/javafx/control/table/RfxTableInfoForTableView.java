package nth.reflect.fw.javafx.control.table;

import java.util.List;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.item.ItemFactory;
import nth.reflect.fw.ui.view.TableView;

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

	@Override
	public Object getValues() {
		try {
			Object methodOwner = tableView.getMethodOwner();
			ActionMethodInfo actionMethodInfo = tableView.getMethodInfo();
			Object methodParameterValue = tableView.getMethodParameter();
			Object methodResult = actionMethodInfo.invoke(methodOwner, methodParameterValue);
			return methodResult;
		} catch (Exception e) {
			StringBuilder message = new StringBuilder(tableView.getViewTitle());
			message.append(": ");
			message.append(languageProvider.getText("Error getting table values."));
			throw new RuntimeException(message.toString(),e);
//			UserInterfaceController userInterfaceController = tableView.getUserInterfaceContainer()
//					.get(UserInterfaceController.class);
//			userInterfaceController.showErrorDialog(tableView.getViewTitle(),
//					"Error getting table values.", e);
		}
	}
	
	@Override
	public Class<?> getValuesType() {
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
	public List<Item> getRowMenuItems(Object selectedObject) {
		List<Item> items = ItemFactory.createTableViewRowMenuItems(tableView,
				selectedObject);
		return items;
	}
}
