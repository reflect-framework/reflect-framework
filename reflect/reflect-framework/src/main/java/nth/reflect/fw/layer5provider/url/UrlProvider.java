package nth.reflect.fw.layer5provider.url;

import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.ProviderHelperNotDeclaredException;

/**
 * <p>
 * The {@link UrlProvider} is a {@link Provider} that ensures you can use
 * {@link ReflectUrl}'s. It knows {@link ReflectUrlStreamHandler}s that can
 * convert a {@link ReflectUrl} to a normal {@link URL}. The following
 * paragraphs show examples of {@link ReflectUrlStreamHandler}s
 * </p>
 * {@insert UrlStreamHandlers}
 */

// TODO look at a maybe nicer solution:
// http://stackoverflow.com/questions/15195890/nio2-how-to-generically-map-a-uri-to-a-path
public class UrlProvider implements URLStreamHandlerFactory, Provider {

	private final List<ReflectUrlStreamHandler> urlStreamHandlers;

	public UrlProvider(ProviderContainer container) {
		urlStreamHandlers = createUrlStreamHandlers(container);
		try {
			// This method can be called at most once in a given Java Virtual Machine.
			URL.setURLStreamHandlerFactory(this);
		} catch (Throwable e) {
			// Do nothing: The URL.setURLStreamHandlerFactory(this) method was already
			// called. We assume it was with the correct urlStreamHandlers
		}
	}

	private List<ReflectUrlStreamHandler> createUrlStreamHandlers(ProviderContainer container) {
		List<Class<? extends ReflectUrlStreamHandler>> urlStreamHandlerClasses = getUrlStreamHandlerClasses(container);
		List<ReflectUrlStreamHandler> handlers = new ArrayList();
		for (Class<? extends ReflectUrlStreamHandler> urlStreamHandlerClass : urlStreamHandlerClasses) {
			container.add(urlStreamHandlerClass);
			ReflectUrlStreamHandler handler = container.get(urlStreamHandlerClass);
			handlers.add(handler);
		}
		return Collections.unmodifiableList(handlers);
	}

	private List<Class<? extends ReflectUrlStreamHandler>> getUrlStreamHandlerClasses(ProviderContainer container) {
		ReflectApplication application = container.get(ReflectApplication.class);
		List<Class<? extends ReflectUrlStreamHandler>> urlStreamHandlerClasses = application
				.getUrlStreamHandlerClasses();
		if (urlStreamHandlerClasses == null || urlStreamHandlerClasses.size() == 0) {
			String canonicalMethodName = ReflectApplication.class.getSimpleName() + ".getUrlStreamHandlerClasses";
			throw new ProviderHelperNotDeclaredException(ReflectUrlStreamHandler.class, canonicalMethodName);
		}
		return urlStreamHandlerClasses;
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
