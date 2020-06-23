package nth.reflect.fw.layer5provider.actionmethod.result.handler;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.JunitActionMethodPreHandler;
import nth.reflect.fw.junit.LogProvider;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UrlResultHandler;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class UrlResultHandlerTest {

	private static final Class<ResultHandlerSerice> SERVICE_CLASS = ResultHandlerSerice.class;
	protected static final String EXECUTED = "Executed";
	private UserInterfaceContainer container;
	private UrlResultHandler resultHandler;
	private ReflectionProvider reflectionProvider;
	private ProviderContainer providerContainer;
	private LogProvider log;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(SERVICE_CLASS).createContainer();
		reflectionProvider = container.get(ReflectionProvider.class);
		providerContainer = container.get(ProviderContainer.class);
		log = container.get(LogProvider.class);
		resultHandler = createUrlResultHandler();
	}

	private UrlResultHandler createUrlResultHandler() {
		return new UrlResultHandler() {
			@Override
			public void openUrl(UserInterfaceContainer container, URL url) {
				log.add(EXECUTED);
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
		log.clear();
		resultHandler.process(container, actionMethodInfo, serviceObject, methodParameter, methodResult);
		assertThat(log).contains(EXECUTED);
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
		log.clear();
		resultHandler.process(container, actionMethodInfo, serviceObject, methodParameter, methodResult);
		String expected = JunitActionMethodPreHandler.class.getSimpleName() + ".preProcess("
				+ ResultHandlerSerice.class.getCanonicalName() + ".domainObjectReturnValue, "
				+ ResultHandlerSerice.class.getCanonicalName() + ", null)";
		assertThat(log.get(0)).startsWith(expected);
	}

}
