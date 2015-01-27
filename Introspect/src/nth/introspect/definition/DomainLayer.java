package nth.introspect.definition;

import nth.introspect.container.impl.DomainContainer;

//TODO synchronize with manual

/**
 * This interface serves for documentation purposes only. <br>
 * <br>
 * The {@link DomainLayer} is part if the {@link IntrospectArchitecture}. <br>
 * The {@link DomainLayer} is also know as:
<ul><li>Business layer</li>
<li>business logic layer</li>
<li>domain model layer</li></ul>
 * The {@link DomainLayer} contains {@link DomainObject}s.<br>
 * The {@link DomainLayer} represents concepts of the business, information about the business situation, and business rules. State that reflects the business situation is controlled and used here, even though the technical details of storing it are delegated to the infrastructure. This layer is the
heart of business software.
<br>
 * The {@link DomainLayer} is implemented by the {@link DomainContainer}<br>
 * Note that this layer is a middle layer: the {@link DomainObject}s have no knowledge of the objects in the higher layers ({@link UserInterfaceLayer} or {@link ServiceLayer} ) , the {@link DomainObject}s may know the objects in the lower {@link InfrastructureLayer} but not visa versa! See {@link IntrospectArchitecture
  
 * @author Nils ten Hoeve
 * 
 */

public interface DomainLayer {

}
