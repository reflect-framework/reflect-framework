package nth.reflect.fw.gui.provider.actionmethodexecution.result;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.actionmethodexecution.result.NoResultHandler;
import nth.reflect.fw.layer5provider.actionmethodexecution.result.UriResultHandler;
import nth.reflect.fw.layer5provider.actionmethodexecution.result.UrlResultHandler;
import nth.reflect.fw.layer5provider.actionmethodexecution.result.depricated.DeprecatedActionMethodResultHandler;

public abstract class ActionMethodResultHandlerFactory
		implements nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandlerFactory {

	private final List<ActionMethodResultHandler> all;

	public ActionMethodResultHandlerFactory(ReflectApplication reflectApplication) {
		all = new ArrayList();
		all.add(createNoResultHandler());
		all.add(createDomainObjectResultHandler());
		all.add(createMultipleValueResultHandler());
		all.add(createUrlResultHandler());
		all.add(createUriResultHandler());
		all.add(createStringConverterResultHandler());
		all.add(createDepricatedActionMethods());
	}

	@Override
	public List<ActionMethodResultHandler> getAll() {
		return all;
	}

	private NoResultHandler createNoResultHandler() {
		return new NoResultHandler();
	}

	public abstract DomainObjectResultHandler createDomainObjectResultHandler();

	public abstract MultipleValueResultHandler createMultipleValueResultHandler();

	public abstract UriResultHandler createUriResultHandler();

	public abstract UrlResultHandler createUrlResultHandler();

	public StringConverterResultHandler createStringConverterResultHandler() {
		return new StringConverterResultHandler();
	}

	/**
	 * TODO remove this method and the {@link #reflectApplication} field when all
	 * {@link UserInterfaceController} open... methods are moved to
	 * {@link ActionMethodResultHandler}s
	 */
	public ActionMethodResultHandler createDepricatedActionMethods() {
		return new DeprecatedActionMethodResultHandler();
	}

}
