package nth.reflect.fw.gui.component.table.info;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import com.vaadin.flow.data.provider.DataProvider;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.menu.PropertyPanelMenuItems;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.component.table.info.column.ColumnInfo;
import nth.reflect.fw.gui.component.table.info.column.ColumnInfoFactory;
import nth.reflect.fw.gui.component.table.info.dataprovider.DataProviderFactory;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class TableInfoForFormTabProperty implements TableInfo {

	private final List<ColumnInfo> columnInfos;
	private final DataProvider dataProvider;
	private final FormTab formTab;
	private final PropertyInfo propertyInfo;
	private final TypeInfo typeInfo;

	public TableInfoForFormTabProperty(FormTab formTab, PropertyValueModel propertyValueModel) {
		this.propertyInfo = propertyValueModel.getPropertyInfo();
		this.formTab = formTab;
		Method method = propertyInfo.getGetterMethod();
		TypeInfo returnTypeInfo = propertyInfo.getTypeInfo();
		this.typeInfo = TypeInfoFactory.createfor(method, returnTypeInfo);
		columnInfos = ColumnInfoFactory.createFor(formTab, propertyValueModel);
		dataProvider = DataProviderFactory.createFor(propertyValueModel);
	}

	@Override
	public TypeInfo getTypeInfo() {
		return typeInfo;
	}

	@Override
	public List<ColumnInfo> getColumnInfos() {
		return columnInfos;
	}

	@Override
	public DataProvider getDataProvider() {
		return dataProvider;
	}

	@Override
	public Collection<Item> getRowMenuItems(ReadOnlyValueModel actionMethodParameterModel) {
		PropertyPanelMenuItems items = new PropertyPanelMenuItems(formTab, actionMethodParameterModel, propertyInfo);
		return items;
	}

}
