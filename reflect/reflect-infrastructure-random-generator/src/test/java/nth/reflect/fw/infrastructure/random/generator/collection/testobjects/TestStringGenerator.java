package nth.reflect.fw.infrastructure.random.generator.collection.testobjects;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class TestStringGenerator extends RandomGenerator<String> {

	private final String testString;

	public TestStringGenerator(String testString) {
		this.testString = testString;
	}
	
	@Override
	public String generate() {
		return testString;
	}

}
