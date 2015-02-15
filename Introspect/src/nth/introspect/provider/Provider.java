package nth.introspect.provider;

import nth.introspect.Introspect;
import nth.introspect.application.IntrospectApplication;
import nth.introspect.documentation.ProviderLayer;

//TODO link to definitions

/**
 * {@link Provider}:
 * <ul>
 * <li>Providers are part of the {@link Introspect} framework.</li>
 * <li>Providers are utility classes (for responsibilities such as
 * authorization, validation, multi language etc.), that may be used by any
 * class within an application. They therefore should be located in the bottom
 * layer (as part of the {@link ProviderLayer}). See the class hierarchy of {@link Provider} to
 * discover all providers</li>
 * <li>Providers are an abstract interface between the infrastructure layer and
 * the layers above</li>
 * <li>Providers may have multiple implementations (depending on what type of
 * application you are writing). Which implementation of each provider needs to
 * be used within an application is defined in the {@link IntrospectApplication}
 * class</li>
 * </ul>
 * 
 * 
 * 
 * @author Nils ten Hoeve
 */
public interface Provider {

}
