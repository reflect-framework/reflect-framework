package nth.reflect.ui.vaadin.tab.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.function.ValueProvider;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.generic.translatablestring.ReflectTranslatable;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.ui.vaadin.css.SizeUnit;
import nth.reflect.ui.vaadin.css.StyleBuilder;
import nth.reflect.ui.vaadin.tab.Tab;

public class TableTab extends Tab {

	private static final long serialVersionUID = -7981487179996807252L;
	@ReflectTranslatable
	private static final String ERROR_INVOKING_ACTION_METHOD = "Error invoking action method: %s";
	@ReflectTranslatable
	private static final String ERROR_INVOKING_ACTION_METHOD_THE_RETURN_TYPE_IS_NOT_SUPPORTED_FOR_A_TABLE_TAB = "Error invoking action method: %s The return type %s is not supported for a TableTab.";
	private final UserInterfaceContainer userInterfaceContainer;
	private final Object actionMethodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private final Grid<?> grid;
	private final ClassInfo domainClassInfo;

	public TableTab(UserInterfaceContainer userInterfaceContainer, Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.actionMethodOwner = actionMethodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;

		this.domainClassInfo = createDomainClassInfo();

		grid = createGrid();
		add(grid);
	}

	private ClassInfo createDomainClassInfo() {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		Class<?> actionMethodReturnType = actionMethodInfo.getReturnTypeInfo().getGenericType();
		ClassInfo domainClassInfo = reflectionProvider.getClassInfo(actionMethodReturnType);
		return domainClassInfo;
	}

	private Grid<Object> createGrid() {
		Grid<Object> grid = new Grid<Object>();
		grid.setDataProvider(createDataProvider());
		addGridColumns(grid);
		new StyleBuilder().setWidth(100, SizeUnit.PERCENT).setHeight(100, SizeUnit.PERCENT).setFor(grid);
		return grid;
	}

	@SuppressWarnings("unchecked")
	private void addGridColumns(Grid<?> grid) {
		@SuppressWarnings("rawtypes")
		ValueProvider valueProvider = createValueProviderToString();
		grid.addColumn(valueProvider);

		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSortedAndVisibleInTable();
		for (PropertyInfo propertyInfo : propertyInfos) {
			valueProvider = createValueProvider(propertyInfo);
			grid.addColumn(valueProvider).setHeader(propertyInfo.getDisplayName());
		}
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	private ValueProvider createValueProviderToString() {
		return new ValueProvider<Object, Object>() {
			@Override
			public Object apply(Object domainObject) {
				return domainObject.toString();
			}
		};
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private ValueProvider createValueProvider(PropertyInfo propertyInfo) {
		return new ValueProvider<Object, Object>() {
			@Override
			public Object apply(Object domainObject) {
				return propertyInfo.getFormatedValue(domainObject);
			}
		};
	}

	//
	private DataProvider<Object, ?> createDataProvider() {
		Collection<Object> items = getCollection();
		// TODO override DataProvider or one of its sub classes so that it
		// refreshes when needed
		return new ListDataProvider<>(items);
	}

	/**
	 * invokes the actionMethod and converts its result into a collection or
	 * throws a {@link ReflectTranslatableException}
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<Object> getCollection() {
		Object methodResult;
		try {
			methodResult = actionMethodInfo.invoke(actionMethodOwner, methodParameterValue);
		} catch (Throwable cause) {
			throw new ReflectTranslatableException(userInterfaceContainer, cause, ERROR_INVOKING_ACTION_METHOD,
					actionMethodInfo.getCanonicalName());
		}
		if (methodResult == null) {
			return new ArrayList<>();
		}
		if (methodResult.getClass().isArray()) {
			return Arrays.asList(methodResult);
		}
		if (methodResult instanceof Collection) {
			return (Collection<Object>) methodResult;
		}
		// TODO streams
		throw new ReflectTranslatableException(userInterfaceContainer,
				ERROR_INVOKING_ACTION_METHOD_THE_RETURN_TYPE_IS_NOT_SUPPORTED_FOR_A_TABLE_TAB,
				actionMethodInfo.getCanonicalName(), actionMethodInfo.getReturnTypeInfo().getType());
	}

	@Override
	public String getDisplayName() {
		return actionMethodInfo.getDisplayName();
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.getDescription();
	}

	@Override
	public void onSelected() {
		grid.getDataProvider().refreshAll();
	}

}
