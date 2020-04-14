package nth.reflect.fw.layer5provider.stringconverter.java.other;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class StringStringConverterTest extends StringConverterTest {

	private final static String STRING = "String";
	private StringStringConverter stringStringConverter;

	@Before
	public void setUp() throws Exception {
		StringConverterFactoryInfo createInfo = createInfo(FullFeatureDomainObject.GET_MY_TEXT);
		stringStringConverter = new StringStringConverter(createInfo);
	}

	@Test
	public void testToString_givenNull_mustReturnEmpty() {
		String result = stringStringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenString_mustReturnString() {
		String result = stringStringConverter.toString(STRING);
		assertThat(result).isEqualTo(STRING);
	}

	@Test
	public void testFromString_givenNull_mustReturnEmpty() {
		String result = stringStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenString_mustReturnString() {
		String result = stringStringConverter.fromString(STRING);
		assertThat(result).isEqualTo(STRING);
	}

}
