package nth.reflect.fw.layer5provider.actionmethod.result;

import java.util.ArrayList;

import nth.reflect.fw.layer5provider.actionmethod.result.handler.DomainObjectResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.result.handler.NoResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.result.handler.StringConverterResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.result.handler.UriResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.result.handler.UrlResultHandler;

public abstract class ActionMethodResultHandlerClasses extends ArrayList<Class<? extends ActionMethodResultHandler>> {

	private static final long serialVersionUID = 2045702016147501338L;

	public ActionMethodResultHandlerClasses() {
		add(getNoResultHandler());
		add(getDomainObjectResultHandler());
		add(getMultipleValueResultHandler());
		add(getUrlResultHandler());
		add(getUriResultHandler());
		add(getStringConverterResultHandler());
	}

	private Class<NoResultHandler> getNoResultHandler() {
		return NoResultHandler.class;
	}

	public abstract Class<? extends DomainObjectResultHandler> getDomainObjectResultHandler();

	public abstract Class<? extends ActionMethodResultHandler> getMultipleValueResultHandler();

	public abstract Class<? extends UrlResultHandler> getUrlResultHandler();

	public abstract Class<? extends UriResultHandler> getUriResultHandler();

	public abstract Class<? extends StringConverterResultHandler> getStringConverterResultHandler();

}
