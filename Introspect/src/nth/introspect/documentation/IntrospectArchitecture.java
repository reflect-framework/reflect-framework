package nth.introspect.documentation;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.layer1userinterface.UserInterfaceLayer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceLayer;
import nth.introspect.layer3domain.DomainLayer;
import nth.introspect.layer4infrastructure.InfrastructureLayer;
import nth.introspect.layer5provider.ProviderLayer;

/**
 * The Introspect Framework helps you to create a <a
 * href="http://en.wikipedia.org/wiki/Multilayered_architecture" >multi layer
 * architecture</a> for your application. A multi layer architecture has several
 * <a href="http://martinfowler.com/bliki/LayeringPrinciples.html"> design
 * principles</a>:
 * <ul>
 * <li>Separation of concerns (separate responsibilities within the application
 * into different layers).</li>
 * <li>Low coupling between layers, high cohesion within them.</li>
 * <li>User interface modules should contain no business logic.</li>
 * <li>Layers should be testable individual.</li>
 * <li>Business logic layers contain no user interface and don't refer to user
 * interface modules.</li>
 * <li>No circular references between layers.</li>
 * <li>Business layer only uses abstractions of technological services.</li>
 * <li>Lower layers should not depend on upper layers.</li>
 * <li>Layers should be shy about their internals.</li>
 * <li>Layers may share infrastructural aspects (eg security)</li>
 * <li>Inbound external interface modules (eg web service handlers) should not
 * contain business logic.</li>
 * </ul>
 * <p>
 * These multi layer architecture design principles try to prevent <a
 * href="http://en.wikipedia.org/wiki/Spaghetti_code"> spaghetti code</a>, which
 * is hard to extend, hard to trouble shoot, hard to test, hard to keep <a
 * href="http://en.wikipedia.org/wiki/Software_bug#Etymology">bug</a> free.<br>
 * </p>
 * 
 * <p>
 * There are many different opinions and definitions on the number of layers,
 * the names of the layers and what each layer should do (see these <a
 * href="https://www.google.nl/search?q=layered+architecture&tbm=isch"
 * >examples</a>). It would be nice if everyone would use the same model and
 * naming. I think that <a href="http://www.herrodius.com/blog/wp-content/uploads/2010/03/LayeredArchitecture.png">the definition of layers</a> from Eric Evans <a href="https://en.wikipedia.org/wiki/Domain-driven_design">Domain Driven Design approach</a> is the most commonly
 * used. The most important thing is that your multi layered architecture
 * complies with the design principles above.
 * </p>
 * 
 * <p>
 * The {@link IntrospectArchitecture} uses the following layer definition, which
 * is pretty close to <a
 * href="https://en.wikipedia.org/wiki/Domain-driven_design">Eric Evans Domain
 * Driven Design</a> approach.:
 * <ul>
 * <li>{@link UserInterfaceLayer}</li>
 * <li>{@link ServiceLayer}</li>
 * <li>{@link DomainLayer}</li>
 * <li>{@link InfrastructureLayer}</li>
 * <li>{@link ProviderLayer}</li>
 * </ul>
 * </p>
 * Each layer is implemented by an {@link IntrospectContainer}<br>
 * <br>
 * 
 * <img src="IntrospectArchitecture.png"><br>
 * <br>
 * 
 * Red objects are provided by the Introspect framework<br>
 * Yellow objects need to be written or included by the developer.
 * 
 * @author Nils ten Hoeve
 * 
 */

public interface IntrospectArchitecture extends Documentation {

}
