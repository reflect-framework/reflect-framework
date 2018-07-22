package nth.reflect.fw.layer3domain;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer4infrastructure.InfrastructureContainer;

/**
 * This {@link DependencyInjectionContainer} represents the {@link DomainLayer}
 * @author nilsth
 *
 */
public class DomainContainer extends DependencyInjectionContainer {
	
	public DomainContainer(ReflectApplication application) {
		super(new InfrastructureContainer(application));
	}

}
