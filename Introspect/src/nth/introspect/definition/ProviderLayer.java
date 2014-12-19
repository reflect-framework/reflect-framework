package nth.introspect.definition;

import nth.introspect.provider.Provider;
//TODO synchronize with manual
/**
 * This interface serves for documentation purposes only. <br><br>
 * The {@link ProviderLayer} is part if the {@link IntrospectArchitecture}. <br>
 * 
 * This layer contains {@link Provider} objects provide generic technical capabilities to support the higher layers (see {@link IntrospectArchitecture})<br>
 * 
 * Note that this layer is the bottom layer, which means that objects in the upper layers may know the objects in this layer but not visa versa! See {@link IntrospectArchitecture}
 * @author Nils ten Hoeve
 * 
 */
public interface ProviderLayer {

}
