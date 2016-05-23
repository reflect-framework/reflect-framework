package nth.introspect.junit.layer5provider.reflection.behavior.icon.foractionmethod;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.junit.IntrospectApplicationForJUnit;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;

public class IconModelForActionMethodTest {
	private static final String ICON_PNG = "icon.png";
	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {
			// FIXME: test will fail
			// @Override
			// public Class<? extends PathProvider> getPathProviderClass() {
			// return IconModelForActionMethodPathProvider.class;
			// }

		};
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider = container.get(ReflectionProvider.class);
	}

	@Test
	public void actionMethodIconDefaultUrl() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconDefaultUrl");
		URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertEquals(getUrl("iconModelForActionMethodTestObject_actionMethodIconDefaultUrl.png"),
				iconUrl);
	}

	@Test
	public void actionMethodIconDefaultUrlNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconDefaultUrlNotExist");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	// TODO: {@link IntrospectApplicationForJUnit} should not depend on
	// reflect-graphical-user-interface
	// @Test
	// public void actionMethodIconAnnotationClassReferenceUrl() throws
	// ÙRLSyntaxException {
	// IconModelForActionMethodTestObject obj = new
	// IconModelForActionMethodTestObject();
	// ActionMethodInfo actionMethodInfo =
	// getActionMethodInfo(obj.getClass(),"actionMethodIconAnnotationClassReferenceUrl");
	// ÙRL iconUrl = actionMethodInfo.getIconÙRL(obj);
	// assertEquals(getUrl(IntrospectImage.class, EDIT_COPY_PNG), iconUrl);
	// }

	@Test
	public void actionMethodIconAnnotationClassReferenceUrlNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconAnnotationClassReferenceUrlNotExist");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	@Test
	public void actionMethodIconAnnotationRelativeUrl() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconAnnotationRelativeUrl");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertEquals(getUrl(ICON_PNG), iconUrl);
	}

	@Test
	public void actionMethodIconAnnotationRelativeUrlNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconAnnotationRelativeUrlNotExist");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	@Test
	public void actionMethodIconAnnotationAbsoluteUrl() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconAnnotationAbsoluteUrl");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertEquals(IconModelForActionMethodTestObject.ABSOLUTE_ÙRL_TO_ICON, iconUrl.toString());
	}

	@Test
	public void actionMethodIconAnnotationAbsoluteUrlNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconAnnotationAbsoluteUrlNotExist");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertNull(iconUrl);
	}
	// TODO: {@link IntrospectApplicationForJUnit} should not depend on
	// reflect-graphical-user-interface
	// @Test
	// public void actionMethodIconMethodClassReferenceUrl() throws
	// ÙRLSyntaxException {
	// IconModelForActionMethodTestObject obj = new
	// IconModelForActionMethodTestObject();
	// ActionMethodInfo actionMethodInfo =
	// getActionMethodInfo(obj.getClass(),"actionMethodIconMethodClassReferenceUrl");
	// ÙRL iconUrl = actionMethodInfo.getIconÙRL(obj);
	// assertEquals(getUrl(IntrospectImage.class, EDIT_COPY_PNG), iconUrl);
	// }

	@Test
	public void actionMethodIconMethodClassReferenceUrlNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconMethodClassReferenceUrlNotExist");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	@Test
	public void actionMethodIconMethodRelativeUrl() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconMethodRelativeUrl");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertEquals(getUrl(ICON_PNG), iconUrl);
	}

	@Test
	public void actionMethodIconMethodRelativeUrlNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconMethodRelativeUrlNotExist");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	@Test
	public void actionMethodIconMethodAbsoluteUrl() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconMethodAbsoluteUrl");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertEquals(IconModelForActionMethodTestObject.ABSOLUTE_ÙRL_TO_ICON, iconUrl.toString());
	}

	@Test
	public void actionMethodIconMethodAbsoluteUrlNotExist() {
		IconModelForActionMethodTestObject obj = new IconModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = getActionMethodInfo(obj.getClass(),
				"actionMethodIconMethodAbsoluteUrlNotExist");
		java.net.URL iconUrl = actionMethodInfo.getIconURL(obj);
		assertNull(iconUrl);
	}

	private java.net.URL getUrl(String resourceName) {
		return getUrl(getClass(), resourceName);
	}

	private java.net.URL getUrl(Class<?> ownerClass, String resourceName) {
		return ownerClass.getResource(resourceName);
	}

	private ActionMethodInfo getActionMethodInfo(Class<?> objectClass, String methodName) {
		ClassInfo classInfo = reflectionProvider.getClassInfo(objectClass);
		return classInfo.getActionMethodInfo(methodName);
	}

}
