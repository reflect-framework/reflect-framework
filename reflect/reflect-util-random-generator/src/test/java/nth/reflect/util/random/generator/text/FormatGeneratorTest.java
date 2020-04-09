package nth.reflect.util.random.generator.text;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.RandomGenerator;
import nth.reflect.util.random.generator.text.CharacterGenerator;
import nth.reflect.util.random.generator.text.CharacterSet;

public class FormatGeneratorTest {
	@Test
	public void testForNoParamater() {
		String result = Random.format().generate();
		assertThat(result).isEqualTo("");
	}

	@Test
	public void forFormat() {
		String format = CharacterSet.common().toCharacterString();
		String result = Random.format().forFormat(format).generate();
		Map<Character, CharacterSet> placeHoldersAndCharacterSets = CharacterSet.placeHoldersAndCharacterSets();
		Set<Character> placeHolders = placeHoldersAndCharacterSets.keySet();
		int index = 0;
		for (Character formatCharacter : format.toCharArray()) {
			if (placeHolders.contains(formatCharacter)) {
				CharacterSet characterSet = placeHoldersAndCharacterSets.get(formatCharacter);
				assertThat(result.charAt(index)).isIn(characterSet);
			} else {
				assertThat(result.charAt(index)).isEqualTo(format.charAt(index));
			}
			index++;
		}
	}

	@Test
	public void forPlaceHoldersAndGenerators() {
		Map<Character, RandomGenerator<Character>> placeHoldersAndGenerators = new HashMap<>();
		placeHoldersAndGenerators.put('1', new CharacterGenerator("A"));
		placeHoldersAndGenerators.put('2', new CharacterGenerator("B"));
		String result = Random.format().forPlaceHoldersAndGenerators(placeHoldersAndGenerators).forFormat("112212")
				.generate();
		assertThat(result).isEqualTo("AABBAB");
	}

	@Test
	public void forPlaceHoldersAndChatacterSets() {
		Map<Character, CharacterSet> placeHoldersAndCharacterSets = new HashMap<>();
		placeHoldersAndCharacterSets.put('1', CharacterSet.fromString("A"));
		placeHoldersAndCharacterSets.put('2', CharacterSet.fromString("B"));
		String result = Random.format().forPlaceHoldersAndCharacterSets(placeHoldersAndCharacterSets)
				.forFormat("112212").generate();
		assertThat(result).isEqualTo("AABBAB");
	}
}
