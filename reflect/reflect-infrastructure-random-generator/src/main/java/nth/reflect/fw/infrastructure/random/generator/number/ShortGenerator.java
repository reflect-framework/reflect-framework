package nth.reflect.fw.infrastructure.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class ShortGenerator extends RandomGenerator<Short> {

	private final short min;
	private final short max;

	public ShortGenerator() {
		this((short) 0, Short.MAX_VALUE);
	}

	public ShortGenerator(short min, short max) {
		if (min >= max) {
			short temp = min;
			min = max;
			max = temp;
		}
		this.min = min;
		this.max = max;
	}

	public ShortGenerator forMax(short max) {
		return new ShortGenerator((short) 0, max);
	}

	public ShortGenerator forRange(short min, short max) {
		return new ShortGenerator(min, max);
	}

	@Override
	public Short generate() {
		return (short) ThreadLocalRandom.current().nextInt(min, max);
	}

}
