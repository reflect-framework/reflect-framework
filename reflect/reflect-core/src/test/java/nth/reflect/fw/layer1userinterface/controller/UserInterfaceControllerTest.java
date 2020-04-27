package nth.reflect.fw.layer1userinterface.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.UserInterfaceControllerForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Person;
import nth.reflect.fw.layer5provider.reflection.behavior.description.PersonService;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class UserInterfaceControllerTest {

	private DependencyInjectionContainer container;
	private UserInterfaceControllerForJUnit controller;
	private PersonService personService;
	private ServiceClassInfo serviceClassInfo;
	private Person person;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(PersonService.class).createContainer();
		controller = container.get(UserInterfaceControllerForJUnit.class);
		personService = container.get(PersonService.class);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		serviceClassInfo = reflectionProvider.getServiceClassInfo(PersonService.class);
		person = new Person();
		person.setName("Nils ten Hoeve");
	}

	@Test
	public void testProcessActionMethod() {
		// This method is tested by testEditActionMethodParameter,
		// testConfirmActionMethod and processMethodResult methods
	}

	@Test
	public void testEditActionMethodParameter() {
		ActionMethodInfo methodInfo = serviceClassInfo.getActionMethodInfo(PersonService.FIND_METHOD_NAME);
		assertNotNull(methodInfo);
		controller.processActionMethod(personService, methodInfo, null);
		List<String> events = controller.getEvents();
		assertThat(events.get(0))
				.isEqualTo("processActionMethod(" + personService.toString() + ", "
						+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME
						+ ", null)");
		String expected = "editActionMethodParameter(" + personService.toString() + ", "
				+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME + ", )";
		assertThat(events.get(1)).isEqualTo(expected);

		controller.processActionMethod(personService, methodInfo, person);
		events = controller.getEvents();
		assertThat(events.get(0))
				.isEqualTo("processActionMethod(" + personService.toString() + ", "
						+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME + ", "
						+ person + ")");
		assertThat(events.get(1))
				.isEqualTo("editActionMethodParameter(" + personService.toString() + ", "
						+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME + ", )");

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
