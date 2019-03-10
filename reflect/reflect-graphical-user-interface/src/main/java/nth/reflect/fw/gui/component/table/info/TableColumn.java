package nth.reflect.fw.gui.component.table.info;

import java.util.Optional;

import nth.reflect.fw.gui.component.table.info.cell.CellValueFactory;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * Holds column information for {@link TableInfo}
 * 
 * @author nilsth
 *
 */
public class TableColumn {

	private final Optional<PropertyInfo> propertyInfo;
	private final CellValueFactory cellValueFactory;

	public TableColumn(PropertyInfo propertyInfo, CellValueFactory cellValueFactory) {
		if (propertyInfo == null) {
			this.propertyInfo = Optional.empty();
		} else {
			this.propertyInfo = Optional.of(propertyInfo);
		}
		this.cellValueFactory = cellValueFactory;
	}

	public TableColumn(CellValueFactory cellValueFactory) {
		this.propertyInfo = Optional.empty();
		this.cellValueFactory = cellValueFactory;
	}

	public Optional<PropertyInfo> getPropertyInfo() {
		return propertyInfo;
	}

	public CellValueFactory getCellValueFactory() {
		return cellValueFactory;
	}

}
