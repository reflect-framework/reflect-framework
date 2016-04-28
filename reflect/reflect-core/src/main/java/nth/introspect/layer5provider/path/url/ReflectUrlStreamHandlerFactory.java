package nth.introspect.layer5provider.path.url;

import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.List;

import nth.introspect.IntrospectApplication;

public class ReflectUrlStreamHandlerFactory implements URLStreamHandlerFactory {

	
	private List<ReflectUrlConnection> urlStreamHandlers;

	public ReflectUrlStreamHandlerFactory(IntrospectApplication application) {
		urlStreamHandlers= application.getReflectUrlStreamHandlers();
	}
	
	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		for (ReflectUrlConnection urlStreamHandler: urlStreamHandlers) {
			if (urlStreamHandler.getProtocol().equals(protocol)) {
				return urlStreamHandler;
			}
		}
		return null;
	}
	
	public void register() {
		URL.setURLStreamHandlerFactory(this);
	}

}
