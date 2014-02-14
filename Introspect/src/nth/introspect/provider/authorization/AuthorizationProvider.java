package nth.introspect.provider.authorization;

import nth.introspect.provider.Provider;


public interface AuthorizationProvider extends Provider{
	public String getCurrentUserName() ;
	public boolean userInRole(String userRole); 
}
