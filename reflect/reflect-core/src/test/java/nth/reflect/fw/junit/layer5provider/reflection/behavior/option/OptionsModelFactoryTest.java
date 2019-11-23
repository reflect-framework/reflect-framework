package nth.reflect.fw.junit.layer5provider.reflection.behavior.option;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.junit.layer5provider.reflection.info.type.TestEnum;
import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsModel;
import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsModelFactory;

public class OptionsModelFactoryTest {
	// TODO make all unit tests working and commit issue #99

	private TestDomainObject domainObject;

	@Before
	public void setup() {
		domainObject = new TestDomainObject();
	}

	@Test
	public void testCreateForStringOptions() throws NoSuchMethodException, SecurityException {
		Method getMethod = domainObject.getClass().getMethod("getStringProperty");
		OptionsModel optionsModel = OptionsModelFactory.create(getMethod);
		assertThat(optionsModel.hasOptions()).isEqualTo(true);
		assertThat(optionsModel.getOptions(domainObject)).containsAll(domainObject.stringPropertyOptions());
	}

	@Test
	public void testCreateForNoOptions() throws NoSuchMethodException, SecurityException {
		Method getMethod = domainObject.getClass().getMethod("getNoOptionsProperty");
		OptionsModel optionsModel = OptionsModelFactory.create(getMethod);
		assertThat(optionsModel.hasOptions()).isEqualTo(false);
		assertThat(optionsModel.getOptions(domainObject)).isEmpty();
	}

	@Test
	public void testCreateForEnumOptions() throws NoSuchMethodException, SecurityException {
		Method getMethod = domainObject.getClass().getMethod("getEnumProperty");
		OptionsModel optionsModel = OptionsModelFactory.create(getMethod);
		assertThat(optionsModel.hasOptions()).isEqualTo(true);
		assertThat(optionsModel.getOptions(domainObject)).containsAll(Arrays.asList(TestEnum.values()));
	}

	@Test
	public void testCreateForLimitedEnumOptions() throws NoSuchMethodException, SecurityException {
		Method getMethod = domainObject.getClass().getMethod("getLimitedEnumProperty");
		OptionsModel optionsModel = OptionsModelFactory.create(getMethod);
		assertThat(optionsModel.hasOptions()).isEqualTo(true);
		assertThat(optionsModel.getOptions(domainObject)).containsAll(domainObject.limitedEnumPropertyOptions());
	}

}
