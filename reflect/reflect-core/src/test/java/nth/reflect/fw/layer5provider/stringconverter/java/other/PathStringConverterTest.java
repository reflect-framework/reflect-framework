package nth.reflect.fw.layer5provider.stringconverter.java.other;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class PathStringConverterTest extends StringConverterTest {

	private final static Path PATH_VALUE = Paths.get("c:/test.txt");
	private final static String PATH_STRING = PATH_VALUE.toString();
	private PathStringConverter stringConverter;

	@Before
	public void setUp() throws Exception {
		StringConverterFactoryInfo info = createInfo(DomainObject.GET_MY_PATH);
		stringConverter = new PathStringConverter(info);
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = stringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenPathValue_mustReturnPathString() {
		String result = stringConverter.toString(PATH_VALUE);
		assertThat(result).isEqualTo(PATH_STRING);
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		Path result = stringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptySyring_mustReturnNull() {
		Path result = stringConverter.fromString(EMPTY);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenPathString_mustReturnPath() {
		Path result = stringConverter.fromString(PATH_STRING);
		assertThat(result).isEqualTo(PATH_VALUE);
	}

}
