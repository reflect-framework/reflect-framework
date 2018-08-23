package nth.reflect.fw.documentation;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer4infrastructure.InfrastructureObject;

/**
 * <p>
 * Almost everyday new libraries, frameworks and tools are being released by the
 * developer community, many of which reinvent the wheel.
 * </p>
 * <p>
 * This is called the "Yet Another Framework Syndrome" (YAFS), or in more
 * general terms "Not Invented Here" (NIH). While innovation is something we
 * should all welcome, YAFS can lead to confusion and frustration for users
 * because there's a big risk of it introducing more noise than value.<br>
 * </p>
 * <p>
 * So why did I develop a new framework while there are so many Java application
 * frameworks out there?
 * </p>
 * <h3>Reason 1: Because its fun</h3> Probably the best reason ever!
 * <h3>Reason 2: Because I wanted to learn</h3> The journey of developing yet
 * another framework has thought me more than I could have learned implementing
 * an existing framework. Specifically on how other frameworks solved issues
 * that I run into.
 * <h3>Reason 3: Because I could not find what I was looking for</h3> There are
 * already several frame works that are based on the
 * <a href="https://en.wikipedia.org/wiki/Naked_objects">Naked Objects</a>
 * pattern, e.g.:
 * <ul>
 * <li><a href=
 * "https://github.com/NakedObjectsGroup/NakedObjectsFramework">Naked Objects
 * <a> (for .net)</li>
 * <li><a href="https://isis.apache.org/">Apache Isis</a>
 * <a href="https://isis.apache.org/guides/ugvw/ugvw.html">Wicket Viewer</a>
 * (for Java)</li>
 * <li><a href="http://metawidget.sourceforge.net/screenshots.php">MetaWidget
 * </a> (for Java)</li>
 * <li><a href="http://www.openxava.org">Openxava</a> (for Java)</li>
 * </ul>
 * These frame works have merit on their own, but they did not have all the
 * things I was looking for (see {@link ReflectCoreValues}).
 * 
 * Specifically:
 * 
 * <h3>Editing objects without JPA dependency</h3> I personally dislike the way
 * Apache Isis manages the editing of objects. This is very tightly linked to
 * its <a href="http://en.wikipedia.org/wiki/Persistence_framework">persistence
 * framework</a>. I however believe that objects do not necessarily need to be
 * persisted after its been edited. I prefer a different approach: domain
 * objects can be passed to a method as a method parameter. This method
 * parameter can be edited by a user before a method is executed (depending on
 * how the method is annotated). The domain object/ method parameter may then be
 * handled by a {@link InfrastructureObject} like a persistence service (or not
 * at all).
 * 
 * <h3>Missing a good graphical user interface.</h3> In my opinion (at the time
 * of this writing), none of these graphical user interfaces meet modern design
 * principles like:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/Responsive_web_design">Responsive
 * Design</a>: An optimized user experience on a variety of devices and screen
 * sizes.</li>
 * <li><a href="https://en.wikipedia.org/wiki/Flat_design">Flat design</a>: A
 * minimalist graphical user interface that does not distract the user execute
 * its tasks, e.g. like Google's <a href="https://material.io/design/">Material
 * Design</a></li>
 * </ul>
 * 
 * <h3>Missing a Naked Objects WORA framework</h3>
 * <a href="https://java.com/">Java</a> was developed with the
 * <a href="https://en.wikipedia.org/wiki/Write_once,_run_anywhere">Write Once
 * Run Anywhere (WORA)</a> philosophy. A lot has changed since. So does WORA
 * still apply for Java? The answer: Yes and No:
 * <ul>
 * <li>Yes: The
 * <a href="https://en.wikipedia.org/wiki/Java_virtual_machine">Java Virtual
 * Machine (JVM)</a> can run on almost all devices (from PC to devices out in
 * space) and has been very successful on the server side.</li>
 * <li>No: Apple does not allow the Oracle JVM on its devices and Android
 * devices use Googles JVM which is different. Furthermore: It is nearly
 * impossible to write a GUI for a desktop, and deploy it as a web application
 * or mobile application or vice-versa</li>
 * </ul>
 * Frameworks like <a href="https://facebook.github.io/react-native/">React
 * Native</a>, <a href="https://flutter.io">Google Flutter</a> and
 * <a href="https://www.codenameone.com">Codename One</a> all aim to write code
 * once and execute it on different platforms. The need for such frameworks is
 * obvious: Most of us want our applications to run on as many devices as
 * possible, without having to re-write test and release the (GUI) application
 * for multiple platforms, because this is tedious, no value added, costly work.
 * </p>
 * <p>
 * At the time of this writing, none of the existing Naked Object frameworks can
 * be dispatched to different platforms (e.g. the command line, desktop, mobile
 * devices and the web). The {@link ReflectFramework} lets you wrap your
 * {@link ServiceObject}s, {@link DomainObject}s and
 * {@link InfrastructureObject}s, in several native implementations of the
 * {@link ReflectFramework}. For more information see:
 * {@link ReflectApplicationProjects}
 * </p>
 */

public interface ReflectWhyItWasDeveloped extends ReflectDocumentationInterface {

}
