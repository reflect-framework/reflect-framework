package nth.reflect.fw.layer5provider.stringconverter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class StringConverterProviderTest {

	private static final String NO_FORMAT = null;
	private StringConverterProvider stringConverterProvider;
	private StringConverterFactoryInfoMap stringConverterFactoryInfoMap;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		stringConverterProvider = container.get(StringConverterProvider.class);
		stringConverterFactoryInfoMap = new StringConverterFactoryInfoMap(container);
	}

	@Test
	public void testCanCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info, stringConverterType) -> {
			boolean canCreate = stringConverterProvider.canCreate(info);
			assertThat(canCreate).isTrue();
		});
	}

	@Test
	public void testCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info, stringConverterType) -> {
			Class<? extends StringConverter> stringConverterClass = stringConverterProvider
					.create(info, NO_FORMAT)
					.getClass();
			assertThat(stringConverterClass).isEqualTo(stringConverterType);
		});
	}

}
