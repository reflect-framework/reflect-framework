package nth.reflect.fw.swing.layer5provider.actionmethodexecution;

public class ActionMethodResultHandlerClasses
		extends nth.reflect.fw.gui.layer5provider.actionmethodexecution.result.ActionMethodResultHandlerClasses {

	private static final long serialVersionUID = -4145632345975347980L;

	@Override
	public Class<? extends nth.reflect.fw.gui.layer5provider.actionmethodexecution.result.DomainObjectResultHandler> getDomainObjectResultHandler() {
		return DomainObjectResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.gui.layer5provider.actionmethodexecution.result.MultipleValueResultHandler> getMultipleValueResultHandler() {
		return MultipleValueResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethodexecution.result.UrlResultHandler> getUrlResultHandler() {
		return UrlResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethodexecution.result.UriResultHandler> getUriResultHandler() {
		return UriResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethodexecution.result.DownloadStreamResultHandler> getDownloadStreamResultHandler() {
		return DownloadStreamResultHandler.class;
	}

}
