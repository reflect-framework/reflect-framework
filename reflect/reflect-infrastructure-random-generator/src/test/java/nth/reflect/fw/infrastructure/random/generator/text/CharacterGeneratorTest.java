package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.ValueGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterSet;

public class CharacterGeneratorTest {

	private static final CharacterSet COMMON_CHARACTERS = CharacterSet.common();

	@Test
	public void testForNoParameters() {
		int size = 10 * COMMON_CHARACTERS.size();
		List<Character> characters = Random.character().generateList(size);
		assertThat(characters, hasSize(size));
		assertThat(characters, hasItems(COMMON_CHARACTERS.toCharacterArray()));
	}

	@Test
	public void testForCharactersString() {
		String commonCharacters = COMMON_CHARACTERS.toCharacterString();
		int size = 10 * COMMON_CHARACTERS.size();
		List<Character> characters = Random.character().forCharacters(commonCharacters).generateList(size);
		assertThat(characters, hasSize(size));
		assertThat(characters, hasItems(COMMON_CHARACTERS.toCharacterArray()));

	}

	@Test
	public void testForCharactersRandomGeneratorOfString() {
		String commonCharacters = COMMON_CHARACTERS.toCharacterString();
		ValueGenerator<String> stringGenerator = new ValueGenerator<String>(commonCharacters);
		int size = 2000;
		List<Character> characters = Random.character().forCharacters(stringGenerator).generateList(size);
		assertThat(characters, hasSize(size));
		assertThat(characters, hasItems(COMMON_CHARACTERS.toCharacterArray()));
	}

	@Test
	public void testForCharactersCharacterSet() {
		int size = 2000;
		List<Character> characters = Random.character().forCharacters(COMMON_CHARACTERS).generateList(size);
		assertThat(characters, hasSize(size));
		assertThat(characters, hasItems(COMMON_CHARACTERS.toCharacterArray()));
	}

}
