package nth.reflect.util.random.generator.text;

import java.util.HashMap;
import java.util.Map;

import nth.reflect.util.random.RandomGenerator;

/**
 * Generates a {@link String} based on a given format {@link String}. Characters
 * in the format string represent place holders that are replaced with a random
 * character from a {@link CharacterSet}. See
 * {@link CharacterSet#placeHoldersAndCharacterSets()}. Characters in the format string
 * that are not a place holder in {@link CharacterSet#placeHoldersAndCharacterSets()} are
 * copied
 * 
 * @author nilsth
 *
 */
public class FormatGenerator extends RandomGenerator<String> {
	private final String format;
	private final Map<Character, RandomGenerator<Character>> placeHoldersAndGenerators;

	
	public FormatGenerator() {
		this("");
	}
	
	/**
	 * @param format
	 *            see {@link CharacterSet#placeHoldersAndCharacterSets()}
	 */
	public FormatGenerator(String format) {
		this(format,  createPlaceHoldersAndGenerators(CharacterSet.placeHoldersAndCharacterSets()));
	}
	
	/**
	 * @param format
	 *            see {@link CharacterSet#placeHoldersAndCharacterSets()}
	 */
	public FormatGenerator(String format, Map<Character, RandomGenerator<Character>> placeHoldersAndGenerators) {
		this.format = format;
		this.placeHoldersAndGenerators = placeHoldersAndGenerators;
	}

	@Override
	public String generate() {
		StringBuilder result = new StringBuilder();
		for (Character placeHolder : format.toCharArray()) {
			if (placeHoldersAndGenerators.keySet().contains(placeHolder)) {
				RandomGenerator<Character> characterGenerator = placeHoldersAndGenerators.get(placeHolder);
				Character generatedValue = characterGenerator.generate();
				result.append(generatedValue);
			} else {
				result.append(placeHolder);
			}
		}
		return result.toString();
	}
	
	public FormatGenerator forFormat(String format) {
		return new FormatGenerator(format, placeHoldersAndGenerators);
	}


	public FormatGenerator forPlaceHoldersAndGenerators(Map<Character, RandomGenerator<Character>> placeHoldersAndGenerators) {
		return new FormatGenerator(format, placeHoldersAndGenerators);
	}

	public FormatGenerator forPlaceHoldersAndCharacterSets(Map<Character, CharacterSet> placeHoldersAndCharacterSets) {
		return new FormatGenerator(format, createPlaceHoldersAndGenerators(placeHoldersAndCharacterSets));
	}

	
	private static Map<Character, RandomGenerator<Character>> createPlaceHoldersAndGenerators(Map<Character, CharacterSet> placeHoldersAndCharacterSets) {
		Map<Character, RandomGenerator<Character>> placeHoldersAndGenerators = new HashMap<>();
		for (Character placeHolder : placeHoldersAndCharacterSets.keySet()) {
			CharacterSet characterSet = placeHoldersAndCharacterSets.get(placeHolder);
			placeHoldersAndGenerators.put(placeHolder, new CharacterGenerator(characterSet));
		}
		return placeHoldersAndGenerators;
	}

}
