package nth.reflect.fw.generic.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilterUtil {
	
	public static <T> List<T> filter(Collection<T> collection, Filter<T> filter) {
		List<T> result = new ArrayList<T>();
		for (T element : collection) {
			if (filter.isMatch(element)) {
				result.add(element);
			}
		}
		return result;
	}
}
