package nth.reflect.fw.infrastructure.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class ByteGenerator extends RandomGenerator<Byte> {

	private final byte min;
	private final byte max;

	public ByteGenerator() {
		this((byte) 0, Byte.MAX_VALUE);
	}

	public ByteGenerator(byte min, byte max) {
		if (min >= max) {
			byte temp = min;
			min = max;
			max = temp;
		}
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
		return (byte) ThreadLocalRandom.current().nextInt(min, max);
	}

}
