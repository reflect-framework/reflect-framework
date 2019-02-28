package nth.reflect.fw.javafx.control.table.info.cell;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class CellValueFactoryForObjectProperties<S, T> implements CellValueFactory<S, T> {

	private final PropertyInfo propertyInfo;

	public CellValueFactoryForObjectProperties(PropertyInfo propertyInfo) {
		this.propertyInfo = propertyInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ObservableValue<T> call(CellDataFeatures<S, T> cellDataFeatures) {
		Object domainObj = cellDataFeatures.getValue();
		Object propertyValue = propertyInfo.getValue(domainObj);
		Object fromatedPropertyValue = propertyInfo.getFormatedValue(propertyValue);
		return new ReadOnlyObjectWrapper<T>((T) fromatedPropertyValue);
	}

}
