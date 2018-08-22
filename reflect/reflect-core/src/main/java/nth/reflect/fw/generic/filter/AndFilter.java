package nth.reflect.fw.generic.filter;

public class AndFilter<T> implements Filter<T> {

	private Filter<T> filter1;
	private Filter<T> filter2;

	public AndFilter(Filter<T> filter1, Filter<T> filter2) {
		this.filter1 = filter1;
		this.filter2 = filter2;
	}

	@Override
	public boolean isMatch(T t) {
		return filter1.isMatch(t) && filter2.isMatch(t);
	}

}