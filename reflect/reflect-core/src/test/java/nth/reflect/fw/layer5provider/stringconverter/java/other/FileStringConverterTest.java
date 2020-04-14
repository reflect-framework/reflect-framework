package nth.reflect.fw.layer5provider.stringconverter.java.other;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class FileStringConverterTest extends StringConverterTest {

	private final static File FILE_VALUE = new File("c:/test.txt");
	private final static String FILE_STRING = FILE_VALUE.toString();
	private FileStringConverter stringConverter;

	@Before
	public void setUp() throws Exception {
		StringConverterFactoryInfo info = createInfo(FullFeatureDomainObject.GET_MY_FILE);
		stringConverter = new FileStringConverter(info);
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = stringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenFileValue_mustReturnFileString() {
		String result = stringConverter.toString(FILE_VALUE);
		assertThat(result).isEqualTo(FILE_STRING);
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		File result = stringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptySyring_mustReturnNull() {
		File result = stringConverter.fromString(EMPTY);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenFileString_mustReturnFile() {
		File result = stringConverter.fromString(FILE_STRING);
		assertThat(result).isEqualTo(FILE_VALUE);
	}

}
