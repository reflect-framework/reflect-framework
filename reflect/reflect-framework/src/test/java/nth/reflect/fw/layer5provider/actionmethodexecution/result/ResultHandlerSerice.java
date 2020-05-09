package nth.reflect.fw.layer5provider.actionmethodexecution.result;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.url.servicemethod.ServiceMethodUrl;

public class ResultHandlerSerice {

	public static final String NO_RETURN_VALUE = "noReturnValue";

	public void noReturnValue() {

	}

	public static final String DOMAIN_OBJECT_RETURN_VALUE = "domainObjectReturnValue";

	public AllFeatureDomainObject domainObjectReturnValue() {
		return new AllFeatureDomainObject();
	}

	public static final String URI_RETURN_VALUE = "openUri";

	public URI openUri() throws URISyntaxException {
		return new URI("mailto:johndoe@email.com");
	}

	public static final String URL_RETURN_VALUE = "openUrl";

	public URL openUrl() throws MalformedURLException {
		return new URL("https://www.linkedin.com/pub/nils-ten-hoeve/a/4b4/915");
	}

	public static final String SERVICE_METHOD_URL_RETURN_VALUE = "openServiceMethodUri";

	public URI openServiceMethodUri() throws URISyntaxException, MalformedURLException {
		ServiceMethodUrl serviceMethodUrl = new ServiceMethodUrl(ResultHandlerSerice.class, DOMAIN_OBJECT_RETURN_VALUE);
		return serviceMethodUrl.toInternalURL().toURI();
	}

	public static final String SERVICE_METHOD_URI_RETURN_VALUE = "openServiceMethodUrl";

	public URL openServiceMethodUrl() throws MalformedURLException, URISyntaxException {
		ServiceMethodUrl serviceMethodUrl = new ServiceMethodUrl(ResultHandlerSerice.class, DOMAIN_OBJECT_RETURN_VALUE);
		return serviceMethodUrl.toInternalURL();
	}

}
