package nth.reflect.fw.gui.component.table.info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.gui.component.table.info.cell.CellValueFactoryForJavaAndEnumTypes;
import nth.reflect.fw.gui.component.table.info.cell.CellValueFactoryForObjectPropertyValue;
import nth.reflect.fw.gui.component.table.info.cell.CellValueFactoryForObjects;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * All information that is needed to create an {@link Table}
 * 
 * @author nilsth
 *
 */
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

	/**
	 * 
	 * @param actionMethodParameterValue
	 *            the selected row value,
	 *            {@link ReadOnlyValueModel#canGetValue()} must return false
	 *            when no row is selected. Important note: this method must be
	 *            recalled to create new row menu items every time the
	 *            actionMethodParameter changes!
	 * @return menu items to be displayed in a pop up menu on a {@link GridTab}
	 *         or {@link ManyToOneOrManyField}
	 */
	public abstract Collection<Item> getRowMenuItems(ReadOnlyValueModel actionMethodParameterValue);

	private ReflectionProvider getReflectionProvider() {
		return reflectionProvider;
	}

	public LanguageProvider getLanguageProvider() {
		return languageProvider;
	}

	private ReflectApplication getReflectApplication() {
		return reflectApplication;
	}

	public List<TableColumn> getTableColumns() {
		Class<?> type = getValuesType();
		if (TypeInfo.isJavaVariableType(type) || type.isEnum()) {
			return createColumnsForJavaAndEnumTypes();
		} else {
			return createColumnForObject();
		}
		// TODO
		// return createColumnsForObject();
	}

	private List<TableColumn> createColumnsForJavaAndEnumTypes() {
		Class<?> type = getValuesType();
		ReflectApplication reflectApplication = getReflectApplication();
		LanguageProvider languageProvider = getLanguageProvider();
		CellValueFactoryForJavaAndEnumTypes cellValueFactory = new CellValueFactoryForJavaAndEnumTypes(
				reflectApplication, languageProvider, type);
		TableColumn tableColumn = new TableColumn(cellValueFactory);
		List<TableColumn> tableColumns = new ArrayList<>();
		tableColumns.add(tableColumn);
		return tableColumns;
	}

	private List<TableColumn> createColumnForObject() {
		Class<?> itemType = getValuesType();
		ReflectionProvider reflectionProvider = getReflectionProvider();
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(itemType);
		CellValueFactoryForObjects<Object, Object> cellValueFactory = new CellValueFactoryForObjects<>(domainClassInfo);
		TableColumn tableColumn = new TableColumn(cellValueFactory);
		List<TableColumn> tableColumns = new ArrayList<>();
		tableColumns.add(tableColumn);
		return tableColumns;
	}

	private List<TableColumn> createColumnsForObject() {
		Class<?> itemType = getValuesType();
		ReflectionProvider reflectionProvider = getReflectionProvider();
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(itemType);
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSortedAndVisibleInTable();
		List<TableColumn> tableColumns = new ArrayList<>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			CellValueFactoryForObjectPropertyValue cellValueFactory = new CellValueFactoryForObjectPropertyValue(
					propertyInfo);
			TableColumn tableColumn = new TableColumn(propertyInfo, cellValueFactory);
			tableColumns.add(tableColumn);
		}
		return tableColumns;
	}

	@SuppressWarnings("rawtypes")
	public List<Object> getList() {
		Object values = getValues();
		if (values == null) {
			List<Object> emptyList = new ArrayList<>();
			return emptyList;
		} else if (values instanceof List) {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) values;
			return list;
		} else if (values instanceof Collection) {
			@SuppressWarnings("unchecked")
			List<Object> list = new ArrayList<>((Collection) values);
			return list;
		} else if (values.getClass().isArray()) {
			List<Object> list = Arrays.asList(values);
			return list;
		} else {
			String message = getLanguageProvider().getText("Error getting table values. Unsupported return type.");
			throw new RuntimeException(message);
		}

	}

}
