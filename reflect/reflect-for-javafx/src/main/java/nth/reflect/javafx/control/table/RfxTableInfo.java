package nth.reflect.javafx.control.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.item.method.MethodOwnerItem;

@SuppressWarnings("restriction")
public abstract class RfxTableInfo {

	public abstract Object getValues();

	public abstract Class<?> getValuesType();

	public abstract ReflectionProvider getReflectionProvider();

	public abstract LanguageProvider getLanguageProvider();

	public abstract List<MethodOwnerItem> getRowMenuItems(Object selectedObject);

	public List<TableColumn<Object, ?>> getTableColumns() {
		Class<?> itemType = getValuesType();
		if (TypeUtil.isJavaType(itemType) || TypeUtil.isEnum(itemType)) {
			return new ArrayList<>();
		} else {
			return createColumnsForObject();
		}
	}

	private List<TableColumn<Object, ?>> createColumnsForObject() {
		Class<?> itemType = getValuesType();
		ReflectionProvider reflectionProvider = getReflectionProvider();
		List<TableColumn<Object, ?>> tableColumns = new ArrayList<>();
		ClassInfo classInfo = reflectionProvider.getClassInfo(itemType);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSortedAnsVisibleInTable();
		for (PropertyInfo propertyInfo : propertyInfos) {
			TableColumn<Object, ?> tableColumn = new TableColumn<Object, Object>(propertyInfo.getDisplayName());
			tableColumn.setMinWidth(100);
			tableColumn
					.setCellValueFactory(new PropertyValueFactory<>(propertyInfo.getSimpleName()));
			tableColumns.add(tableColumn);
		}
		return tableColumns;
	}

	@SuppressWarnings("rawtypes")
	public ObservableList<Object> getObservableList() {
		Object values = getValues();
		if (values == null) {
			List<Object> emptyList = new ArrayList<>();
			return new ObservableListWrapper<Object>(emptyList);
		} else if (values instanceof List) {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) values;
			return new ObservableListWrapper<Object>(list);
		} else if (values instanceof Collection) {
			@SuppressWarnings("unchecked")
			List<Object> list = new ArrayList<>((Collection) values);
			return new ObservableListWrapper<Object>(list);
		} else if (values.getClass().isArray()) {
			List<Object> list = Arrays.asList(values);
			return new ObservableListWrapper<Object>(list);
		} else {
			String message = getLanguageProvider()
					.getText("Error getting table values. Unsupported return type.");
			throw new RuntimeException(message);
		}

	}

}
