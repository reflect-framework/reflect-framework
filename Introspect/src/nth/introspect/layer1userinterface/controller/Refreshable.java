package nth.introspect.layer1userinterface.controller;

import nth.introspect.layer5provider.domain.info.DomainInfoProvider;

/**
 * @deprecated use {@link DomainInfoProvider#invokePropertyChangeListeners(Object, String, nth.introspect.layer5provider.domaininfo.PropertyChangeType)}
 * @author nilsth
 *
 */
public interface Refreshable {
	public void refresh();
}
