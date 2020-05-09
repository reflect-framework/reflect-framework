package nth.reflect.fw.layer5provider.reflection.behavior.option;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsMethodModel;

public class OptionsMethodModelTest {

	private TestDomainObject domainObject;
	private OptionsMethodModel optionsMethodModel;

	@Before
	public void setUp() throws NoSuchMethodException, SecurityException {
		domainObject = new TestDomainObject();
		Method optionsMethod = domainObject.getClass().getMethod("stringPropertyOptions");
		optionsMethodModel = new OptionsMethodModel(optionsMethod);
	}

	@Test
	public void testHasOptions() {
		assertThat(optionsMethodModel.hasOptions()).isEqualTo(true);
	}

	@Test
	public void testGetOptions() {
		assertThat(optionsMethodModel.getOptions(domainObject)).containsAll(domainObject.stringPropertyOptions());
	}

}
