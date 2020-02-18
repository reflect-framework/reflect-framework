package nth.reflect.fw.javafx.control.table;

import javafx.scene.control.TableColumn;

public class TableColumnWraper extends TableColumn<Object, String> {

	public TableColumnWraper(nth.reflect.fw.gui.component.table.info.ColumnInfo columnInfo) {
		setMinWidth(100);
		setText(columnInfo.getHeaderText());
		setCellValueFactory(new CellValueFactory(columnInfo));
	}

}
