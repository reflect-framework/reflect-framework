package nth.introspect.documentation;

import nth.introspect.container.impl.ServiceContainer;

//TODO synchronize with manual
/**
 * The {@link ServiceLayer} is part if the {@link IntrospectArchitecture}. <br>
 * The {@link ServiceLayer} is also know as application layer.<br>
 * The {@link ServiceLayer} contains {@link ServiceObject}s.<br>
 * The {@link ServiceLayer} defines the jobs the software is supposed to do and
 * directs the expressive domain objects to work out problems. The tasks this
 * layer is responsible for are meaningful to the business or necessary for
 * interaction with the application layers of other systems. This layer is kept
 * thin (see Martin Fowlers article on <a href="http://martinfowler.com/bliki/AnemicDomainModel.html">Anemic Domain Model</a> on why the service
 * layer should be thin). It does not contain business rules or knowledge, but
 * only coordinates tasks and delegates work to collaborations of domain objects
 * in the next layer down. It does not have state reflecting the business
 * situation, but it can have state that reflects the progress of a task for the
 * user or the program.<br><br>
 * The {@link ServiceLayer} is implemented by the {@link ServiceContainer}<br>
 * Note that this layer is a middle layer: the {@link ServiceObject}s have no knowledge of the {@link UserInterfaceLayer} , the {@link ServiceObject}s may know the objects in the lower layers but not visa versa! See {@link IntrospectArchitecture
  
 * @author Nils ten Hoeve
 * 
 */
public interface ServiceLayer extends Documentation{

}
