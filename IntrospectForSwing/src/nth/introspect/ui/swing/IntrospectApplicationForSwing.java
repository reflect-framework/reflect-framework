package nth.introspect.ui.swing;

import nth.introspect.Introspect;
import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.about.DefaultAboutProvider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.layer5provider.domain.info.DefaultDomainInfoProvider;
import nth.introspect.layer5provider.domain.info.DomainInfoProvider;
import nth.introspect.layer5provider.language.DefaultLanguageProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.DefaultNotificationProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.path.DefaultPathProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.validation.DefaultValidationProvider;
import nth.introspect.layer5provider.validation.ValidationProvider;

public abstract class IntrospectApplicationForSwing implements
		IntrospectApplication {

	private final String[] commandLineArguments;

	public IntrospectApplicationForSwing(String[] commandLineArguments)  {
		this.commandLineArguments = commandLineArguments;
		Introspect.start(this);
	}
	
	@Override
	public Class<? extends UserInterfaceController<?>> getUserInterfaceControllerClass() {
		return UserinterfaceControllerForSwing.class;
	}

	@Override
	public Class<? extends DomainInfoProvider> getDomainInfoProviderClass() {
		return DefaultDomainInfoProvider.class;
	}

	@Override
	public Class<? extends AboutProvider> getVersionProviderClass() {
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
