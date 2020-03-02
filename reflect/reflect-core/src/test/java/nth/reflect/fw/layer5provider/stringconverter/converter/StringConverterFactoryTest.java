package nth.reflect.fw.layer5provider.stringconverter.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterFactoryInfoMap;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;

public abstract class StringConverterFactoryTest {

	private DependencyInjectionContainer container;
	private StringConverterFactory stringConverterFactory;
	private StringConverterFactoryInfoMap stringConverterFactoryInfoMap;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().createContainer();
		stringConverterFactory = getStringConverterFactory();
		stringConverterFactoryInfoMap = new StringConverterFactoryInfoMap(container);
	}

	protected abstract StringConverterFactory getStringConverterFactory();

	@Test
	public void testCanCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info, stringConverterType) -> {
			boolean sameConverterFactoryAndConverter = stringConverterFactory.getClass().getName()
					.startsWith(stringConverterType.getName());
			assertThat(stringConverterFactory.canCreate(info)).isEqualTo(sameConverterFactoryAndConverter);
		});

	}

	@Test
	public void testCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info, stringConverterType) -> {
			boolean sameConverterFactoryAndConverter = stringConverterFactory.getClass().getName()
					.startsWith(stringConverterType.getName());
			if (sameConverterFactoryAndConverter) {
				assertThat(stringConverterFactory.create(info).getClass()).isEqualTo(stringConverterType);
			}
		});
	}

}
