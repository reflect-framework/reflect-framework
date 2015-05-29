package nth.introspect.documentation;

import nth.introspect.util.TitleBuilder;

public class Customer {
	private String givenName;
	private String familyName;
	private boolean male;

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFullName() {
		return new TitleBuilder().append(givenName).append(familyName).toString();
	}
	
	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

}
