package nth.reflect.fw.layer5provider.actionmethodexecution.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.UserInterfaceControllerForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class UrlResultHandlerTest {

	private static final Class<ResultHandlerSerice> SERVICE_CLASS = ResultHandlerSerice.class;
	protected static final String EXECUTED = "Executed";
	private UserInterfaceContainer container;
	private UrlResultHandler resultHandler;
	private ReflectionProvider reflectionProvider;
	private UserInterfaceControllerForJUnit userInterface;
	private ProviderContainer providerContainer;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(SERVICE_CLASS).createContainer();
		userInterface = container.get(UserInterfaceControllerForJUnit.class);
		reflectionProvider = container.get(ReflectionProvider.class);
		providerContainer = container.get(ProviderContainer.class);
		resultHandler = new UrlResultHandler() {
			@Override
			public void openUrl(URL url) {
				userInterface.getEvents().add(EXECUTED);
			}
		};
	}

	@Test
	public void testCanProcess_givenMethodWithUrl_isTrue() {
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo.getActionMethodInfo(ResultHandlerSerice.URL_RETURN_VALUE);
		boolean actual = resultHandler.canProcess(providerContainer, actionMethodInfo);
		assertThat(actual).isTrue();
	}

	@Test
	public void testCanProcess_givenMethodWithUri_isFalse() {
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo.getActionMethodInfo(ResultHandlerSerice.URI_RETURN_VALUE);
		boolean actual = resultHandler.canProcess(providerContainer, actionMethodInfo);
		assertThat(actual).isFalse();
	}

	@Test
	public void testExecutesActionMethod_givenUrlResultHandler_opensUrl() throws MalformedURLException {
		ResultHandlerSerice serviceObject = container.get(SERVICE_CLASS);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo.getActionMethodInfo(ResultHandlerSerice.URL_RETURN_VALUE);
		Object methodParameter = null;
		Object methodResult = new ResultHandlerSerice().openUrl();
		resultHandler.process(container, serviceObject, actionMethodInfo, methodParameter, methodResult);
		List<String> events = userInterface.getEventsAndClear();
		assertThat(events).contains(EXECUTED);
	}

	@Test
	public void testExecutesActionMethod_givenUrlResultHandler_WithServiceMethodUrl_opensServiceMethod()
			throws MalformedURLException, URISyntaxException {
		ResultHandlerSerice serviceObject = container.get(SERVICE_CLASS);
		ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(SERVICE_CLASS);
		ActionMethodInfo actionMethodInfo = serviceClassInfo
				.getActionMethodInfo(ResultHandlerSerice.SERVICE_METHOD_URL_RETURN_VALUE);
		Object methodParameter = null;
		Object methodResult = new ResultHandlerSerice().openServiceMethodUrl();
		resultHandler.process(container, serviceObject, actionMethodInfo, methodParameter, methodResult);
		List<String> events = userInterface.getEventsAndClear();
		String expected = "processActionMethod(nth.reflect.fw.layer5provider.actionmethodexecution.result.ResultHandlerSerice";
		assertThat(events.get(0)).startsWith(expected);
	}

}
