package nth.introspect.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated Use {@link LogicFilter instead}
 * @author nilsth
 *
 * @param <T>
 */
public class FilterBuilder<T> implements Filter<T>{
	private List<Filter<T>> inludeFilters;
	private List<Filter<T>> excludeFilters;
	
	public FilterBuilder() 	{
		inludeFilters=new ArrayList<Filter<T>>();
		excludeFilters=new ArrayList<Filter<T>>();
	}
	
	public FilterBuilder<T> include(final Filter<T> filter) {
		inludeFilters.add(filter);
		return this;
	}
	
	public FilterBuilder<T> exclude(final Filter<T> filter) {
		excludeFilters.add(filter);
		return this;
	}

	@Override
	public boolean isMatch(T t) {
		for (Filter<T> filter:excludeFilters) {
			if (filter.isMatch(t)) {
				return false;
			}
		}
		for (Filter<T> filter:inludeFilters) {
			if (filter.isMatch(t)) {
				return true;
			}
		}
		return false;
	}
	
}
