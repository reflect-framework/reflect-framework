package nth.introspect.ui.swing;

import nth.introspect.Introspect;
import nth.introspect.application.IntrospectApplication;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.about.DefaultAboutProvider;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.provider.domain.info.DefaultDomainInfoProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.DefaultLanguageProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.notification.DefaultNotificationProvider;
import nth.introspect.provider.notification.NotificationProvider;
import nth.introspect.provider.path.DefaultPathProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.DefaultValidationProvider;
import nth.introspect.provider.validation.ValidationProvider;

public abstract class IntrospectApplicationForSwing implements
		IntrospectApplication {

	private final String[] commandLineArguments;

	public IntrospectApplicationForSwing(String[] commandLineArguments)  {
		this.commandLineArguments = commandLineArguments;
		Introspect.start(this);
	}
	
	@Override
	public Class<? extends UserInterfaceProvider<?>> getUserInterfaceProviderClass() {
		return SwingUserinterfaceProvider.class;
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
