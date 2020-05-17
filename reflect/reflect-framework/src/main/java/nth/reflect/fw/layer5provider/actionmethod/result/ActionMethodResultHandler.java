package nth.reflect.fw.layer5provider.actionmethod.result;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * An {@link ActionMethodResultHandler} processes the return value of a
 * {@link ActionMethod}, e.g. see following paragraphs:
 * 
 * <h3>NoResultHandler</h3> {@insert NoResultHandler}
 * 
 * <h3>DomainObjectResultHandler</h3> {@insert DomainObjectResultHandler}
 * 
 * <h3>MultipleValueResultHandler</h3> {@insert MultipleValueResultHandler}
 * 
 * <h3>UriResultHandler</h3>{@insert UriResultHandler}
 * 
 * <h3>UrlResultHandler</h3>{@insert UrlResultHandler}
 * 
 * <h3>StringConverterResultHandler</h3>{@insert StringConverterResultHandler}
 * 
 * <h3>Custom ActionmethodResultHandler</h3> You can add, remove or change the
 * order of the {@link ActionMethodResultHandler}'s by overriding the
 * {@link ReflectApplication#getActionMethodResultHandlers}.
 * 
 * @author nilsth
 *
 */
public interface ActionMethodResultHandler {

	public boolean canProcess(ProviderContainer container, ActionMethodInfo methodInfo);

	/**
	 * 
	 * TODO public void process(UserInterfaceContainer container, Object
	 * methodOwner, ActionMethodInfo methodInfo, Object methodParameter);<br>
	 * Remove method result: the ActionMethodResultHandler needs to get the method
	 * result {@link TableResultHandler} does not need it.<br>
	 * see https://stackoverflow.com/questions/9148899/returning-value-from-thread
	 */

	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult);

}
