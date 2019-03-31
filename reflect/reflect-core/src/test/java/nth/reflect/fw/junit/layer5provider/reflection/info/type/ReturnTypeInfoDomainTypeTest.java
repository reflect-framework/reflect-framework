package nth.reflect.fw.junit.layer5provider.reflection.info.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class ReturnTypeInfoDomainTypeTest {

	private ReflectApplicationForJUnit application;
	private TypeInfo typeInfo;

	public TestDomainClass theMethod() {
		return new TestDomainClass();
	}

	@Before
	public void setUp() throws Exception {
		application = new ReflectApplicationForJUnit();
		Method method = this.getClass().getMethod("theMethod");
		typeInfo = new ReturnTypeInfo(application, method);
	}

	@Test
	public void testGetType() {
		assertThat(typeInfo.getType()).isEqualTo(TestDomainClass.class);
	}

	@Test
	public void testGetGenericType() {
		assertThat(typeInfo.getGenericType()).isEqualTo(TestDomainClass.class);
	}

	@Test
	public void testIsDomainClass() {
		assertThat(typeInfo.isDomainClass()).isEqualTo(true);
	}

	@Test
	public void testIsVoid() {
		assertThat(typeInfo.isVoid()).isEqualTo(false);
	}

	@Test
	public void testIsCollection() {
		assertThat(typeInfo.isCollection()).isEqualTo(false);
	}

	@Test
	public void testHasMultipleValues() {
		assertThat(typeInfo.hasMultipleValues()).isEqualTo(false);
	}

	@Test
	public void testIsJavaType() {
		assertThat(typeInfo.isJavaVariableType()).isEqualTo(false);
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
