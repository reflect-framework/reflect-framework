package nth.introspect.layer4infrastructure;

import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.ProviderContainer;

/**
 * This {@link DependencyInjectionContainer} represents the {@link InfrastructureLayer}
 * 
 * @author nilsth
 * 
 */
public class InfrastructureContainer extends DependencyInjectionContainer {

	public InfrastructureContainer(IntrospectApplication application) {
		super(new ProviderContainer(application));
		List<Class<?>> infrastructureClasses = application
				.getInfrastructureClasses();
		if (infrastructureClasses != null) {
			add(infrastructureClasses);
		}
	}

}
