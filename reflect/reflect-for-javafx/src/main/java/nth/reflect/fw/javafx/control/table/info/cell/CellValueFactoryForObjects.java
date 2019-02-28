package nth.reflect.fw.javafx.control.table.info.cell;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

public class CellValueFactoryForObjects<S, T> implements CellValueFactory<S, T> {

	private final ClassInfo classInfo;

	public CellValueFactoryForObjects(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ObservableValue<T> call(CellDataFeatures<S, T> cellDataFeatures) {
		Object obj = cellDataFeatures.getValue();
		Object objTitle = classInfo.getTitle(obj);
		return new ReadOnlyObjectWrapper<T>((T) objTitle);
	}

}
