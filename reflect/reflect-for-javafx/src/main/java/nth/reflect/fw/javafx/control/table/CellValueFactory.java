package nth.reflect.fw.javafx.control.table;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

public class CellValueFactory<S, T> implements
		javafx.util.Callback<javafx.scene.control.TableColumn.CellDataFeatures<S, T>, javafx.beans.value.ObservableValue<T>> {

	private final ClassInfo classInfo;

	public CellValueFactory(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ObservableValue<T> call(CellDataFeatures<S, T> cellDataFeatures) {
		Object obj = cellDataFeatures.getValue();
		Object objTitle = classInfo.getTitle(obj);
		return new ReadOnlyObjectWrapper<T>((T) objTitle);
	}

	// private Callback<CellDataFeatures<Object, String>,
	// ObservableValue<String>> createCellValueFactoryForJavaTypeOrEnum(
	// ReflectApplication reflectApplication, LanguageProvider languageProvider,
	// Class<?> type) {
	// JavaFormatFactory formatFactory = new
	// JavaFormatFactory(languageProvider);
	// TypeInfo typeInfo = new TypeInfo(reflectApplication, type, type);
	// Format format = formatFactory.create(typeInfo);
	// return new Callback<CellDataFeatures<Object, String>,
	// ObservableValue<String>>() {
	//
	// @Override
	// public ObservableValue<String> call(CellDataFeatures<Object, String>
	// param) {
	// String value = format.format(param);
	// return new ReadOnlyObjectWrapper<String>(value);
	// }
	// };
	// }
}
