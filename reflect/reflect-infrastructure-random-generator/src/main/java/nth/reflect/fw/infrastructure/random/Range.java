package nth.reflect.fw.infrastructure.random;

import java.math.BigDecimal;

public class Range<T extends Number>  {

	
	private static final int GREATHER_THAN = 1;
	private final T min;
	private final T max;

	private Range(T min, T max) {
		if (minGreatherThanMax(min, max)) {
			T temp=min;
			min=max;
			max=temp;
		}
		this.min = min;
		this.max = max;	
	}

	private boolean minGreatherThanMax(T min, T max) {
		return new BigDecimal(min.toString()).compareTo(new BigDecimal(max.toString()))==GREATHER_THAN;
	}
	
	public static <T extends Number> Range<T> forMinMax(T min, T max) {
		return new Range<T>(min,max);
	}
	
	public static <T extends Number> Range<T> forFixed(T fixed) {
		return new Range<T>(fixed,fixed);
	}
	
	public boolean isFixed() {
		return min.equals(max);
	}

	public T getMin() {
		return min;
	}

	public T getMax() {
		return max;
	}

}
