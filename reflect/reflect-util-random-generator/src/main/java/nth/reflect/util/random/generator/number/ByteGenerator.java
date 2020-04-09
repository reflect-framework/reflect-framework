package nth.reflect.util.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.util.random.RandomGenerator;

public class ByteGenerator extends RandomGenerator<Byte> {

	private final byte min;
	private final byte max;

	public ByteGenerator() {
		this((byte) 0, Byte.MAX_VALUE);
	}

	public ByteGenerator(byte min, byte max) {
		this.min = min;
		this.max = max;
	}

	public ByteGenerator forMax(byte max) {
		return new ByteGenerator((byte) 0, max);
	}

	public ByteGenerator forRange(byte min, byte max) {
		return new ByteGenerator(min, max);
	}

	@Override
	public Byte generate() {
		if (min >= max) {
			return min;
		}
		return (byte) ThreadLocalRandom.current().nextInt(min, max);
	}

}
