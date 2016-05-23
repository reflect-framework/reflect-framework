package nth.introspect.layer5provider.about;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.layer5provider.validation.ValidationProvider;

public class About extends VersionInfo {

	private IntrospectApplication application;

	public About(IntrospectApplication introspectApplication) {
		super(introspectApplication.getClass());
		this.application = introspectApplication;
	}

	public List<VersionInfo> getProviders() {
		List<VersionInfo> providerInfos = new ArrayList<VersionInfo>();
		providerInfos.add(getAuthorizationProviderInfo());
		providerInfos.add(getReflectionProviderInfo());
		providerInfos.add(getLanguageProviderInfo());
		providerInfos.addAll(getUrlProviderInfos());
		providerInfos.add(getValidationProviderInfo());
		providerInfos.add(getNotificationProviderInfo());
		providerInfos.add(getUserInterfaceControllerInfo());
		return providerInfos;
	}

	private Collection<? extends VersionInfo> getUrlProviderInfos() {
		List<VersionInfo> urlProviderInfos=new ArrayList<>();
		List<Class<? extends UrlProvider>> urlProviderClasses = application
				.getUrlProviderClasses();
		for (Class<? extends UrlProvider> urlProviderClass : urlProviderClasses) {
			VersionInfo urlProviderInfo = new VersionInfo(urlProviderClass);
			urlProviderInfos.add(urlProviderInfo);
		}
		return urlProviderInfos; 
	}

	private VersionInfo getNotificationProviderInfo() {
		Class<? extends NotificationProvider> notificationProviderClass = application
				.getNotificationProviderClass();
		return new VersionInfo(notificationProviderClass);
	}

	private VersionInfo getValidationProviderInfo() {
		Class<? extends ValidationProvider> validationProviderClass = application
				.getValidationProviderClass();
		return new VersionInfo(validationProviderClass);
	}

	private VersionInfo getAuthorizationProviderInfo() {
		Class<? extends AuthorizationProvider> authorizationProviderClass = application
				.getAuthorizationProviderClass();
		return new VersionInfo(authorizationProviderClass);
	}

	private VersionInfo getReflectionProviderInfo() {
		Class<? extends ReflectionProvider> reflectionProviderClass = application
				.getReflectionProviderClass();
		return new VersionInfo(reflectionProviderClass);
	}

	private VersionInfo getLanguageProviderInfo() {
		Class<? extends LanguageProvider> languageProviderClass = application
				.getLanguageProviderClass();
		return new VersionInfo(languageProviderClass);
	}

	private VersionInfo getUserInterfaceControllerInfo() {
		Class<? extends UserInterfaceController> userInterfaceControllerClass = application
				.getUserInterfaceControllerClass();
		return new VersionInfo(userInterfaceControllerClass);
	}

}
