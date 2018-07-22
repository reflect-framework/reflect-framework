package nth.reflect.fw.layer1userinterface;

import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer2service.ServiceContainer;

/**
 * This {@link DependencyInjectionContainer} represents the
 * {@link UserInterfaceLayer}
 * 
 * @author nilsth
 * 
 */
public class UserInterfaceContainer extends DependencyInjectionContainer {

	public UserInterfaceContainer(ReflectApplication application) {
		this(application, true);
	}

	public UserInterfaceContainer(ReflectApplication application, boolean mustHaveServiceObject) {
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
