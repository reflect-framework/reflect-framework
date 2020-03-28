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
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.stubs.DomainObjectStub;

public class DomainClassInfoTest {

	private DomainClassInfo domainClassInfo;
	private Class<DomainObjectStub> domainObjectClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainObjectClass = DomainObjectStub.class;
		domainClassInfo = reflectionProvider.getDomainClassInfo(domainObjectClass);
	}

	@Test
	public void testGetSimpleName() {
		assertThat(domainClassInfo.getSimpleName()).isEqualTo(domainObjectClass.getSimpleName());
	}

	@Test
	public void testGetCanonicalName() {
		assertThat(domainClassInfo.getCanonicalName()).isEqualTo(domainObjectClass.getCanonicalName());
	}

	@Test
	public void testGetObjectClass() {
		assertThat(domainClassInfo.getType()).isEqualTo(domainObjectClass);
	}

	@Test
	public void testGetDisplayName() {
		String expected = StringUtil.convertToNormalCase(domainObjectClass.getSimpleName());
		assertThat(domainClassInfo.getDisplayName().getTranslation()).isEqualTo(expected);
	}

	@Test
	public void testGetDescription() {
		assertThat(domainClassInfo.getDescription().toString()).isEqualTo(DomainObjectStub.DESCRIPTION);
	}

	@Test
	public void testGetIconURI() {
		assertThat(domainClassInfo.getFontIconUrl(new DomainObjectStub())).isNull();
	}

	@Test
	public void testGetTitle() {
		assertThat(domainClassInfo.getTitle(new DomainObjectStub())).isEqualTo(DomainObjectStub.TITLE);
	}

	@Test
	public void testToString() {
		assertThat(domainClassInfo.toString()).isEqualTo(domainObjectClass.getCanonicalName());
	}

	@Test
	public void testGetAllValidationMethods() {
		assertThat(domainClassInfo.getAllValidationMethods().size()).isEqualTo(0);
	}

	@Test
	public void testGetPropertyInfosSorted() {
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
		assertThat(propertyInfos.size()).isEqualTo(2);
	}

	@Test
	public void testGetPropertyInfo() {
		String result = domainClassInfo.getPropertyInfo(DomainObjectStub.PROPERTY1).getCanonicalName();
		String expected = DomainObjectStub.class.getCanonicalName() + "." + DomainObjectStub.PROPERTY1;
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public final void testGetPropertyInfosClassOfQ() {
		int result = domainClassInfo.getPropertyInfosSorted().size();
		assertThat(result).isEqualTo(2);
	}

	@Test
	public void testGetActionMethodSorted() {
		List<ActionMethodInfo> actionMethods = domainClassInfo.getActionMethodInfosSorted();
		assertThat(actionMethods.size()).isEqualTo(1);
		String expected = DomainObjectStub.class.getCanonicalName() + "." + DomainObjectStub.CLASS_ACTION_METHOD;
		assertThat(actionMethods.get(0).getCanonicalName()).isEqualTo(expected);
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = domainClassInfo
				.getActionMethodInfos(new MethodNameFilter(DomainObjectStub.CLASS_ACTION_METHOD));
		assertThat(actionMethods.size()).isEqualTo(1);
		String expected = DomainObjectStub.class.getCanonicalName() + "." + DomainObjectStub.CLASS_ACTION_METHOD;
		assertThat(actionMethods.get(0).getCanonicalName()).isEqualTo(expected);
	}

}
