package nth.reflect.fw.infrastructure.random;

import java.util.function.Function;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class LambdaMatcher<T> extends BaseMatcher<T> {
	private static final String DEFAULT_DESCRIPTION = "A matching lambda expression";
	private final Function<T, Boolean> matcher;
	private final String description;

	public LambdaMatcher(Function<T, Boolean> matcher) {
		this(matcher, DEFAULT_DESCRIPTION);
	}

	public LambdaMatcher(Function<T, Boolean> matcher, String description) {
		this.matcher = matcher;
		this.description = description;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean matches(Object argument) {
		return matcher.apply((T) argument);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(this.description);
	}

	public static <T> LambdaMatcher<T> lambdaMatcher(Function<T, Boolean> matcher){
		return new LambdaMatcher<>(matcher);
	}
	
	public static <T> LambdaMatcher<T> lambdaMatcher(Function<T, Boolean> matcher, String description){
		return new LambdaMatcher<>(matcher, description);
	}
}
