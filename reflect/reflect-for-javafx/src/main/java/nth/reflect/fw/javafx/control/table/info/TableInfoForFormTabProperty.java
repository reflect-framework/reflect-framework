package nth.reflect.fw.javafx.control.table.info;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.menu.PropertyPanelMenuItems;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class TableInfoForFormTabProperty extends TableInfo {

	private final PropertyValueModel propertyValueModel;
	private final FormTab formTab;

	public TableInfoForFormTabProperty(FormTab formTab, PropertyValueModel propertyValueModel) {
		super(formTab.getUserInterfaceContainer());
		this.formTab = formTab;
		this.propertyValueModel = propertyValueModel;
	}

	@Override
	public Object getValues() {
		return propertyValueModel.getValue();
	}

	@Override
	public Class<?> getValuesType() {
		return propertyValueModel.getValueType();
	}

	@Override
	public PropertyPanelMenuItems getRowMenuItems(Object selectedObject) {
		PropertyPanelMenuItems items = new PropertyPanelMenuItems(formTab, propertyValueModel,
				propertyValueModel.getPropertyInfo());
		return items;
	}

}
