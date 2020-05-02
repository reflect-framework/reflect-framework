package nth.reflect.fw.layer5provider.url.servicemethod;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandler;

/**
 * A {@link ServiceMethodUrlStreamHandler} handles a {@link ServiceMethodUrl}.
 * 
 * {@insert ClassResourceUrl}
 * 
 * @author nilsth
 *
 */
public class ServiceMethodUrlStreamHandler extends ReflectUrlStreamHandler {

	@Override
	public String getProtocol() {
		return ServiceMethodUrl.PROTOCOL;
	}

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		throw new ServiceMethodUrlCanNotBeOpenedException();
	}

}
