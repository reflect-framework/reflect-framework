package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;

public class ApplicationClassInfoTest {

	private ApplicationClassInfo applicationClassInfo;
	private Class<?> applicationClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		applicationClassInfo = reflectionProvider.getApplicationClassInfo();
		applicationClass = applicationClassInfo.getType();
	}

	@Test
	public void testGetSimpleName() {
		assertThat(applicationClassInfo.getSimpleName()).isEqualTo(applicationClass.getSimpleName());
	}

	@Test
	public void testGetCanonicalName() {
		assertThat(applicationClassInfo.getCanonicalName()).isEqualTo(applicationClass.getCanonicalName());
	}

	@Test
	public void testGetObjectClass() {
		assertThat(applicationClassInfo.getType()).isEqualTo(applicationClass);
	}

	@Test
	public void testGetDisplayName() {
		String expected = StringUtil.convertToNormalCase(applicationClass.getSimpleName());
		TranslatedString displayName = applicationClassInfo.getDisplayName();
		String translation = displayName.getTranslation();
		assertThat(translation).isEqualTo(expected);
	}

	@Test
	public void testGetDescription() {
		assertThat(applicationClassInfo.getDescription().toString()).isEqualTo("Reflect application for jUnit");
	}

	@Test
	public void testGetIconURI() {
		assertThat(applicationClassInfo.getIcon()).isNull();
	}

	@Test
	public void testToString() {
		assertThat(applicationClassInfo.toString()).isEqualTo(applicationClass.getCanonicalName());
	}

}
