package nth.reflect.fw.infrastructure.random.generator.number;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class BoolGeneratorTest {

	@Test
	public void testForNoParameters() {
		List<Boolean> randomBools = Random.boolGenerator().generateList(20);
		assertThat(randomBools, hasItem(true));
		assertThat(randomBools, hasItem(false));
	}

	@Test
	public void testForProbability() {
		List<Boolean> randomBools = Random.boolGenerator().forProbability(0).generateList(20);
		assertThat(randomBools, everyItem(equalTo(false)));
		
		randomBools = Random.boolGenerator().forProbability(100).generateList(20);
		assertThat(randomBools, everyItem(equalTo(true)));
	}
	

}
