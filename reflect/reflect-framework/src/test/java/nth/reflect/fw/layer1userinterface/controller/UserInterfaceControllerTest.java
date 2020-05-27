package nth.reflect.fw.layer1userinterface.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.LogProvider;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Person;
import nth.reflect.fw.layer5provider.reflection.behavior.description.PersonService;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class UserInterfaceControllerTest {

	private static final Object NO_PARAMETER = null;
	private UserInterfaceContainer container;
	private PersonService personService;
	private ServiceClassInfo serviceClassInfo;
	private Person person;
	private LogProvider log;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(PersonService.class).createContainer();
		personService = container.get(PersonService.class);
		log = container.get(LogProvider.class);
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
		log.clear();
		methodInfo.process(container, personService, NO_PARAMETER);
//		assertThat(log.get(0))
//				.isEqualTo("processActionMethod(" + personService.toString() + ", "
//						+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME
//						+ ", null)");
		String expected = "editActionMethodParameter(" + personService.toString() + ", "
				+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME + ", )";
		assertThat(log.get(0)).isEqualTo(expected);

		log.clear();
		methodInfo.process(container, personService, person);
//		assertThat(log.get(0))
//				.isEqualTo("processActionMethod(" + personService.toString() + ", "
//						+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME + ", "
//						+ person + ")");
		assertThat(log.get(0))
				.isEqualTo("editActionMethodParameter(" + personService.toString() + ", "
						+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME + ", )");

		// TODO verify editActionMethodWithoutParameter
	}

}
