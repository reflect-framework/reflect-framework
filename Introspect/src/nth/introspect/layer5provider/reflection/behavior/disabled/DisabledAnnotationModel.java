package nth.introspect.layer5provider.reflection.behavior.disabled;

import nth.introspect.layer5provider.authorization.AuthorizationProvider;

/**
 * See {@link Disabled} annotation
 * 
 * @author nilsth
 *
 */
public class DisabledAnnotationModel implements DisabledModel {


	private static final String SEPARATOR = ",";
	private final AuthorizationProvider authorizationProvider;
	private final String[] roleNames;

	public DisabledAnnotationModel(AuthorizationProvider authorizationProvider, Disabled disabledAnnotation) {
		this.authorizationProvider = authorizationProvider;
		roleNames=disabledAnnotation.exceptForRoleNames().replace(" ", "").split(SEPARATOR);
	}


	@Override
	public boolean isDisabled(Object obj) {
		for (String roleName : roleNames) {
			if (authorizationProvider.userInRole(roleName)) {
				return false;
			}
		}
		return true;
	}

}
