package nth.reflect.fw.javafx.control.table;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class CellValueFactory implements Callback<CellDataFeatures<Object, String>, ObservableValue<String>> {

	private final nth.reflect.fw.gui.component.table.info.cell.CellValueFactory cellValueFactory;

	public CellValueFactory(nth.reflect.fw.gui.component.table.info.TableColumn tableColumn) {
		cellValueFactory = tableColumn.getCellValueFactory();
	}

	@Override
	public ObservableValue<String> call(CellDataFeatures<Object, String> cellDataFeatures) {
		Object obj = cellDataFeatures.getValue();
		String text = cellValueFactory.getValue(obj);
		return new ReadOnlyObjectWrapper<String>(text);
	}

}
