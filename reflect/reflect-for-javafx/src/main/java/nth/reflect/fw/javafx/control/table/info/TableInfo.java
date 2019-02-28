package nth.reflect.fw.javafx.control.table.info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.javafx.control.table.info.cell.CellValueFactoryForJavaAndEnumTypes;
import nth.reflect.fw.javafx.control.table.info.cell.CellValueFactoryForObjectProperties;
import nth.reflect.fw.javafx.control.table.info.cell.CellValueFactoryForObjects;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

@SuppressWarnings("restriction")
public abstract class TableInfo {

	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;
	private final ReflectApplication reflectApplication;

	public TableInfo(UserInterfaceContainer userInterfaceContainer) {
		this.reflectApplication = userInterfaceContainer.get(ReflectApplication.class);
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
	}

	public abstract Object getValues();

	public abstract Class<?> getValuesType();

	private ReflectionProvider getReflectionProvider() {
		return reflectionProvider;
	}

	public LanguageProvider getLanguageProvider() {
		return languageProvider;
	}

	private ReflectApplication getReflectApplication() {
		return reflectApplication;
	}

	public abstract Collection<Item> getRowMenuItems(Object selectedObject);

	public List<TableColumn<Object, ?>> getTableColumns() {
		Class<?> type = getValuesType();
		if (TypeInfo.isJavaVariableType(type) || type.isEnum()) {
			return createColumnsForJavaAndEnumTypes();
		} else {
			return createColumnForObject();
		}
		// TODO
		// return createColumnsForObject();
	}

	private List<TableColumn<Object, ?>> createColumnsForJavaAndEnumTypes() {
		Class<?> type = getValuesType();
		ReflectApplication reflectApplication = getReflectApplication();
		LanguageProvider languageProvider = getLanguageProvider();
		TableColumn<Object, ?> tableColumn = new TableColumn<Object, Object>();
		tableColumn.setMinWidth(100);
		tableColumn.setCellValueFactory(
				new CellValueFactoryForJavaAndEnumTypes<>(reflectApplication, languageProvider, type));
		List<TableColumn<Object, ?>> tableColumns = new ArrayList<>();
		tableColumns.add(tableColumn);
		return tableColumns;
	}

	private List<TableColumn<Object, ?>> createColumnForObject() {
		Class<?> itemType = getValuesType();
		ReflectionProvider reflectionProvider = getReflectionProvider();
		ClassInfo classInfo = reflectionProvider.getClassInfo(itemType);
		TableColumn<Object, ?> tableColumn = new TableColumn<Object, Object>();
		tableColumn.setMinWidth(100);
		tableColumn.setCellValueFactory(new CellValueFactoryForObjects<>(classInfo));
		List<TableColumn<Object, ?>> tableColumns = new ArrayList<>();
		tableColumns.add(tableColumn);
		return tableColumns;
	}

	private List<TableColumn<Object, ?>> createColumnsForObject() {
		Class<?> itemType = getValuesType();
		ReflectionProvider reflectionProvider = getReflectionProvider();
		ClassInfo classInfo = reflectionProvider.getClassInfo(itemType);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSortedAndVisibleInTable();
		List<TableColumn<Object, ?>> tableColumns = new ArrayList<>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			TableColumn<Object, ?> tableColumn = new TableColumn<Object, Object>(propertyInfo.getDisplayName());
			tableColumn.setMinWidth(100);
			tableColumn.setCellValueFactory(new CellValueFactoryForObjectProperties<>(propertyInfo));
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
			String message = getLanguageProvider().getText("Error getting table values. Unsupported return type.");
			throw new RuntimeException(message);
		}

	}

}
