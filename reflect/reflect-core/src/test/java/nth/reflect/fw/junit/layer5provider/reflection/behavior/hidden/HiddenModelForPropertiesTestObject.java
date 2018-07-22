package nth.reflect.fw.junit.layer5provider.reflection.behavior.hidden;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenFor;

public class HiddenModelForPropertiesTestObject {

	private static final String CORRECT_ROLE = "salesmanager";
	private static final String BOGUS_ROLE = "bogus";
	private String propertyHiddenInTable;
	private String propertyHiddenInForm;
	private String propertyHiddenInFormAndTable;
	private String propertyHiddenNotInRole;
	private String propertyVisibleInRole;
	private List<String> propertyCollection; 
	private String propertyHiddenMethod;
	private String propertyVisibleMethod;
	private String propertyHiddenInTableHiddenInRole;
	private String propertyHiddenInTableVisibleInRole;
	private String propertyHiddenInTableHiddenMethod;
	private String propertyHiddenInTableVisibleMethod;

	@Hidden(propertyHiddenFor = HiddenFor.TABLES)
	public String getPropertyHiddenInTable() {
		return propertyHiddenInTable;
	}

	public void setPropertyHiddenInTable(String propertyHiddenInTable) {
		this.propertyHiddenInTable = propertyHiddenInTable;
	}

	@Hidden(propertyHiddenFor = HiddenFor.FORMS)
	public String getPropertyHiddenInForm() {
		return propertyHiddenInForm;
	}

	public void setPropertyHiddenInForm(String propertyHiddenInForm) {
		this.propertyHiddenInForm = propertyHiddenInForm;
	}

	@Hidden(propertyHiddenFor = HiddenFor.TABLES_AND_FORMS)
	public String getPropertyHiddenInFormAndTable() {
		return propertyHiddenInFormAndTable;
	}

	public void setPropertyHiddenInFormAndTable(
			String propertyHiddenInFormAndTable) {
		this.propertyHiddenInFormAndTable = propertyHiddenInFormAndTable;
	}

	@Hidden(exceptForRoleNames = BOGUS_ROLE)
	public String getPropertyHiddenNotInRole() {
		return propertyHiddenNotInRole;
	}

	public void setPropertyHiddenNotInRole(String propertyHiddenNotInRole) {
		this.propertyHiddenNotInRole = propertyHiddenNotInRole;
	}

	@Hidden(exceptForRoleNames = CORRECT_ROLE)
	public String getPropertyVisibleInRole() {
		return propertyVisibleInRole;
	}

	public void setPropertyVisibleInRole(String propertyVisibleInRole) {
		this.propertyVisibleInRole = propertyVisibleInRole;
	}

	public List<String> getPropertyCollection() {
		return propertyCollection;
	}

	public void setPropertyCollection(List<String> propertyCollection) {
		this.propertyCollection = propertyCollection;
	}

	public String getPropertyHiddenMethod() {
		return propertyHiddenMethod;
	}

	public void setPropertyHiddenMethod(String propertyHiddenMethod) {
		this.propertyHiddenMethod = propertyHiddenMethod;
	}

	public boolean propertyHiddenMethodHidden() {
		return true;
	}

	
	public String getPropertyVisibleMethod() {
		return propertyVisibleMethod;
	}

	public void setPropertyVisibleMethod(String propertyVisibleMethod) {
		this.propertyVisibleMethod = propertyVisibleMethod;
	}

	public boolean propertyVisibleMethodHidden() {
		return false;
	}

	@Hidden(propertyHiddenFor=HiddenFor.TABLES, exceptForRoleNames=BOGUS_ROLE)
	public String getPropertyHiddenInTableHiddenInRole() {
		return propertyHiddenInTableHiddenInRole;
	}

	public void setPropertyHiddenInTableHiddenInRole(
			String propertyHiddenInTableHiddenInRole) {
		this.propertyHiddenInTableHiddenInRole = propertyHiddenInTableHiddenInRole;
	}


	@Hidden(propertyHiddenFor=HiddenFor.TABLES, exceptForRoleNames=CORRECT_ROLE)
	public String getPropertyHiddenInTableVisibleInRole() {
		return propertyHiddenInTableVisibleInRole;
	}

	public void setPropertyHiddenInTableVisibleInRole(
			String propertyHiddenInTableVisibleInRole) {
		this.propertyHiddenInTableVisibleInRole = propertyHiddenInTableVisibleInRole;
	}


	@Hidden(propertyHiddenFor=HiddenFor.TABLES)
	public String getPropertyHiddenInTableHiddenMethod() {
		return propertyHiddenInTableHiddenMethod;
	}

	public void setPropertyHiddenInTableHiddenMethod(
			String propertyHiddenInTableHiddenMethod) {
		this.propertyHiddenInTableHiddenMethod = propertyHiddenInTableHiddenMethod;
	}

	public boolean propertyHiddenInTableHiddenMethodHidden() {
		return true;
	}
	
	@Hidden(propertyHiddenFor=HiddenFor.TABLES)
	public String getPropertyHiddenInTableVisibleMethod() {
		return propertyHiddenInTableVisibleMethod;
	}

	public void setPropertyHiddenInTableVisibleMethod(
			String propertyHiddenInTableVisibleMethod) {
		this.propertyHiddenInTableVisibleMethod = propertyHiddenInTableVisibleMethod;
	}

	public boolean propertyHiddenInTableVisibleMethodHidden() {
		return false;
	}
	
	
}
