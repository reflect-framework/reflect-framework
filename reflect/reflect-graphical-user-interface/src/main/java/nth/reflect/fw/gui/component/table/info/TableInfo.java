package nth.reflect.fw.gui.component.table.info;

import java.util.Collection;
import java.util.List;

import com.vaadin.flow.data.provider.DataProvider;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.gui.component.table.info.column.ColumnInfo;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public interface TableInfo {

	public TypeInfo getTypeInfo();

	public List<ColumnInfo> getColumnInfos();

	/**
	 * @return something that provides the data (content of the table rows). We are
	 *         using the Vaadin {@link DataProvider} because.... well, why reinvent
	 *         the wheel? Better to reuse something good.
	 */
	public DataProvider getDataProvider();

	/**
	 * 
	 * @param actionMethodParameterValue the selected row value,
	 *                                   {@link ReadOnlyValueModel#canGetValue()}
	 *                                   must return false when no row is selected.
	 *                                   Important note: this method must be
	 *                                   recalled to create new row menu items every
	 *                                   time the actionMethodParameter changes!
	 * @return menu items to be displayed in a pop up menu on a {@link GridTab} or
	 *         {@link ManyToOneOrManyField}
	 */
	public Collection<Item> getRowMenuItems(ReadOnlyValueModel actionMethodParameterValue);

}
