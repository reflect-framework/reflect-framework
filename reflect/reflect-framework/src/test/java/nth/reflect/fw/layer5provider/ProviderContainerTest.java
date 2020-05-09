package nth.reflect.fw.layer5provider;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;
import nth.reflect.fw.layer5provider.version.VersionProvider;

public class ProviderContainerTest {

	private ProviderContainer providerContainer;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		providerContainer = container.get(ProviderContainer.class);
	}

	@Test
	public void testGet_givenProviderContainer_mustNotBeNull() {
		assertThat(providerContainer.get(ProviderContainer.class)).isNotNull();
	}

	@Test
	public void testGet_givenReflectApplication_mustNotBeNull() {
		assertThat(providerContainer.get(ReflectApplication.class)).isNotNull();
	}

	@Test
	public void testGet_givenNotificationProvider_mustNotBeNull() {
		assertThat(providerContainer.get(NotificationProvider.class)).isNotNull();
	}

	@Test
	public void testGet_givenAboutProvider_mustNotBeNull() {
		assertThat(providerContainer.get(VersionProvider.class)).isNotNull();
	}

	@Test
	public void testGet_givenValidationProvider_mustNotBeNull() {
		assertThat(providerContainer.get(ValidationProvider.class)).isNotNull();
	}

	@Test
	public void testGet_givenLanguageProvider_mustNotBeNull() {
		assertThat(providerContainer.get(LanguageProvider.class)).isNotNull();
	}

	@Test
	public void testGet_givenReflectProvider_mustNotBeNull() {
		assertThat(providerContainer.get(ReflectionProvider.class)).isNotNull();
	}
	
	@Test
	public void testGet_givenAuthorizationProvider_mustNotBeNull() {
		assertThat(providerContainer.get(AuthorizationProvider.class)).isNotNull();
	}

}
