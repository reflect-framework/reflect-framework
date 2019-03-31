package nth.reflect.fw.junit.layer5provider.reflection.info.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.info.type.FirstParameterTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class FirstParameterTypeInfoJavaComplexTypeTest {

	private ReflectApplicationForJUnit application;
	private TypeInfo typeInfo;

	public void theMethod(Integer i) {
	}

	@Before
	public void setUp() throws Exception {
		application = new ReflectApplicationForJUnit();
		Method method = this.getClass().getMethod("theMethod", Integer.class);
		typeInfo = new FirstParameterTypeInfo(application, method);
	}

	@Test
	public void testGetType() {
		assertThat(typeInfo.getType()).isEqualTo(Integer.class);

	}

	@Test
	public void testGetGenericType() {
		assertThat(typeInfo.getGenericType()).isEqualTo(Integer.class);
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
		assertThat(typeInfo.isCollection()).isEqualTo(false);
	}

	@Test
	public void testHasMultipleValues() {
		assertThat(typeInfo.hasMultipleValues()).isEqualTo(false);
	}

	@Test
	public void testIsJavaType() {
		assertThat(typeInfo.isJavaVariableType()).isEqualTo(true);
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
