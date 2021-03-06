package nth.reflect.fw.vaadin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldProvider;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandlerProvider;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandlerProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.DefaultNotificationProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.DefaultReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterFactories;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandler;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.url.UrlStreamHandlers;
import nth.reflect.fw.layer5provider.validation.DefaultValidationProvider;
import nth.reflect.fw.layer5provider.version.DefaultVersionProvider;
import nth.reflect.fw.layer5provider.version.VersionProvider;
import nth.reflect.fw.vaadin.css.StyleBuilder;
import nth.reflect.fw.vaadin.layer5provider.actionmethod.execution.ActionMethodExecutionProvider;
import nth.reflect.fw.vaadin.layer5provider.actionmethod.prehandler.ActionMethodPreHandlerClasses;
import nth.reflect.fw.vaadin.layer5provider.actionmethod.result.ActionMethodResultHandelerClasses;
import nth.reflect.fw.vaadin.layer5provider.properyfield.PropertyFieldFactoryClasses;
import nth.reflect.fw.vaadin.mainwindow.MainWindow;

/**
 * <p>
 * {@link ReflectApplicationForVaadin14} is an implementation of the
 * {@link ReflectFramework} that provides a
 * <a href="http://en.wikipedia.org/wiki/Graphical_user_interface"> graphical
 * user interface</a> for
 * <a href="https://en.wikipedia.org/wiki/Web_application">web applications</a>,
 * using the <a href="https://vaadin.com/">Vaadin Framework</a>.
 * {@link ReflectApplicationForVaadin14} tries to comply with the
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
 * {@link VaadinSession}). It will:
 * <ul>
 * <li>initialize the {@link ReflectFramework} with implemented methods from
 * this class</li>
 * <li>create the {@link MainWindow}</li>
 * </ul>
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
@Route("")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
//@PWA(name = "My Application", shortName = "My App")
public abstract class ReflectApplicationForVaadin14 extends Div
		implements GraphicalUserInterfaceApplication, HasDynamicTitle {

	private static final long serialVersionUID = -1895258196768761919L;
	private final UserInterfaceContainer userInterfaceContainer;
	private final nth.reflect.fw.vaadin.mainwindow.MainWindow mainWindow;

	public ReflectApplicationForVaadin14() {
		userInterfaceContainer = ReflectFramework.launch(this);
		mainWindow = new MainWindow(userInterfaceContainer);
		add(mainWindow);
		setCssColors();
		setSizeFull();
	}

	private void setCssColors() {
		ColorProvider colorProvider = userInterfaceContainer.get(ColorProvider.class);
		new StyleBuilder().setReflectColors(colorProvider).setFor(this);
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserInterfaceControllerForVaadin14.class;
	}

	@Override
	public Class<? extends ReflectionProvider> getReflectionProviderClass() {
		return DefaultReflectionProvider.class;
	}

	@Override
	public Class<? extends VersionProvider> getVersionProviderClass() {
		return DefaultVersionProvider.class;
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
	public Class<? extends UrlProvider> getUrlProviderClass() {
		return UrlProvider.class;
	}

	@Override
	public List<Class<? extends ReflectUrlStreamHandler>> getUrlStreamHandlerClasses() {
		return new UrlStreamHandlers();
	}

	@Override
	public Class<? extends StringConverterProvider> getStringConverterProviderClass() {
		return StringConverterProvider.class;
	}

	@Override
	public List<Class<? extends StringConverterFactory>> getStringConverterFactoryClasses() {
		return new StringConverterFactories();
	}

	@Override
	public Class<? extends PropertyFieldProvider> getPropertyFieldProviderClass() {
		return PropertyFieldProvider.class;
	}

	@Override
	public List<Class<? extends PropertyFieldFactory>> getPropertyFieldFactoryClasses() {
		return new PropertyFieldFactoryClasses();
	}

	@Override
	public Class<? extends ActionMethodExecutionProvider> getActionMethodExecutionProviderClass() {
		return ActionMethodExecutionProvider.class;
	}

	@Override
	public Class<? extends ActionMethodResultHandlerProvider> getActionMethodResultHandlerProviderClass() {
		return ActionMethodResultHandlerProvider.class;
	}

	@Override
	public Class<? extends ActionMethodPreHandlerProvider> getActionMethodPreHandlerProviderClass() {
		return ActionMethodPreHandlerProvider.class;
	}

	@Override
	public List<Class<? extends ActionMethodPreHandler>> getActionMethodPreHandlerClasses() {
		return new ActionMethodPreHandlerClasses();
	}

	@Override
	public ColorProvider getColorProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class<? extends ActionMethodResultHandler>> getActionMethodResultHandlerClasses() {
		return new ActionMethodResultHandelerClasses();
	}

	@Override
	public String getPageTitle() {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ApplicationClassInfo applicationClassInfo = reflectionProvider.getApplicationClassInfo();
		String title = applicationClassInfo.getDisplayName().getTranslation();
		return title;
	}
}
