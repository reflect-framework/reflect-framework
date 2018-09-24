package nth.reflect.fw.infrastructure.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class IntGenerator implements RandomGenerator<Integer> {

	private final int min;
	private final int max;

	
	public IntGenerator() {
		this(0,Integer.MAX_VALUE);
	}
	
	public IntGenerator(int min, int max) {
		if (min>=max) {
			int temp=min;
			min=max;
			max=temp;
		}
		this.min = min;
		this.max = max;
	}
	
	public IntGenerator forMin(int min) {
		return new IntGenerator(min, max);
	}
	
	public IntGenerator forMax(int max) {
		return new IntGenerator(min, max);
	}
	
	public IntGenerator forRange(int min, int max) {
		return new IntGenerator(min, max);
	}
	
	@Override
	public Integer generate() {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
