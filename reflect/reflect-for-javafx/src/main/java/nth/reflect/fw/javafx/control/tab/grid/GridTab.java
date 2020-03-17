package nth.reflect.fw.javafx.control.tab.grid;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.javafx.control.table.Table;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class GridTab extends Tab implements nth.reflect.fw.gui.component.tab.grid.GridTab {

	private final LanguageProvider languageProvider;
	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private ReadOnlyValueModel selectedRowsModel;
	private final UserInterfaceContainer userInterfaceContainer;
	private final Table grid;

	public GridTab(UserInterfaceContainer userInterfaceContainer, Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;

		grid = new Table(this);
		setCenter(grid);

	}

	@Override
	public String getDisplayName() {
		return actionMethodInfo.createTitle(methodParameterValue).translate(languageProvider);
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.createTitle(methodParameterValue).translate(languageProvider);
	}

	@Override
	public void onRefresh() {
		grid.updateData();
	}

	@Override
	public ReadOnlyValueModel getSelectedRowModel() {
		if (selectedRowsModel == null) {
			selectedRowsModel = new ReadOnlyValueModel() {

				@Override
				public Object getValue() {
					return grid.getSelectionModel().getSelectedItem();
				}

				@Override
				public Class<?> getValueType() {
					return actionMethodInfo.getReturnTypeInfo().getType();
				}

				@Override
				public boolean canGetValue() {
					return !grid.getSelectionModel().isEmpty();
				}

			};
		}
		return selectedRowsModel;
	}

	@Override
	public ActionMethodInfo getMethodInfo() {
		return actionMethodInfo;
	}

	@Override
	public Object getMethodOwner() {
		return methodOwner;
	}

	@Override
	public Object getMethodParameter() {
		return methodParameterValue;
	}

	@Override
	public UserInterfaceContainer getUserInterfaceContainer() {
		return userInterfaceContainer;
	}

}
