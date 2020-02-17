package nth.reflect.fw.javafx.control.table;

import javafx.scene.control.TableColumn;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class TableColumnWraper extends TableColumn<Object, String> {

	public TableColumnWraper(nth.reflect.fw.gui.component.table.info.ColumnInfo tableColumn) {
		setMinWidth(100);
		setHeaderText(tableColumn);
		setCellValueFactory(new CellValueFactory(tableColumn));
	}

	private void setHeaderText(nth.reflect.fw.gui.component.table.info.ColumnInfo tableColumn) {
		if (tableColumn.getPropertyInfo().isPresent()) {
			PropertyInfo propertyInfo = tableColumn.getPropertyInfo().get();
			String displayName = propertyInfo.getDisplayName();
			setText(displayName);
		}
	}

}
