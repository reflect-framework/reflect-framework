package nth.reflect.fw.gui.component.table.info.dataprovider;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDataProvider<T, F> extends MethodListDataProvider<T, F, Stream<T>> {

	private static final long serialVersionUID = -8102929795560086861L;

	public StreamDataProvider(Method method, Object methodOwner, Object methodParameter) {
		super(method, methodOwner, methodParameter);
	}

	@Override
	public Collection<T> toCollection(Stream<T> stream) {
		List<T> list = stream.collect(Collectors.toList());
		return list;
	}

}
