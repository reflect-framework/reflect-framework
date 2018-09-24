package nth.reflect.fw.infrastructure.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class BoolGenerator implements RandomGenerator<Boolean> {

	private final int probability;

	public BoolGenerator() {
		this.probability=50;
	}

	public BoolGenerator(int probability) {
		this.probability = probability;
	}
	
	public BoolGenerator forProbability(int probability) {
		return new BoolGenerator(probability);
	}

	@Override
	public Boolean generate() {
		float randomFloat = ThreadLocalRandom.current().nextFloat();
	        return randomFloat <= ((float) probability / 100);
	}

}
