package nth.reflect.fw.infrastructure.random.generator.word;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.FromEnumGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BoolGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterSet;
import nth.reflect.fw.infrastructure.random.generator.text.LetterCase;
import nth.reflect.fw.infrastructure.random.generator.text.StringGenerator;

public class SyllableGenerator extends StringGenerator {

	private final RandomGenerator<Character> consonantGenerator;
	private final RandomGenerator<Character> vowelGenerator;
	private final BoolGenerator hasDoubleVowelGenerator;
	private final FromEnumGenerator<SyllableType> syllableTypeGenerator;

	private enum SyllableType {
		CONSONANT_VOWELS,
		CONSONANT_VOWELS_CONSONANT,
		VOWELS_CONSONANT
	}

	@SuppressWarnings("unchecked")
	public SyllableGenerator() {
		syllableTypeGenerator=Random.fromEnumGenerator(SyllableType.class);
		hasDoubleVowelGenerator=Random.boolGenerator().forProbability(20);
		vowelGenerator=Random.characterGenerator().forCharacters(CharacterSet.vowels(LetterCase.LOWER));
		consonantGenerator=Random.characterGenerator().forCharacters(CharacterSet.consonants(LetterCase.LOWER));
	}
	
	
	@Override
	public String generate() {
		StringBuilder syllable = new StringBuilder();
		SyllableType syllableType = syllableTypeGenerator.generate();
		switch (syllableType) {
		case CONSONANT_VOWELS:
			syllable.append(generateConsonant());
			syllable.append(generateVowels());
			break;
		case VOWELS_CONSONANT:
			syllable.append(generateVowels());
			syllable.append(generateConsonant());
			break;
		case CONSONANT_VOWELS_CONSONANT:
		default:
			syllable.append(generateConsonant());
			syllable.append(generateVowels());
			syllable.append(generateConsonant());
			break;
		}
		return syllable.toString();
	}


	private String generateVowels() {
		String vowels;
		if (hasDoubleVowelGenerator.generate()) {
			vowels= vowelGenerator.generateString(2,"");
		} else {
			vowels= vowelGenerator.generateString(1);
		}
		return vowels;
	}


	private Character generateConsonant() {
		return consonantGenerator.generate();
	}

}
