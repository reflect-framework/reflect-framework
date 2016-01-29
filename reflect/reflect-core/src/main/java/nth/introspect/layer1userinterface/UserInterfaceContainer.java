package nth.introspect.layer1userinterface;

import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer2service.ServiceContainer;

/**
 * This {@link DependencyInjectionContainer} represents the
 * {@link UserInterfaceLayer}
 * 
 * @author nilsth
 * 
 */
public class UserInterfaceContainer extends DependencyInjectionContainer {

	public UserInterfaceContainer(IntrospectApplication application) {
		this(application, true);
	}

	public UserInterfaceContainer(IntrospectApplication application, boolean mustHaveServiceObject) {
		super(new ServiceContainer(application, mustHaveServiceObject));
		add(application.getUserInterfaceControllerClass());
	}

	@Override
	public List<Class<?>> getAllClasses() {
		List<Class<?>> allClasses = super.getAllClasses();
		allClasses.remove(DependencyInjectionContainer.class);
		allClasses.add(UserInterfaceContainer.class);
		return allClasses;
	}

}
