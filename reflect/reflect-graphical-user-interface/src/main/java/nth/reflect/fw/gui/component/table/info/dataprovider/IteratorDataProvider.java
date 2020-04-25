package nth.reflect.fw.gui.component.table.info.dataprovider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class IteratorDataProvider<T, F> extends MethodListDataProvider<T, F, Iterator<T>> {

	private static final long serialVersionUID = 1L;

	public IteratorDataProvider(Method method, Object methodOwner, Object methodParameter) {
		super(method, methodOwner, methodParameter);
	}

	@Override
	public Collection<T> toCollection(Iterator<T> iterator) {
		List list = new ArrayList<>();
		iterator.forEachRemaining(list::add);
		return list;
	}

}
