package nth.reflect.fw.gui.component.table.info.dataprovider;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

import com.vaadin.flow.data.provider.DataProvider;

import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * Create for arrays, Collections, Iterators, Streams, Callbacks, DataProviders
 * otherwise throw {@link MethodReturnTypeNotSupportedException}
 * 
 * @see MethodReturnTypeNotSupportedException
 * @see GettingTableValuesException
 * @see DataProvider
 * 
 * @author nilsth
 *
 */
public class DataProviderFactory {

	public static DataProvider createFor(GridTab gridTab) {
		ActionMethodInfo methodInfo = gridTab.getMethodInfo();
		Method method = methodInfo.getMethod();
		Object methodOwner = gridTab.getMethodOwner();
		Object methodParameter = gridTab.getMethodParameter();
		return create(method, methodOwner, methodParameter);

	}

	public static DataProvider createFor(PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		Method getterMethod = propertyInfo.getGetterMethod();
		Object domainObject = propertyValueModel.getDomainObject();
		Object noMethodParameter = null;
		return create(getterMethod, domainObject, noMethodParameter);
	}

	private static DataProvider create(Method method, Object methodOwner, Object methodParameter) {

		Class<?> returnType = method.getReturnType();

		if (returnType.isArray()) {
			return new ArrayDataProvider(method, methodOwner, methodParameter);

		} else if (Collection.class.isAssignableFrom(returnType)) {
			return new CollectionDataProvider(method, methodOwner, methodParameter);

		} else if (Iterator.class.isAssignableFrom(returnType)) {
			return new IteratorDataProvider(method, methodOwner, methodParameter);

		} else if (Stream.class.isAssignableFrom(returnType)) {
			return new StreamDataProvider(method, methodOwner, methodParameter);

		} else if (DataProvider.class.isAssignableFrom(returnType)) {
			return createForDataProvider(method, methodOwner, methodParameter);

		} else {
			throw new MethodReturnTypeNotSupportedException(method);
		}
	}

	private static DataProvider createForDataProvider(Method method, Object methodOwner, Object methodParameter) {
		DataProvider dataProvider = (DataProvider) getReturnValue(method, methodOwner, methodParameter);
		if (dataProvider == null) {
			return DataProvider.ofItems();
		} else {
			return dataProvider;
		}
	}

	private static Object getReturnValue(Method method, Object methodOwner, Object methodParameter) {
		try {
			switch (method.getParameterCount()) {
			case 0:
				return method.invoke(methodOwner);
			case 1:
				return method.invoke(methodOwner, methodParameter);
			default:
				throw new TooManyMethodParametersException(method);
			}
		} catch (Exception e) {
			throw new GettingTableValuesException(method, e);
		}
	}

}
