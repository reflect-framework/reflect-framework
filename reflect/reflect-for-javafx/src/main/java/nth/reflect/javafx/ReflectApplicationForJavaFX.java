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
 * {@link ReflectApplicationForJavaFX} is an implementation of the
 * {@link IntrospectFramework} for desktop computers. It might also be used for
 * mobile devices, but porting JavaFX for Android is still somewhat of a problem
 * at the time of this writing.
 * {@link ReflectApplicationForJavaFX} ties to comply with the <a href="https://material.io/guidelines/">Google Material Design</a> as much as possible. It optimizes the user interface depending on the size of the main window. 
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

	public abstract Color getPrimaryColor();

	public abstract Color getSecondaryColor();

	public abstract Color getAccentColor();

	public abstract ContentColor getContentColor();

}
