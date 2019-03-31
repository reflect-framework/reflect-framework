package nth.reflect.fw.junit.layer5provider.reflection.info.classinfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;

public class ApplicationClassInfoTest {

	private ApplicationClassInfo applicationClassInfo;
	private Class<?> applicationClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		applicationClassInfo = reflectionProvider.getApplicationClassInfo();
		applicationClass = applicationClassInfo.getObjectClass();
	}

	@Test
	public void testGetSimpleName() {
		assertEquals(applicationClass.getSimpleName(), applicationClassInfo.getSimpleName());
	}

	@Test
	public void testGetCanonicalName() {
		assertEquals(applicationClass.getCanonicalName(), applicationClassInfo.getCanonicalName());
	}

	@Test
	public void testGetObjectClass() {
		assertEquals(applicationClass, applicationClassInfo.getObjectClass());
	}

	@Test
	public void testGetDisplayName() {
		assertEquals(StringUtil.convertToNormalCase(applicationClass.getSimpleName()),
				applicationClassInfo.getDisplayName());
	}

	@Test
	public void testGetDescription() {
		assertEquals("Reflect application for jUnit", applicationClassInfo.getDescription());
	}

	@Test
	public void testGetIconURI() {
		assertNull(applicationClassInfo.getIcon());
	}

	@Test
	public void testToString() {
		assertEquals(applicationClass.getCanonicalName(), applicationClassInfo.toString());
	}

}
