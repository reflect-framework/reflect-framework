package nth.reflect.fw.layer2service;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.layer1userinterface.UserInterfaceLayer;
import nth.reflect.fw.layer3domain.DomainLayer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer4infrastructure.InfrastructureLayer;
import nth.reflect.fw.layer5provider.ProviderLayer;

/**
 * <p>
 * The {@link ServiceLayer} (sometimes also called application layer) gives the user access to the
 * {@link DomainObject}s so that the user can work on them.
 * </p>
 * <p>The {@link ServiceContainer} is an {@link DependencyInjectionContainer} that
 * represents the {@link ServiceLayer} and holds and manages
 * {@link ServiceObject}s. </p>
 * <p>
 * Note that the {@link ServiceLayer} is a middle layer (see
 * {@link ReflectArchitecture}):
 * <ul>
 * <li>
 * The {@link ServiceObject}s have NO references to objects in the
 * {@link UserInterfaceLayer}</li>
 * <li>The {@link ServiceObject}s may have references to the objects in the
 * lower {@link DomainLayer}, {@link InfrastructureLayer} and
 * {@link ProviderLayer}, but not visa versa!</li>
 * </ul>
 * </p>
 * <h2>Service Objects</h2> {@insert ServiceObject}
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface ServiceLayer extends ReflectDocumentationInterface {

}
