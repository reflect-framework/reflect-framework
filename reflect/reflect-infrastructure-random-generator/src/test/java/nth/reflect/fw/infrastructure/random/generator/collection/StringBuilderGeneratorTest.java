package nth.reflect.fw.infrastructure.random.generator.collection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.infrastructure.random.ValueGenerator;

public class StringBuilderGeneratorTest {

	private ValueGenerator<String> valueGenerator;

	@Before
	public void setup() {
		valueGenerator = new ValueGenerator<String>("1");
	}

	@Test
	public void testStringBuilderGeneratorRandomGeneratorOfTInt() {
		int generationTimes = 5;
		String result = new StringBuilderGenerator<>(valueGenerator, generationTimes).generate();
		assertThat(result).isEqualTo("1, 1, 1, 1, 1");
	}

	@Test
	public void testStringBuilderGeneratorRandomGeneratorOfTIntString() {
		int generationTimes = 5;
		String result = new StringBuilderGenerator<>(valueGenerator, generationTimes, "-").generate();
		assertThat(result).isEqualTo("1-1-1-1-1");
	}

	@Test
	public void testStringBuilderGeneratorRandomGeneratorOfTIntInt() {
		int min = 0;
		int max = 0;
		String seperator = StringBuilderGenerator.DEFAULT_SEPERATOR;

		StringBuilderGenerator<String> stringBuilderGenerator = new StringBuilderGenerator<>(valueGenerator, min, max);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 0;
		max = 1;
		stringBuilderGenerator = new StringBuilderGenerator<>(valueGenerator, min, max);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 5;
		max = 10;
		stringBuilderGenerator = new StringBuilderGenerator<>(valueGenerator, min, max);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 10;
		max = 5;
		stringBuilderGenerator = new StringBuilderGenerator<>(valueGenerator, min, max);
		validateResult(min, min, stringBuilderGenerator, seperator);
	}

	private void validateResult(int min, int max, StringBuilderGenerator<String> stringBuilderGenerator,
			String seperator) {
		for (int i = 0; i < 20; i++) {
			String result = stringBuilderGenerator.generate();
			int expectedLengthMin = min + ((min == 0 ? 0 : min - 1) * seperator.length());
			int expectedLengthMax = max + ((max == 0 ? 0 : max - 1) * seperator.length());
			if (min > max) {
				expectedLengthMin = 0;
				expectedLengthMax = 0;
			}
			assertThat(result.length()).isGreaterThanOrEqualTo(expectedLengthMin)
					.isLessThanOrEqualTo((expectedLengthMax));
		}
	}

	@Test
	public void testStringBuilderGeneratorRandomGeneratorOfTIntIntString() {
		int min = 0;
		int max = 0;
		String seperator = "--\n--";

		StringBuilderGenerator<String> stringBuilderGenerator = new StringBuilderGenerator<>(valueGenerator, min, max,
				seperator);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 0;
		max = 1;
		stringBuilderGenerator = new StringBuilderGenerator<>(valueGenerator, min, max, seperator);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 5;
		max = 10;
		stringBuilderGenerator = new StringBuilderGenerator<>(valueGenerator, min, max, seperator);
		validateResult(min, max, stringBuilderGenerator, seperator);

		min = 10;
		max = 5;
		stringBuilderGenerator = new StringBuilderGenerator<>(valueGenerator, min, max, seperator);
		validateResult(min, min, stringBuilderGenerator, seperator);
	}

}
