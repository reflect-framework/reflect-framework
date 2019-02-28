package nth.reflect.fw.javafx.control.table.info.cell;

import nth.reflect.fw.javafx.control.table.Table;

/**
 * Interface for CallValueFactories for in a {@link Table}
 * 
 * @author nilsth
 *
 * @param <S>
 *            Type of values being displayed in the {@link Table}
 * @param <T>
 *            Type of value in the {@link Table} cell
 */
public interface CellValueFactory<S, T> extends
		javafx.util.Callback<javafx.scene.control.TableColumn.CellDataFeatures<S, T>, javafx.beans.value.ObservableValue<T>> {
}
