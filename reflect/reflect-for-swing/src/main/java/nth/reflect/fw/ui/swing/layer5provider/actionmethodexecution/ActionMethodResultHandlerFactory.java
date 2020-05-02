package nth.reflect.fw.ui.swing.layer5provider.actionmethodexecution;

import nth.reflect.fw.ReflectApplication;

public class ActionMethodResultHandlerFactory
		extends nth.reflect.fw.gui.provider.actionmethodexecution.result.ActionMethodResultHandlerFactory {

	public ActionMethodResultHandlerFactory(ReflectApplication reflectApplication) {
		super(reflectApplication);
	}

	@Override
	public FormResultHandler createFormResultHandler() {
		return new FormResultHandler();
	}

	@Override
	public TableResultHandler createTableResultHandler() {
		return new TableResultHandler();
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
