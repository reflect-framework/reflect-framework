package nth.reflect.fw.javafx;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactory;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldService;
import nth.reflect.fw.javafx.control.mainwindow.MainWindow;
import nth.reflect.fw.javafx.control.style.StyleSheetUrlHandler;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.CheckBoxFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.ComboBoxFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.DateTimeFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.ManyToOneOrManyFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.OneToOneOrManyFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.TableFieldFactory;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.TextFieldFactory;
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
		return Arrays.asList(ClassResourceUrlProvider.class, ApplicationUrlProvider.class, FontIconUrlProvider.class,
				StyleSheetUrlHandler.class);
	}

	@Override
	public PropertyFieldService getPropertyFieldService() {
		return new PropertyFieldService(new PropertyFieldFactory[] { new TextFieldFactory(), new CheckBoxFieldFactory(),
				new DateTimeFieldFactory(), new ComboBoxFieldFactory(), new TableFieldFactory(),
				new ManyToOneOrManyFieldFactory(), new OneToOneOrManyFieldFactory() });
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
