package nth.reflect.fw.junit.layer5provider.reflection.behavior.icon.forclass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

public class IconModelForClassTest {

	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit() {
//FIXME: test will fail
//			@Override
//			public Class<? extends PathProvider> getPathProviderClass() {
//				return IconModelForClassPathProvider.class;
//			}

		};
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider = container.get(ReflectionProvider.class);
	}

	@Test
	public void classIconDefaultUri() throws URISyntaxException {
		IconModelForClass_Default obj = new IconModelForClass_Default();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertEquals(getUri("iconModelForClass_Default.png"), iconUrl);
	}

	@Test
	public void classIconDefaultUriNotExist() {
		IconModelForClass_DefaultNotExist obj = new IconModelForClass_DefaultNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	//TODO: {@link ReflectApplicationForJUnit} should not depend on reflect-graphical-user-interface
//	@Test
//	public void classIconAnnotationClassReferenceUri()
//			throws URISyntaxException {
//		IconModelForClass_AnnotationClassReferenceUri obj = new IconModelForClass_AnnotationClassReferenceUri();
//		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
//		URI iconUri = classInfo.getIconURI(obj);
//		assertEquals(getUri(ReflectImage.class, "edit_copy.png"), iconUri);
//	}

	@Test
	public void classIconAnnotationClassReferenceUriNotExist() {
		IconModelForClass_AnnotationClassReferenceUriNotExist obj = new IconModelForClass_AnnotationClassReferenceUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	@Test
	public void classIconAnnotationRelativeUri() throws URISyntaxException {
		IconModelForClass_AnnotationRelativeUri obj = new IconModelForClass_AnnotationRelativeUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertEquals(getUri("icon.png"), iconUrl);
	}

	@Test
	public void classIconAnnotationRelativeUriNotExist() {
		IconModelForClass_AnnotationRelativeUriNotExist obj = new IconModelForClass_AnnotationRelativeUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	@Test
	public void classIconAnnotationAbsoluteUri() throws URISyntaxException {
		IconModelForClass_AnnotationAbsoluteUri obj = new IconModelForClass_AnnotationAbsoluteUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertEquals(IconModelForClass_MethodAbsoluteUri.ABSOLUTE_URI_TO_ICON, iconUrl.toString());
	}

	@Test
	public void classIconAnnotationAbsoluteUriNotExist() {
		IconModelForClass_AnnotationAbsoluteUriNotExist obj = new IconModelForClass_AnnotationAbsoluteUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	@Test
	public void classIconMethodClassReferenceUri() throws URISyntaxException {
		IconModelForClass_MethodClassReferenceUri obj = new IconModelForClass_MethodClassReferenceUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertEquals(getUri("icon.png"), iconUrl);
	}

	@Test
	public void classIconMethodClassReferenceUriNotExist() {
		IconModelForClass_MethodClassReferenceUriNotExist obj = new IconModelForClass_MethodClassReferenceUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	@Test
	public void classIconMethodRelativeUri() throws URISyntaxException {
		IconModelForClass_MethodRelativeUri obj = new IconModelForClass_MethodRelativeUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertEquals(getUri("icon.png"), iconUrl);
	}

	@Test
	public void classIconMethodRelativeUriNotExist() {
		IconModelForClass_MethodRelativeUriNotExist obj = new IconModelForClass_MethodRelativeUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	@Test
	public void classIconMethodAbsoluteUri() throws URISyntaxException {
		IconModelForClass_MethodAbsoluteUri obj = new IconModelForClass_MethodAbsoluteUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertEquals(IconModelForClass_MethodAbsoluteUri.ABSOLUTE_URI_TO_ICON, iconUrl.toString());
	}

	@Test
	public void classIconMethodAbsoluteUriNotExist() {
		IconModelForClass_MethodAbsoluteUriNotExist obj = new IconModelForClass_MethodAbsoluteUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URL iconUrl = classInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	private URI getUri(String resourceName) throws URISyntaxException {
		return getUri(getClass(), resourceName);
	}

	private URI getUri(Class<?> ownerClass, String resourceName)
			throws URISyntaxException {
		return ownerClass.getResource(resourceName).toURI();
	}

}
