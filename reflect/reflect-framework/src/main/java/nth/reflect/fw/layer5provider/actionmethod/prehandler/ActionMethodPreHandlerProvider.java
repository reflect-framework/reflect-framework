package nth.reflect.fw.layer5provider.actionmethod.prehandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.ProviderHelperNotDeclaredException;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandler;

public class ActionMethodPreHandlerProvider implements Provider {

	private List<ActionMethodPreHandler> actionMethodPreHandlers;

	public ActionMethodPreHandlerProvider(ProviderContainer providerContainer) {
		actionMethodPreHandlers = createHandlers(providerContainer);
	}

	private List<ActionMethodPreHandler> createHandlers(ProviderContainer providerContainer) {
		List<Class<? extends ActionMethodPreHandler>> handlerClasses = getHandlerClasses(providerContainer);
		ArrayList<ActionMethodPreHandler> handlers = new ArrayList();
		for (Class<? extends ActionMethodPreHandler> handlerClass : handlerClasses) {
			providerContainer.add(handlerClass);
			ActionMethodPreHandler handler = providerContainer.get(handlerClass);
			handlers.add(handler);
		}
		return Collections.unmodifiableList(handlers);
	}

	private List<Class<? extends ActionMethodPreHandler>> getHandlerClasses(ProviderContainer providerContainer) {
		ReflectApplication application = providerContainer.get(ReflectApplication.class);
		List<Class<? extends ActionMethodPreHandler>> handlerClasses = application.getActionMethodPreHandlerClasses();
		if (handlerClasses == null || handlerClasses.size() == 0) {
			String canonicalMethodName = ReflectApplication.class.getSimpleName() + ".getActionMethodPreHandlerClasses";
			throw new ProviderHelperNotDeclaredException(ReflectUrlStreamHandler.class, canonicalMethodName);
		}
		return handlerClasses;
	}

	public ActionMethodPreHandler get(ActionMethodInfo actionMethodInfo) {
		Optional<ActionMethodPreHandler> result = actionMethodPreHandlers
				.stream()
				.filter(a -> a.canProcess(actionMethodInfo))
				.findFirst();
		return result.orElseThrow(() -> new ActionMethodPreHandlerNotFoundException(actionMethodInfo));
	}

}
