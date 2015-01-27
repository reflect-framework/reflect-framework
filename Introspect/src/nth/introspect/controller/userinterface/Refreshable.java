package nth.introspect.controller.userinterface;

import nth.introspect.provider.domain.info.DomainInfoProvider;

/**
 * @deprecated use {@link DomainInfoProvider#invokePropertyChangeListeners(Object, String, nth.introspect.provider.domaininfo.PropertyChangeType)}
 * @author nilsth
 *
 */
public interface Refreshable {
	public void refresh();
}
