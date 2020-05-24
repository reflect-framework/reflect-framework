package nth.reflect.fw.javafx;

import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldProvider;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.javafx.control.mainwindow.MainWindow;
import nth.reflect.fw.javafx.layer5provider.actionmethod.execution.ActionMethodExecutionProvider;
import nth.reflect.fw.javafx.layer5provider.actionmethod.result.ActionMethodResultHandlerClasses;
import nth.reflect.fw.javafx.layer5provider.properyfield.PropertyFieldFactoryClasses;
import nth.reflect.fw.javafx.layer5provider.url.UrlStreamHandlers;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethod.result.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.result.ActionMethodResultProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.DefaultNotificationProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.DefaultReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationProvider;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterFactories;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandler;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.validation.DefaultValidationProvider;
import nth.reflect.fw.layer5provider.version.DefaultVersionProvider;
import nth.reflect.fw.layer5provider.version.VersionProvider;

/**
 * <p>
 * {@link ReflectApplicationForJavaFX} is an implementation of the
 * {@link ReflectFramework} that provides a
 * <a href="http://en.wikipedia.org/wiki/Graphical_user_interface"> graphical
 * user interface</a> for a computer with an
 * <a href="https://en.wikipedia.org/wiki/Desktop_environment">desktop
 * environment</a>. It might also be used for mobile devices in the future as
 * well, but porting JavaFX for Android is still somewhat of a problem at the
 * time of this writing. {@link ReflectApplicationForJavaFX} tries to comply
 * with the <a href="https://material.io/guidelines/">Google Material Design</a>
 * as much as possible. The application can be used on a
 * <a href="https://en.wikipedia.org/wiki/Desktop_computer">desktop</a>,
 * <a href="https://en.wikipedia.org/wiki/Laptop">lap-top</a>,
 * <a href="https://en.wikipedia.org/wiki/Tablet_computer">tablet</a> or
 * <a href="https://en.wikipedia.org/wiki/Mobile_device">mobile device</a>. It
 * has an
 * <a href="https://en.wikipedia.org/wiki/Responsive_web_design">responsive web
 * design</a>: It optimizes the user interface depending on the size of the
 * {@link MainWindow}.
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
 * 
 * @author nilsth
 *
 */
public abstract class ReflectApplicationForJavaFX extends Application implements GraphicalUserInterfaceApplication {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		ReflectFramework.launch(this);
	}

	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserinterfaceControllerForJavaFX.class;
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
	public Class<? extends ActionMethodResultProvider> getActionMethodResultProviderClass() {
		return ActionMethodResultProvider.class;
	}

	@Override
	public List<Class<? extends ActionMethodResultHandler>> getActionMethodResultHandlerClasses() {
		return new ActionMethodResultHandlerClasses();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
