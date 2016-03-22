package nth.introspect.documentation;

import nth.introspect.IntrospectFramework;
import nth.introspect.layer4infrastructure.InfrastructureObject;

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
 * <h3>Reason 3: Because I wanted to do it different</h3> I love the thoughts
 * behind the <a href="http://nakedobjects.codeplex.com/">Naked Objects
 * Framework</a> (for <a
 * href="http://en.wikipedia.org/wiki/.NET_Framework">.net</a>) and the <a
 * href="http://isis.apache.org/">Apache Isis Framework</a> (for <a
 * href="http://en.wikipedia.org/wiki/Java_(programming_language)">Java</a>).
 * But looking at the Apache Isis Framework, there are several things I
 * wanted to do it different in the {@link IntrospectFramework} (good or bad is all open for debate):
 * <ul>
 * <li>The <a href="http://en.wikipedia.org/wiki/Business_logic">domain
 * objects</a> and service objects (often repository objects) usually extend a
 * convenience class that contains methods to interact with the Apache Isis
 * framework/ object container. Extending such a class is not mandatory, because
 * you can implement these methods in your objects, but to me this still ignores
 * the principle of "Naked objects" or <a
 * href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">POJO’s</a> .</li>
 * <li><a href="http://isis.apache.org/">Apache Isis</a> uses (depends on) <a
 * href="http://en.wikipedia.org/wiki/Apache_Maven">Maven</a>. Maven has its
 * pros (managing dependencies, and standardizing the build process), but is
 * also famous for its cons (difficult to configure, steep learning curve, etc,
 * etc).</li>
 * <li>I personally dislike the way Apache Isis manages the editing of objects.
 * This is very tightly linked to its <a
 * href="http://en.wikipedia.org/wiki/Persistence_framework">persistence
 * framework</a>. I however believe that objects do not necessarily need to be
 * persisted after its been edited. I prefer a different approach: domain
 * objects can be passed to a method as a method parameter. This method
 * parameter can be edited by a user before a method is executed (depending on
 * how the method is annotated). The domain object/ method parameter may then be
 * handled by a {@link InfrastructureObject} like a persistence service (or not
 * at all).</li>
 * </ul>
 * <h3>Reason 4: I could not find what I needed.</h3> I have not found an
 * framework that provides an out of the box User Interface for both desktop,
 * mobile devices, web interface, command line interface, etc. They are probably
 * out there (Naked Objects and Isis coming close) but I haven't found one (or
 * one that I liked).
 */

public interface IntrospectWhyItWasDeveloped extends Documentation {

}
