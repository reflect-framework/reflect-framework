package nth.reflect.fw.gui.provider.actionmethodexecution.result;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.actionmethodexecution.result.depricated.DeprecatedActionMethodResultHandler;

public abstract class ActionMethodResultHandlerClasses
		extends nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandlerClasses {

	private static final long serialVersionUID = -2972430128516924842L;

	public ActionMethodResultHandlerClasses() {
		super();
		add(getDepricatedActionMethodsClass());
	}

	/**
	 * Overriding this method so that the {@link DomainObjectResultHandler} in this
	 * package is used
	 */
	@Override
	public abstract Class<? extends DomainObjectResultHandler> getDomainObjectResultHandler();

	/**
	 * Overriding this method so that the {@link MultipleValueResultHandler} in this
	 * package is used
	 */
	@Override
	public abstract Class<? extends MultipleValueResultHandler> getMultipleValueResultHandler();

	/**
	 * Overriding this method so that the {@link StringConverterResultHandler} in
	 * this package is used, and other {@link ActionMethodResultHandlerClasses} do
	 * not have to implement it.
	 */
	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethodexecution.result.StringConverterResultHandler> getStringConverterResultHandler() {
		return StringConverterResultHandler.class;
	}

	/**
	 * TODO remove this method and the {@link #reflectApplication} field when all
	 * {@link UserInterfaceController} open... methods are moved to
	 * {@link ActionMethodResultHandler}s
	 */
	public Class<DeprecatedActionMethodResultHandler> getDepricatedActionMethodsClass() {
		return DeprecatedActionMethodResultHandler.class;
	}

}
