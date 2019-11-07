package nth.reflect.fw.junit.stubs;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.PropertyActionMethod;

/**
 * A Stub of a {@link DomainObject}. Stubs provide canned answers to calls made
 * during the test, usually not responding at all to anything outside what's
 * programmed in for the test.
 * 
 * @author nilsth
 *
 */
@Description(englishDescription = DomainObjectStub.DESCRIPTION)
public class DomainObjectStub {

	public static String TITLE = DomainObjectStub.class.getSimpleName();
	private String property1;
	private String property2;
	public static final String DESCRIPTION = "description";
	public static final String PROPERTY1 = "property1";
	public static final String PROPERTY1_DESCRIPTION = PROPERTY1 + " " + DESCRIPTION;
	public static final String PROPERTY2 = "property2";
	public static final String CLASS_ACTION_METHOD = "classActionMethod";
	public static final String PROPERTY1_ACTION_METHOD = PROPERTY1 + "ActionMethod";
	public static final int PROPERTY1_ORDER = 10;

	@Order(value = 1)
	public void classActionMethod() {

	}

	@Hidden
	@Description(englishDescription = PROPERTY1_DESCRIPTION)
	@Order(PROPERTY1_ORDER)
	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	@PropertyActionMethod(PROPERTY1)
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
