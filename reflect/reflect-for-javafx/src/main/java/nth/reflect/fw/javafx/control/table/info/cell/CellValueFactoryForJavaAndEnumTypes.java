package nth.reflect.fw.javafx.control.table.info.cell;

import java.text.Format;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.JavaFormatFactory;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class CellValueFactoryForJavaAndEnumTypes<S, T> implements CellValueFactory<S, T> {

	private final Format format;

	public CellValueFactoryForJavaAndEnumTypes(ReflectApplication reflectApplication, LanguageProvider languageProvider,
			Class<?> type) {
		JavaFormatFactory formatFactory = new JavaFormatFactory(languageProvider);
		TypeInfo typeInfo = new TypeInfo(reflectApplication, type, type);
		format = formatFactory.create(typeInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ObservableValue<T> call(CellDataFeatures<S, T> cellDataFeatures) {
		S value = cellDataFeatures.getValue();
		Object formatedValue = format.format(value);
		return new ReadOnlyObjectWrapper<T>((T) formatedValue);
	}
}
