package nth.reflect.fw.layer5provider.stringconverter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

/**
 * Abstract concinience class to test {@link StringConverterFactory}s, using
 * {@link StringConverterFactoryInfoMap}.
 * 
 * @author nilsth
 *
 */
public abstract class StringConverterFactoryTest {

	private StringConverterFactory stringConverterFactory;
	private StringConverterFactoryInfoMap stringConverterFactoryInfoMap;

	@Before
	public void setUp() throws Exception {
		stringConverterFactory = getStringConverterFactory();
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		stringConverterFactoryInfoMap = new StringConverterFactoryInfoMap(container);
	}

	protected abstract StringConverterFactory getStringConverterFactory();

	@Test
	public void testCanCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info, stringConverterType) -> {
			boolean sameConverterFactoryAndConverter = stringConverterFactory.getClass().getName()
					.startsWith(stringConverterType.getName());
			boolean result = stringConverterFactory.canCreate(info);
			assertThat(result).isEqualTo(sameConverterFactoryAndConverter);
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
