package nth.reflect.fw.layer5provider.url;

import java.util.ArrayList;

import nth.reflect.fw.layer5provider.url.application.ApplicationUrlStreamHandler;
import nth.reflect.fw.layer5provider.url.classresource.ClassResourceUrlStreamHandler;
import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrlStreamHandler;
import nth.reflect.fw.layer5provider.url.servicemethod.ServiceMethodUrlStreamHandler;

/**
 * <h3>ApplicationUrlStreamHandler</h3>
 * <p>
 * {@insert ApplicationUrlStreamHandler}
 * </p>
 * <h3>ClassResourceUrlStreamHandler</h3>
 * <p>
 * {@insert ClassResourceUrlStreamHandler}
 * </p>
 * <h3>FontIconUrlStreamHandler</h3>
 * <p>
 * {@insert FontIconUrlStreamHandler}
 * <p>
 * <h3>ServiceMethodUrlStreamHandler</h3>
 * {@insert ServiceMethodUrlStreamHandler}
 * </p>
 * 
 * @author nilsth
 *
 */
public class UrlStreamHandlers extends ArrayList<Class<? extends ReflectUrlStreamHandler>> {

	private static final long serialVersionUID = -3908039892660703902L;

	public UrlStreamHandlers() {
		add(ClassResourceUrlStreamHandler.class);
		add(ServiceMethodUrlStreamHandler.class);
		add(ApplicationUrlStreamHandler.class);
		add(FontIconUrlStreamHandler.class);
	}
}
