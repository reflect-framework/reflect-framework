package nth.reflect.fw.stubs;

import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

/**
 * A Stub of a {@link ServiceObject}. Stubs provide canned answers to calls made
 * during the test, usually not responding at all to anything outside what's
 * programmed in for the test.
 * 
 * @author nilsth
 *
 */
@Description(englishDescription = ServiceObjectStub.DESCRIPTION)
public class ServiceObjectStub {

	public static final String DESCRIPTION = "Description";
	public static final String ACTION_METHOD_NAME = "createDomainTestObject";

	@Order(value = 1)
	@ParameterFactory
	public void createDomainTestObject(DomainObjectStub domainObjectStub) {

	}

	@Override
	public String toString() {
		return ServiceObjectStub.class.getSimpleName();
	};
}
