package nth.reflect.fw.layer5provider.reflection.behavior.disabled;

import java.util.List;

import nth.reflect.fw.generic.util.TestString;

public class DisabledModelForPropertiesTestObject {

	private static final String CORRECT_ROLE = "salesmanager";
	private static final String BOGUS_ROLE = TestString.BOGUS;
	private String propertyDisabled;
	private String propertyEnabled;
	private String propertyDisabledNotInRole;
	private String propertyEnabledInRole;
	private List<String> propertyCollection;
	private String propertyDisabledMethod;
	private String propertyEnabledMethod;
	private String propertyDisabledAnnotationDisabledMethod;
	private String propertyDisabledAnnotationEnabledMethod;

	@Disabled()
	public String getPropertyDisabled() {
		return propertyDisabled;
	}

	public void setPropertyDisabled(String propertyDisabled) {
		this.propertyDisabled = propertyDisabled;
	}

	@Disabled(exceptForRoleNames = BOGUS_ROLE)
	public String getPropertyDisabledNotInRole() {
		return propertyDisabledNotInRole;
	}

	public void setPropertyDisabledNotInRole(String propertyDisabledNotInRole) {
		this.propertyDisabledNotInRole = propertyDisabledNotInRole;
	}

	public String getPropertyEnabled() {
		return propertyEnabled;
	}

	public void setPropertyEnabled(String propertyEnabled) {
		this.propertyEnabled = propertyEnabled;
	}

	@Disabled(exceptForRoleNames = CORRECT_ROLE)
	public String getPropertyEnabledInRole() {
		return propertyEnabledInRole;
	}

	public void setPropertyEnabledInRole(String propertyEnabledInRole) {
		this.propertyEnabledInRole = propertyEnabledInRole;
	}

	public List<String> getPropertyCollection() {
		return propertyCollection;
	}

	public void setPropertyCollection(List<String> propertyCollection) {
		this.propertyCollection = propertyCollection;
	}

	public String getPropertyDisabledMethod() {
		return propertyDisabledMethod;
	}

	public void setPropertyDisabledMethod(String propertyDisabledMethod) {
		this.propertyDisabledMethod = propertyDisabledMethod;
	}

	public boolean propertyDisabledMethodDisabled() {
		return true;
	}

	public String getPropertyEnabledMethod() {
		return propertyEnabledMethod;
	}

	public void setPropertyEnabledMethod(String propertyEnabledMethod) {
		this.propertyEnabledMethod = propertyEnabledMethod;
	}

	public boolean propertyEnabledMethodDisabled() {
		return false;
	}

	@Disabled()
	public String getPropertyDisabledAnnotationDisabledMethod() {
		return propertyDisabledAnnotationDisabledMethod;
	}

	public void setPropertyDisabledAnnotationDisabledMethod(String propertyDisabledInTableDisabledMethod) {
		this.propertyDisabledAnnotationDisabledMethod = propertyDisabledInTableDisabledMethod;
	}

	public boolean propertyDisabledInTableDisabledMethodDisabled() {
		return true;
	}

	@Disabled()
	public String getPropertyDisabledAnnotationEnabledMethod() {
		return propertyDisabledAnnotationEnabledMethod;
	}

	public void setPropertyDisabledAnnotationEnabledMethod(String propertyDisabledInTableEnabledMethod) {
		this.propertyDisabledAnnotationEnabledMethod = propertyDisabledInTableEnabledMethod;
	}

	public boolean propertyDisabledAnnotationEnabledMethodDisabled() {
		return false;
	}

}
