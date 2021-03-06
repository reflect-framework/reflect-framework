package nth.reflect.fw.documentation;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;

/**
 * <p>
 * There are different implementations of the {@link ReflectApplication} class,
 * to support different type of applications (e.g. for the command line, web
 * applications, etc...). This chapter will explain the different
 * {@link ReflectApplication}, and how to get started with them. If needed you
 * can also write your own implementation of the {@link ReflectApplication}
 * class.
 * </p>
 * 
 * <h2>Setting up your development computer</h2>
 * <p>
 * First step is installing the following software on your development computer
 * (if you did not do so already):
 * <ul>
 * <li><b>Operating system:</b> The {@link ReflectFramework} was created and
 * tested on <a href="https://en.wikipedia.org/wiki/Microsoft_Windows">Microsoft
 * Windows</a>. It is therefore recommended to use
 * <a href="https://en.wikipedia.org/wiki/Microsoft_Windows">Microsoft
 * Windows</a> for your development computer. You might also be able to use
 * another operating system, because both
 * <a href="https://en.wikipedia.org/wiki/Java_(programming_language)">Java</a>
 * and <a href=
 * "https://en.wikipedia.org/wiki/Integrated_development_environment">Integrated
 * Development Environments</a> will work on multiple operating systems. This
 * however never was tested.</li>
 * <li><b>Java Development Kit:</b> You need to install a
 * <a href="https://en.wikipedia.org/wiki/Java_Development_Kit">Java Development
 * Kit</a> on your development computer in order to
 * <a href="https://en.wikipedia.org/wiki/Compiler">compile</a> and run
 * <a href="https://en.wikipedia.org/wiki/Java_(programming_language)">Java
 * code</a>. There are multiple Vendors that supply OpenJdk versions. The
 * {@link ReflectFramework} was created and tested with AdoptOpenJDK version 11
 * and is therefore recommended for your development computer. You can install
 * and use it for free by downloading the correct version for your operating
 * system from <a href= "https://adoptopenjdk.net/">adoptopenjdk.net</a>.</li>
 * <li><b>Integrated Development Environment:</b> You need to install a <a href=
 * "https://en.wikipedia.org/wiki/Integrated_development_environment">Integrated
 * Development Environment</a> on your development computer. The
 * {@link ReflectFramework} was created with Eclipse and this is therefore
 * recommended for your development computer. You can install and use Eclipse
 * for free by downloading the installer from <a href=
 * "https://www.eclipse.org/downloads/">https://www.eclipse.org/downloads/</a>.
 * You might also be ale to use another <a href=
 * "https://en.wikipedia.org/wiki/Integrated_development_environment">Integrated
 * Development Environment</a>. This however never was tested.</li>
 * <li><b>NodeJs:</b> NodeJs is a javascript framework that you will need to
 * install when using the {@link ReflectApplicationForVaadin14}. It can be
 * downloaded from:
 * <a href="https://nodejs.org/en/">https://nodejs.org/en/</a>. It automatically
 * comes with NPM: a Node Package Manager for downloading java script libraries.
 * </li>
 * </ul>
 * 
 * <!-- TODO add youtube film -->
 * 
 * <h2>Reflect for the Web</h2>
 * <p>
 * {@insert ReflectApplicationForVaadin14}
 * 
 * <h2>Reflect for JavaFx</h2>
 * <p>
 * {@insert ReflectApplicationForJavaFX}
 * 
 * <h2>Reflect for CommandLine</h2>
 * <p>
 * {@insert ReflectApplicationForCommandLine}
 * 
 * <h2>Reflect for JUnit</h2>
 * <p>
 * {@insert ReflectApplicationForJUnit}
 * 
 * <h2>Possible future projects</h2>
 * <p>
 * This is a list of possible future projects (still to be developed)
 * 
 * <h3>Reflect for Android</h3>
 * <p>
 * An application with a
 * <a href="http://en.wikipedia.org/wiki/Graphical_user_interface"> graphical
 * user interface</a> for
 * <a href="https://en.wikipedia.org/wiki/Mobile_device">mobile devices</a> such
 * as <a href="https://en.wikipedia.org/wiki/Smartphone">smart phones</a> and
 * <a href="https://en.wikipedia.org/wiki/Tablet_computer">tablets</a>.
 * 
 * <h3>Reflect for SOAP</h3>
 * <p>
 * A <a href="https://en.wikipedia.org/wiki/Web_service">web service</a> that
 * allows other computer applications to interact via the
 * <a href="http://en.wikipedia.org/wiki/SOAP">Simple Object Access Protocol
 * (SOAP)</a>
 * 
 * <h3>Reflect for RESTfull XML</h3>
 * <p>
 * A <a href="https://en.wikipedia.org/wiki/Web_service">web service</a> that
 * allows other computer applications to interact using
 * <a href="http://en.wikipedia.org/wiki/Representational_state_transfer" >
 * Representational State Transfer (RESTfull)</a> with
 * <a href="https://en.wikipedia.org/wiki/XML">XML</a>
 * 
 * <h3>Reflect for RESTfull JSON</h3>
 * <p>
 * A <a href="https://en.wikipedia.org/wiki/Web_service">web service</a> that
 * allows other computer applications to interact using
 * <a href="http://en.wikipedia.org/wiki/Representational_state_transfer" >
 * Representational State Transfer (RESTfull)</a> with
 * <a href="https://en.wikipedia.org/wiki/JSON">JSON</a>
 * 
 * @author nilsth
 *
 */
public interface ReflectApplicationProjects extends ReflectDocumentationInterface {

}
