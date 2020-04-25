package nth.reflect.fw.gui.component.table.info;

import java.util.Collection;
import java.util.List;

import com.vaadin.flow.data.provider.DataProvider;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.table.TableTabMenuItems;
import nth.reflect.fw.gui.component.tab.table.TableTab;
import nth.reflect.fw.gui.component.table.info.column.ColumnInfo;
import nth.reflect.fw.gui.component.table.info.column.ColumnInfoFactory;
import nth.reflect.fw.gui.component.table.info.dataprovider.DataProviderFactory;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class TableInfoForTableTab implements TableInfo {

	private final List<ColumnInfo> columnInfos;
	private final DataProvider dataProvider;
	private final TableTab tableTab;
	private final TypeInfo typeInfo;

	public TableInfoForTableTab(TableTab tableTab) {
		this.tableTab = tableTab;
		typeInfo = TypeInfoFactory.createfor(tableTab);
		columnInfos = ColumnInfoFactory.createFor(tableTab);
		dataProvider = DataProviderFactory.createFor(tableTab);
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
		return new TableTabMenuItems(tableTab, actionMethodParameterModel);
	}

}
