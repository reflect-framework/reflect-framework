package nth.reflect.fw.javafx.control.table;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import nth.reflect.fw.gui.component.table.info.column.ColumnInfo;

public class CellValueFactory implements Callback<CellDataFeatures<Object, String>, ObservableValue<String>> {

	private ColumnInfo columnInfo;

	public CellValueFactory(ColumnInfo columnInfo) {
		this.columnInfo = columnInfo;
	}

	@Override
	public ObservableValue<String> call(CellDataFeatures<Object, String> cellDataFeatures) {
		Object rowValue = cellDataFeatures.getValue();
		String cellValue = columnInfo.toString(rowValue);
		return new ReadOnlyObjectWrapper<String>(cellValue);
	}

}
