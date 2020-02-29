package nth.reflect.fw.layer5provider.reflection.info.property;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.text.Format;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.StringFormat;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.stubs.DomainObjectStub;

public class PropertyInfoTest {

	private static final String PROPERTY_VALUE = "123";
	private static final String PROPERTY_NAME = DomainObjectStub.PROPERTY1;
	private static final Class<DomainObjectStub> DOMAIN_OBJECT_TYPE = DomainObjectStub.class;
	private DomainObjectStub domainObject;
	private PropertyInfo propertyInfo;

	@Before
	public void setup() throws InstantiationException, IllegalAccessException {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		domainObject = DOMAIN_OBJECT_TYPE.newInstance();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		DomainClassInfo classInfo = reflectionProvider.getDomainClassInfo(DOMAIN_OBJECT_TYPE);
		propertyInfo = classInfo.getPropertyInfo(PROPERTY_NAME);
	}

	@Test
	public void testGetTypeInfo() {
		assertThat(propertyInfo.getTypeInfo().getType()).isEqualTo(String.class);
	}

	@Test
	public void testGetSimpleName() {
		assertThat(propertyInfo.getSimpleName()).isEqualTo(PROPERTY_NAME);
	}

	@Test
	public void testGetCanonicalName() {
		assertThat(propertyInfo.getCanonicalName())
				.isEqualTo(DOMAIN_OBJECT_TYPE.getCanonicalName() + "." + PROPERTY_NAME);
	}

	@Test
	public void testGetGetterMethod() {
		assertThat(propertyInfo.getGetterMethod().getName())
				.isEqualTo("get" + StringUtil.firstCharToUpperCase(PROPERTY_NAME));
	}

	@Test
	public void testGetSetterMethod() {
		assertThat(propertyInfo.getSetterMethod().getName())
				.isEqualTo("set" + StringUtil.firstCharToUpperCase(PROPERTY_NAME));
	}

	@Test
	public void testGetDisplayName() {
		assertThat(propertyInfo.getDisplayName()).isEqualTo(StringUtil.firstCharToUpperCase(PROPERTY_NAME));
	}

	@Test
	public void testGetDescription() {
		assertThat(propertyInfo.getDescription()).isEqualTo(DomainObjectStub.PROPERTY1_DESCRIPTION);
	}

	@Test
	public void testGetOrder() {
		assertThat(propertyInfo.getOrder()).isEqualTo(DomainObjectStub.PROPERTY1_ORDER);
	}

	@Test
	public void testIsVisibleInForm() {
		assertThat(propertyInfo.isVisibleInForm(domainObject)).isEqualTo(false);
	}

	@Test
	public void testIsVisibleInTable() {
		assertThat(propertyInfo.isVisibleInTable()).isEqualTo(false);
	}

	@Test
	public void testIsEnabled() {
		assertThat(propertyInfo.isEnabled(domainObject)).isEqualTo(true);
	}

	@Test
	public void testSetAndGetValue() {
		propertyInfo.setValue(domainObject, PROPERTY_VALUE);
		assertThat(propertyInfo.getValue(domainObject)).isEqualTo(PROPERTY_VALUE);
	}

	@Test
	public void testToString() {
		assertThat(propertyInfo.toString()).isEqualTo(DOMAIN_OBJECT_TYPE.getCanonicalName() + "." + PROPERTY_NAME);
	}

	@Test
	public void testIsReadOnly() {
		assertThat(propertyInfo.isReadOnly()).isEqualTo(false);
	}

	@Test
	public void testGetFormatPattern() {
		assertThat(propertyInfo.getFormatPattern()).isEqualTo(null);
	}

	@Test
	public void testGetFormat() {
		Format format = propertyInfo.getFormat().get();
		assertThat(format).isInstanceOf(StringFormat.class);
	}

	@Test
	public void testGetFormatedValue() {
		domainObject.setProperty1(PROPERTY_VALUE);
		assertThat(propertyInfo.getFormatedValue(domainObject)).isEqualTo(PROPERTY_VALUE);
	}

	@Test
	public void testIsGetterMethod() {
		Method getterMethod = propertyInfo.getGetterMethod();
		assertThat(PropertyInfo.isGetterMethod(getterMethod)).isTrue();
		Method setterMethod = propertyInfo.getSetterMethod();
		assertThat(PropertyInfo.isGetterMethod(setterMethod)).isFalse();
	}

	@Test
	public void testGetActionMethodInfos() {
		List<ActionMethodInfo> actionMethodInfos = propertyInfo.getActionMethodInfos();
		assertThat(actionMethodInfos).hasSize(1);
	}

	@Test
	public void testHasOptions() {
		assertThat(propertyInfo.hasOptions()).isEqualTo(false);
	}

	@Test
	public void testGetOptions() {
		assertThat(propertyInfo.getOptions(domainObject)).isEmpty();
	}

}
