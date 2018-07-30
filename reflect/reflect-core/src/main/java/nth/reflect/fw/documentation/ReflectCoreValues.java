package nth.reflect.fw.documentation;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer4infrastructure.InfrastructureObject;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.reflection.behavior.ObjectBehavior;

/**
 * The {@link ReflectFramework} is designed around the following core values:
 * <h3>Based on the Naked Objects Design Pattern</h3> See the Wiki page on the
 * <a href="http://en.wikipedia.org/wiki/Naked_objects">Naked Objects</a>
 * <a href="https://en.wikipedia.org/wiki/Architectural_pattern">Architectural
 * pattern</a>:
 * <ul>
 * <li>All <a href="http://en.wikipedia.org/wiki/Business_logic">business
 * logic</a> should be encapsulated onto the <a href=
 * "http://en.wikipedia.org/wiki/Business_object_(computer_science)">domain
 * objects</a>. This principle is not unique to naked objects: it is just a
 * strong commitment to <a href=
 * "http://en.wikipedia.org/wiki/Encapsulation_%28object-oriented_programming%29"
 * >encapsulation</a>.</li>
 * <li>The <a href="http://en.wikipedia.org/wiki/User_interface">user
 * interface</a> should be a direct representation of the {@link DomainObject}s,
 * with all user actions consisting, explicitly, of creating or retrieving
 * {@link DomainObject}s and/or invoking
 * <a href="http://en.wikipedia.org/wiki/Method_(computer_science)">methods</a>
 * on those objects. This principle is also not unique to naked objects: it is
 * just a specific interpretation of an
 * <a href="http://en.wikipedia.org/wiki/Object-oriented_user_interface"
 * >object-oriented user interface (OOUI)</a>.</li>
 * <li>The user interface should be created 100% automatically from the
 * definition of the {@link DomainObject}s.
 * <a href="http://en.wikipedia.org/wiki/Reflection_(computer_science)"
 * >Reflection</a> is used instead of code generation. Hence the name of the
 * {@link ReflectFramework}: The framework reflects the code of the
 * {@link ServiceObject}s and {@link DomainObject}s to the user interface.</li>
 * </ul>
 * <h3>Written in Java</h3> At the time of this writing,
 * <a href="https://java.com">Java</a> is still the <a href=
 * "https://www.codingdojo.com/blog/7-most-in-demand-programming-languages-of-2018/">most
 * used programming language</a>. Most developers are able to write (or read)
 * Java code. There are many Java libraries available that can be used in the
 * application, e.g. for:
 * <ul>
 * <li>Testing or improving code</li>
 * <li>Logging</li>
 * <li>Storing or retreiving data (e.g. JPA)</li>
 * <li>PDF and M$ Excel reading and writing</li>
 * <li>email clients</li>
 * <li>etc</li>
 * </ul>
 * 
 * <h3>Provide a good structure for applications</h3>
 * <ul>
 * <li>Enforce
 * <a href="http://en.wikipedia.org/wiki/Separation_of_concerns">separation of
 * concerns</a> (see {@link ReflectArchitecture}).</li>
 * <li>Facilitate
 * <a href="http://en.wikipedia.org/wiki/Domain-driven_design">domain driven
 * design</a>.</li>
 * <li>The {@link ReflectFramework} should not create a
 * <a href="http://en.wikipedia.org/wiki/Vendor_lock-in">vendor lock-in</a> (not
 * that there is such a thing as an {@link ReflectFramework} Vendor, since it is
 * open source). {@link DomainObject}'s, {@link ServiceObject}'s and
 * {@link InfrastructureObject}'s should have no (or almost no) dependencies
 * with the {@link ReflectFramework} so that it could easily (within a few
 * hours) be replaced with another dependency injection framework (such as
 * <a href="http://en.wikipedia.org/wiki/Spring_Framework">Spring</a>,
 * <a href="http://en.wikipedia.org/wiki/Google_Guice">Guice</a> or
 * <a href="http://picocontainer.codehaus.org/">Pico container</a>, etc...) or
 * visa versa.</li>
 * <li>The {@link ReflectFramework} must accept {@link ServiceObject}s,
 * {@link DomainObject}s, {@link InfrastructureObject}s that are
 * <a href="https://en.wikipedia.org/wiki/Plain_old_Java_object">Plain Old Java
 * Objects (POJO's)</a>.</li>
 * <li>The {@link ReflectFramework} exists of a few basic interfaces like
 * {@link UserInterfaceController} and {@link Provider}s that can have multiple
 * different implementations.</li>
 * </ul>
 * 
 * <h3>Have a modern graphical user interface</h3>
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/Responsive_web_design">Responsive
 * Design</a>: An optimized user experience on a variety of devices and screen
 * sizes.</li>
 * <li><a href="https://en.wikipedia.org/wiki/Flat_design">Flat design</a>: A
 * minimalist graphical user interface that does not distract the user in
 * executing his or her tasks, e.g. like Google's
 * <a href="https://material.io/design/">Material Design</a></li>
 * </ul>
 * 
 * <h3>Write once and deploy as different type of applications</h3>
 * <p>
 * The {@link ReflectFramework} allows you to write your code once, and deploy
 * it as different types of applications without much of a hassle. See the
 * {@link ReflectApplicationProjects} for more information.
 * </p>
 * 
 * <h3>Simple to configure</h3> The {@link ReflectFramework} has no
 * configuration files (see <a href=
 * "http://www.martinfowler.com/articles/injection.html#CodeOrConfigurationFiles"
 * >“Code or configuration files”</a> section in Martin Fowlers article for the
 * arguments why).<br>
 * <br>
 * There are 3 things that need to be configured:
 * <ul>
 * <li>Which implementations of the {@link UserInterfaceController} and
 * {@link Provider}'s are going to be used by the {@link ReflectApplication}.
 * </li>
 * <li>Which {@link ServiceObject} classes and {@link InfrastructureObject}
 * classes are going to be used by the {@link ReflectApplication}.</li>
 * <li>The {@link ObjectBehavior} of {@link DomainObject}'s and
 * {@link ServiceObject} 's (how the domain needs to handled by the
 * {@link ReflectApplication})</li>
 * </ul>
 * <p>
 * Furthermore the {@link ReflectFramework} prefers “Configure by Exception”.
 * This means that the {@link ReflectFramework} uses reasonable defaults
 * wherever possible. These default values can be overridden by the developer.
 * </p>
 * 
 * <h3>Lightweight</h3>
 * <p>
 * The {@link ReflectFramework} is aimed to have a small distribution size.
 * </p>
 * 
 * <h3>Open source and free</h3>
 * <p>
 * See the {@link ReflectLicense}.
 * </p>
 */

public interface ReflectCoreValues extends ReflectDocumentationInterface {

}
