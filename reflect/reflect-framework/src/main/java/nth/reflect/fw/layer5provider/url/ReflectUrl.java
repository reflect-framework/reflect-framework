package nth.reflect.fw.layer5provider.url;

import java.net.URL;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;

/**
 * A {@link ReflectUrl} is a special type of URL that is only supported by the
 * {@link ReflectFramework}. {@link ReflectUrl}'s needs to have a
 * {@link ReflectUrlStreamHandler} that needs to be registered with the
 * {@link ReflectApplication#getUrlProviderClass()} so that they can be
 * registered with {@link URL#setURLStreamHandlerFactory()}. {@link ReflectUrl}
 * 's are an adapter for an URL, since a {@link URL} can not be overridden.
 * 
 * <h3>Application URL</h3>{@insert ApplicationUrl}
 * 
 * <h3>Class Resource URL</h3>{@insert ClassResourceUrl}
 * 
 * <h3>Font Icon URL</h3>{@insert FontIconUrl}
 * 
 * 
 * @author nilsth
 *
 */

public interface ReflectUrl {

	/**
	 * 
	 * @return a URL that can be used within the {@link ReflectFramework} (a URL
	 *         like reflect...://some path)
	 */
	public URL toInternalURL();

}
