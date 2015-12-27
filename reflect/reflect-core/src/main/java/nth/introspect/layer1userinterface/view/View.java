package nth.introspect.layer1userinterface.view;

import java.net.URI;

public interface View {

	public String getViewTitle();
	public String getViewDescription();
	public URI getViewIconURI();
	public void onViewActivate();
	
}
