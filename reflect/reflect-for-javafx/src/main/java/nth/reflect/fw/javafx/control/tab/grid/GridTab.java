package nth.reflect.fw.javafx.control.tab.grid;

import nth.reflect.fw.generic.util.TitleUtil;
import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.javafx.control.table.Table;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class GridTab extends Tab implements nth.reflect.fw.gui.component.tab.grid.GridTab {

	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private ReadOnlyValueModel selectedRowsModel;
	private final UserInterfaceContainer userInterfaceContainer;
	private final ReflectionProvider reflectionProvider;
	private final Table grid;

	public GridTab(UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;

		reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);

		grid = new Table(this);
		setCenter(grid);

	}

	@Override
	public String getDisplayName() {
		return TitleUtil.createTitle(reflectionProvider, actionMethodInfo, methodParameterValue);
	}

	@Override
	public String getDescription() {
		return TitleUtil.createTitle(reflectionProvider, actionMethodInfo, methodParameterValue);
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
