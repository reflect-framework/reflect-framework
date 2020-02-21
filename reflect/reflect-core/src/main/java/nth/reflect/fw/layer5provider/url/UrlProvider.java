package nth.reflect.fw.layer5provider.url;

import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import nth.reflect.fw.layer5provider.Provider;

/**
 * <p>
 * The {@link UrlProvider} is a {@link Provider} that ensures you can use
 * {@link ReflectUrl}'s. It knows {@link ReflectUrlStreamHandler}s that can
 * convert a {@link ReflectUrl} to a normal {@link URL}. The following
 * paragraphs show examples of {@link ReflectUrlStreamHandler}s
 * </p>
 * <h3>ApplicationUrlProvider</h3>
 * <p>
 * {@insert ApplicationUrlProvider}
 * </p>
 * <h3>ClassResourceUrlProvider</h3>
 * <p>
 * {@insert ClassResourceUrlProvider}
 * </p>
 * <h3>FontIconUrlProvider</h3>
 * <p>
 * {@insert FontIconUrlProvider}
 * </p>
 */

// TODO look at a maybe nicer solution:
// http://stackoverflow.com/questions/15195890/nio2-how-to-generically-map-a-uri-to-a-path
public class UrlProvider implements URLStreamHandlerFactory, Provider {

	private final ReflectUrlStreamHandler[] urlStreamHandlers;

	public UrlProvider(ReflectUrlStreamHandler... urlStreamHandlers) {
		this.urlStreamHandlers = urlStreamHandlers;
		try {
			// This method can be called at most once in a given Java Virtual Machine.
			URL.setURLStreamHandlerFactory(this);
		} catch (Throwable e) {
			// Do nothing: The URL.setURLStreamHandlerFactory(this) method was already
			// called. We assume it was with the correct urlStreamHandlers
		}
	}

	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		for (ReflectUrlStreamHandler urlProvider : urlStreamHandlers) {
			if (urlProvider.getProtocol().equals(protocol)) {
				return urlProvider;
			}
		}
		return null;
	}

}
