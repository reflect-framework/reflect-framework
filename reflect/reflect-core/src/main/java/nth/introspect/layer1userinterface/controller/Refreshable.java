package nth.introspect.layer1userinterface.controller;

import nth.introspect.layer5provider.notification.NotificationProvider;

/**
 * @deprecated use {@link NotificationProvider#refreshUserInterface()
 * @author nilsth
 *
 */
public interface Refreshable {
	public void refresh();
}
