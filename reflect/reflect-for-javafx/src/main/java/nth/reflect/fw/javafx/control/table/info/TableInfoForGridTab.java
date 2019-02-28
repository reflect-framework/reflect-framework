package nth.reflect.fw.javafx.control.table.info;

import java.util.Collection;

import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.gui.component.tab.grid.GridTabMenuItems;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class TableInfoForGridTab extends TableInfo {

	private final GridTab gridTab;
	private final Class<?> itemType;

	public TableInfoForGridTab(GridTab gridTab) {
		super(gridTab.getUserInterfaceContainer());
		this.gridTab = gridTab;
		ActionMethodInfo actionMethodInfo = gridTab.getMethodInfo();
		this.itemType = actionMethodInfo.getReturnTypeInfo().getGenericType();

	}

	@Override
	public Object getValues() {
		try {
			Object methodOwner = gridTab.getMethodOwner();
			ActionMethodInfo actionMethodInfo = gridTab.getMethodInfo();
			Object methodParameterValue = gridTab.getMethodParameter();
			Object methodResult = actionMethodInfo.invoke(methodOwner, methodParameterValue);
			return methodResult;
		} catch (Exception e) {
			StringBuilder message = new StringBuilder(gridTab.getDisplayName());
			message.append(": ");
			message.append(getLanguageProvider().getText("Error getting table values."));
			throw new RuntimeException(message.toString(), e);
			// UserInterfaceController userInterfaceController =
			// gridTab.getUserInterfaceContainer()
			// .get(UserInterfaceController.class);
			// userInterfaceController.showErrorDialog(gridTab.getViewTitle(),
			// "Error getting table values.", e);
		}
	}

	@Override
	public Class<?> getValuesType() {
		return itemType;
	}

	@Override
	public Collection<Item> getRowMenuItems(Object selectedObject) {
		Collection<Item> items = new GridTabMenuItems(gridTab, selectedObject);
		return items;
	}
}
