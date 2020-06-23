package nth.reflect.fw.layer5provider.url.servicemethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Optional;

import nth.reflect.fw.layer2service.ServiceObjectActionMethod;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UriResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.url.ReflectUrl;

/**
 * <p>
 * A {@link ServiceMethodUrl} is a {@link ReflectUrl} for a reference to a
 * {@link ServiceObjectActionMethod}.
 * </p>
 * <p>
 * The format of a {@link ServiceMethodUrl} is:
 * reflect-service-method:&lt;canonical service name&gt;.&lt;action method
 * name&gt;
 * </p>
 * <p>
 * E.g.: reflect-service-method:com.acme.web.shop.ProductService.findProduct
 * </p>
 * 
 * <p>
 * A {@link ActionMethod} defined in a {@link ServiceMethodUrl} will be
 * processed by a {@link UriResultHandler} when the {@link ServiceMethodUrl} is
 * returned by a {@link ActionMethod}
 * </p>
 * 
 * @author nilsth
 *
 */
public class ServiceMethodUrl implements ReflectUrl {

	public static final String PROTOCOL = "reflect-service-method";
	private final URL serviceMethodUrl;
	private String serviceClassCanonicalName;
	private String actionMethodName;
	private Class<?> serviceClass;

	public ServiceMethodUrl(Class<?> serviceClass, String methodName) throws MalformedURLException {
		this(serviceClass.getCanonicalName(), methodName);
	}

	public ServiceMethodUrl(String serviceClassCanonicalName, String actionMethodName) throws MalformedURLException {
		this(PROTOCOL + ":" + serviceClassCanonicalName + "." + actionMethodName);
	}

	public ServiceMethodUrl(String serviceMethodUrl) throws MalformedURLException {
		this(new URL(serviceMethodUrl));
	}

	public ServiceMethodUrl(URL serviceMethodUrl) throws MalformedURLException {
		this.serviceMethodUrl = serviceMethodUrl;
		verify();
	}

	private void verify() throws MalformedURLException {
		if (!PROTOCOL.equals(serviceMethodUrl.getProtocol())) {
			throw new MalformedURLException("The protocol must be: " + PROTOCOL);
		}

		String uriString = serviceMethodUrl.toString();
		int positionColon = uriString.indexOf(":");
		int positionLastDot = uriString.lastIndexOf(".");
		if (positionColon < 0 || positionLastDot < 0 || positionColon >= positionLastDot
				|| positionLastDot == uriString.length()) {
			throw new MalformedURLException("No canonical service class name found. URL should be: " + PROTOCOL
					+ ":<serviceClassCanonicalName>.<actionMethodName>");
		}
		serviceClassCanonicalName = uriString.substring(positionColon + 1, positionLastDot);

		try {
			serviceClass = Class.forName(serviceClassCanonicalName);
		} catch (Exception e) {
			throw new RuntimeException("Service class: " + serviceClassCanonicalName + " could not be found.");
		}

		actionMethodName = uriString.substring(positionLastDot + 1);
		if (actionMethodName == null || actionMethodName.trim().length() == 0) {
			throw new MalformedURLException("No method name could be found. URL should be: " + PROTOCOL
					+ ":<serviceClassCanonicalName>.<actionMethodName>");
		}
	}

	@Override
	public URL toInternalURL() {
		return serviceMethodUrl;
	}

	public String getServiceClassCanonicalName() {
		return serviceClassCanonicalName;
	}

	public String getActionMethodName() {
		return actionMethodName;
	}

	public Class<?> getServiceClass() {
		return serviceClass;
	}

	public static Optional<ServiceMethodUrl> createFor(URL url) {
		try {
			ServiceMethodUrl serviceMethodUrl = new ServiceMethodUrl(url);
			return Optional.of(serviceMethodUrl);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public static Optional<ServiceMethodUrl> createFor(URI uri) {
		try {
			URL url = uri.toURL();
			return createFor(url);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public String toString() {
		return toInternalURL().toString();
	}

}
