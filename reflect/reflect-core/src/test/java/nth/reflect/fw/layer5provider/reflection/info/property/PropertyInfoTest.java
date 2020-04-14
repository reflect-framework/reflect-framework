package nth.reflect.fw.layer5provider.reflection.info.property;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.DateStringConverter;

public class PropertyInfoTest {

	private static final String PROPERTY_ACTION_METHOD = FullFeatureDomainObject.GET_MY_ANNOTATED_DATE_TODAY;
	private static final Date PROPERTY_VALUE = new Date();
	private static final String PROPERTY_NAME = FullFeatureDomainObject.GET_MY_ANNOTATED_DATE.replace("getM", "m");
	private static final Class<FullFeatureDomainObject> DOMAIN_OBJECT_TYPE = FullFeatureDomainObject.class;
	private FullFeatureDomainObject domainObject;
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
		Class<?> actual = propertyInfo.getTypeInfo().getType();
		Class<? extends Date> expected = PROPERTY_VALUE.getClass();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetSimpleName() {
		String actual = propertyInfo.getSimpleName();
		assertThat(actual).isEqualTo(PROPERTY_NAME);
	}

	@Test
	public void testGetCanonicalName() {
		String actual = propertyInfo.getCanonicalName();
		String expected = DOMAIN_OBJECT_TYPE.getCanonicalName() + "." + PROPERTY_NAME;
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetGetterMethod() {
		String actual = propertyInfo.getGetterMethod().getName();
		String expected = "get" + StringUtil.firstCharToUpperCase(PROPERTY_NAME);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetSetterMethod() {
		String actual = propertyInfo.getSetterMethod().getName();
		String expected = "set" + StringUtil.firstCharToUpperCase(PROPERTY_NAME);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetDisplayName() {
		String actual = propertyInfo.getDisplayName().getTranslation();
		String expected = StringUtil.convertToNormalCase(PROPERTY_NAME);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetDescription() {
		String actual = propertyInfo.getDescription().toString();
		String expected = StringUtil.convertToNormalCase(PROPERTY_NAME);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetOrder() {
		double order = propertyInfo.getOrder();
		double expected = FullFeatureDomainObject.GET_MY_ANNOTATED_DATE_ORDER;
		assertThat(order).isEqualTo(expected);
	}

	@Test
	public void testIsVisibleInForm() {
		Boolean actual = propertyInfo.isVisibleInForm(domainObject);
		assertThat(actual).isFalse();
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
		Object actual = propertyInfo.getValue(domainObject);
		assertThat(actual).isEqualTo(PROPERTY_VALUE);
	}

	@Test
	public void testToString() {
		String actual = propertyInfo.toString();
		String expected = DOMAIN_OBJECT_TYPE.getCanonicalName() + "." + PROPERTY_NAME;
		assertThat(actual).isEqualTo(expected);
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
	public void testGetStringConverter() {
		StringConverter stringConverter = propertyInfo.getStringConverter().get();
		assertThat(stringConverter).isInstanceOf(DateStringConverter.class);
	}

	@Test
	public void testGetFormatedValue() {
		domainObject.setMyAnnotatedDate(PROPERTY_VALUE);
		String actual = propertyInfo.getStringValue(domainObject);
		String expected = new SimpleDateFormat().format(PROPERTY_VALUE);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testIsGetterMethod() {
		Method getterMethod = propertyInfo.getGetterMethod();
		assertThat(PropertyInfo.isGetterMethod(getterMethod)).isTrue();
		Method setterMethod = propertyInfo.getSetterMethod();
		assertThat(PropertyInfo.isGetterMethod(setterMethod)).isFalse();
	}

	@Test
	public void testGetActionMethodInfos_givenDomainObjectStub_returns1() {
		List<ActionMethodInfo> actionMethodInfos = propertyInfo.getActionMethodInfos();
		assertThat(actionMethodInfos).hasSize(1);
	}

	@Test
	public void testGetActionMethodInfosFirstCanonicalName_givenDomainObjectStub_returnsPath() {
		List<ActionMethodInfo> actionMethodInfos = propertyInfo.getActionMethodInfos();
		ActionMethodInfo firstActionMethodInfo = actionMethodInfos.get(0);
		String actual = firstActionMethodInfo.getCanonicalName();
		String expected = propertyInfo.getCanonicalName() + PROPERTY_ACTION_METHOD.replace(PROPERTY_NAME, "");
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetActionMethodInfosFirstSimpleName_givenDomainObjectStub_returnsSimpleName() {
		List<ActionMethodInfo> actionMethodInfos = propertyInfo.getActionMethodInfos();
		ActionMethodInfo firstActionMethodInfo = actionMethodInfos.get(0);
		String expected = StringUtil.firstCharToLowerCase(PROPERTY_ACTION_METHOD.replace(PROPERTY_NAME, ""));
		String actual = firstActionMethodInfo.getSimpleName();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetActionMethodInfosFirstDisplayName_givenDomainObjectStub_returnsDisplayName() {
		List<ActionMethodInfo> actionMethodInfos = propertyInfo.getActionMethodInfos();
		ActionMethodInfo firstActionMethodInfo = actionMethodInfos.get(0);
		String expected = StringUtil.convertToNormalCase(PROPERTY_ACTION_METHOD.replace(PROPERTY_NAME, ""));
		assertThat(firstActionMethodInfo.getDisplayName().getTranslation()).isEqualTo(expected);
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
