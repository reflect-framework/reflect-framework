package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class DomainClassInfoTest {

	private DomainClassInfo domainClassInfo;
	private Class<FullFeatureDomainObject> domainObjectClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainObjectClass = FullFeatureDomainObject.class;
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
		String expected = StringUtil.convertToNormalCase(FullFeatureDomainObject.class.getSimpleName());
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetIconURI() {
		URL actual = domainClassInfo.getFontIconUrl(new FullFeatureDomainObject());
		assertThat(actual).isNull();
	}

	@Test
	public void testGetTitle() {
		String actual = domainClassInfo.getTitle(new FullFeatureDomainObject());
		String expected = FullFeatureDomainObject.class.getSimpleName() + ", 0";
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
		assertThat(propertyInfos.size()).isEqualTo(FullFeatureDomainObject.NR_OF_PROPERTIES);
	}

	@Test
	public void testGetPropertyInfo() {
		String propertyName = FullFeatureDomainObject.GET_MY_BYTE.replace("getM", "m");
		String actual = domainClassInfo.getPropertyInfo(propertyName).getCanonicalName();
		String expected = FullFeatureDomainObject.class.getCanonicalName() + "." + propertyName;
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public final void testGetPropertyInfosClassOfQ() {
		int result = domainClassInfo.getPropertyInfosSorted().size();
		assertThat(result).isEqualTo(FullFeatureDomainObject.NR_OF_PROPERTIES);
	}

	@Test
	public void testGetActionMethodSorted() {
		List<ActionMethodInfo> actionMethods = domainClassInfo.getActionMethodInfosSorted();
		assertThat(actionMethods.size()).isEqualTo(1);
		String expected = FullFeatureDomainObject.class.getCanonicalName() + "."
				+ FullFeatureDomainObject.ACTION_METHOD;
		String actual = actionMethods.get(0).getCanonicalName();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = domainClassInfo
				.getActionMethodInfos(new MethodNameFilter(FullFeatureDomainObject.ACTION_METHOD));
		assertThat(actionMethods.size()).isEqualTo(1);
		String expected = FullFeatureDomainObject.class.getCanonicalName() + "."
				+ FullFeatureDomainObject.ACTION_METHOD;
		assertThat(actionMethods.get(0).getCanonicalName()).isEqualTo(expected);
	}

}
