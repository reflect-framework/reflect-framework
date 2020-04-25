package nth.reflect.fw.gui.component.table.info.column;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.component.tab.table.TableTab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.FormatPatternFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class ColumnInfoFactory {

	public static List<ColumnInfo> createFor(TableTab tableTab) {
		UserInterfaceContainer container = tableTab.getUserInterfaceContainer();

		ActionMethodInfo methodInfo = tableTab.getMethodInfo();
		Method method = methodInfo.getMethod();

		Optional<TypeInfo> optionalGenericTypeInfo = methodInfo.getReturnTypeInfo().getGenericTypeInfo();
		TypeInfo genericTypeInfo = optionalGenericTypeInfo
				.orElseThrow(() -> new UnknownGenericReturnTypeException(method));

		return create(container, method, genericTypeInfo);
	}

	public static List<ColumnInfo> createFor(FormTab formTab, PropertyValueModel propertyValueModel) {
		UserInterfaceContainer container = formTab.getUserInterfaceContainer();

		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();

		Method method = propertyInfo.getGetterMethod();

		Optional<TypeInfo> optionalGenericTypeInfo = propertyInfo.getTypeInfo().getGenericTypeInfo();
		TypeInfo genericTypeInfo = optionalGenericTypeInfo
				.orElseThrow(() -> new UnknownGenericReturnTypeException(method));

		return create(container, method, genericTypeInfo);
	}

	private static List<ColumnInfo> create(UserInterfaceContainer container, Method method, TypeInfo genericTypeInfo) {
		if (genericTypeInfo.isDomainClass()) {
			Class<?> domainClass = genericTypeInfo.getType();
			return createForDomainClass(container, domainClass);
		} else {
			return createForNoneDomainClass(container, method, genericTypeInfo);
		}
	}

	private static List<ColumnInfo> createForNoneDomainClass(UserInterfaceContainer container, Method method,
			TypeInfo genericTypeInfo) {
		StringConverter<Object> stringConverter = createStringConverter(container, method, genericTypeInfo);
		ColumnInfo clumnInfo = new StringConverterColumnInfo(stringConverter);
		List<ColumnInfo> columnInfos = new ArrayList<>();
		columnInfos.add(clumnInfo);
		return columnInfos;
	}

	private static StringConverter<Object> createStringConverter(UserInterfaceContainer container, Method method,
			TypeInfo genericTypeInfo) {
		String formatPattern = getFormatPattern(method);
		StringConverterProvider stringConverterProvider = container.get(StringConverterProvider.class);
		StringConverterFactoryInfo stringConverterInfo = new StringConverterFactoryInfo(genericTypeInfo, container,
				formatPattern);
		StringConverter<Object> stringConverter = stringConverterProvider.create(stringConverterInfo);
		return stringConverter;
	}

	private static String getFormatPattern(Method method) {
		String formatPattern = FormatPatternFactory.create(method);
		return formatPattern;
	}

	private static List<ColumnInfo> createForDomainClass(UserInterfaceContainer container, Class<?> domainClass) {
		List<PropertyInfo> propertyInfos = getPropertyInfos(container, domainClass);

		List<ColumnInfo> tableColumns = propertyInfos
				.stream()
				.map(propertyInfo -> new PropertyColumnInfo(propertyInfo))
				.collect(Collectors.toList());

		return tableColumns;

	}

	private static List<PropertyInfo> getPropertyInfos(UserInterfaceContainer container, Class<?> domainClass) {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSortedAndVisibleInTable();
		return propertyInfos;
	}

}
