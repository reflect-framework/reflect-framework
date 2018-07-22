package nth.reflect.fw.layer4infrastructure;

import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;

/**
 * This {@link DependencyInjectionContainer} represents the {@link InfrastructureLayer}
 * 
 * @author nilsth
 * 
 */
public class InfrastructureContainer extends DependencyInjectionContainer {

	public InfrastructureContainer(ReflectApplication application) {
		super(new ProviderContainer(application));
		List<Class<?>> infrastructureClasses = application
				.getInfrastructureClasses();
		if (infrastructureClasses != null) {
			add(infrastructureClasses);
		}
	}

}
