package nth.reflect.fw.layer5provider.url.servicemethod;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.actionmethod.result.handler.ResultHandlerSerice;

public class ServiceMethodUrlTest {

	private static final String METHOD_NAME = ResultHandlerSerice.DOMAIN_OBJECT_RETURN_VALUE;
	private static final Class<ResultHandlerSerice> SERVICE_CLASS = ResultHandlerSerice.class;
	private static final String SERVICE_CLASS_CANONICAL_NAME = ResultHandlerSerice.class.getCanonicalName();
	private static final String EXPECTED = ServiceMethodUrl.PROTOCOL + ":" + SERVICE_CLASS_CANONICAL_NAME + "."
			+ METHOD_NAME;
	private ServiceMethodUrl correctServiceMethodUrl;

	@Before
	public void setUp() throws Exception {
		new ReflectApplicationForJUnit().createContainer();
		correctServiceMethodUrl = new ServiceMethodUrl(SERVICE_CLASS, METHOD_NAME);
	}

	@Test
	public void testServiceMethodUrl_givenClassNameAndMethodName_resultsInCorrectUrl() throws MalformedURLException {
		String serviceMethodUrl = new ServiceMethodUrl(SERVICE_CLASS_CANONICAL_NAME, METHOD_NAME).toString();
		assertThat(serviceMethodUrl.toString()).isEqualTo(EXPECTED);
	}

	@Test
	public void testServiceMethodUrl_givenServiceMethodUrlString_resultsInCorrectUrl() throws MalformedURLException {
		String serviceMethodUrl = new ServiceMethodUrl(EXPECTED).toString();
		assertThat(serviceMethodUrl.toString()).isEqualTo(EXPECTED);
	}

	@Test
	public void testServiceMethodUrl_givenCorrectServiceMethodUrl_resultsInCorrectUrl() throws MalformedURLException {
		String serviceMethodUrl = new ServiceMethodUrl(correctServiceMethodUrl.toInternalURL()).toString();
		assertThat(serviceMethodUrl.toString()).isEqualTo(EXPECTED);
	}

	@Test
	public void testServiceMethodUrl_givenServiceMethodUrlStringWithInvalidProtocol_throwsException() {
		String invalidServiceMethodProtocol = "http:";
		String invalidServiceMethodUrl = invalidServiceMethodProtocol + ":" + SERVICE_CLASS_CANONICAL_NAME + "."
				+ METHOD_NAME;
		assertThatThrownBy(() -> {
			new ServiceMethodUrl(invalidServiceMethodUrl);
		}).isInstanceOf(MalformedURLException.class).hasMessage("The protocol must be: reflect-service-method");
	}

	@Test
	public void testServiceMethodUrl_givenServiceMethodUrlStringWithoutClassName_throwsException() {
		String invalidServiceMethodUrl = ServiceMethodUrl.PROTOCOL + ":" + METHOD_NAME;
		assertThatThrownBy(() -> {
			new ServiceMethodUrl(invalidServiceMethodUrl);
		})
				.isInstanceOf(MalformedURLException.class)
				.hasMessage(
						"No canonical service class name found. URL should be: reflect-service-method:<serviceClassCanonicalName>.<actionMethodName>");
	}

	@Test
	public void testServiceMethodUrl_givenServiceMethodUrlStringWithInvalidClassName_throwsException() {
		String invalidServiceMethodUrl = ServiceMethodUrl.PROTOCOL + ":" + "InvalidClassName" + "." + METHOD_NAME;
		assertThatThrownBy(() -> {
			new ServiceMethodUrl(invalidServiceMethodUrl);
		}).isInstanceOf(RuntimeException.class).hasMessage("Service class: InvalidClassName could not be found.");
	}

	@Test
	public void testServiceMethodUrl_givenServiceMethodUrlStringWithoutDot_throwsException() {
		String invalidServiceMethodUrl = ServiceMethodUrl.PROTOCOL + ":" + SERVICE_CLASS_CANONICAL_NAME;
		assertThatThrownBy(() -> {
			new ServiceMethodUrl(invalidServiceMethodUrl);
		})
				.isInstanceOf(RuntimeException.class)
				.hasMessage(
						"Service class: nth.reflect.fw.layer5provider.actionmethod.result.handler could not be found.");
	}

	@Test
	public void testServiceMethodUrl_givenServiceMethodUrlStringWithoutMethodName_throwsException() {
		String invalidServiceMethodUrl = ServiceMethodUrl.PROTOCOL + ":" + SERVICE_CLASS_CANONICAL_NAME + ".";
		assertThatThrownBy(() -> {
			new ServiceMethodUrl(invalidServiceMethodUrl);
		})
				.isInstanceOf(MalformedURLException.class)
				.hasMessage(
						"No method name could be found. URL should be: reflect-service-method:<serviceClassCanonicalName>.<actionMethodName>");
	}

	@Test
	public void testGetCanonicalServiceClassName() {
		assertThat(correctServiceMethodUrl.getServiceClassCanonicalName()).isEqualTo(SERVICE_CLASS_CANONICAL_NAME);
	}

	@Test
	public void testGetActionMethodName() {
		assertThat(correctServiceMethodUrl.getActionMethodName()).isEqualTo(METHOD_NAME);
	}

	@Test
	public void testGetServiceClass() {
		assertThat(correctServiceMethodUrl.getServiceClass()).isEqualTo(SERVICE_CLASS);
	}

}
