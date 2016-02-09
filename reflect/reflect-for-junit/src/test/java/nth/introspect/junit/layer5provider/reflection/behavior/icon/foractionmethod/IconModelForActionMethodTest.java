package nth.introspect.junit.layer5provider.reflection.behavior.icon.foractionmethod;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;
import java.net.URISyntaxException;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

public class IconModelForActionMethodTest {
	private static final String EDIT_COPY_PNG = "edit_copy.png";
	private static final String ICON_PNG = "icon.png";
	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {

			@Override
			public Class<? extends PathProvider> getPathProviderClass() {
				return IconModelForActionMethodPathProvider.class;
			}

		};
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider = container.get(ReflectionProvider.class);
	}

	@Test
	public void actionMethodIconDefaultUri() throws URISyntaxException {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconDefaultUri");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertEquals(getUri("iconModelForActionMethodTestObject_actionMethodIconDefaultUri.png"), iconUri);
	}

	@Test
	public void actionMethodIconDefaultUriNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconDefaultUriNotExist");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	// TODO: {@link IntrospectApplicationForJUnit} should not depend on reflect-graphical-user-interface
//	 @Test
//	public void actionMethodIconAnnotationClassReferenceUri() throws URISyntaxException {
//		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
//		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconAnnotationClassReferenceUri");
//		URI iconUri = actionMethodInfo.getIconURI(obj);
//		assertEquals(getUri(IntrospectImage.class, EDIT_COPY_PNG), iconUri);
//	}

	@Test
	public void actionMethodIconAnnotationClassReferenceUriNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconAnnotationClassReferenceUriNotExist");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void actionMethodIconAnnotationRelativeUri() throws URISyntaxException {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconAnnotationRelativeUri");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertEquals(getUri(ICON_PNG), iconUri);
	}

	@Test
	public void actionMethodIconAnnotationRelativeUriNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconAnnotationRelativeUriNotExist");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void actionMethodIconAnnotationAbsoluteUri() throws URISyntaxException {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconAnnotationAbsoluteUri");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertEquals(IconModelForActionMethodTestObject.ABSOLUTE_URI_TO_ICON, iconUri.toString());
	}

	@Test
	public void actionMethodIconAnnotationAbsoluteUriNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconAnnotationAbsoluteUriNotExist");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertNull(iconUri);
	}
	//TODO: {@link IntrospectApplicationForJUnit} should not depend on reflect-graphical-user-interface
//	@Test
//	public void actionMethodIconMethodClassReferenceUri() throws URISyntaxException {
//		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
//		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconMethodClassReferenceUri");
//		URI iconUri = actionMethodInfo.getIconURI(obj);
//		assertEquals(getUri(IntrospectImage.class, EDIT_COPY_PNG), iconUri);
//	}

	@Test
	public void actionMethodIconMethodClassReferenceUriNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconMethodClassReferenceUriNotExist");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void actionMethodIconMethodRelativeUri() throws URISyntaxException {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconMethodRelativeUri");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertEquals(getUri(ICON_PNG), iconUri);
	}

	@Test
	public void actionMethodIconMethodRelativeUriNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconMethodRelativeUriNotExist");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	@Test
	public void actionMethodIconMethodAbsoluteUri() throws URISyntaxException {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconMethodAbsoluteUri");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertEquals(IconModelForActionMethodTestObject.ABSOLUTE_URI_TO_ICON, iconUri.toString());
	}

	@Test
	public void actionMethodIconMethodAbsoluteUriNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),"actionMethodIconMethodAbsoluteUriNotExist");
		URI iconUri = actionMethodInfo.getIconURI(obj);
		assertNull(iconUri);
	}

	private URI getUri(String resourceName) throws URISyntaxException {
		return getUri(getClass(), resourceName);
	}

	private URI getUri(Class<?> ownerClass, String resourceName)
			throws URISyntaxException {
		return ownerClass.getResource(resourceName).toURI();
	}

	private ActionMethodInfo getActionMethodInfo(
			Class<?> objectClass, String methodName) {
		ClassInfo classInfo=reflectionProvider.getClassInfo(objectClass);
		return classInfo.getActionMethodInfo(methodName);
	}

	
}
