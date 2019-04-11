package nth.reflect.ui.vaadin;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.server.VaadinSession;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.about.AboutProvider;
import nth.reflect.fw.layer5provider.about.DefaultAboutProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.DefaultNotificationProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.DefaultReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.url.application.ApplicationUrlProvider;
import nth.reflect.fw.layer5provider.url.classresource.ClassResourceUrlProvider;
import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrlProvider;
import nth.reflect.fw.layer5provider.validation.DefaultValidationProvider;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;
import nth.reflect.ui.vaadin.mainwindow.MainWindow;

/**
 * <p>
 * {@link ReflectApplicationForVaadin} is an implementation of the
 * {@link ReflectFramework} that provides a
 * <a href="http://en.wikipedia.org/wiki/Graphical_user_interface"> graphical
 * user interface</a> for
 * <a href="https://en.wikipedia.org/wiki/Web_application">web applications</a>,
 * using the <a href="https://vaadin.com/">Vaadin Framework</a>.
 * {@link ReflectApplicationForVaadin} tries to comply with the
 * <a href="https://material.io/guidelines/">Google Material Design</a> as much
 * as possible. The application can be used on a
 * <a href="https://en.wikipedia.org/wiki/Desktop_computer">desktop</a>,
 * <a href="https://en.wikipedia.org/wiki/Laptop">lap-top</a>,
 * <a href="https://en.wikipedia.org/wiki/Tablet_computer">tablet</a> or
 * <a href="https://en.wikipedia.org/wiki/Mobile_device">mobile device</a>. It
 * has an
 * <a href="https://en.wikipedia.org/wiki/Responsive_web_design">responsive web
 * design</a>: it optimizes the user interface depending on the size of the
 * {@link MainWindow}.
 * </p>
 * <p>
 * This class will be created and used by the
 * <a href="http://vaadin.com">Vaadin</a> framework when a new
 * {@link VaadinSession} for this application is created (after receiving a new
 * {@link HttpServletRequest} from a user that does not have a active
 * {@link VaadinSession}). It will: <ui>
 * <li>initialize the {@link ReflectFramework} with implemented methods from
 * this class</li>
 * <li>create the {@link MainWindow}</li>
 * </ul>
 * </p>
 * <h3>How to download a ReflectForJavaFX demo project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * <h3>How to create a new ReflectForJavaFX project</h3>
 * <p>
 * TODO
 * </p>
 * <h3>How to start the application in the IDE</h3>
 * <p>
 * You start the application with Mvn jetty:run<br>
 * E.g. in the Eclipse IDE create a debug configuration:
 * <ul>
 * <li>Ensure that you got a Java JDK installed and Eclipse knows where to find
 * it</li>
 * <ul>
 * <li>Ensure you have downloaded and installed a Java JDK on your computer (for
 * windows see: c:\program files\java)</li>
 * <li>On the Eclipse main menu click Window, Preferences, Java, Installed
 * JRE's</li>
 * <li>Add the JDK to the list if it is missing: Add, Standard VM, and select
 * the folder where the JDK is installed</li>
 * <li>Ensure the JDK is now in the list and selected as default</li>
 * </ul>
 * <li>Select Debug as</li>
 * <li>Select Maven Build...</li>
 * <li>Give the debug configuration a logical name: e.g.
 * <application-name>-run-jetty</li>
 * <li>On Source tab:</li>
 * <ul>
 * <li>Click add button</li>
 * <li>Select Java Project and click OK button</li>
 * <li>Select Your and click OK button</li>
 * </ul>
 * <li>On Main tab:</li>
 * <ul>
 * <li>Base Directory: Click on Workspace button, select your project and click
 * OK button</li>
 * <li>Enter Goal: jetty:run</li>
 * </ul>
 * <li>Click on debug button</li>
 * <li>Check if there are no build errors in the console window: almost at the
 * end it should read: [INFO] Started Jetty Server</li>
 * <li>Open the application in a browser, and open address: localhost:8080</li>
 * </ul>
 * </p>
 * 
 * @author nilsth
 *
 */

@SuppressWarnings("serial")
public abstract class ReflectApplicationForVaadin extends Div implements GraphicalUserInterfaceApplication {

	private final UserInterfaceContainer userInterfaceContainer;
	private final MainWindow mainWindow;

	public ReflectApplicationForVaadin() {
		userInterfaceContainer = ReflectFramework.launch(this);
		mainWindow = new MainWindow(userInterfaceContainer);
		add(mainWindow);
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserInterfaceControllerForVaadin.class;
	}

	@Override
	public Class<? extends ReflectionProvider> getReflectionProviderClass() {
		return DefaultReflectionProvider.class;
	}

	@Override
	public Class<? extends AboutProvider> getAboutProviderClass() {
		return DefaultAboutProvider.class;
	}

	@Override
	public Class<? extends LanguageProvider> getLanguageProviderClass() {
		return DefaultLanguageProvider.class;
	}

	@Override
	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
		return DefaultAuthorizationProvider.class;
	}

	@Override
	public Class<? extends ValidationProvider> getValidationProviderClass() {
		return DefaultValidationProvider.class;
	}

	@Override
	public Class<? extends NotificationProvider> getNotificationProviderClass() {
		return DefaultNotificationProvider.class;
	}

	@Override
	public List<Class<? extends UrlProvider>> getUrlProviderClasses() {
		return Arrays.asList(ClassResourceUrlProvider.class, ApplicationUrlProvider.class, FontIconUrlProvider.class);
	}

//	// TODO remove later so other ReflectApplicationForVaadin implementations
//	// can implement it
//	// TODO remove reflect-example-domain from pom.xml
//	@Override
//	public List<Class<?>> getServiceClasses() {
//		return Arrays.asList(ProductService.class, ShoppingCartService.class);
//	}
//
//	// TODO remove later so other ReflectApplicationForVaadin implementations
//	// can implement it
//	@Override
//	public List<Class<?>> getInfrastructureClasses() {
//		return Arrays.asList(ProductRepository.class, ProductGenerator.class);
//	}
//
//	@Override
//	public ReflectColors getColors() {
//		return new ReflectColors(MaterialColorPalette.blue700(), MaterialColorPalette.orange500(), MaterialColorPalette.white());
//	}

}
