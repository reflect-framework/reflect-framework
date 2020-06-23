package nth.reflect.fw.javafx.layer5provider.actionmethod.result;

public class ActionMethodResultHandlerClasses
		extends nth.reflect.fw.gui.layer5provider.actionmethod.result.ActionMethodResultHandlerClasses {

	private static final long serialVersionUID = -4145632345975347980L;

	@Override
	public Class<? extends nth.reflect.fw.gui.layer5provider.actionmethod.result.DomainObjectResultHandler> getDomainObjectResultHandler() {
		return DomainObjectResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.gui.layer5provider.actionmethod.result.MultipleValueResultHandler> getMultipleValueResultHandler() {
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
