package nth.introspect.documentation;

import nth.introspect.Introspect;
import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;

/** 
 * 
 *Introspect is a light weight <a href="http://en.wikipedia.org/wiki/Java_(programming_language)">Java</a> <a href="http://en.wikipedia.org/wiki/Application_framework">Application Framework</a>. It provides a framework for writing business like applications for viewing and editing information in forms and tables (not for graphical application such as games)<br><br>
*With the introspect framework you only need to create domain classes. Introspect provides you the user interface, without writing any user interface code. This means is that the Introspect Framework is ideal to learn programming (e.g. at schools), rapid prototyping or rapid development.<br>
* <br>
*  The Introspect framework provides different user interface implementations that you can use for: 
*<ul>
*<li>The desktop (based on <a href="http://en.wikipedia.org/wiki/Swing_(Java)">Swing</a>)</li>
*<li>Mobile devices (based on <a href="http://en.wikipedia.org/wiki/Android_(operating_system)">Android</a>)</li>
*<li>The web (based on <a href="http://en.wikipedia.org/wiki/Vaadin">Vaadin</a>)</li> 
*<li><a href="http://en.wikipedia.org/wiki/Command-line_interface">Command line</a></li>
*<li>And others</li>
*</ul>
*If you want to start coding right now (even if you are a beginner) please go to {@linkplain IntrospectGettingStarted}. If you want to know more about the Introspect Framework continue reading....
*
*<h2>Why the Introspect framework was developed</h2>
*Almost everyday new libraries, frameworks and tools are being released by the developer community, many of which reinvent the wheel.<br><br>
*This is called the "Yet Another Framework Syndrome" (YAFS), or in more general terms "Not Invented Here" (NIH). While innovation is something we should all welcome, YAFS can lead to confusion and frustration for users because there's a big risk of it introducing more noise than value.<br><br> 
* So why did I develop a new framework while there are so many Java application frameworks out there?
* <h3>Reason 1: Because its fun</h3>
* Probably the best reason ever!
* <h3>Reason 2: Because I wanted to learn</h3> 
*The journey of developing yet another framework has thought me more than I could have learned implementing an existing framework. Specifically on how other frameworks solved issues that I run into.
*<h3>Reason 3: Because I wanted to do it better</h3>
*I love the thoughts behind the <a href="http://nakedobjects.codeplex.com/">Naked Objects Framework</a> (for <a href="http://en.wikipedia.org/wiki/.NET_Framework">.net</a>) and the <a href="http://isis.apache.org/">Apache Isis Framework</a> (for <a href="http://en.wikipedia.org/wiki/Java_(programming_language)">Java</a>). But looking at the Apache Isis Framework, there are several things I personally dislike which I tried to do better in the Introspect framework. Note that what I dislike does not mean its wrong!
*<ul>
*<li>The <a href="http://en.wikipedia.org/wiki/Business_logic">domain objects</a> and service objects (often repository objects) usually extend a convenience class that contains methods to interact with the Apache Isis framework/ object container. Extending such a class is not mandatory, because you can implement these methods in your objects, but to me this still ignores the principle of "Naked objects" or <a href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">POJO’s</a> .</li>
*<li>Apache Isis’s uses (depends on) Maven. Maven has its pros (managing dependencies, and standardizing the build process), but is also famous for its cons (difficult to configure, steep learning curve, etc, etc).</li>
*<li>I personally dislike the way Apache Isis manages the editing of objects. This is very tightly linked to its persistence framework. I however believe that objects do not necessarily need to be persisted after its been edited. I prefer a different approach: domain objects can be passed to a method as a method parameter. This method parameter can be edited by a user before a method is executed (depending on how the method is annotated). The domain object/ method parameter may then be handled by a {@link ServiceObject} like a persistence service (or not at all).</li>  
*</ul>
*<h3>Reason 4: I could not find what I needed.</h3>
*I have not found an framework that provides an out of the box User Interface for both desktop, mobile devices, web interface, command line interface, etc. They are probably out there (Naked Objects and Isis coming close) but I haven't found one (or one that I liked). 
*<h2>Introspect Core Values</h2>
*Introspect is designed around the following core values:
*<h3>Based on the Naked Objects Design Pattern</h3>
*See the Wiki page on the <a href="http://en.wikipedia.org/wiki/Naked_objects">Naked Objects Design Pattern</a>:
*<ul>
*<li>All business logic should be encapsulated onto the domain objects. This principle is not unique to naked objects: it is just a strong commitment to encapsulation.
*<li>The user interface should be a direct representation of the domain objects, with all user actions consisting, explicitly, of creating or retrieving domain objects and/or invoking methods on those objects. This principle is also not unique to naked objects: it is just a specific interpretation of an object-oriented user interface (OOUI).
*<li>The user interface should be created 100% automatically from the definition of the domain objects. Introspect uses reflection instead of code generation.
*</ul>
*<h3>Provide a good structure for applications</h3>
*<ul>
*<li>Enforce separation of concerns (see Architecture of an introspect application).</li>
*<li>Facilitate domain driven design.</li>
*<li>The Introspect Framework should not create a vendor lock-in (not that there is such a thing as an Introspect Framework Vendor, since it is open source). Domain objects, service objects and infrastructure objects should have no (are almost no) dependencies with the Introspect Framework so that the Introspect Framework could easily (within a few hours) be replaced with another dependency injection framework (such as Spring, Guice or Pico container).</li> 
*<li>The introspect framework exists of a few basic interfaces (providers) that can have multiple different implementations.</li>
*</ul>
*<h3>Lightweight</h3>
*<ul><li>The Introspect framework should only be a few kilobytes in size.</li></ul>
*<h3>Simple to configure</h3> 
*The Introspect framework has no configuration files (see “Code or configuration files” chapter in Martin Fowlers article for the arguments why).<br><br>
*There are 2 things that need to be configured:
*<ul>
*<li>How the framework is initialized.</li>
*<li>How the domain objects and service objects behave (how the domain needs to handled by Introspect framework)</li>
*</ul>
* Furthermore the Introspect framework prefers “Configure by Exception”. This means that the Introspect framework uses reasonable defaults wherever possible. These default values can be overridden by the developer.
* <h3>No dependencies with tools</h3>
*<ul><li>No dependencies with an Integrated Development Environment or build tool.</li></ul>
*<h2>Introspect License</h2>
*Introspect is an open source project under the GNU Lesser General Public License. The license can be found here. 
*<h2>Architecture of an introspect application</h2>
*{@insert IntrospectArchitecture} 
*<h2>Dependency Injection</h2>
*{@insert IntrospectContainer} 
*<h2>Initializing the framework</h2>
*{@insert Introspect} 
*<h2>The Introspect Application</h2>
*{@insert IntrospectApplication} 
 */


public interface IntrospectFramework extends Documentation {

	}
