package nth.reflect.fw.infrastructure.random;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.collection.StringBuilderGenerator;

public class RandomGeneratorTest {

	private static final String DASH = "-";
	private static final String ONE = "1";
	private ValueGenerator<String> valueGenerator;

	@Before
	public void setup() {
		valueGenerator = new ValueGenerator<String>(ONE);
	}

	@Test
	public void testGenerate() {
		String result = valueGenerator.generate();
		assertEquals(result, ONE);
	}

	@Test
	public void testGenerateListInt() {
		int size = 0;
		List<String> result = valueGenerator.generateList(size);
		assertThat(result, hasSize(size));
		assertThat(result, empty());

		size = 1;
		result = valueGenerator.generateList(size);
		assertThat(result, hasSize(size));
		assertThat(result, hasItem(ONE));

		size = 10;
		result = valueGenerator.generateList(size);
		assertThat(result, hasSize(size));
		assertThat(result, hasItem(ONE));
	}

	@Test
	public void testGenerateListIntInt() {
		int min = 0;
		int max = 0;
		for (int i = 0; i < 20; i++) {
			List<String> result = valueGenerator.generateList(min, max);
			assertEquals(result.size(), 0);
			assertThat(result, empty());
		}

		min = 1;
		max = 1;
		for (int i = 0; i < 20; i++) {
			List<String> result = valueGenerator.generateList(min, max);
			assertEquals(result.size(), max);
			assertThat(result, hasItem(ONE));
		}

		min = 10;
		max = 20;
		for (int i = 0; i < 20; i++) {
			List<String> result = valueGenerator.generateList(min, max);
			assertThat(result.size(), allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max)));
			assertThat(result, hasItem(ONE));
		}

		min = 20;
		max = 10;
		for (int i = 0; i < 20; i++) {
			List<String> result = valueGenerator.generateList(min, max);
			assertThat(result.size(), allOf(greaterThanOrEqualTo(max), lessThanOrEqualTo(min)));
			assertThat(result, hasItem(ONE));
		}
	}

	@Test
	public void testGenerateSetInt() {
		int size = 0;
		Set<String> result = valueGenerator.generateSet(size);
		assertThat(result, hasSize(size));
		assertThat(result, empty());

		size = 1;
		result = valueGenerator.generateSet(size);
		assertThat(result, hasSize(size));
		assertThat(result, hasItem(ONE));

		size = 10;
		result = valueGenerator.generateSet(size);
		assertThat(result, hasSize(1));
		assertThat(result, hasItem(ONE));
	}

	@Test
	public void testGenerateSetIntInt() {
		int min = 0;
		int max = 0;
		Set<String> result = valueGenerator.generateSet(min, max);
		assertThat(result, hasSize(min));
		assertThat(result, empty());

		min = 1;
		max = 1;
		result = valueGenerator.generateSet(min, max);
		assertThat(result, hasSize(max));
		assertThat(result, hasItem(ONE));

		min = 10;
		max = 20;
		result = valueGenerator.generateSet(min);
		assertThat(result, hasSize(1));
		assertThat(result, hasItem(ONE));
	}

	@Test
	public void testGenerateStringInt() {
		int generationTimes = 0;
		String result = valueGenerator.generateString(generationTimes);
		assertEquals("", result);

		generationTimes = 1;
		result = valueGenerator.generateString(generationTimes);
		assertEquals("1", result);

		generationTimes = 5;
		result = valueGenerator.generateString(generationTimes);
		assertEquals("1, 1, 1, 1, 1", result);
	}

	@Test
	public void testGenerateStringIntString() {
		int generationTimes = 0;
		String result = valueGenerator.generateString(generationTimes, DASH);
		assertEquals("", result);

		generationTimes = 1;
		result = valueGenerator.generateString(generationTimes, DASH);
		assertEquals("1", result);

		generationTimes = 5;
		result = valueGenerator.generateString(generationTimes, DASH);
		assertEquals("1-1-1-1-1", result);
	}

	@Test
	public void testGenerateStringIntInt() {
		int min = 0;
		int max = 0;
		assertStringLengthWithDefaultSeperator(min, max);

		min = 0;
		max = 1;
		assertStringLengthWithDefaultSeperator(min, max);

		min = 5;
		max = 10;
		assertStringLengthWithDefaultSeperator(min, max);

		min = 10;
		max = 5;
		assertStringLengthWithDefaultSeperator(max, min);
	}

	private void assertStringLengthWithDefaultSeperator(int min, int max) {
		String seperator = StringBuilderGenerator.DEFAULT_SEPERATOR;
		for (int i = 0; i < 20; i++) {
			String result = valueGenerator.generateString(min, max);
			int expectedLengthMin = min + ((min == 0 ? 0 : min - 1) * seperator.length());
			int expectedLengthMax = max + ((max == 0 ? 0 : max - 1) * seperator.length());
			assertThat(result.length(),
					allOf(greaterThanOrEqualTo(expectedLengthMin), lessThanOrEqualTo((expectedLengthMax))));
			if (result.length() > 0) {
				assertThat(result, containsString(ONE));
			}
			if (result.length() > 1) {
				assertThat(result, containsString(seperator));
			}
		}
	}

	@Test
	public void testGenerateStringIntIntString() {
		int min = 0;
		int max = 0;
		assertStringLengthWithGivenSeperator(min, max);

		min = 0;
		max = 1;
		assertStringLengthWithGivenSeperator(min, max);

		min = 5;
		max = 10;
		assertStringLengthWithGivenSeperator(min, max);

		min = 10;
		max = 5;
		assertStringLengthWithGivenSeperator(max, min);

	}

	private void assertStringLengthWithGivenSeperator(int min, int max) {
		String seperator = "***\n***";
		for (int i = 0; i < 20; i++) {
			String result = valueGenerator.generateString(min, max, seperator);
			int expectedLengthMin = min + ((min == 0 ? 0 : min - 1) * seperator.length());
			int expectedLengthMax = max + ((max == 0 ? 0 : max - 1) * seperator.length());
			assertThat(result.length(),
					allOf(greaterThanOrEqualTo(expectedLengthMin), lessThanOrEqualTo((expectedLengthMax))));
			if (result.length() > 0) {
				assertThat(result, containsString(ONE));
			}
			if (result.length() > 1) {
				assertThat(result, containsString(seperator));
			}
		}
	}

}
