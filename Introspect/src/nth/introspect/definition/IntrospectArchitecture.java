package nth.introspect.definition;

import nth.introspect.container.IntrospectContainer;


/**
 * This interface serves for documentation purposes only. <br>
 * <br>
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
 * These multi layer architecture design principles try to prevent <a
 * href="http://en.wikipedia.org/wiki/Spaghetti_code"> spaghetti code</a>, which
 * is hard to extend, hard to trouble shoot, hard to test, hard to keep <a
 * href="http://en.wikipedia.org/wiki/Software_bug#Etymology">bug</a> free.<br>
 * <br>
 * 
 * There are many different opinions on how a multi layered architecture should
 * <a href="https://www.google.nl/search?q=layered+architecture&tbm=isch">look
 * like</a>. It really doesn’t matter what it should look like, as long as your
 * multi layered architecture complies with the design principles above. <br>
 * 
 * The {@link IntrospectArchitecture} uses the following layer definition (you are free to use different layers or layer names): 
 * <ul>
 * <li>{@link UserInterfaceLayer}</li>
 * <li>{@link ServiceLayer}</li>
 * <li>{@link DomainLayer}</li>
 * <li>{@link InfrastructureLayer}</li>
 * <li>{@link ProviderLayer}</li>
 * </ul>
 * 
 * Each layer is implemented by a {@link IntrospectContainer}
 * 
 * 
 * @author Nils ten Hoeve
 * 
 */

public interface IntrospectArchitecture {

}
