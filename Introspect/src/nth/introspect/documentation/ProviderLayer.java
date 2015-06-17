package nth.introspect.documentation;

import nth.introspect.container.impl.ProviderContainer;
import nth.introspect.provider.Provider;

/**
 * The {@link ProviderLayer} is part if the {@link IntrospectArchitecture}. <br>
 * 
 * The {@link ProviderLayer} contains {@link Provider} objects provide generic
 * technical capabilities to support the higher layers (see
 * {@link IntrospectArchitecture})<br>
 * The {@link ProviderLayer} is implemented by the {@link ProviderContainer}<br>
 * <p>
 * Note that this layer is the bottom layer (see {@link IntrospectArchitecture}
 * ), which means that objects in the upper layers may know the objects in this
 * layer but not visa versa!
 * </p>
 * 
 * <h2>Provider Objects</h2> {@insert Provider}
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface ProviderLayer extends Documentation {

}
