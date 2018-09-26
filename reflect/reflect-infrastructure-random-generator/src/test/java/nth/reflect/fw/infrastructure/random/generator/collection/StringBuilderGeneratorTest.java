package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.junit.Assert.*;

import org.junit.Before;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.allOf;
import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.testobjects.TestStringGenerator;

public class StringBuilderGeneratorTest {

	private TestStringGenerator testStringGenerator;

	@Before
	public void setup() {
		testStringGenerator = new TestStringGenerator("1");
	}
	
	@Test
	public void testStringBuilderGeneratorRandomGeneratorOfTInt() {
		int generationTimes = 5;
		String result = new StringBuilderGenerator<>(testStringGenerator, generationTimes).generate();
		assertEquals("1, 1, 1, 1, 1", result);
	}

	@Test
	public void testStringBuilderGeneratorRandomGeneratorOfTIntString() {
		int generationTimes = 5;
		String result = new StringBuilderGenerator<>(testStringGenerator, generationTimes, "-").generate();
		assertEquals("1-1-1-1-1", result);
	}

	@Test
	public void testStringBuilderGeneratorRandomGeneratorOfTIntInt() {
		int min = 0;
		int max = 0;
		String seperator = StringBuilderGenerator.DEFAULT_SEPERATOR;

		StringBuilderGenerator<String> stringBuilderGenerator = new StringBuilderGenerator<>(testStringGenerator, min,
				max);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 0;
		max = 1;
		stringBuilderGenerator = new StringBuilderGenerator<>(testStringGenerator, min, max);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 5;
		max = 10;
		stringBuilderGenerator = new StringBuilderGenerator<>(testStringGenerator, min, max);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 10;
		max = 5;
		stringBuilderGenerator = new StringBuilderGenerator<>(testStringGenerator, min, max);
		validateResult(max, min, stringBuilderGenerator, seperator);
	}

	private void validateResult(int min, int max, StringBuilderGenerator<String> stringBuilderGenerator,
			String seperator) {
		for (int i = 0; i < 20; i++) {
			String result = stringBuilderGenerator.generate();
			int expectedLengthMin = min + ((min == 0 ? 0 : min - 1) * seperator.length());
			int expectedLengthMax = max + ((max == 0 ? 0 : max - 1) * seperator.length());
			assertThat(result.length(),
					allOf(greaterThanOrEqualTo(expectedLengthMin), lessThanOrEqualTo((expectedLengthMax))));
		}
	}

	@Test
	public void testStringBuilderGeneratorRandomGeneratorOfTIntIntString() {
		int min = 0;
		int max = 0;
		String seperator = "--\n--";

		StringBuilderGenerator<String> stringBuilderGenerator = new StringBuilderGenerator<>(testStringGenerator, min,
				max, seperator);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 0;
		max = 1;
		stringBuilderGenerator = new StringBuilderGenerator<>(testStringGenerator, min, max, seperator);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 5;
		max = 10;
		stringBuilderGenerator = new StringBuilderGenerator<>(testStringGenerator, min, max, seperator);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 10;
		max = 5;
		stringBuilderGenerator = new StringBuilderGenerator<>(testStringGenerator, min, max, seperator);
		validateResult(max, min, stringBuilderGenerator, seperator);
	}

}
