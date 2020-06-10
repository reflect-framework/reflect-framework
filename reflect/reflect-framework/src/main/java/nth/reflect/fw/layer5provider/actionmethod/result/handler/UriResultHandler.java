package nth.reflect.fw.layer5provider.actionmethod.result.handler;

import java.net.URI;
import java.util.Optional;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer2service.ServiceObjectActionMethod;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.result.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.url.servicemethod.ServiceMethodUrl;

/**
 * Opens a URI on the local system:
 * <ul>
 * <li>It will process a {@link ServiceObjectActionMethod} when the
 * {@link ActionMethod} return value is a {@link ServiceMethodUrl}</li>
 * <li>Otherwise the operating system of the local system will open the URL in a
 * local application that can process the URL (e.g. a web browser, e-mail
 * application or other application)</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public abstract class UriResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo methodInfo) {
		Class<?> methodReturnType = methodInfo.getReturnTypeInfo().getType();
		boolean isUri = URI.class.isAssignableFrom(methodReturnType);
		return isUri;
	}

	@Override
	public void process(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, Object methodResult) {
		URI uri = (URI) methodResult;

		Optional<ServiceMethodUrl> serviceMethodUrl = ServiceMethodUrl.createFor(uri);
		if (serviceMethodUrl.isPresent()) {
			UrlResultHandler.openServiceMethodUrl(container, serviceMethodUrl.get());
		} else {
			openUri(container, uri);
		}
	}

	public abstract void openUri(UserInterfaceContainer container, URI uri);
}
