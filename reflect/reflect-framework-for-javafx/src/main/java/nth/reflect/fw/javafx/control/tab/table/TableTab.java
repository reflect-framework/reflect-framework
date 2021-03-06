package nth.reflect.fw.javafx.control.tab.table;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.javafx.control.table.Table;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class TableTab extends Tab implements nth.reflect.fw.gui.component.tab.table.TableTab {

	private final LanguageProvider languageProvider;
	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private ReadOnlyValueModel selectedRowsModel;
	private final UserInterfaceContainer userInterfaceContainer;
	private final Table table;

	public TableTab(UserInterfaceContainer userInterfaceContainer, Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;

		table = new Table(this);
		setCenter(table);

	}

	@Override
	public String getDisplayName() {
		return actionMethodInfo.getTitle(methodParameterValue).getTranslation(languageProvider);
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.getTitle(methodParameterValue).getTranslation(languageProvider);
	}

	@Override
	public void onRefresh() {
		table.updateData();
	}

	@Override
	public ReadOnlyValueModel getSelectedRowModel() {
		if (selectedRowsModel == null) {
			selectedRowsModel = new ReadOnlyValueModel() {

				@Override
				public Object getValue() {
					return table.getSelectionModel().getSelectedItem();
				}

				@Override
				public Class<?> getValueType() {
					return actionMethodInfo.getReturnTypeInfo().getType();
				}

				@Override
				public boolean canGetValue() {
					return !table.getSelectionModel().isEmpty();
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
