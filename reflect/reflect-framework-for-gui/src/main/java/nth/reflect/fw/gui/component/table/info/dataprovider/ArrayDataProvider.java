package nth.reflect.fw.gui.component.table.info.dataprovider;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ArrayDataProvider<T, F> extends MethodListDataProvider<T, F, Object[]> {

	private static final long serialVersionUID = 3244849787412201549L;

	public ArrayDataProvider(Method method, Object methodOwner, Object methodParameter) {
		super(method, methodOwner, methodParameter);
	}

	@Override
	public Collection<T> toCollection(Object[] methodReturnValue) {
		List<T> list = (List<T>) Arrays.asList(methodReturnValue);
		return list;
	}

}
