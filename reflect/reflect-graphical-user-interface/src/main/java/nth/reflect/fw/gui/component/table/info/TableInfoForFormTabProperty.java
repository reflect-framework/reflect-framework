package nth.reflect.fw.gui.component.table.info;

import java.util.Collection;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.menu.PropertyPanelMenuItems;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;

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
	public Collection<Item> getRowMenuItems(ReadOnlyValueModel actionMethodParameterModel) {
		PropertyPanelMenuItems items = new PropertyPanelMenuItems(formTab, actionMethodParameterModel,
				propertyValueModel.getPropertyInfo());
		return items;
	}

}
