package nth.reflect.fw.layer5provider.stringconverter.java.other;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

public class UriStringConverterTest {

	private final static String URI_STRING = "mailto:John.Doe@example.com";
	private static URI URI_VALUE;
	private UriStringConverter uriStringConverter;

	@Before
	public void setUp() throws Exception {
		uriStringConverter = new UriStringConverter(null, null);
		URI_VALUE = new URI(URI_STRING);
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = uriStringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenUriValue_mustReturnUriString() {
		String result = uriStringConverter.toString(URI_VALUE);
		assertThat(result).isEqualTo(URI_STRING);
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		URI result = uriStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenString_mustReturnString() {
		URI result = uriStringConverter.fromString(URI_STRING);
		assertThat(result).isEqualTo(URI_VALUE);
	}

}
