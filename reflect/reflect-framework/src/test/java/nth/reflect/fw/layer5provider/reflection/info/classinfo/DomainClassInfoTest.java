package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class DomainClassInfoTest {

	private DomainClassInfo domainClassInfo;
	private Class<AllFeatureDomainObject> domainObjectClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainObjectClass = AllFeatureDomainObject.class;
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
		String actual = domainClassInfo.getDescription().toString();
		String expected = StringUtil.convertToNormalCase(AllFeatureDomainObject.class.getSimpleName());
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetIconURI() {
		URL actual = domainClassInfo.getFontIconUrl(new AllFeatureDomainObject());
		assertThat(actual).isNull();
	}

	@Test
	public void testGetTitle() {
		String actual = domainClassInfo.getTitle(new AllFeatureDomainObject());
		String expected = AllFeatureDomainObject.class.getSimpleName() + ", 0";
		assertThat(actual).isEqualTo(expected);
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
		assertThat(propertyInfos.size()).isEqualTo(AllFeatureDomainObject.NR_OF_PROPERTIES);
	}

	@Test
	public void testGetPropertyInfo() {
		String propertyName = AllFeatureDomainObject.GET_MY_BYTE.replace("getM", "m");
		String actual = domainClassInfo.getPropertyInfo(propertyName).getCanonicalName();
		String expected = AllFeatureDomainObject.class.getCanonicalName() + "." + propertyName;
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public final void testGetPropertyInfosClassOfQ() {
		int result = domainClassInfo.getPropertyInfosSorted().size();
		assertThat(result).isEqualTo(AllFeatureDomainObject.NR_OF_PROPERTIES);
	}

	@Test
	public void testGetActionMethodSorted() {
		List<ActionMethodInfo> actionMethods = domainClassInfo.getActionMethodInfosSorted();
		assertThat(actionMethods.size()).isEqualTo(1);
		String expected = AllFeatureDomainObject.class.getCanonicalName() + "."
				+ AllFeatureDomainObject.ACTION_METHOD;
		String actual = actionMethods.get(0).getCanonicalName();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = domainClassInfo
				.getActionMethodInfos(new MethodNameFilter(AllFeatureDomainObject.ACTION_METHOD));
		assertThat(actionMethods.size()).isEqualTo(1);
		String expected = AllFeatureDomainObject.class.getCanonicalName() + "."
				+ AllFeatureDomainObject.ACTION_METHOD;
		assertThat(actionMethods.get(0).getCanonicalName()).isEqualTo(expected);
	}

}
