package nth.reflect.fw.layer5provider.actionmethod.execution;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.JunitActionMethodPreHandler;
import nth.reflect.fw.junit.LogProvider;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Person;
import nth.reflect.fw.layer5provider.reflection.behavior.description.PersonService;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class ActionMethodExecutionProviderTest {

	private static final String NO_PARAMETER = null;
	private UserInterfaceContainer container;
	private PersonService personService;
	private ServiceClassInfo serviceClassInfo;
	private LogProvider log;
	private Person person;
	private ActionMethodExecutionProvider executionProvider;
	private ActionMethodInfo methodInfo;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(PersonService.class).createContainer();
		personService = container.get(PersonService.class);
		log = container.get(LogProvider.class);
		executionProvider = container.get(ActionMethodExecutionProvider.class);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		serviceClassInfo = reflectionProvider.getServiceClassInfo(PersonService.class);
		person = new Person();
		person.setName("Nils ten Hoeve");
		methodInfo = serviceClassInfo.getActionMethodInfo(PersonService.FIND_METHOD_NAME);
		assertNotNull(methodInfo);
	}

	@Test
	public void testProcess_givenServiceMethodWithNullParameter_thenCorrectLog() {
		log.clear();

		executionProvider.process(container, methodInfo, personService, NO_PARAMETER);
		String expected = JunitActionMethodPreHandler.class.getSimpleName() + ".preProcess("
				+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME + ", "
				+ personService.getClass().getCanonicalName() + ", " + Person.NO_NAME + ")";
		assertThat(log.get(0)).isEqualTo(expected);
	}

	@Test
	public void testProcess_givenServiceMethodWithPersonParameter_thenCorrectLog() {

		log.clear();
		executionProvider.process(container, methodInfo, personService, person);
		String expected = JunitActionMethodPreHandler.class.getSimpleName() + ".preProcess("
				+ personService.getClass().getCanonicalName() + "." + PersonService.FIND_METHOD_NAME + ", "
				+ personService.getClass().getCanonicalName() + ", " + Person.NO_NAME + ")";
		assertThat(log.get(0)).isEqualTo(expected);

		// TODO verify editActionMethodWithoutParameter
	}

}
