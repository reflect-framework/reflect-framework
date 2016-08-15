package nth.introspect.layer5provider.url;

import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer5provider.ProviderContainer;

/**
 * TODO look at a maybe nicer solution:
 * http://stackoverflow.com/questions/15195890/nio2-how-to-generically-map-a-uri-to-a-path
 * 
 * @author nilsth
 *
 */
public class ReflectUrlStreamHandlerFactory implements URLStreamHandlerFactory {

	private List<UrlProvider> urlProviders;

	public ReflectUrlStreamHandlerFactory(IntrospectApplication application,
			ProviderContainer providerContainer) {
		List<Class<? extends UrlProvider>> urlProviderClasses = application.getUrlProviderClasses();
		urlProviders = new ArrayList<>();
		for (Class<? extends UrlProvider> urlProviderClass : urlProviderClasses) {
			UrlProvider urlProvider = providerContainer.get(urlProviderClass);
			urlProviders.add(urlProvider);
		}
	}

	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		for (UrlProvider urlProvider : urlProviders) {
			if (urlProvider.getProtocol().equals(protocol)) {
				return urlProvider;
			}
		}
		return null;
	}

	public void register() {
		URL.setURLStreamHandlerFactory(this);
	}

}
