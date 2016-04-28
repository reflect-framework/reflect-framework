package nth.introspect.layer5provider.path.url;

import java.net.URL;
import java.net.URLStreamHandlerFactory;

import nth.introspect.IntrospectApplication;
import nth.introspect.IntrospectFramework;

/**
 * A {@link ReflectUrl} is a special type of URL that is only supported by the
 * {@link IntrospectFramework}. {@link ReflectUrl}'s needs to have a {@link ReflectUrlConnection} that need to be registered with
 * the {@link IntrospectApplication#getReflectUrlHandlers()} so that they can be
 * registered with {@link URL#setURLStreamHandlerFactory()}. {@link ReflectUrl}
 * 's are an adapter for an URL, since a {@link URL} can not be overridden.
 * 
 * 
 * 
 * @author nilsth
 *
 */

public interface ReflectUrl {

	public URL toURL();

}
