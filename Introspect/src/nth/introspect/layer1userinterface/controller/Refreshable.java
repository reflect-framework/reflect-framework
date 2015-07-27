package nth.introspect.layer1userinterface.controller;

import nth.introspect.provider.domain.info.DomainInfoProvider;

/**
 * @deprecated use {@link DomainInfoProvider#invokePropertyChangeListeners(Object, String, nth.introspect.provider.domaininfo.PropertyChangeType)}
 * @author nilsth
 *
 */
public interface Refreshable {
	public void refresh();
}
