package nth.reflect.fw.infrastructure.random.generator.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CharacterSet extends HashSet<Character> {

	public static final String SYMBOLS = "!@#$%^&()[]<>";
	public static final String NUMBERS = "1234567890";
	public static final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
	public static final String VOWELS = "aeiouy";
	public static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
	private static final long serialVersionUID = 2975964856391508512L;

	public CharacterSet() {
		super();
	}

	public CharacterSet(String characters) {
		super();
		char[] characterArray = characters.toCharArray();
		for (Character character : characterArray) {
			add(character);
		}
	}

	public CharacterSet(String characters, LetterCase letterCase) {
		super();
		char[] characterArray = characters.toCharArray();
		for (Character character : characterArray) {
			switch (letterCase) {
			case LOWER:
				add(Character.toLowerCase(character));
				break;
			case UPPER:
				add(Character.toUpperCase(character));
				break;
			case UPPER_AND_LOWER:
			default:
				add(Character.toLowerCase(character));
				add(Character.toUpperCase(character));
			}
		}
	}

	public static CharacterSet fromString(String string) {
		return new CharacterSet(string);
	}

	public CharacterSet withString(String string) {
		addAll(new CharacterSet(string));
		return this;
	}

	public static CharacterSet fromString(String string, LetterCase letterCase) {
		return new CharacterSet(string, letterCase);
	}

	public CharacterSet withString(String string, LetterCase letterCase) {
		addAll(new CharacterSet(string, letterCase));
		return this;
	}

	public static CharacterSet letters(LetterCase letterCase) {
		return new CharacterSet(LETTERS, letterCase);
	}

	public CharacterSet withLetters(LetterCase letterCase) {
		addAll(letters(letterCase));
		return this;
	}

	public static CharacterSet vowels(LetterCase letterCase) {
		return new CharacterSet(VOWELS, letterCase);
	}

	public CharacterSet withVowels(LetterCase letterCase) {
		addAll(vowels(letterCase));
		return this;
	}

	public static CharacterSet consonants(LetterCase letterCase) {
		return new CharacterSet(CONSONANTS, letterCase);
	}

	public CharacterSet withConsonants(LetterCase letterCase) {
		addAll(consonants(letterCase));
		return this;
	}

	public static CharacterSet symbols() {
		return new CharacterSet(SYMBOLS);
	}

	public CharacterSet withSymbols() {
		addAll(symbols());
		return this;
	}

	public static CharacterSet numbers() {
		return new CharacterSet(NUMBERS);
	}

	public CharacterSet withNumbers() {
		addAll(numbers());
		return this;
	}

	public static CharacterSet common() {
		return CharacterSet.letters(LetterCase.UPPER_AND_LOWER).withNumbers().withSymbols();
	}
	
	public static Map<Character, CharacterSet> placeHoldersAndCharacterSets() {
		Map<Character, CharacterSet> formatPlaceHolders = new HashMap<>();
		formatPlaceHolders.put('A', letters(LetterCase.UPPER));
		formatPlaceHolders.put('a', letters(LetterCase.LOWER));
		formatPlaceHolders.put('V', vowels(LetterCase.UPPER));
		formatPlaceHolders.put('v', vowels(LetterCase.LOWER));
		formatPlaceHolders.put('C', consonants(LetterCase.UPPER));
		formatPlaceHolders.put('c', consonants(LetterCase.LOWER));
		formatPlaceHolders.put('!', symbols());
		formatPlaceHolders.put('#', numbers());
		return formatPlaceHolders;
	}

	public String toCharacterString() {
		StringBuilder characters = new StringBuilder();
		for (Character character : this) {
			characters.append(character);
		}
		return characters.toString();
	}

	public Character[] toCharacterArray() {
		Character[] characterArray = new Character[size()];
		return new ArrayList<Character>(this).toArray(characterArray);
	}

}
