package nth.introspect.junit.layer5provider.reflection.behavior.disabled;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.disabled.Disabled;
import nth.introspect.layer5provider.reflection.info.valuemodel.annotations.GenericReturnType;

public class DisabledModelForActionMethodTestObject {

	private static final String CORRECT_ROLE = "salesmanager";
	private static final String BOGUS_ROLE = "bogus";

	@Disabled()
	public void actionMethodDisabled() {
	}

	@Disabled(exceptForRoleNames = BOGUS_ROLE)
	public void actionMethodDisabledNotInRole() {
	}

	@Disabled(exceptForRoleNames = CORRECT_ROLE)
	public void actionMethodEnabledInRole() {
	}

	@GenericReturnType(String.class)
	public List<String> actionMethodCollection() {
		return new ArrayList<>();
	}


	public String actionMethodDisabledMethod() {
		return "actionMethodDisabledMethod";
	}

	public boolean actionMethodDisabledMethodDisabled() {
		return true;
	}

	
	public void actionMethodEnabledMethod() {
	}

	public boolean actionMethodEnabledMethodDisabled() {
		return false;
	}

	@Disabled()
	public void actionMethodDisabledAnnotationDisabledMethod() {
	}


	public boolean actionMethodDisabledAnnotationDisabledMethodDisabled() {
		return true;
	}
	
	@Disabled()
	public void actionMethodDisabledAnnotationEnabledMethod() {
	}


	public boolean actionMethodDisabledAnnotationEnabledMethodDisabled() {
		return false;
	}
	
	
}
