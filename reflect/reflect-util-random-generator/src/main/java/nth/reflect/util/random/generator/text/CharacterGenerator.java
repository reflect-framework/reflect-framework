package nth.reflect.util.random.generator.text;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.util.random.RandomGenerator;
import nth.reflect.util.random.ValueGenerator;

public class CharacterGenerator extends RandomGenerator<Character> {

	private final RandomGenerator<Character> characterGenerator;

	public CharacterGenerator() {
		this(CharacterSet.common().toCharacterString());
	}


	public CharacterGenerator(RandomGenerator<String> stringGenerator) {
		this.characterGenerator = new RandomGenerator<Character>() {
			
			@Override
			public Character generate() {
				String string=stringGenerator.generate();
				if (string==null) {
					return null;
				}
				char[] characters = string.toCharArray();		
				int randomIndex=ThreadLocalRandom.current().nextInt(characters.length);
				return characters[randomIndex];
			}
		};
	}

	public CharacterGenerator(CharacterSet characterSet) {
		this(characterSet.toCharacterString());
	}

	public CharacterGenerator(String characters) {
		this(new ValueGenerator<String>(characters));
	}

	@Override
	public Character generate() {
		return characterGenerator.generate();
	}
	
	public RandomGenerator<Character> forCharacters(String characters) {
		return new CharacterGenerator(characters);
	}
	
	public RandomGenerator<Character> forCharacters(RandomGenerator<String> stringGenerator) {
		return new CharacterGenerator(stringGenerator);
	}
	
	public RandomGenerator<Character> forCharacters(CharacterSet characterSet) {
		return new CharacterGenerator(characterSet);
	}

}
