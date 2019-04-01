package nth.reflect.fw.infrastructure.random.generator.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class BoolGeneratorTest {

	@Test
	public void testForNoParameters() {
		List<Boolean> randomBools = Random.bool().generateList(20);
		assertThat(randomBools).contains(true);
		assertThat(randomBools).contains(false);
	}

	@Test
	public void testForProbability() {
		List<Boolean> randomBools = Random.bool().forProbability(0).generateList(20);
		assertThat(randomBools).allSatisfy(b -> assertThat(b).isEqualTo(false));

		randomBools = Random.bool().forProbability(100).generateList(20);
		assertThat(randomBools).allSatisfy(b -> assertThat(b).isEqualTo(true));
	}

}
