package nth.reflect.fw.junit.layer5provider.reflection.behavior.option;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsNotExistingModel;

public class OptionsNotExistingModelTest {
	private OptionsNotExistingModel optionsNotExistingModel;

	@Before
	public void setUp() {
		optionsNotExistingModel = new OptionsNotExistingModel();
	}

	@Test
	public void testHasOptions() {
		assertThat(optionsNotExistingModel.hasOptions()).isEqualTo(false);
	}

	@Test
	public void testGetOptions() {
		assertThat(optionsNotExistingModel.getOptions(null)).isEmpty();
	}

}
