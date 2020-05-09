package nth.reflect.fw.layer5provider.url;

import java.net.URLStreamHandler;

import nth.reflect.fw.layer5provider.Provider;

/**
 * See {@link UrlProvider}
 * @author nilsth
 *
 */

// TODO look at a maybe nicer solution:
// http://stackoverflow.com/questions/15195890/nio2-how-to-generically-map-a-uri-to-a-path
public abstract class ReflectUrlStreamHandler extends URLStreamHandler implements Provider {

	public abstract String getProtocol();
}
