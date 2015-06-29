package nth.introspect.documentation;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.impl.DomainContainer;
import nth.introspect.container.impl.ServiceContainer;

/**
 * The Domain layer is the hart of any Introspect application. The domain layer
 * represents concepts of the business, information about the business
 * situation, and business rules. State that reflects the business situation is
 * controlled and used here.<br>
 * <br>
 * Because there is many debate on the naming within a layered architecture, the
 * domain layer is sometimes also called:
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Business_logic">Business</a> layer</li>
 * <li><a href="http://en.wikipedia.org/wiki/Business_logic">Business logic</a>
 * layer</li>
 * <li><a href="http://en.wikipedia.org/wiki/Domain_model">Domain model</a>
 * layer</li>
 * </ul>
 * <p>
 * The domain layer is basically where all the domain objects are. The {@link DomainContainer} is an {@link IntrospectContainer} that
 * that can be used to create and hold {@link DomainObject}'s that need dependency injection.
 * </p>
 * Note that the {@link DomainLayer} is a middle layer (see
 * {@link IntrospectArchitecture}):
 * <ul>
 * <li>
 * The {@link DomainObject}s have NO references to objects in the upper
 * {@link UserInterfaceLayer} nor {@link ServiceLayer}</li>
 * <li>The {@link DomainObject}s may have references to the objects in the lower
 * {@link InfrastructureLayer} or {@link ProviderLayer}, but not visa versa!</li>
 * </ul>
 * 
 * <h2>{@link DomainObject}s</h2> {@insert DomainObject}
 * 
 * @author Nils ten Hoeve
 * @see IntrospectArchitecture
 */

public interface DomainLayer extends Documentation {

}
