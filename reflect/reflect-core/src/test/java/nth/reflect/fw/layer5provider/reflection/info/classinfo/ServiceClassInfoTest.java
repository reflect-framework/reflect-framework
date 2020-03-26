package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.reflect.fw.stubs.DomainObjectStub;
import nth.reflect.fw.stubs.ServiceObjectStub;

public class ServiceClassInfoTest {

	private ServiceClassInfo serviceClassInfo;
	private Class<?> serviceClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		application.addServiceClass(ServiceObjectStub.class);
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		serviceClassInfo = reflectionProvider.getServiceClassInfo(ServiceObjectStub.class);
		serviceClass = serviceClassInfo.getObjectClass();
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
		assertThat(serviceClassInfo.getObjectClass()).isEqualTo(serviceClass);
	}

	@Test
	public void testGetDisplayName() {
		assertThat(serviceClassInfo.getDisplayName())
				.isEqualTo(StringUtil.convertToNormalCase(serviceClass.getSimpleName()));
	}

	@Test
	public void testGetDescription() {
		assertThat(serviceClassInfo.getDescription().toString()).isEqualTo(ServiceObjectStub.DESCRIPTION);
	}

	@Test
	public void testGetIconURI() {
		assertThat(serviceClassInfo.getFontIconUrl(new DomainObjectStub())).isNull();
	}

	@Test
	public void testGetTitle() {
		assertThat(serviceClassInfo.getTitle(new DomainObjectStub())).isEqualTo(DomainObjectStub.TITLE);
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
		String expectyed = ServiceObjectStub.class.getCanonicalName() + "." + ServiceObjectStub.ACTION_METHOD_NAME;
		assertThat(actionMethods.get(0).getCanonicalName()).isEqualTo(expectyed);
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = serviceClassInfo
				.getActionMethodInfos(new MethodNameFilter(ServiceObjectStub.ACTION_METHOD_NAME));
		assertThat(actionMethods.size()).isEqualTo(1);
		String expected = ServiceObjectStub.class.getCanonicalName() + "." + ServiceObjectStub.ACTION_METHOD_NAME;
		assertThat(actionMethods.get(0).getCanonicalName()).isEqualTo(expected);
	}

}
