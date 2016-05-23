package nth.introspect.layer1userinterface.view;

import java.net.URL;

public interface View {

	public String getViewTitle();
	public String getViewDescription();
	public URL getViewIconURL();
	public void onViewActivate();
	
}
