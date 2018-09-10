package nth.reflect.fw.junit.layer1userinterface.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.UserInterfaceControllerForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

public class UserInterfaceControllerTest {

	private DependencyInjectionContainer container;
	private UserInterfaceControllerForJUnit controller;
	private UserInterfaceControllerTestServiceObject serviceObject;
	private ClassInfo serviceObjectInfo;
	private UserInterfaceControllerTestDomainObject domainObject;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit()
				.addServiceClass(UserInterfaceControllerTestServiceObject.class).createContainer();
		controller=container.get(UserInterfaceControllerForJUnit.class);
		serviceObject=container.get(UserInterfaceControllerTestServiceObject.class);
		ReflectionProvider reflectionProvider=container.get(ReflectionProvider.class);
		serviceObjectInfo=reflectionProvider.getClassInfo(UserInterfaceControllerTestServiceObject.class);
		domainObject=new UserInterfaceControllerTestDomainObject();
	}

	@Test
	public void testProcessActionMethod() {
		//This method is tested by testEditActionMethodParameter, testConfirmActionMethod and processMethodResult methods
		}

	@Test
	public void testEditActionMethodParameter() {
		ActionMethodInfo methodInfo= serviceObjectInfo.getActionMethodInfo("editActionMethodDomainParameter");
		assertNotNull(methodInfo);
		controller.processActionMethod(serviceObject, methodInfo, null);
		List<String> events = controller.getEvents();
		assertEquals("processActionMethod(ServiceObject, nth.reflect.fw.junit.layer1userinterface.controller.UserInterfaceControllerTestServiceObject.editActionMethodDomainParameter, null)", events.get(0));
		assertEquals("editActionMethodParameter(ServiceObject, nth.reflect.fw.junit.layer1userinterface.controller.UserInterfaceControllerTestServiceObject.editActionMethodDomainParameter, DomainObject)", events.get(1));

		controller.processActionMethod(serviceObject, methodInfo, domainObject);
		events = controller.getEvents();
		assertEquals("processActionMethod(ServiceObject, nth.reflect.fw.junit.layer1userinterface.controller.UserInterfaceControllerTestServiceObject.editActionMethodDomainParameter, DomainObject)", events.get(0));
		assertEquals("editActionMethodParameter(ServiceObject, nth.reflect.fw.junit.layer1userinterface.controller.UserInterfaceControllerTestServiceObject.editActionMethodDomainParameter, DomainObject)", events.get(1));

	//TODO verify editActionMethodWithoutParameter
	}

//	@Test
//	public void testConfirmActionMethod() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testProcessActionMethodExecution() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testProcessActionMethodResult() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testShowActionMethodResultObjectActionMethodInfoObject() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testShowActionMethodResultObjectActionMethodInfoObjectObject() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testShowActionMethodResultObjectActionMethodInfoObjectListOfQ() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testShowActionMethodResultObjectActionMethodInfoObjectURI() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testShowActionMethodResultObjectActionMethodInfoObjectDownloadStream() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testShowActionMethodResultObjectActionMethodInfoObjectString() {
//		fail("Not yet implemented"); // TODO
//	}

}
