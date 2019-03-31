package nth.reflect.fw.junit.layer1userinterface.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.UserInterfaceControllerForJUnit;
import nth.reflect.fw.junit.stubs.DomainObjectStub;
import nth.reflect.fw.junit.stubs.ServiceObjectStub;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class UserInterfaceControllerTest {

	private DependencyInjectionContainer container;
	private UserInterfaceControllerForJUnit controller;
	private ServiceObjectStub serviceObject;
	private ServiceClassInfo serviceClassInfo;
	private DomainObjectStub domainObject;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(ServiceObjectStub.class).createContainer();
		controller = container.get(UserInterfaceControllerForJUnit.class);
		serviceObject = container.get(ServiceObjectStub.class);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		serviceClassInfo = reflectionProvider.getServiceClassInfo(ServiceObjectStub.class);
		domainObject = new DomainObjectStub();
	}

	@Test
	public void testProcessActionMethod() {
		// This method is tested by testEditActionMethodParameter,
		// testConfirmActionMethod and processMethodResult methods
	}

	@Test
	public void testEditActionMethodParameter() {
		ActionMethodInfo methodInfo = serviceClassInfo.getActionMethodInfo(ServiceObjectStub.ACTION_METHOD_NAME);
		assertNotNull(methodInfo);
		controller.processActionMethod(serviceObject, methodInfo, null);
		List<String> events = controller.getEvents();
		assertThat(events.get(0)).isEqualTo("processActionMethod(" + serviceObject.toString() + ", "
				+ serviceObject.getClass().getCanonicalName() + "." + ServiceObjectStub.ACTION_METHOD_NAME + ", null)");
		assertThat(events.get(1)).isEqualTo("editActionMethodParameter(" + serviceObject.toString() + ", "
				+ serviceObject.getClass().getCanonicalName() + "." + ServiceObjectStub.ACTION_METHOD_NAME + ", "
				+ domainObject + ")");

		controller.processActionMethod(serviceObject, methodInfo, domainObject);
		events = controller.getEvents();
		assertThat(events.get(0)).isEqualTo(
				"processActionMethod(" + serviceObject.toString() + ", " + serviceObject.getClass().getCanonicalName()
						+ "." + ServiceObjectStub.ACTION_METHOD_NAME + ", " + domainObject + ")");
		assertThat(events.get(1)).isEqualTo("editActionMethodParameter(" + serviceObject.toString() + ", "
				+ serviceObject.getClass().getCanonicalName() + "." + ServiceObjectStub.ACTION_METHOD_NAME + ", "
				+ domainObject + ")");

		// TODO verify editActionMethodWithoutParameter
	}

	// @Test
	// public void testConfirmActionMethod() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// public void testProcessActionMethodExecution() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// public void testProcessActionMethodResult() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// public void testShowActionMethodResultObjectActionMethodInfoObject() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// public void
	// testShowActionMethodResultObjectActionMethodInfoObjectObject() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// public void
	// testShowActionMethodResultObjectActionMethodInfoObjectListOfQ() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// public void testShowActionMethodResultObjectActionMethodInfoObjectURI() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// public void
	// testShowActionMethodResultObjectActionMethodInfoObjectDownloadStream() {
	// fail("Not yet implemented"); // TODO
	// }
	//
	// @Test
	// public void
	// testShowActionMethodResultObjectActionMethodInfoObjectString() {
	// fail("Not yet implemented"); // TODO
	// }

}
