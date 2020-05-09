package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.PersonService;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;

public class ServiceClassInfoTest {

	private ServiceClassInfo serviceClassInfo;
	private Class<?> serviceClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		application.addServiceClass(PersonService.class);
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		serviceClassInfo = reflectionProvider.getServiceClassInfo(PersonService.class);
		serviceClass = serviceClassInfo.getType();
	}

	@Test
	public void testGetSimpleName() {
		assertThat(serviceClassInfo.getSimpleName()).isEqualTo(serviceClass.getSimpleName());
	}

	@Test
	public void testGetCanonicalName() {
		assertThat(serviceClassInfo.getCanonicalName()).isEqualTo(serviceClass.getCanonicalName());
	}

	@Test
	public void testGetObjectClass() {
		assertThat(serviceClassInfo.getType()).isEqualTo(serviceClass);
	}

	@Test
	public void testGetDisplayName() {
		assertThat(serviceClassInfo.getDisplayName().getTranslation()).isEqualTo(PersonService.DISPLAY_NAME);
	}

	@Test
	public void testGetDescription() {
		assertThat(serviceClassInfo.getDescription().toString()).isEqualTo(PersonService.DESCRIPTION);
	}

	@Test
	public void testGetIconURI() {
		URL actual = serviceClassInfo.getFontIconUrl(new AllFeatureDomainObject());
		assertThat(actual).isNull();
	}

	@Test
	public void testGetTitle() {
		String actual = serviceClassInfo.getTitle(new AllFeatureDomainObject());
		String expected = AllFeatureDomainObject.class.getSimpleName() + ", 0";
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testToString() {
		assertThat(serviceClassInfo.toString()).isEqualTo(serviceClass.getCanonicalName());
	}

	@Test
	public void testGetAllValidationMethods() {
		assertThat(serviceClassInfo.getAllValidationMethods().size()).isEqualTo(0);
	}

	@Test
	public void testGetActionMethodSorted() {
		List<ActionMethodInfo> actionMethods = serviceClassInfo.getActionMethodInfosSorted();
		assertThat(actionMethods.size()).isEqualTo(1);
		String expectyed = PersonService.class.getCanonicalName() + "." + PersonService.FIND_METHOD_NAME;
		assertThat(actionMethods.get(0).getCanonicalName()).isEqualTo(expectyed);
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = serviceClassInfo
				.getActionMethodInfos(new MethodNameFilter(PersonService.FIND_METHOD_NAME));
		assertThat(actionMethods.size()).isEqualTo(1);
		String expected = PersonService.class.getCanonicalName() + "." + PersonService.FIND_METHOD_NAME;
		assertThat(actionMethods.get(0).getCanonicalName()).isEqualTo(expected);
	}

}
