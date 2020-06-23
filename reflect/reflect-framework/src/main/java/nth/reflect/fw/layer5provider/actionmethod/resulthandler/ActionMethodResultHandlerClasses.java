package nth.reflect.fw.layer5provider.actionmethod.resulthandler;

import java.util.ArrayList;

import nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.DomainObjectResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.NoResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.StringConverterResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UriResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UrlResultHandler;

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
