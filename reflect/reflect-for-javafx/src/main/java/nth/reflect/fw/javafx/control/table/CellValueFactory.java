package nth.reflect.fw.javafx.control.table;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import nth.reflect.fw.gui.component.table.info.ColumnInfo;

public class CellValueFactory implements Callback<CellDataFeatures<Object, String>, ObservableValue<String>> {

	private ColumnInfo tableInfo;

	public CellValueFactory(nth.reflect.fw.gui.component.table.info.ColumnInfo tableInfo) {
		this.tableInfo = tableInfo;
	}

	@Override
	public ObservableValue<String> call(CellDataFeatures<Object, String> cellDataFeatures) {
		Object rowValue = cellDataFeatures.getValue();
		String cellValue = tableInfo.getStringValue(rowValue);
		return new ReadOnlyObjectWrapper<String>(cellValue);
	}

}
