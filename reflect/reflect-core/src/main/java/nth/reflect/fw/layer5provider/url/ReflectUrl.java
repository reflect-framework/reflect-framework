package nth.reflect.fw.layer5provider.url;

import java.net.URL;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;

/**
 * A {@link ReflectUrl} is a special type of URL that is only supported by the
 * {@link ReflectFramework}. {@link ReflectUrl}'s needs to have a
 * {@link UrlProvider} that needs to be registered with the
 * {@link ReflectApplication#getUrlProviderClasses()} so that they can be
 * registered with {@link URL#setURLStreamHandlerFactory()}. {@link ReflectUrl}
 * 's are an adapter for an URL, since a {@link URL} can not be overridden.
 * 
 * 
 * 
 * @author nilsth
 *
 */

public interface ReflectUrl {

	/**
	 * 
	 * @return a URL that can be used within the {@link ReflectFramework} (a
	 *         URL like reflect...://some path)
	 */
	public URL toInternalURL();


}
