package nth.introspect.provider.userinterface;

import nth.introspect.provider.domain.DomainProvider;

/**
 * @deprecated use {@link DomainProvider#invokePropertyChangeListeners(Object, String, nth.introspect.provider.domain.PropertyChangeType)}
 * @author nilsth
 *
 */
public interface Refreshable {
	public void refresh();
}
