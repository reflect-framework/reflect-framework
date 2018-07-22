package nth.reflect.fw.layer3domain;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.layer1userinterface.UserInterfaceLayer;
import nth.reflect.fw.layer2service.ServiceLayer;
import nth.reflect.fw.layer4infrastructure.InfrastructureLayer;
import nth.reflect.fw.layer5provider.ProviderLayer;

/**
 * The {@link DomainLayer} is the heart of any {@link ReflectApplication}. The
 * {@link DomainLayer} represents:
 * <ul>
 * <li>The concepts of the business</li>
 * <li>The business rules</li>
 * <li>The state that reflects the business situation</li>
 * </ul>
 * Because there is many debate on the naming within a layered architecture, the
 * domain layer is sometimes also called:
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Business_logic">Business</a>
 * layer</li>
 * <li><a href="http://en.wikipedia.org/wiki/Business_logic">Business logic</a>
 * layer</li>
 * <li><a href="http://en.wikipedia.org/wiki/Domain_model">Domain model</a>
 * layer</li>
 * </ul>
 * <p>
 * The domain layer is basically where all the domain objects are. The
 * {@link DomainContainer} is an {@link DependencyInjectionContainer} that can
 * be used to create and hold {@link DomainObject}'s that need dependency
 * injection.
 * </p>
 * Note that the {@link DomainLayer} is a middle layer (see
 * {@link ReflectArchitecture}):
 * <ul>
 * <li>The {@link DomainObject}s have NO references to objects in the upper
 * {@link UserInterfaceLayer} nor {@link ServiceLayer}</li>
 * <li>The {@link DomainObject}s may have references to the objects in the lower
 * {@link InfrastructureLayer} or {@link ProviderLayer}, but not visa
 * versa!</li>
 * </ul>
 * 
 * <h2>Domain Objects</h2> {@insert DomainObject}
 * 
 * @author Nils ten Hoeve
 * @see ReflectArchitecture
 */

public interface DomainLayer extends ReflectDocumentationInterface {

}
