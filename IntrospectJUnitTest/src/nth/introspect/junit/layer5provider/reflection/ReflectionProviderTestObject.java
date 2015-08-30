package nth.introspect.junit.layer5provider.reflection;

import nth.introspect.layer5provider.reflection.behavior.hidden.Hidden;

public class ReflectionProviderTestObject {

	
	private String property1;
	private String property2;
	
	public void classActionMethod() {
		
	}

	@Hidden
	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}
	
	public void property1ActionMethod() {
		
	}

	public String getProperty2() {
		return property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2;
	}
	
	
}
