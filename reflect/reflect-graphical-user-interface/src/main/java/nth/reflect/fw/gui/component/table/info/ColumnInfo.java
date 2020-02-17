package nth.reflect.fw.gui.component.table.info;

import java.util.Optional;

import nth.reflect.fw.gui.component.table.info.cell.CellStringConverter;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * Holds column information for {@link TableInfo}
 * 
 * @author nilsth
 *
 */
public class ColumnInfo {

	private final Optional<PropertyInfo> propertyInfo;
	private final CellStringConverter cellStringConverter;

	public ColumnInfo(PropertyInfo propertyInfo, CellStringConverter cellStringConverter) {
		if (propertyInfo == null) {
			this.propertyInfo = Optional.empty();
		} else {
			this.propertyInfo = Optional.of(propertyInfo);
		}
		this.cellStringConverter = cellStringConverter;
	}

	public ColumnInfo(CellStringConverter cellValueFactory) {
		this.propertyInfo = Optional.empty();
		this.cellStringConverter = cellValueFactory;
	}

	public Optional<PropertyInfo> getPropertyInfo() {
		return propertyInfo;
	}

	public String getStringValue(Object value) {
		String stringValue = cellStringConverter.getValue(value);
		return stringValue;
	}

}
