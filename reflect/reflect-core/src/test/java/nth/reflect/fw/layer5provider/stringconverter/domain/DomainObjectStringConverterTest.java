package nth.reflect.fw.layer5provider.stringconverter.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class DomainObjectStringConverterTest extends StringConverterTest {

	private DomainObjectStringConverter stringConverter;

	@Before
	public void setUp() throws Exception {
		StringConverterFactoryInfo info = createInfo(FullFeatureDomainObject.GET_MY_DOMAIN_OBJECT);
		stringConverter = new DomainObjectStringConverter(info);
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = stringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenDomainObject_mustReturnTitle() {
		FullFeatureDomainObject domainObject = new FullFeatureDomainObject();
		String result = stringConverter.toString(domainObject);
		assertThat(result).isEqualTo(domainObject.toString());
	}

	@Test
	public void testFromStringString() {
		assertThrows(MethodNotSupportedException.class, () -> {
			stringConverter.fromString(null);
		});
	}

}
