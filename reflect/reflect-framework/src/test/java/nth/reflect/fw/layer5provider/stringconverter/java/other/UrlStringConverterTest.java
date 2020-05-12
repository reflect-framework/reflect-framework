package nth.reflect.fw.layer5provider.stringconverter.java.other;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class UrlStringConverterTest extends StringConverterTest {

	private final static String URL_STRING = "https://google.com/";
	private static URL URL_VALUE;
	private UrlStringConverter urlStringConverter;

	@Before
	public void setUp() throws Exception {
		urlStringConverter = new UrlStringConverter();
		URL_VALUE = new URL(URL_STRING);
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = urlStringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenUrlValue_mustReturnUrlString() {
		String result = urlStringConverter.toString(URL_VALUE);
		assertThat(result).isEqualTo(URL_STRING);
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		URL result = urlStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptyString_mustReturnNull() {
		URL result = urlStringConverter.fromString(EMPTY);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenBogusString_mustReturnUrl() {
		assertThrows(StringConverterException.class, () -> {
			urlStringConverter.fromString(BOGUS);
		});
	}

	@Test
	public void testFromString_givenUrlString_mustReturnUrl() {
		URL result = urlStringConverter.fromString(URL_STRING);
		assertThat(result).isEqualTo(URL_VALUE);
	}

}
