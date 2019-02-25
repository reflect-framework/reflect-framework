package nth.reflect.fw.javafx.control.table;

import java.util.Collection;

import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.gui.component.tab.grid.GridTabMenuItems;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class TableInfoForTableTab extends TableInfo {

	private final GridTab gridTab;
	private final Class<?> itemType;
	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;

	public TableInfoForTableTab(GridTab gridTab) {
		this.gridTab = gridTab;
		UserInterfaceContainer userInterfaceContainer = gridTab.getUserInterfaceContainer();
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
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
			message.append(languageProvider.getText("Error getting table values."));
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
	public ReflectionProvider getReflectionProvider() {
		return reflectionProvider;
	}

	@Override
	public LanguageProvider getLanguageProvider() {
		return languageProvider;
	}

	@Override
	public Collection<Item> getRowMenuItems(Object selectedObject) {
		Collection<Item> items = new GridTabMenuItems(gridTab, selectedObject);
		return items;
	}
}
