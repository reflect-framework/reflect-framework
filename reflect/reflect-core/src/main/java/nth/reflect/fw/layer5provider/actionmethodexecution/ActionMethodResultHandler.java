package nth.reflect.fw.layer5provider.actionmethodexecution;

import java.net.URI;

import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;

/**
 * An {@link ActionMethodResultHandler} processes the return value of a
 * {@link ActionMethod}, e.g.:
 * <ul>
 * <li>Show a Message that the {@link ActionMethod} is executed, when
 * {@link ActionMethod} result is {@link Void}.</li>
 * <li>Show a {@link DomainObject}</li>
 * <li>Show a {@link TableInfo}</li>
 * <li>Send a {@link DownloadStream}</li>
 * <li>Open a {@link URL}</li>
 * <li>Open a {@link URI}</li>
 * <li>Show a Type that can be conerterd by the
 * {@link StringConverterProvider}</li>
 * <li>Other</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public interface ActionMethodResultHandler {

	public boolean canProcess(ActionMethodInfo actionMethodInfo);

	public void process(UserInterfaceController userInterfaceController, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter, Object methodResult);

}
