package nth.reflect.util.random.generator.text;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.util.random.IntRange;
import nth.reflect.util.random.Random;
import nth.reflect.util.random.RandomGenerator;
import nth.reflect.util.random.generator.text.CharacterSet;
import nth.reflect.util.random.generator.text.StringGenerator;

public class StringGeneratorTest {

	@Test
	public void testForNoParamaters() {
		List<String> results = Random.string().generateList(10);
		assertResults(results, StringGenerator.DEFAULT_LENGTH, StringGenerator.DEFAULT_CHARACTER_SET);
	}

	private void assertResults(List<String> results, int length, CharacterSet characterSet) {
		assertResults(results, length, length, characterSet);
	}

	private void assertResults(List<String> results, int minLength, int maxLength, CharacterSet characterSet) {
		for (String result : results) {
			assertThat(result).hasSizeGreaterThanOrEqualTo(minLength);
			assertThat(result).hasSizeLessThanOrEqualTo(maxLength);
			for (Character character : result.toCharArray()) {
				assertThat(character).isIn(characterSet);
			}
		}
	}

	@Test
	public void testForLengthInt() {
		int length = -10;
		List<String> results = Random.string().forLength(length).generateList(length);
		assertResults(results, length, StringGenerator.DEFAULT_CHARACTER_SET);

		length = 0;
		results = Random.string().forLength(length).generateList(length);
		assertResults(results, length, StringGenerator.DEFAULT_CHARACTER_SET);

		length = 20;
		results = Random.string().forLength(length).generateList(length);
		assertResults(results, length, StringGenerator.DEFAULT_CHARACTER_SET);
	}

	@Test
	public void testForLengthIntInt() {
		int minLength = -10;
		int maxLength = -10;
		List<String> results = Random.string().forLength(minLength, maxLength).generateList(10);
		IntRange range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = -10;
		maxLength = 0;
		results = Random.string().forLength(minLength, maxLength).generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 0;
		maxLength = -10;
		results = Random.string().forLength(minLength, maxLength).generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 0;
		maxLength = 10;
		results = Random.string().forLength(minLength, maxLength).generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 10;
		maxLength = 0;
		results = Random.string().forLength(minLength, maxLength).generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 10;
		maxLength = 20;
		results = Random.string().forLength(minLength, maxLength).generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 20;
		maxLength = 30;
		results = Random.string().forLength(minLength, maxLength).generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

	}

	@Test
	public void testForCharSetCharacterSet() {
		CharacterSet numbers = CharacterSet.numbers();
		List<String> results = Random.string().forCharSet(numbers).generateList(10);
		assertResults(results, StringGenerator.DEFAULT_LENGTH, numbers);
	}

	@Test
	public void testForCharSetRandomGeneratorOfCharacter() {
		CharacterSet numbers = CharacterSet.numbers();
		RandomGenerator<Character> characterGenerator = Random.character().forCharacters(numbers);
		List<String> results = Random.string().forCharacterGenerator(characterGenerator).generateList(10);
		assertResults(results, StringGenerator.DEFAULT_LENGTH, numbers);
	}

}
