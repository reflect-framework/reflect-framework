package nth.reflect.fw.layer5provider.stringconverter.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;

public class DomainObjectStringConverterTest extends StringConverterTest {

	private DomainObjectStringConverter stringConverter;

	@Before
	public void setUp() throws Exception {
		TypeInfo info = createTypeInfo(AllFeatureDomainObject.GET_MY_DOMAIN_OBJECT);
		stringConverter = new DomainObjectStringConverter(getReflectionProvider(), info);
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = stringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenDomainObject_mustReturnTitle() {
		AllFeatureDomainObject domainObject = new AllFeatureDomainObject();
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
