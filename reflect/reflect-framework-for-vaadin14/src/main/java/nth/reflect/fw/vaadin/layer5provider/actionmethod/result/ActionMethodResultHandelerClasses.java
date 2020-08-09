package nth.reflect.fw.vaadin.layer5provider.actionmethod.result;

import nth.reflect.fw.vaadin.layer5provider.actionmethod.result.impl.DomainObjectResultHandler;
import nth.reflect.fw.vaadin.layer5provider.actionmethod.result.impl.DownloadStreamResultHandler;
import nth.reflect.fw.vaadin.layer5provider.actionmethod.result.impl.MultipleValueResultHandler;
import nth.reflect.fw.vaadin.layer5provider.actionmethod.result.impl.UriResultHandler;
import nth.reflect.fw.vaadin.layer5provider.actionmethod.result.impl.UrlResultHandler;

public class ActionMethodResultHandelerClasses
		extends nth.reflect.fw.gui.layer5provider.actionmethod.result.ActionMethodResultHandlerClasses {

	private static final long serialVersionUID = 2547177520256232384L;

	@Override
	public Class<? extends nth.reflect.fw.gui.layer5provider.actionmethod.result.impl.DomainObjectResultHandler> getDomainObjectResultHandler() {
		return DomainObjectResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.gui.layer5provider.actionmethod.result.impl.MultipleValueResultHandler> getMultipleValueResultHandler() {
		return MultipleValueResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UrlResultHandler> getUrlResultHandler() {
		return UrlResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UriResultHandler> getUriResultHandler() {
		return UriResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.DownloadStreamResultHandler> getDownloadStreamResultHandler() {
		return DownloadStreamResultHandler.class;
	}

}
