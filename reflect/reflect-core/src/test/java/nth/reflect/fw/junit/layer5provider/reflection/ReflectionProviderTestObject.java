package nth.reflect.fw.junit.layer5provider.reflection;

import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@Description(englishDescription=ReflectionProviderTestObject.DESCRIPTION)
public class ReflectionProviderTestObject {

	
	private String property1;
	private String property2;
	public static final String DESCRIPTION = "description";
	public static final String PROPERTY1 = "property1";
	public static final String PROPERTY1_DESCRIPTION = PROPERTY1+" "+DESCRIPTION;
	public static final String PROPERTY2 = "property2";
	public static final String CLASS_ACTION_METHOD = "classActionMethod";
	public static final String PROPERTY1_ACTION_METHOD =  PROPERTY1+"ActionMethod";
	public static final String TITLE = "MaterialAppBarTitle";
	

	@Order(sequenceNumber=1)
	public void classActionMethod() {
		
	}

	@Hidden
	@Description(englishDescription=PROPERTY1_DESCRIPTION)
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
	
	@Override
	public String toString() {
		return TITLE;
	}
	
}
