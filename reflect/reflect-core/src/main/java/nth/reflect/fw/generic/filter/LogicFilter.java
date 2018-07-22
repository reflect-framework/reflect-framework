package nth.reflect.fw.generic.filter;


public class LogicFilter<T> implements Filter<T> {

	private Filter<T> currentFilter;

	public LogicFilter(Filter<T> startFilter) {
		this.currentFilter = startFilter;
	}
	
	@Override
	public boolean isMatch(T t) {
		return currentFilter.isMatch(t);
	}
	
	public Filter<T> and(Filter<T> additionalFilter) {
		currentFilter= new AndFilter<T>(currentFilter, additionalFilter);
		return currentFilter;
	}
	
	public Filter<T> andNot(Filter<T> additionalFilter) {
		currentFilter= new AndNotFilter<T>(currentFilter, additionalFilter);
		return currentFilter;
	}
	
	public Filter<T> or(Filter<T> additionalFilter) {
		currentFilter= new OrFilter<T>(currentFilter, additionalFilter);
		return currentFilter;
	}
	
	public Filter<T> orNot(Filter<T> additionalFilter) {
		currentFilter= new OrNotFilter<T>(currentFilter, additionalFilter);
		return currentFilter;
	}
	
}
