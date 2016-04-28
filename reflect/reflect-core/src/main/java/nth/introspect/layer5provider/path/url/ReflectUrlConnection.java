package nth.introspect.layer5provider.path.url;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import nth.introspect.IntrospectApplication;

/**
 * {@link ReflectUrl}'s needs to have a {@link ReflectUrlConnection} that need to
 * be registered with the {@link IntrospectApplication#getReflectUrlHandlers()}
 * so that they can be registered with {@link URL#setURLStreamHandlerFactory()}.
 */

public abstract class ReflectUrlConnection extends URLStreamHandler {

	public abstract String getProtocol();
}
