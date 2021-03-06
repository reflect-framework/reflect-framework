package nth.reflect.util.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.util.random.RandomGenerator;

public class FloatGenerator extends RandomGenerator<Float> {

	private final float min;
	private final float max;

	public FloatGenerator() {
		this(0, Float.MAX_VALUE);
	}

	public FloatGenerator(float min, float max) {
		this.min = min;
		this.max = max;
	}

	public FloatGenerator forMax(float max) {
		return new FloatGenerator(0, max);
	}

	public FloatGenerator forRange(float min, float max) {
		return new FloatGenerator(min, max);
	}

	@Override
	public Float generate() {
		if (min >= max) {
			return min;
		}
		return min + ThreadLocalRandom.current().nextFloat() * (max - min);
	}

}
