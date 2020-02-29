package nth.reflect.fw.layer5provider.stringconverter;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

public class StringConverterProviderTest {

	private DependencyInjectionContainer container;
	private StringConverterProvider stringConverterProvider;
	private ReflectApplicationForJUnit application;
	private List<StringConverterFactoryInfo> infosToTest;

	@Before
	public void setUp() throws Exception {
		application = new ReflectApplicationForJUnit();
		container = application.createContainer();
		stringConverterProvider = container.get(StringConverterProvider.class);
		infosToTest = new ArrayList();
//		infosToTest.add(createInfo(DomainObject.GET_MY_String));
//		infosToTest.add(createInfo(DomainObject.GET_MY_boolean));
//		infosToTest.add(createInfo(DomainObject.GET_MY_Boolean));
//		infosToTest.add(createInfo(DomainObject.GET_MY_char));
		infosToTest.add(createInfo(DomainObject.GET_MY_PRIMITIVE_BYTE));
		infosToTest.add(createInfo(DomainObject.GET_MY_BYTE));
//		infosToTest.add(createInfo(DomainObject.GET_MY_short));
//		infosToTest.add(createInfo(DomainObject.GET_MY_Short));
//		infosToTest.add(createInfo(DomainObject.GET_MY_int));
//		infosToTest.add(createInfo(DomainObject.GET_MY_Integer));
//		infosToTest.add(createInfo(DomainObject.GET_MY_long));
//		infosToTest.add(createInfo(DomainObject.GET_MY_Long));
//		infosToTest.add(createInfo(DomainObject.GET_MY_double));
//		infosToTest.add(createInfo(DomainObject.GET_MY_Double));
//		infosToTest.add(createInfo(DomainObject.GET_MY_float));
//		infosToTest.add(createInfo(DomainObject.GET_MY_Float));
//		infosToTest.add(createInfo(DomainObject.GET_MY_BigInteger));
//		infosToTest.add(createInfo(DomainObject.GET_MY_BigDecimal));
//		infosToTest.add(createInfo(DomainObject.GET_MY_AtomicLong));
//		infosToTest.add(createInfo(DomainObject.GET_MY_AtomicInteger));
// AND MORE!
	}

	private StringConverterFactoryInfo createInfo(String domainObjectGetterMethod) {
		Method method = findMethod(domainObjectGetterMethod);
		ReturnTypeInfo typeInfo = new ReturnTypeInfo(application, method);
		StringConverterFactoryInfo stringConverterFactoryInfo = new StringConverterFactoryInfo(typeInfo, container,
				null);
		return stringConverterFactoryInfo;
	}

	private Method findMethod(String domainObjectGetterMethod) {
		Method[] allMethods = DomainObject.class.getDeclaredMethods();
		for (Method method : allMethods) {
			if (method.getName().equals(domainObjectGetterMethod)) {
				return method;
			}
		}
		throw new RuntimeException(
				"Could not find method " + domainObjectGetterMethod + " in " + DomainObject.class.getCanonicalName());
	}

	@Test
	public void testCanCreate() {
		assertThat(infosToTest).allSatisfy((info) -> {
			assertThat(stringConverterProvider.canCreate(info)).isTrue();
		});
	}

	@Test
	public void testCreate() {
		assertThat(infosToTest).allSatisfy((info) -> {
			assertThat(stringConverterProvider.create(info).get()).isNotNull();
		});
	}


}
