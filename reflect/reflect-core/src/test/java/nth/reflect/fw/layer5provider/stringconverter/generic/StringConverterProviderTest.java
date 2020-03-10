package nth.reflect.fw.layer5provider.stringconverter.generic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;

public class StringConverterProviderTest {

	private DependencyInjectionContainer container;
	private StringConverterProvider stringConverterProvider;
	private ReflectApplicationForJUnit application;
	private StringConverterFactoryInfoMap stringConverterFactoryInfoMap;

	@Before
	public void setUp() throws Exception {
		application = new ReflectApplicationForJUnit();
		container = application.createContainer();
		stringConverterProvider = container.get(StringConverterProvider.class);
		stringConverterFactoryInfoMap = new StringConverterFactoryInfoMap(container);
	}

	
	@Test
	public void testCanCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info,stringConverterType) -> {
			assertThat(stringConverterProvider.canCreate(info)).isTrue();
		});
	}

	@Test
	public void testCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info,stringConverterType) -> {
			assertThat(stringConverterProvider.create(info).getClass()).isEqualTo(stringConverterType);
		});
	}


}
