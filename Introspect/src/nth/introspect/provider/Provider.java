package nth.introspect.provider;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.notification.NotificationProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.validation.ValidationProvider;

/**
 * {@link Provider}s are responsible for different <a href="cross cutting concerns">cross cutting concerns</a> within the
 * {@link IntrospectFramework} such as:
 * <ul>
 * <li>Authorization (see {@link AuthorizationProvider})</li>
 * <li>Validation (see {@link ValidationProvider})</li>
 * <li>Multi language (see {@link LanguageProvider})</li>
 * <li>Notifications (see {@link NotificationProvider})</li>
 * <li>File path information (see {@link PathProvider})</li>
 * <li>Domain object information (see {@link DomainProvider})</li>
 * <li>Version information (see {@link AboutProvider})</li>
 * </ul>
 * <p>
 * {@link Provider}s may be used by any class within an application.
 * </p>
 * <p>
 * Providers are interfaces and can have multiple implementations (depending on
 * what type of application you are using/writing). Which implementation of each
 * provider needs to be used within an application is defined in the
 * {@link IntrospectApplication} class
 * </p>
 * <h3>Authorization Provider</h3>
 * <p>
 * {@insert AuthorizationProvider}
 * </p>
 * <h3>Validation Provider</h3>
 * <p>
 * {@insert ValidationProvider}
 * </p>
 * <h3>Language Provider</h3>
 * <p>
 * {@insert LanguageProvider}
 * </p>
 * <h3>Notification Provider</h3>
 * <p>
 * {@insert NotificationProvider}
 * </p>
 * <h3>Path Provider</h3>
 * <p>
 * {@insert PathProvider}
 * </p>
 * <h3>Domain Provider</h3>
 * <p>
 * {@insert DomainInfoProvider}
 * </p>
 * <h3>About Provider</h3>
 * <p>
 * {@insert AboutProvider}
 * </p>
 * 
 * 
 * 
 * @author Nils ten Hoeve
 */
public interface Provider {

}
