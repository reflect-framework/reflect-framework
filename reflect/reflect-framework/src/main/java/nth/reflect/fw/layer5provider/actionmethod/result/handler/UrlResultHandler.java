package nth.reflect.fw.layer5provider.actionmethod.result.handler;

import java.net.URL;
import java.util.Optional;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer2service.ServiceObjectActionMethod;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.result.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;
import nth.reflect.fw.layer5provider.url.servicemethod.ServiceMethodUrl;
import nth.reflect.fw.layer5provider.url.servicemethod.ServiceMethodUrlMalformedException;

/**
 * Opens a URL on the local system:
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
public abstract class UrlResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo methodInfo) {
		Class<?> methodReturnType = methodInfo.getReturnTypeInfo().getType();
		boolean isUrl = URL.class.isAssignableFrom(methodReturnType);
		return isUrl;
	}

	@Override
	public void process(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, Object methodResult) {
		URL url = (URL) methodResult;
		Optional<ServiceMethodUrl> serviceMethodUrl = ServiceMethodUrl.createFor(url);
		if (serviceMethodUrl.isPresent()) {
			openServiceMethodUrl(container, serviceMethodUrl.get());
		} else {
			openUrl(container, url);
		}
	}

	public static void openServiceMethodUrl(UserInterfaceContainer container, ServiceMethodUrl serviceMethodUrl) {
		try {
			Class<?> serviceClass = serviceMethodUrl.getServiceClass();
			String actionMethodName = serviceMethodUrl.getActionMethodName();

			ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
			ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(serviceClass);
			ActionMethodInfo methodInfo = serviceClassInfo.getActionMethodInfo(actionMethodName);
			Object serviceObject = container.get(serviceClass);
			methodInfo.process(container, serviceObject, null);
		} catch (Exception exception) {
			throw new ServiceMethodUrlMalformedException(exception);
		}
	}

	public abstract void openUrl(UserInterfaceContainer container, URL url);

}
