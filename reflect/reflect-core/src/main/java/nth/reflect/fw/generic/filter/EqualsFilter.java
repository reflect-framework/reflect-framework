package nth.reflect.fw.generic.filter;

public class EqualsFilter<T> implements Filter<T> {



	private T objectToCompare;

	public EqualsFilter(T objectToCompare) {
		this.objectToCompare = objectToCompare;
	}
	
	@Override
	public boolean isMatch(T t) {
		return t.equals(objectToCompare);
	}
}
