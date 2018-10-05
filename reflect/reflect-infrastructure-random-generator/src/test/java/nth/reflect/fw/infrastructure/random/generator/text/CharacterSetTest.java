package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.text.CharacterSet;
import nth.reflect.fw.infrastructure.random.generator.text.LetterCase;

public class CharacterSetTest {

	private static final String TEST1 = "abCDe12@#";
	private static final String TEST2 = "(&zxyWVu89";

	private Character[] charactersOf(String string) {
		Character[] newArray = new Character[string.length()];
		for (int index = 0; index < string.length(); index++) {
			newArray[index] = string.charAt(index);
		}
		return newArray;
	}

	@Test
	public void testFromStringString() {
		CharacterSet characterSet = CharacterSet.fromString(TEST1);
		assertThat(characterSet, hasItems(charactersOf(TEST1)));
	}

	@Test
	public void testWithStringString() {
		CharacterSet characterSet = new CharacterSet(TEST1).withString(TEST2);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + TEST2)));
	}

	@Test
	public void testFromStringStringLetterCase() {
		CharacterSet characterSet = CharacterSet.fromString(TEST1, LetterCase.LOWER);
		assertThat(characterSet, hasItems(charactersOf(TEST1.toLowerCase())));

		characterSet = CharacterSet.fromString(TEST1, LetterCase.UPPER);
		assertThat(characterSet, hasItems(charactersOf(TEST1.toUpperCase())));

		characterSet = CharacterSet.fromString(TEST1, LetterCase.UPPER_AND_LOWER);
		assertThat(characterSet, hasItems(charactersOf(TEST1.toLowerCase() + TEST1.toUpperCase())));
	}

	@Test
	public void testWithStringStringLetterCase() {
		CharacterSet characterSet = CharacterSet.fromString(TEST1).withString(TEST2, LetterCase.LOWER);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + TEST2.toLowerCase())));

		characterSet = CharacterSet.fromString(TEST1).withString(TEST2, LetterCase.UPPER);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + TEST2.toUpperCase())));

		characterSet = CharacterSet.fromString(TEST1).withString(TEST2, LetterCase.UPPER_AND_LOWER);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + TEST2.toLowerCase() + TEST2.toUpperCase())));
	}

	@Test
	public void testLetters() {
		CharacterSet characterSet = CharacterSet.letters(LetterCase.LOWER);
		assertThat(characterSet, hasItems(charactersOf(CharacterSet.LETTERS.toLowerCase())));

		characterSet = CharacterSet.letters(LetterCase.UPPER);
		assertThat(characterSet, hasItems(charactersOf(CharacterSet.LETTERS.toUpperCase())));

		characterSet = CharacterSet.letters(LetterCase.UPPER_AND_LOWER);
		assertThat(characterSet,
				hasItems(charactersOf(CharacterSet.LETTERS.toUpperCase() + CharacterSet.LETTERS.toLowerCase())));
	}

	@Test
	public void testWithLetters() {
		CharacterSet characterSet = CharacterSet.fromString(TEST1).withLetters(LetterCase.LOWER);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + CharacterSet.LETTERS.toLowerCase())));

		characterSet = CharacterSet.fromString(TEST1).withLetters(LetterCase.UPPER);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + CharacterSet.LETTERS.toUpperCase())));

		characterSet = CharacterSet.fromString(TEST1).withLetters(LetterCase.UPPER_AND_LOWER);
		assertThat(characterSet, hasItems(
				charactersOf(TEST1 + CharacterSet.LETTERS.toUpperCase() + CharacterSet.LETTERS.toLowerCase())));
	}

	@Test
	public void testVowels() {
		CharacterSet characterSet = CharacterSet.vowels(LetterCase.LOWER);
		assertThat(characterSet, hasItems(charactersOf(CharacterSet.VOWELS.toLowerCase())));

		characterSet = CharacterSet.vowels(LetterCase.UPPER);
		assertThat(characterSet, hasItems(charactersOf(CharacterSet.VOWELS.toUpperCase())));

		characterSet = CharacterSet.vowels(LetterCase.UPPER_AND_LOWER);
		assertThat(characterSet,
				hasItems(charactersOf(CharacterSet.VOWELS.toUpperCase() + CharacterSet.VOWELS.toLowerCase())));
	}

	@Test
	public void testWithVowels() {
		CharacterSet characterSet = CharacterSet.fromString(TEST1).withVowels(LetterCase.LOWER);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + CharacterSet.VOWELS.toLowerCase())));

		characterSet = CharacterSet.fromString(TEST1).withVowels(LetterCase.UPPER);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + CharacterSet.VOWELS.toUpperCase())));

		characterSet = CharacterSet.fromString(TEST1).withVowels(LetterCase.UPPER_AND_LOWER);
		assertThat(characterSet,
				hasItems(charactersOf(TEST1 + CharacterSet.VOWELS.toUpperCase() + CharacterSet.VOWELS.toLowerCase())));
	}

	@Test
	public void testConsonants() {
		CharacterSet characterSet = CharacterSet.consonants(LetterCase.LOWER);
		assertThat(characterSet, hasItems(charactersOf(CharacterSet.CONSONANTS.toLowerCase())));

		characterSet = CharacterSet.consonants(LetterCase.UPPER);
		assertThat(characterSet, hasItems(charactersOf(CharacterSet.CONSONANTS.toUpperCase())));

		characterSet = CharacterSet.consonants(LetterCase.UPPER_AND_LOWER);
		assertThat(characterSet,
				hasItems(charactersOf(CharacterSet.CONSONANTS.toUpperCase() + CharacterSet.CONSONANTS.toLowerCase())));
	}

	@Test
	public void testWithConsonants() {
		CharacterSet characterSet = CharacterSet.fromString(TEST1).withConsonants(LetterCase.LOWER);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + CharacterSet.CONSONANTS.toLowerCase())));

		characterSet = CharacterSet.fromString(TEST1).withConsonants(LetterCase.UPPER);
		assertThat(characterSet, hasItems(charactersOf(TEST1 + CharacterSet.CONSONANTS.toUpperCase())));

		characterSet = CharacterSet.fromString(TEST1).withConsonants(LetterCase.UPPER_AND_LOWER);
		assertThat(characterSet, hasItems(
				charactersOf(TEST1 + CharacterSet.CONSONANTS.toUpperCase() + CharacterSet.CONSONANTS.toLowerCase())));
	}

	@Test
	public void testSymbols() {
		CharacterSet characterSet = CharacterSet.symbols();
		assertThat(characterSet, hasItems(charactersOf(CharacterSet.SYMBOLS)));
	}

	@Test
	public void testWithSymbols() {
		CharacterSet characterSet = CharacterSet.fromString(TEST1).withSymbols();
		assertThat(characterSet, hasItems(charactersOf(TEST1 + CharacterSet.SYMBOLS)));
	}

	@Test
	public void testNumbers() {
		CharacterSet characterSet = CharacterSet.numbers();
		assertThat(characterSet, hasItems(charactersOf(CharacterSet.NUMBERS)));
	}

	@Test
	public void testWithNumbers() {
		CharacterSet characterSet = CharacterSet.fromString(TEST1).withNumbers();
		assertThat(characterSet, hasItems(charactersOf(TEST1 + CharacterSet.NUMBERS)));
	}

	@Test
	public void testCommon() {
		CharacterSet characterSet = CharacterSet.common();
		assertThat(characterSet, hasItems(charactersOf(CharacterSet.LETTERS+CharacterSet.LETTERS.toUpperCase()+CharacterSet.SYMBOLS+CharacterSet.NUMBERS)));
	}
	
	@Test
	public void testFormatPlaceHolders() {
		Map<Character, CharacterSet> actual = CharacterSet.placeHoldersAndCharacterSets();
		
		Map<Character, CharacterSet> expected=new HashMap<>();
		expected.put('A', CharacterSet.letters(LetterCase.UPPER));
		expected.put('a', CharacterSet.letters(LetterCase.LOWER));
		expected.put('V', CharacterSet.vowels(LetterCase.UPPER));
		expected.put('v', CharacterSet.vowels(LetterCase.LOWER));
		expected.put('C', CharacterSet.consonants(LetterCase.UPPER));
		expected.put('c', CharacterSet.consonants(LetterCase.LOWER));
		expected.put('!', CharacterSet.symbols());
		expected.put('0', CharacterSet.numbers());
		expected.put('9', CharacterSet.numbers());
		
		assertThat(actual, is(expected));
	}

	
	
}
