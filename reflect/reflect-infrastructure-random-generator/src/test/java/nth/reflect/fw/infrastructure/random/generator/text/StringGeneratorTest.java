package nth.reflect.fw.infrastructure.random.generator.text;

import static nth.reflect.fw.infrastructure.random.LambdaMatcher.lambdaMatcher;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.IntRange;
import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class StringGeneratorTest {

	@Test
	public void testForNoParamaters() {
		List<String> results = Random.stringGenerator().generateList(10);
		assertResults(results, StringGenerator.DEFAULT_LENGTH, StringGenerator.DEFAULT_CHARACTER_SET);
	}

	private void assertResults(List<String> results, int length, CharacterSet characterSet) {
		assertResults(results, length, length,characterSet);
	}
	
	private void assertResults(List<String> results, int minLength, int maxLength, CharacterSet characterSet) {
		for (String result : results) {
			assertThat(result.length(), greaterThanOrEqualTo(minLength));
			assertThat(result.length(), lessThanOrEqualTo(maxLength));
			for (Character character : result.toCharArray()) {
				assertThat(character,  lambdaMatcher(ch ->  characterSet.contains(ch)));
			}
		}
	}

	@Test
	public void testForLengthInt() {
		int length = -10;
		List<String> results = Random.stringGenerator().forLength(length) .generateList(length);
		assertResults(results, length, StringGenerator.DEFAULT_CHARACTER_SET);

		length = 0;
		results = Random.stringGenerator().forLength(length) .generateList(length);
		assertResults(results, length, StringGenerator.DEFAULT_CHARACTER_SET);
		
		length = 20;
		results = Random.stringGenerator().forLength(length) .generateList(length);
		assertResults(results, length, StringGenerator.DEFAULT_CHARACTER_SET);
	}

	@Test
	public void testForLengthIntInt() {
		int minLength = -10;
		int maxLength = -10;
		List<String> results = Random.stringGenerator().forLength(minLength, maxLength) .generateList(10);
		IntRange range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = -10;
		maxLength = 0;
		results = Random.stringGenerator().forLength(minLength, maxLength) .generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 0;
		maxLength = -10;
		results = Random.stringGenerator().forLength(minLength, maxLength) .generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 0;
		maxLength = 10;
		results = Random.stringGenerator().forLength(minLength, maxLength) .generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 10;
		maxLength = 0;
		results = Random.stringGenerator().forLength(minLength, maxLength) .generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 10;
		maxLength = 20;
		results = Random.stringGenerator().forLength(minLength, maxLength) .generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);

		minLength = 20;
		maxLength = 30;
		results = Random.stringGenerator().forLength(minLength, maxLength) .generateList(10);
		range = new IntRange(minLength, maxLength);
		assertResults(results, range.getMin(), range.getMax(), StringGenerator.DEFAULT_CHARACTER_SET);
		
	}

	@Test
	public void testForCharSetCharacterSet() {
		CharacterSet numbers = CharacterSet.numbers();
		List<String> results = Random.stringGenerator().forCharSet(numbers).generateList(10);
		assertResults(results, StringGenerator.DEFAULT_LENGTH, numbers);
	}

	@Test
	public void testForCharSetRandomGeneratorOfCharacter() {
		CharacterSet numbers = CharacterSet.numbers();
		RandomGenerator<Character> characterGenerator=Random.characterGenerator().forCharacters(numbers);
		List<String> results = Random.stringGenerator().forCharacterGenerator(characterGenerator).generateList(10);
		assertResults(results, StringGenerator.DEFAULT_LENGTH, numbers);
	}

}
