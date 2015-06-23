package nth.introspect.documentation;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.impl.ProviderContainer;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.provider.Provider;

/**
 * <p>The {@link ProviderLayer} contains {@link Provider} objects that provide generic
 * {@link IntrospectFramework} capabilities to support the higher layers (see
 * {@link IntrospectArchitecture})</p>
 * <p>The {@link ProviderContainer} is an {@link IntrospectContainer} that
 * represents the {@link ProviderLayer} and holds and manages
 * {@link Provider}Objects.</p>
 * </p>
 * <p>
 * Note that this layer is the bottom layer (see {@link IntrospectArchitecture}
 * ), which means that objects in the upper layers may haved references to {@link Provider}Objects, but not visa versa!
 * </p>
 * 
 * <h2>Provider Objects</h2> {@insert Provider}
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface ProviderLayer extends Documentation {

}
