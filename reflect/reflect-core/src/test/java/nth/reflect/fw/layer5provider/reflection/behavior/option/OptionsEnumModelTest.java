package nth.reflect.fw.layer5provider.reflection.behavior.option;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsEnumModel;

public class OptionsEnumModelTest {
	private static final Class<TestEnum> ENUM_TYPE = TestEnum.class;
	private static final List<Enum<?>> ENUM_LIST = Arrays.asList(ENUM_TYPE.getEnumConstants());
	private OptionsEnumModel optionsEnumModel;

	@Before
	public void setup() {
		optionsEnumModel = new OptionsEnumModel(ENUM_TYPE);
	}

	@Test
	public void testHasOptions() {
		assertThat(optionsEnumModel.hasOptions()).isEqualTo(true);
	}

	@Test
	public void testGetOptions() {
		assertThat(optionsEnumModel.getOptions(null)).containsAll(ENUM_LIST);
	}

}
