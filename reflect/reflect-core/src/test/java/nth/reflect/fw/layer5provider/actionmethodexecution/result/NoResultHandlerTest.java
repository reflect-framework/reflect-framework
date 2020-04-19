package nth.reflect.fw.layer5provider.actionmethodexecution.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.UserInterfaceControllerForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class NoResultHandlerTest {

	private static final Class<ResultHandlerSerice> SERVICE_CLASS = ResultHandlerSerice.class;
	private DependencyInjectionContainer container;
	private NoResultHandler resultHandler;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(SERVICE_CLASS).createContainer();
		resultHandler = new NoResultHandler();
	}

	@Test
	public void testCanProcess_givenMethodWithoutReturnValue_isTrue() {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo.getActionMethodInfo(ResultHandlerSerice.NO_RETURN_VALUE);
		boolean actual = resultHandler.canProcess(actionMethodInfo);
		assertThat(actual).isTrue();
	}

	@Test
	public void testCanProcess_givenMethodWithReturnValue_isFalse() {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo
				.getActionMethodInfo(ResultHandlerSerice.DOMAIN_OBJECT_RETURN_VALUE);
		boolean actual = resultHandler.canProcess(actionMethodInfo);
		assertThat(actual).isFalse();
	}

	@Test
	public void testExecutesActionMethod_givenNoResultHanlder_showsMessage() {
		UserInterfaceControllerForJUnit userInterface = container.get(UserInterfaceControllerForJUnit.class);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ResultHandlerSerice serviceObject = container.get(SERVICE_CLASS);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo.getActionMethodInfo(ResultHandlerSerice.NO_RETURN_VALUE);
		Object methodParameter = null;
		Object methodResult = null;
		resultHandler.process(userInterface, serviceObject, actionMethodInfo, methodParameter, methodResult);
		assertThat(userInterface.getEvents()).contains("showMessage(No return value was successfully executed.)");

	}

}
