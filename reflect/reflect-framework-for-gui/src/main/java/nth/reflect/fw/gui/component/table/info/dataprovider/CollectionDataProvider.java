package nth.reflect.fw.gui.component.table.info.dataprovider;

import java.lang.reflect.Method;
import java.util.Collection;

public class CollectionDataProvider<T, F> extends MethodListDataProvider<T, F, Collection<T>> {

	private static final long serialVersionUID = 2282361542008112000L;

	public CollectionDataProvider(Method method, Object methodOwner, Object methodParameter) {
		super(method, methodOwner, methodParameter);
	}

	@Override
	public Collection<T> toCollection(Collection<T> methodReturnValue) {
		return methodReturnValue;
	}

}
