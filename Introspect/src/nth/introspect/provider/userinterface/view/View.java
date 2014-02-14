package nth.introspect.provider.userinterface.view;

import java.net.URI;

public interface View {

	public String getViewTitle();
	public String getViewDescription();
	public URI getViewIconURI();
	public void onViewActivate();
	//TODO public void onViewDeactivate();
	//TODO public void onViewClose();
	
	
}
