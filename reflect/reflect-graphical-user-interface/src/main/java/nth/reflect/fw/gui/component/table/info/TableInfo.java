package nth.reflect.fw.gui.component.table.info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.gui.component.table.info.cell.GenericFormatter;
import nth.reflect.fw.gui.component.table.info.cell.PropertyValueFormatter;
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

	public TableInfo(UserInterfaceContainer userInterfaceContainer) {
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
	}

	public abstract Object getValues();

	public abstract TypeInfo getTypeInfo();

	/**
	 * 
	 * @param actionMethodParameterValue the selected row value,
	 *                                   {@link ReadOnlyValueModel#canGetValue()}
	 *                                   must return false when no row is selected.
	 *                                   Important note: this method must be
	 *                                   recalled to create new row menu items every
	 *                                   time the actionMethodParameter changes!
	 * @return menu items to be displayed in a pop up menu on a {@link GridTab} or
	 *         {@link ManyToOneOrManyField}
	 */
	public abstract Collection<Item> getRowMenuItems(ReadOnlyValueModel actionMethodParameterValue);

	private ReflectionProvider getReflectionProvider() {
		return reflectionProvider;
	}

	public LanguageProvider getLanguageProvider() {
		return languageProvider;
	}

	public List<ColumnInfo> getColumnInfos() {
		TypeInfo typeInfo = getTypeInfo();
		if (typeInfo.getArrayOrCollectionTypeInfo().get().isDomainClass()) {
			return createColumnsForDomainObject();
		} else {
			return createColumnsForJavaAndEnumTypes();
		}
	}

	private List<ColumnInfo> createColumnsForJavaAndEnumTypes() {
		TypeInfo typeInfo = getTypeInfo();
		GenericFormatter cellStringConverter = new GenericFormatter(reflectionProvider, languageProvider, typeInfo);
		ColumnInfo clumnInfo = new ColumnInfo(cellStringConverter);
		List<ColumnInfo> columnInfos = new ArrayList<>();
		columnInfos.add(clumnInfo);
		return columnInfos;
	}

	private List<ColumnInfo> createColumnsForDomainObject() {
		Class<?> domainClass = getTypeInfo().getArrayOrCollectionTypeInfo().get().getType();
		ReflectionProvider reflectionProvider = getReflectionProvider();
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSortedAndVisibleInTable();
		List<ColumnInfo> tableColumns = new ArrayList<>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			PropertyValueFormatter cellStringConverter = new PropertyValueFormatter(propertyInfo);
			ColumnInfo tableColumn = new ColumnInfo(propertyInfo, cellStringConverter);
			tableColumns.add(tableColumn);
		}
		return tableColumns;
	}

	@SuppressWarnings("rawtypes")
	public List<Object> getValueList() {
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
