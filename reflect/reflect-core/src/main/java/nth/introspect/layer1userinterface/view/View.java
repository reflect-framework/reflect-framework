package nth.introspect.layer1userinterface.view;

import java.net.URL;

public interface View {

	public String getViewTitle();
	/**
	 * TODO remove {@link #getViewDescription()}. New user interfaces will not have a tool tip
	 */
	public String getViewDescription();
	public URL getViewIconURL();
	public void onViewActivate();
	
}
