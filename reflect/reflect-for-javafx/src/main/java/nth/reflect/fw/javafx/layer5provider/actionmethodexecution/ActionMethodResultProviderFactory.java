package nth.reflect.fw.javafx.layer5provider.actionmethodexecution;

import nth.reflect.fw.ReflectApplication;

public class ActionMethodResultProviderFactory
		extends nth.reflect.fw.gui.provider.actionmethodexecution.result.ActionMethodResultHandlerFactory {

	public ActionMethodResultProviderFactory(ReflectApplication reflectApplication) {
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

}
