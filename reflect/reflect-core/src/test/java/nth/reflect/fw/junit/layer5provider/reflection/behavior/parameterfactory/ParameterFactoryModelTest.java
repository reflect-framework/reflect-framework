package nth.reflect.fw.junit.layer5provider.reflection.behavior.parameterfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

public class ParameterFactoryModelTest {

	private ParameterFactoryModelTestObject obj;
	private ReflectionProvider reflectionProvider;
	private DomainClassInfo domainClassInfo;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider = container.get(ReflectionProvider.class);
		domainClassInfo = reflectionProvider.getDomainClassInfo(ParameterFactoryModelTestObject.class);
		obj = new ParameterFactoryModelTestObject();
	}

	@Test
	public void actionMethodWithParameterFactoryMethod() throws InstantiationException, IllegalAccessException {
		ActionMethodInfo actionMethodInfo = domainClassInfo.getActionMethodInfo("actionMethod1");
		Object parameter = actionMethodInfo.createMethodParameter(obj);
		assertNotNull(parameter);
		assertEquals(parameter.getClass(), ParameterTestObject.class);
	}

	@Test
	public void actionMethodWithParameterFactoryAnnotation() throws InstantiationException, IllegalAccessException {
		ActionMethodInfo actionMethodInfo = domainClassInfo.getActionMethodInfo("actionMethod2");
		Object parameter = actionMethodInfo.createMethodParameter(obj);
		assertNotNull(parameter);
		assertEquals(parameter.getClass(), ParameterTestObject.class);
	}

}
