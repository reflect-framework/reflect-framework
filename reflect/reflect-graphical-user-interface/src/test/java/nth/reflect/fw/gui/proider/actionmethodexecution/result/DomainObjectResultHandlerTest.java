package nth.reflect.fw.gui.proider.actionmethodexecution.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class DomainObjectResultHandlerTest {

	private static final Class<ResultHandlerSerice> SERVICE_CLASS = ResultHandlerSerice.class;
	private DependencyInjectionContainer container;
	private DomainObjectResultHandler resultHandler;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(SERVICE_CLASS).createContainer();
		resultHandler = new DomainObjectResultHandler();
	}

	@Test
	public void testCanProcess_givenMethodWithDomainObjectReturnValue_isTrue() {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo
				.getActionMethodInfo(ResultHandlerSerice.DOMAIN_OBJECT_RETURN_VALUE);
		boolean actual = resultHandler.canProcess(actionMethodInfo);
		assertThat(actual).isTrue();
	}

	@Test
	public void testCanProcess_givenMethodWithoutReturnValue_isFalse() {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo.getActionMethodInfo(ResultHandlerSerice.NO_RETURN_VALUE);
		boolean actual = resultHandler.canProcess(actionMethodInfo);
		assertThat(actual).isFalse();
	}

}
