package nth.introspect.ui.swing;

import nth.introspect.Introspect;
import nth.introspect.IntrospectApplication;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.about.DefaultAboutProvider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.layer5provider.language.DefaultLanguageProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.DefaultNotificationProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.path.DefaultPathProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.DefaultReflectionProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.validation.DefaultValidationProvider;
import nth.introspect.layer5provider.validation.ValidationProvider;

/**
 * {@link IntrospectApplicationForSwing} is an implementation of the {@link IntrospectFramework} for desktop computers such as ...  
 * 
 *  <h3>How to download a IntrospectForSwing demo project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * <h3>How to create a new IntrospectForSwing project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * @author nilsth
 *
 */
public abstract class IntrospectApplicationForSwing implements
		IntrospectApplication {

	public IntrospectApplicationForSwing(String[] commandLineArguments)  {
		Introspect.start(this);
	}
	
	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserinterfaceControllerForSwing.class;
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
	public Class<? extends PathProvider> getPathProviderClass() {
		return DefaultPathProvider.class;
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

	
	
}
