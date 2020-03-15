package nth.reflect.fw.javafx.control.table;

import javafx.scene.control.TableColumn;
import nth.reflect.fw.gui.component.table.info.column.ColumnInfo;

public class TableColumnWraper extends TableColumn<Object, String> {

	public TableColumnWraper(ColumnInfo columnInfo) {
		setMinWidth(100);
		setText(columnInfo.getName());
		setCellValueFactory(new CellValueFactory(columnInfo));
	}

}
