package nth.reflect.javafx;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import nth.introspect.IntrospectApplication;
import nth.introspect.IntrospectFramework;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.about.DefaultAboutProvider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.layer5provider.language.DefaultLanguageProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.DefaultNotificationProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.reflection.DefaultReflectionProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.layer5provider.url.application.ApplicationUrlProvider;
import nth.introspect.layer5provider.url.classresource.ClassResourceUrlProvider;
import nth.introspect.layer5provider.validation.DefaultValidationProvider;
import nth.introspect.layer5provider.validation.ValidationProvider;
import nth.introspect.ui.style.ContentColor;
import nth.introspect.ui.style.basic.Color;
import nth.introspect.ui.style.fonticonurl.FontIconUrlHandler;
import nth.reflect.javafx.control.style.RfxStyleSheetUrlHandler;

/**
 * <p>
 * {@link ReflectApplicationForJavaFX} is an implementation of the
 * {@link IntrospectFramework} that has a
 * <a href="http://en.wikipedia.org/wiki/Graphical_user_interface"> graphical
 * user interface</a> for a computer with an
 * <a href="https://en.wikipedia.org/wiki/Desktop_environment">desktop
 * environment</a>. It might also be used for mobile devices in the future as
 * well, but porting JavaFX for Android is still somewhat of a problem at the
 * time of this writing. {@link ReflectApplicationForJavaFX} tries to comply
 * with the <a href="https://material.io/guidelines/">Google Material Design</a>
 * as much as possible. It optimizes the user interface depending on the size of
 * the main window.
 * </p>
 * <h3>How to download a IntrospectForJavaFX demo project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * <h3>How to create a new IntrospectForJavaFX project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * @author nilsth
 *
 */
public abstract class ReflectApplicationForJavaFX extends Application
		implements IntrospectApplication {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		IntrospectFramework.launch(this);
	}

	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return RfxUserinterfaceController.class;
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
		return Arrays.asList(ClassResourceUrlProvider.class, ApplicationUrlProvider.class,
				FontIconUrlHandler.class, RfxStyleSheetUrlHandler.class);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @return A primary color is the color displayed most frequently across
	 *         your app’s screens and components. To create contrast between
	 *         elements, you can use lighter or darker tones of your primary
	 *         color. The contrast between lighter and darker tones helps show
	 *         division between surfaces, such as between the status bar and a
	 *         toolbar.
	 * 
	 *         TODO: 700 for AppBar, 500 for toolbar, 300 for secondary
	 *         information
	 */
	public abstract Color getPrimaryColor();

	/**
	 * @return A accent color is used to accent select parts of your UI. It can
	 *         be complementary or analogous to your primary color, but it
	 *         should not simply be a light or dark variation of your primary
	 *         color. It should contrast with elements that surround it and be
	 *         applied sparingly as an accent. Accent colors can be used for
	 *         floating action buttons (e.g a plus to add a item) or to indicate
	 *         focus on elements such as buttons or text fields.
	 */
	public abstract Color getAccentColor();

	/**
	 * 
	 * @return The content color is the back ground color of the content views
	 *         (e.g. the background color of a form view), The content color can
	 *         only be white or black
	 */
	public abstract ContentColor getContentColor();

}
