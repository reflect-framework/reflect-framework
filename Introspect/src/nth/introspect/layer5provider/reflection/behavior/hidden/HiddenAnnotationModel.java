package nth.introspect.layer5provider.reflection.behavior.hidden;

import nth.introspect.layer5provider.authorization.AuthorizationProvider;

/**
 * Model that returns a value that is created with help of the {@link Hidden}
 * annotation
 * 
 * @author nilsth
 *
 */
public class HiddenAnnotationModel implements HiddenModel {

	private static final String SEPARATOR = ",";
	private final AuthorizationProvider authorizationProvider;
	private final String[] roleNames;
	private HiddenFor hiddenFor;

	public HiddenAnnotationModel(AuthorizationProvider authorizationProvider,
			Hidden hiddenAnnotation) {
		this.authorizationProvider = authorizationProvider;
		roleNames = hiddenAnnotation.exceptForRoleNames().replace(" ", "")
				.split(SEPARATOR);
		hiddenFor = hiddenAnnotation.propertyHiddenFor();
	}

	// hiddenFor authorized Hidden_in_form Hidden_in_form HiddenActionMethod
	// ======================================================================================
	// FORMS false True False false
	// TABLES false False True false
	// FORMS_AND_TABLES false True True false
	// FORMS true False False true
	// TABLES true False False true
	// FORMS_AND_TABLES true False False true

	@Override
	public boolean isPropertyHiddenInForm(Object obj) {
		boolean isAuthorized = isAuthorized();
		if (isAuthorized) {
			return false;
		}

		return hiddenFor == HiddenFor.FORMS
				|| hiddenFor == HiddenFor.TABLES_AND_FORMS;
	}

	@Override
	public boolean isPropertyHiddenInTable() {
		boolean isAuthorized = isAuthorized();
		if (isAuthorized) {
			return false;
		}

		return hiddenFor == HiddenFor.TABLES
				|| hiddenFor == HiddenFor.TABLES_AND_FORMS;
	}

	@Override
	public boolean isHiddenActionMethod(Object obj) {
		return !isAuthorized();
	}

	private boolean isAuthorized() {
		for (String roleName : roleNames) {
			if (roleName.length() > 0) {
				if (authorizationProvider.userInRole(roleName)) {
					return true;
				}
			}
		}
		return false;
	}

}
