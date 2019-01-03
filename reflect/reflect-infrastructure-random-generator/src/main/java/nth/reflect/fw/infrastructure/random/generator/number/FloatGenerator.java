package nth.reflect.fw.infrastructure.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class FloatGenerator extends RandomGenerator<Float> {

	private final float min;
	private final float max;

	public FloatGenerator() {
		this(0, Float.MAX_VALUE);
	}

	public FloatGenerator(float min, float max) {
		if (min >= max) {
			float temp = min;
			min = max;
			max = temp;
		}
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
		return min + ThreadLocalRandom.current().nextFloat() * (max - min);
	}

}
