package nth.reflect.fw.infrastructure.randomfactory;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringFactory implements Factory<String> {


	private final int length;
	private final boolean includeLetters;
	private final boolean includeNumbers;

	
	
	public RandomStringFactory(int length, boolean includeLetters, boolean includeNumbers) {
		super();
		this.length = length;
		this.includeLetters = includeLetters;
		this.includeNumbers = includeNumbers;
	}

	@Override
	public String create() {
		return RandomStringUtils.random(length, includeLetters, includeNumbers);
	}

}
