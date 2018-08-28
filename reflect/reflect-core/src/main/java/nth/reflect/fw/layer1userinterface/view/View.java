package nth.reflect.fw.layer1userinterface.view;

import java.net.URL;

/**
 * TODO rename View to Tab
 * TODO remove {@link #getViewDescription()}. New user interfaces will not have a tool tip
 * TODO remove getViewIcon
 */

public interface View {

	public String getViewTitle();
	public String getViewDescription();
	public URL getViewIconURL();
	public void onViewActivate();
	
}
