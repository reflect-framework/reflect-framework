package nth.reflect.fw.layer5provider.reflection.behavior.serviceobjectchildren;

import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer2service.MainMenu;
import nth.reflect.fw.layer2service.ServiceContainer;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer5provider.ProviderContainer;

//TODO change to annotation for now (see displayName as an example)
//TODO change menu: hide service objects in root if they are annotated as child and order
/**
 * <p>
 * {@link ServiceObject}s can be nested so that they can be displayed as a
 * <a href="https://en.wikipedia.org/wiki/Tree_view">tree view</a> in the
 * {@link MainMenu}.
 * </p>
 * 
 * <h3>ServiceObjectChildren annotation</h3>
 * <p>
 * {@insert ServiceObjectChildren}
 * </p>
 * 
 * @author nilsth
 *
 */
public class ServiceObjectChildrenModel {

	private final Class<?>[] serviceObjectChildren;
	private final boolean beforeActionMethods;

	public ServiceObjectChildrenModel(ProviderContainer providerContainer,
			Class<?> serviceClass) {

		ServiceObjectChildren annotation = serviceClass.getAnnotation(ServiceObjectChildren.class);
		if (annotation == null) {
			serviceObjectChildren = new Class<?>[0];
			beforeActionMethods = true;
		} else {
			serviceObjectChildren = annotation.serviceClasses();
			beforeActionMethods = annotation.beforeActionMethods();
			requireServiceObjectChildrenAreRegistered(providerContainer, serviceClass,
					serviceObjectChildren);
		}
	}

	private void requireServiceObjectChildrenAreRegistered(
			ProviderContainer providerContainer, Class<?> serviceClass,
			Class<?>[] serviceObjectChildren) {
		ReflectApplication application = providerContainer.get(ReflectApplication.class);
		List<Class<?>> registeredServiceClasses = application.getServiceClasses();
		StringBuilder message = new StringBuilder();
		for (Class<?> serviceObjectChild : serviceObjectChildren) {
			if (!registeredServiceClasses.contains(serviceObjectChild)) {
				if (message.length() == 0) {
					message.append("Service object: ");
					message.append(serviceClass.getName());
					message.append(" has a ");
					message.append(ServiceObjectChildren.class.getSimpleName());
					message.append(" annotation with the following class(es): {");
					message.append(serviceObjectChild.getName());
				} else {
					message.append(", ");
					message.append(serviceObjectChild.getName());
				}
			}
		}
		if (message.length() > 0) {
			message.append("} that is/are not registered as a service class in the ");
			message.append(ReflectApplication.class.getSimpleName());
			message.append(".getServiceClasses() method.");
			throw new RuntimeException(message.toString());
		}

	}

	public Class<?>[] getServiceObjectChildren() {
		return serviceObjectChildren;
	}

	public boolean isBeforeActionMethods() {
		return beforeActionMethods;
	}

}
