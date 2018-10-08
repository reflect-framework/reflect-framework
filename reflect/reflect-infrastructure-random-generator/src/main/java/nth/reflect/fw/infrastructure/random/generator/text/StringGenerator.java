package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.IntRange;
import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * Generates a {@link String} based on a given {@link CharacterSet}. This is basically a wrapper for a {@link CharacterGenerator#generateString()}
 * 
 * @author nilsth
 *
 */
public class StringGenerator extends RandomGenerator<String> {
	public static final CharacterSet DEFAULT_CHARACTER_SET = CharacterSet.common();
	public static final int DEFAULT_LENGTH = 10;
	private final RandomGenerator<Character> characterGenerator;
	private final IntRange length;
	
	public StringGenerator() {
		this(DEFAULT_CHARACTER_SET,DEFAULT_LENGTH);
	}

	public StringGenerator(CharacterSet characterSet, int length) {
		this(Random.character().forCharacters(characterSet), length);
	}

	public StringGenerator(CharacterSet characterSet, int minLength, int maxLength) {
		this(Random.character().forCharacters(characterSet), minLength, maxLength);
	}
	
	public StringGenerator(RandomGenerator<Character> randomGenerator, int length) {
		this.characterGenerator = randomGenerator;
		this.length=new IntRange(length);
	}

	public StringGenerator(RandomGenerator<Character> randomGenerator, int minLength, int maxLength) {
		this.characterGenerator = randomGenerator;
		this.length=new IntRange(minLength, maxLength);
	}

	
	@Override
	public String generate() {
		return characterGenerator.generateString(length.getMin(), length.getMax(), "");
	}
	
	public StringGenerator forLength(int length) {
		return new StringGenerator(characterGenerator,length,length);
	}

	public StringGenerator forLength(int minLength, int maxLength) {
		return new StringGenerator(characterGenerator,minLength,maxLength);
	}

	
	public StringGenerator forCharSet(CharacterSet characterSet) {
		return new StringGenerator(characterSet,length.getMin(), length.getMax());
	}

	public StringGenerator forCharacterGenerator(RandomGenerator<Character> characterGenerator) {
		return new StringGenerator(characterGenerator,length.getMin(), length.getMax());
	}

}
