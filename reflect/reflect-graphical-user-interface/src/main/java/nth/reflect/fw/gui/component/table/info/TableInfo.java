package nth.reflect.fw.gui.component.table.info;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.gui.component.table.info.column.ColumnInfo;
import nth.reflect.fw.gui.component.table.info.column.PropertyColumnInfo;
import nth.reflect.fw.gui.component.table.info.column.StringConverterColumnInfo;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer3domain.DomainObjectActionMethod;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.FormatPatternFactory;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

/**
 * All information that is needed to create an {@link Table}
 * 
 * @author nilsth
 *
 */
public abstract class TableInfo {

	private final DependencyInjectionContainer container;
	private final LanguageProvider languageProvider;

	public TableInfo(DependencyInjectionContainer container) {
		this.container = container;
		this.languageProvider = container.get(LanguageProvider.class);
	}

	public abstract Object getValues();

	/**
	 * 
	 * @return The method that returns the table values. This is often a
	 *         {@link DomainObjectActionMethod} or a {@link DomainObjectProperty}
	 *         getter method.
	 */
	public abstract Method getValuesMethod();

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

	public List<ColumnInfo> getColumnInfos() {
		TypeInfo typeInfo = getTypeInfo();
		if (typeInfo.getArrayOrCollectionTypeInfo().get().isDomainClass()) {
			return createColumnsForDomainObject();
		} else {
			return createColumnForNoneDomainObjects();
		}
	}

	private List<ColumnInfo> createColumnForNoneDomainObjects() {
		TypeInfo typeInfo = getTypeInfo();
		StringConverterProvider stringConverterProvider = container.get(StringConverterProvider.class);
		String formatPattern = getFormatPattern();
		StringConverterFactoryInfo stringConverterInfo = new StringConverterFactoryInfo(typeInfo, container,
				formatPattern);
		StringConverter<Object> stringConverter = stringConverterProvider.create(stringConverterInfo);
		ColumnInfo clumnInfo = new StringConverterColumnInfo(stringConverter);
		List<ColumnInfo> columnInfos = new ArrayList<>();
		columnInfos.add(clumnInfo);
		return columnInfos;
	}

	protected String getFormatPattern() {
		String formatPettern = FormatPatternFactory.create(getValuesMethod());
		return formatPettern;
	}

	private List<ColumnInfo> createColumnsForDomainObject() {
		List<PropertyInfo> propertyInfos = getPropertyInfos();

		List<ColumnInfo> tableColumns = new ArrayList<>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			PropertyColumnInfo tableColumn = new PropertyColumnInfo(propertyInfo);
			tableColumns.add(tableColumn);
		}
		return tableColumns;
	}

	private List<PropertyInfo> getPropertyInfos() {
		Class<?> domainClass = getTypeInfo().getArrayOrCollectionTypeInfo().get().getType();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSortedAndVisibleInTable();
		return propertyInfos;
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
			throw new MethodReturnTypeNotSupportedException(getTypeInfo());
		}

	}

	public LanguageProvider getLanguageProvider() {
		return languageProvider;
	}

}
