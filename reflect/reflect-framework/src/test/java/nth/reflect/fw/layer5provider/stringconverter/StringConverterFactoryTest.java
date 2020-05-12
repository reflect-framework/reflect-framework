package nth.reflect.fw.layer5provider.stringconverter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

/**
 * Abstract convenience class to test {@link StringConverterFactory}s, using
 * {@link StringConverterFactoryInfoMap}.
 * 
 * @author nilsth
 *
 */
public abstract class StringConverterFactoryTest {

	private static final String NO_FORMAT = null;
	private StringConverterFactory stringConverterFactory;
	private StringConverterFactoryInfoMap stringConverterFactoryInfoMap;
	private ReflectionProvider reflectionProvider;
	private LanguageProvider languageProvider;
	private DependencyInjectionContainer container;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().createContainer();
		stringConverterFactoryInfoMap = new StringConverterFactoryInfoMap(container);
		stringConverterFactory = getStringConverterFactory();
	}

	public ReflectionProvider getReflectionProvider() {
		if (reflectionProvider == null) {
			reflectionProvider = container.get(ReflectionProvider.class);
		}
		return reflectionProvider;
	}

	public LanguageProvider getLanguageProvider() {
		if (languageProvider == null) {
			languageProvider = container.get(LanguageProvider.class);
		}
		return languageProvider;
	}

	protected abstract StringConverterFactory getStringConverterFactory();

	@Test
	public void testCanCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info, stringConverterType) -> {
			boolean sameConverterFactoryAndConverter = stringConverterFactory
					.getClass()
					.getName()
					.startsWith(stringConverterType.getName());
			boolean result = stringConverterFactory.canCreate(info);
			assertThat(result).isEqualTo(sameConverterFactoryAndConverter);
		});

	}

	@Test
	public void testCreate() {
		assertThat(stringConverterFactoryInfoMap).allSatisfy((info, stringConverterType) -> {
			boolean sameConverterFactoryAndConverter = stringConverterFactory
					.getClass()
					.getName()
					.startsWith(stringConverterType.getName());
			if (sameConverterFactoryAndConverter) {
				assertThat(stringConverterFactory.create(info, NO_FORMAT).getClass()).isEqualTo(stringConverterType);
			}
		});
	}

}
