package nth.reflect.fw.javafx.layer5provider.actionmethodexecution;

import nth.reflect.fw.ReflectApplication;

public class ActionMethodResultProviderFactory
		extends nth.reflect.fw.gui.provider.actionmethodexecution.result.ActionMethodResultHandlerFactory {

	public ActionMethodResultProviderFactory(ReflectApplication reflectApplication) {
		super(reflectApplication);
	}

	@Override
	public DomainObjectResultHandler createDomainObjectResultHandler() {
		return new DomainObjectResultHandler();
	}

	@Override
	public MultipleValueResultHandler createMultipleValueResultHandler() {
		return new MultipleValueResultHandler();
	}

	@Override
	public UriResultHandler createUriResultHandler() {
		return new UriResultHandler();
	}

	@Override
	public UrlResultHandler createUrlResultHandler() {
		return new UrlResultHandler();
	}

}
