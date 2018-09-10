package nth.reflect.fw.layer5provider.url;

import java.net.URL;
import java.net.URLStreamHandler;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.Provider;

/**
 * <p>
 * You can use custom URL's or {@link ReflectUrl}'s that are translated to
 * normal URL's using {@link UrlProvider}s. {@link UrlProvider}s need to be
 * registered with the {@link ReflectApplication#getUrlProviderClasses()} so
 * that they can be registered with {@link URL#setURLStreamHandlerFactory()}
 * (See {@link ReflectUrlStreamHandlerFactory}). The following paragraphs show
 * examples of {@link UrlProvider}s
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
public abstract class UrlProvider extends URLStreamHandler implements Provider {

	public abstract String getProtocol();
}
