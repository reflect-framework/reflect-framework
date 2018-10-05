package nth.reflect.fw.infrastructure.random.generator.text;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterSet;

public class FormatGeneratorTest {

	@Test
	public void testForNoParamater() {
		String result = Random.formatGenerator().generate();
		assertEquals("", result);
	}

	@Test
	public void forFormat() {
		String format=CharacterSet.common().toCharacterString();
		String result = Random.formatGenerator().forFormat(format).generate();
		Map<Character, CharacterSet> placeHoldersAndCharacterSets = CharacterSet.placeHoldersAndCharacterSets();
		Set<Character> placeHolders = placeHoldersAndCharacterSets.keySet();
		int index=0;
		for (Character formatCharacter : format.toCharArray()) {
			if (placeHolders.contains(formatCharacter)) {
				CharacterSet characterSet=placeHoldersAndCharacterSets.get(formatCharacter);
				assertTrue(result.charAt(index) +" is not part of: "+characterSet.toCharacterString(),  characterSet.contains(result.charAt(index)));
			} else {
				assertEquals(""+format.charAt(index), ""+result.charAt(index));
			}
			index++;
		}
	}
	
	@Test
	public void forPlaceHoldersAndGenerators() {
		Map<Character, RandomGenerator<Character>> placeHoldersAndGenerators=new HashMap<>();
		placeHoldersAndGenerators.put('1', new CharacterGenerator("A"));
		placeHoldersAndGenerators.put('2', new CharacterGenerator("B"));
		String result = Random.formatGenerator().forPlaceHoldersAndGenerators(placeHoldersAndGenerators).forFormat("112212").generate();
		assertEquals("AABBAB", result);
	}

	
	
	@Test
	public void forPlaceHoldersAndChatacterSets() {
		Map<Character, CharacterSet> placeHoldersAndCharacterSets=new HashMap<>();
		placeHoldersAndCharacterSets.put('1', CharacterSet.fromString("A"));
		placeHoldersAndCharacterSets.put('2', CharacterSet.fromString("B"));
		String result = Random.formatGenerator().forPlaceHoldersAndCharacterSets(placeHoldersAndCharacterSets).forFormat("112212").generate();
		assertEquals("AABBAB", result);
	}
}
