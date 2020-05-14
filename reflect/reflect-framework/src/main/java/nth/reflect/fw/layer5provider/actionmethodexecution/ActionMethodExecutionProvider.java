package nth.reflect.fw.layer5provider.actionmethodexecution;

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

public class ActionMethodExecutionProvider implements Provider {

	private List<ActionMethodResultHandler> actionMethodResultHandlers;

	public ActionMethodExecutionProvider(ProviderContainer providerContainer) {
		actionMethodResultHandlers = createHandlers(providerContainer);
	}

	private List<ActionMethodResultHandler> createHandlers(ProviderContainer providerContainer) {
		List<Class<? extends ActionMethodResultHandler>> handlerClasses = getHandlerClasses(providerContainer);
		ArrayList<ActionMethodResultHandler> handlers = new ArrayList();
		for (Class<? extends ActionMethodResultHandler> handlerClass : handlerClasses) {
			providerContainer.add(handlerClass);
			ActionMethodResultHandler handler = providerContainer.get(handlerClass);
			handlers.add(handler);
		}
		return Collections.unmodifiableList(handlers);
	}

	private List<Class<? extends ActionMethodResultHandler>> getHandlerClasses(ProviderContainer providerContainer) {
		ReflectApplication application = providerContainer.get(ReflectApplication.class);
		List<Class<? extends ActionMethodResultHandler>> handlerClasses = application
				.getActionMethodResultHandlerClasses();
		if (handlerClasses == null || handlerClasses.size() == 0) {
			String canonicalMethodName = ReflectApplication.class.getSimpleName()
					+ ".getActionMethodResultHandlerClasses";
			throw new ProviderHelperNotDeclaredException(ReflectUrlStreamHandler.class, canonicalMethodName);
		}
		return handlerClasses;
	}

	public ActionMethodResultHandler getActionMethodResultHandler(ProviderContainer container,
			ActionMethodInfo actionMethodInfo) {
		Optional<ActionMethodResultHandler> result = actionMethodResultHandlers
				.stream()
				.filter(a -> a.canProcess(container, actionMethodInfo))
				.findFirst();
		return result.orElseThrow(() -> new ActionMethodResultHandlerNotFoundException(actionMethodInfo));
	}
}
