package nth.reflect.fw.gui.layer5provider.actionmethod.result;

public abstract class ActionMethodResultHandlerClasses
		extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandlerClasses {

	private static final long serialVersionUID = -2972430128516924842L;

	public ActionMethodResultHandlerClasses() {
		super();
		add(1, getDownloadStreamResultHandler());
	}

	public abstract Class<? extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.DownloadStreamResultHandler> getDownloadStreamResultHandler();

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
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.StringConverterResultHandler> getStringConverterResultHandler() {
		return StringConverterResultHandler.class;
	}

}
