package nth.introspect.documentation;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.Provider;

/**
 * Introspect is designed around the following core values: <h3>Based on the
 * Naked Objects Design Pattern</h3> See the Wiki page on the <a
 * href="http://en.wikipedia.org/wiki/Naked_objects">Naked Objects Design
 * Pattern</a>:
 * <ul>
 * <li>All <a href="http://en.wikipedia.org/wiki/Business_logic">business
 * logic</a> should be encapsulated onto the <a
 * href="http://en.wikipedia.org/wiki/Business_object_(computer_science)">domain
 * objects</a>. This principle is not unique to naked objects: it is just a
 * strong commitment to <a href=
 * "http://en.wikipedia.org/wiki/Encapsulation_%28object-oriented_programming%29"
 * >encapsulation</a>.</li>
 * <li>The <a href="http://en.wikipedia.org/wiki/User_interface">user
 * interface</a> should be a direct representation of the domain objects, with
 * all user actions consisting, explicitly, of creating or retrieving domain
 * objects and/or invoking <a
 * href="http://en.wikipedia.org/wiki/Method_(computer_science)">methods</a> on
 * those objects. This principle is also not unique to naked objects: it is just
 * a specific interpretation of an <a
 * href="http://en.wikipedia.org/wiki/Object-oriented_user_interface"
 * >object-oriented user interface (OOUI)</a>.</li>
 * <li>The user interface should be created 100% automatically from the
 * definition of the domain objects. Introspect uses <a
 * href="http://en.wikipedia.org/wiki/Reflection_(computer_science)"
 * >reflection</a> instead of code generation.</li>
 * </ul>
 * <h3>Provide a good structure for applications</h3>
 * <ul>
 * <li>Enforce <a
 * href="http://en.wikipedia.org/wiki/Separation_of_concerns">separation of
 * concerns</a> (see {@link IntrospectArchitecture}).</li>
 * <li>Facilitate <a
 * href="http://en.wikipedia.org/wiki/Domain-driven_design">domain driven
 * design</a>.</li>
 * <li>The Introspect Framework should not create a <a
 * href="http://en.wikipedia.org/wiki/Vendor_lock-in">vendor lock-in</a> (not
 * that there is such a thing as an Introspect Framework Vendor, since it is
 * open source). {@link DomainObject}'s, {@link ServiceObject}'s and
 * {@link InfrastructureObject}'s should have no (are almost no) dependencies
 * with the Introspect Framework so that the Introspect Framework could easily
 * (within a few hours) be replaced with another dependency injection framework
 * (such as <a href="http://en.wikipedia.org/wiki/Spring_Framework">Spring</a>,
 * <a href="http://en.wikipedia.org/wiki/Google_Guice">Guice</a> or <a
 * href="http://picocontainer.codehaus.org/">Pico container</a>) or visa versa.</li>
 * <li>The introspect framework exists of a few basic interfaces like
 * {@link UserInterfaceController} and {@link Provider}s that can have multiple
 * different implementations.</li>
 * </ul>
 * <h3>Lightweight</h3>
 * <ul>
 * <li>The Introspect framework should only be a few kilobytes in size.</li>
 * </ul>
 * <h3>Simple to configure</h3> The Introspect framework has no configuration
 * files (see <a href=
 * "http://www.martinfowler.com/articles/injection.html#CodeOrConfigurationFiles"
 * >“Code or configuration files”</a> chapter in Martin Fowlers article for the
 * arguments why).<br>
 * <br>
 * There are 3 things that need to be configured:
 * <ul>
 * <li>Which implementations of the {@link UserInterfaceController} and
 * {@link Provider}'s are going to be used by the framework.</li>
 * <li>Which {@link ServiceObject} classes and {@link InfrastructureObject}
 * classes are going to be used by the framework.</li>
 * <li>The {@link Behavior} of {@link DomainObject}'s and {@link ServiceObject}
 * 's (how the domain needs to handled by Introspect framework)</li>
 * </ul>
 * Furthermore the Introspect framework prefers “Configure by Exception”. This
 * means that the Introspect framework uses reasonable defaults wherever
 * possible. These default values can be overridden by the developer. <h3>No
 * dependencies with tools</h3>
 * <ul>
 * <li>No dependencies with an Integrated Development Environment or build tool.
 * </li>
 * </ul>
 */

public interface IntrospectCoreValues extends Documentation {

}
