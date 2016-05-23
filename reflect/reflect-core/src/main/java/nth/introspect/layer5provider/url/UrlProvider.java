package nth.introspect.layer5provider.url;

import java.net.URL;
import java.net.URLStreamHandler;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer5provider.Provider;

/**
 * {@link ReflectUrl}'s needs to have a {@link UrlProvider} that need to
 * be registered with the {@link IntrospectApplication#getUrlProviderClasses()}
 * so that they can be registered with {@link URL#setURLStreamHandlerFactory()}
 * (See {@link ReflectUrlStreamHandlerFactory}).
 */

public abstract class UrlProvider extends URLStreamHandler implements Provider {

	public abstract String getProtocol();
}
