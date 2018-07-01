package nth.reflect.javafx.control.table;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.item.method.MethodOwnerItem;

public abstract class RfxTableInfo {

	public abstract ObservableList<Object> getItems();

	public abstract Class<?> getItemType();

	public abstract ReflectionProvider getReflectionProvider();

	public abstract LanguageProvider getLanguageProvider();
	
	public abstract List<MethodOwnerItem> getRowMenuItems(Object selectedObject);


	
	public List<TableColumn<Object, ?>> getTableColumns() {
		Class<?> itemType = getItemType();
		if (TypeUtil.isJavaType(itemType) || TypeUtil.isEnum(itemType)) {
			return new ArrayList<>();
		} else {
			return createColumnsForObject();
		}
	}

	private List<TableColumn<Object, ?>> createColumnsForObject() {
		Class<?> itemType = getItemType();
		ReflectionProvider reflectionProvider = getReflectionProvider();
		List<TableColumn<Object, ?>> tableColumns = new ArrayList<>();
		ClassInfo classInfo = reflectionProvider.getClassInfo(itemType);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSortedAnsVisibleInTable();
		for (PropertyInfo propertyInfo : propertyInfos) {
			TableColumn tableColumn = new TableColumn(propertyInfo.getDisplayName());
			tableColumn.setMinWidth(100);
			tableColumn
					.setCellValueFactory(new PropertyValueFactory<>(propertyInfo.getSimpleName()));
			tableColumns.add(tableColumn);
		}
		return tableColumns;
	}



}
