package nth.reflect.fw.layer5provider.stringconverter.java.other;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class CharacterStringConverterTest extends StringConverterTest {

	private static final char PRIMITIVE_CHAR = 'a';
	private static final Character CHARACTER = new Character(PRIMITIVE_CHAR);
	private static final String CHARACTER_STRING = CHARACTER.toString();
	private CharacterStringConverter characterStringConverter;

	@Before
	public void setUp() throws Exception {
		characterStringConverter = new CharacterStringConverter();
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = characterStringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenCharacter_mustReturnCharacterString() {
		String result = characterStringConverter.toString(CHARACTER);
		assertThat(result).isEqualTo(CHARACTER_STRING);
	}

	@Test
	public void testToString_givenPrimitiveChar_mustReturnCharacterString() {
		String result = characterStringConverter.toString(PRIMITIVE_CHAR);
		assertThat(result).isEqualTo(CHARACTER_STRING);
	}

	@Test
	public void testFromString_givenNull_MustReturnNull() {
		Character result = characterStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenCharacter_MustReturnCharacter() {
		Character result = characterStringConverter.fromString(CHARACTER_STRING);
		assertThat(result).isEqualTo(PRIMITIVE_CHAR);
	}

	@Test
	public void testFromString_givenString_MustThrowException() {
		assertThrows(StringConverterException.class, () -> {
			characterStringConverter.fromString(CHARACTER_STRING + CHARACTER_STRING);
		});
	}

	@Test
	public void testFromString_givenEmptyString_MustThrowException() {
		assertThrows(StringConverterException.class, () -> {
			characterStringConverter.fromString(EMPTY);
		});
	}

}
