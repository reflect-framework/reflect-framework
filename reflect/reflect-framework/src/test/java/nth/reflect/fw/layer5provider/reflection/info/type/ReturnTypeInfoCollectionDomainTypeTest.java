package nth.reflect.fw.layer5provider.reflection.info.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class ReturnTypeInfoCollectionDomainTypeTest {

	private ReflectApplicationForJUnit application;
	private TypeInfo typeInfo;

	public List<DomainObject> theMethod() {
		return new ArrayList<DomainObject>();
	}

	@Before
	public void setUp() throws Exception {
		application = new ReflectApplicationForJUnit();
		Method method = this.getClass().getMethod("theMethod");
		typeInfo = new ReturnTypeInfo(application, method);
	}

	@Test
	public void testGetType() {
		assertThat(typeInfo.getType()).isEqualTo(List.class);
	}

	@Test
	public void testGetGenericType() {
		assertThat(typeInfo.getGenericTypeInfo().get().getType()).isEqualTo(DomainObject.class);
	}

	@Test
	public void testIsDomainClass() {
		assertThat(typeInfo.isDomainClass()).isEqualTo(false);
	}

	@Test
	public void testIsVoid() {
		assertThat(typeInfo.isVoid()).isEqualTo(false);
	}

	@Test
	public void testIsCollection() {
		assertThat(typeInfo.isCollection()).isEqualTo(true);
	}

	@Test
	public void testHasMultipleValues() {
		assertThat(typeInfo.hasMultipleValues()).isEqualTo(true);
	}

	@Test
	public void testIsJavaType() {
		assertThat(typeInfo.isJavaType()).isEqualTo(true);
	}

	@Test
	public void testIsArray() {
		assertThat(typeInfo.isArray()).isEqualTo(false);
	}

	@Test
	public void testIsMap() {
		assertThat(typeInfo.isMap()).isEqualTo(false);
	}

	@Test
	public void testIsEnum() {
		assertThat(typeInfo.isEnum()).isEqualTo(false);
	}

}
