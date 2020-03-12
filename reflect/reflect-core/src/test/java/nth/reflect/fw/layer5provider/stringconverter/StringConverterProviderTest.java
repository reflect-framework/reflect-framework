package nth.reflect.fw.layer5provider.stringconverter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;

public class StringConverterProviderTest {

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
			assertThat(stringConverterProvider.canCreate(info)).isTrue();
		});
	}

	@Test
	public void testCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info, stringConverterType) -> {
			assertThat(stringConverterProvider.create(info).getClass()).isEqualTo(stringConverterType);
		});
	}

}
