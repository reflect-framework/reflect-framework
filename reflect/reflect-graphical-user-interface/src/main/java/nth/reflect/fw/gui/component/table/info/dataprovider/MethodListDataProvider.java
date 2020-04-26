package nth.reflect.fw.gui.component.table.info.dataprovider;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.stream.Stream;

import com.vaadin.flow.data.provider.AbstractDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializablePredicate;

import nth.reflect.fw.gui.component.table.info.dataprovider.exception.GettingTableValuesException;
import nth.reflect.fw.gui.component.table.info.dataprovider.exception.TooManyMethodParametersException;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * A wrapper on the {@link ListDataProvider} that creates a new inner
 * {@link ListDataProvider} when {@link #refreshAll()} is called.
 * 
 * The data comes from the return value of an {@link ActionMethod} or
 * {@link DomainObjectProperty} getter method
 * 
 * @author nilsth
 *
 */

public abstract class MethodListDataProvider<T, F, METHOD_RETURN_TYPE> extends AbstractDataProvider<T, F> {

	private static final long serialVersionUID = -8150798988271428378L;
	private ListDataProvider<T> listDataProvider;
	private final Method method;
	private final Object methodOwner;
	private final Object methodParameter;

	public MethodListDataProvider(Method method, Object methodOwner, Object methodParameter) {
		this.method = method;
		this.methodOwner = methodOwner;
		this.methodParameter = methodParameter;
		listDataProvider = createNewListDataProvider();
	}

	@Override
	public boolean isInMemory() {
		return listDataProvider.isInMemory();
	}

	@Override
	public int size(Query<T, F> query) {
		return listDataProvider.size((Query<T, SerializablePredicate<T>>) query);
	}

	@Override
	public Stream<T> fetch(Query<T, F> query) {
		return listDataProvider.fetch((Query<T, SerializablePredicate<T>>) query);
	}

	@Override
	public void refreshItem(T item) {
		listDataProvider.refreshItem(item);
	}

	@Override
	public void refreshAll() {
		listDataProvider = createNewListDataProvider();
		super.refreshAll();
	}

	private ListDataProvider<T> createNewListDataProvider() {
		METHOD_RETURN_TYPE methodReturnValue = getMethodReturnValue();
		if (methodReturnValue == null) {
			return DataProvider.ofItems();
		}
		Collection<T> collection = toCollection(methodReturnValue);
		ListDataProvider<T> listDataProvider = new ListDataProvider<T>(collection);
		return listDataProvider;
	}

	public abstract Collection<T> toCollection(METHOD_RETURN_TYPE methodReturnValue);

	public METHOD_RETURN_TYPE getMethodReturnValue() {
		try {
			switch (method.getParameterCount()) {
			case 0:
				return (METHOD_RETURN_TYPE) method.invoke(methodOwner);
			case 1:
				return (METHOD_RETURN_TYPE) method.invoke(methodOwner, methodParameter);
			default:
				throw new TooManyMethodParametersException(method);
			}
		} catch (Exception e) {
			throw new GettingTableValuesException(method, e);
		}
	}

}
