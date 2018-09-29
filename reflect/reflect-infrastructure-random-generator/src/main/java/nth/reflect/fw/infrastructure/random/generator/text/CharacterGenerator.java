package nth.reflect.fw.infrastructure.random.generator.text;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.ValueGenerator;

public class CharacterGenerator extends RandomGenerator<Character> {

	private final RandomGenerator<Character> characterGenerator;

	public CharacterGenerator() {
		this(new ValueGenerator<String>(CharacterSet.common().toCharacterString()));
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

	@Override
	public Character generate() {
		return characterGenerator.generate();
	}
	
	public RandomGenerator<Character> forCharacters(String characters) {
		return new CharacterGenerator(new ValueGenerator<String>(characters));
	}
	
	public RandomGenerator<Character> forCharacters(RandomGenerator<String> stringGenerator) {
		return new CharacterGenerator(stringGenerator);
	}
	
	public RandomGenerator<Character> forCharacters(CharacterSet characterSet) {
		return forCharacters(characterSet.toCharacterString());
	}

}
