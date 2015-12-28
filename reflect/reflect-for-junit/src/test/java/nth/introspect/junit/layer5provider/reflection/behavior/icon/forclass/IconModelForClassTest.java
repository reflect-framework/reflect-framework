package nth.introspect.junit.layer5provider.reflection.behavior.icon.forclass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;
import java.net.URISyntaxException;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.images.IntrospectImage;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

public class IconModelForClassTest {

	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {

			@Override
			public Class<? extends PathProvider> getPathProviderClass() {
				return IconModelForClassPathProvider.class;
			}

		};
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider = container.get(ReflectionProvider.class);
	}

	@Test
	public void classIconDefaultUri() throws URISyntaxException {
		IconModelForClass_Default obj = new IconModelForClass_Default();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertEquals(getUri("iconModelForClass_Default.png"), iconUri);
	}

	@Test
	public void classIconDefaultUriNotExist() {
		IconModelForClass_DefaultNotExist obj = new IconModelForClass_DefaultNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void classIconAnnotationClassReferenceUri()
			throws URISyntaxException {
		IconModelForClass_AnnotationClassReferenceUri obj = new IconModelForClass_AnnotationClassReferenceUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertEquals(getUri(IntrospectImage.class, "edit_copy.png"), iconUri);
	}

	@Test
	public void classIconAnnotationClassReferenceUriNotExist() {
		IconModelForClass_AnnotationClassReferenceUriNotExist obj = new IconModelForClass_AnnotationClassReferenceUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void classIconAnnotationRelativeUri() throws URISyntaxException {
		IconModelForClass_AnnotationRelativeUri obj = new IconModelForClass_AnnotationRelativeUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertEquals(getUri("icon.png"), iconUri);
	}

	@Test
	public void classIconAnnotationRelativeUriNotExist() {
		IconModelForClass_AnnotationRelativeUriNotExist obj = new IconModelForClass_AnnotationRelativeUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void classIconAnnotationAbsoluteUri() throws URISyntaxException {
		IconModelForClass_AnnotationAbsoluteUri obj = new IconModelForClass_AnnotationAbsoluteUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertEquals(IconModelForClass_MethodAbsoluteUri.ABSOLUTE_URI_TO_ICON, iconUri.toString());
	}

	@Test
	public void classIconAnnotationAbsoluteUriNotExist() {
		IconModelForClass_AnnotationAbsoluteUriNotExist obj = new IconModelForClass_AnnotationAbsoluteUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void classIconMethodClassReferenceUri() throws URISyntaxException {
		IconModelForClass_MethodClassReferenceUri obj = new IconModelForClass_MethodClassReferenceUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertEquals(getUri("icon.png"), iconUri);
	}

	@Test
	public void classIconMethodClassReferenceUriNotExist() {
		IconModelForClass_MethodClassReferenceUriNotExist obj = new IconModelForClass_MethodClassReferenceUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void classIconMethodRelativeUri() throws URISyntaxException {
		IconModelForClass_MethodRelativeUri obj = new IconModelForClass_MethodRelativeUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertEquals(getUri("icon.png"), iconUri);
	}

	@Test
	public void classIconMethodRelativeUriNotExist() {
		IconModelForClass_MethodRelativeUriNotExist obj = new IconModelForClass_MethodRelativeUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void classIconMethodAbsoluteUri() throws URISyntaxException {
		IconModelForClass_MethodAbsoluteUri obj = new IconModelForClass_MethodAbsoluteUri();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertEquals(IconModelForClass_MethodAbsoluteUri.ABSOLUTE_URI_TO_ICON, iconUri.toString());
	}

	@Test
	public void classIconMethodAbsoluteUriNotExist() {
		IconModelForClass_MethodAbsoluteUriNotExist obj = new IconModelForClass_MethodAbsoluteUriNotExist();
		ClassInfo classInfo = reflectionProvider.getClassInfo(obj.getClass());
		URI iconUri = classInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	private URI getUri(String resourceName) throws URISyntaxException {
		return getUri(getClass(), resourceName);
	}

	private URI getUri(Class<?> ownerClass, String resourceName)
			throws URISyntaxException {
		return ownerClass.getResource(resourceName).toURI();
	}

}
