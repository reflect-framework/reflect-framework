package nth.reflect.util.random.generator.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class ByteGeneratorTest {

	@Test
	public void testForNoParameter() {
		byte min = Byte.MIN_VALUE;
		byte max = Byte.MAX_VALUE;
		List<Byte> randomBytes = Random.byte_().generateList(20);
		assertThat(randomBytes).allSatisfy(b -> assertThat(b).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max));
	}

	@Test
	public void testForMax() {
		List<Byte> randomBytes = Random.byte_().forMax((byte) 1).generateList(20);
		assertThat(randomBytes)
				.allSatisfy(b -> assertThat(b).isGreaterThanOrEqualTo(Byte.MIN_VALUE).isLessThanOrEqualTo((byte) 1));

		randomBytes = Random.byte_().forMax((byte) 10).generateList(20);
		assertThat(randomBytes)
				.allSatisfy(b -> assertThat(b).isGreaterThanOrEqualTo(Byte.MIN_VALUE).isLessThanOrEqualTo((byte) 10));
	}

	@Test
	public void testForRange() {
		List<Byte> randomBytes = Random.byte_().forRange((byte) 0, (byte) 1).generateList(20);
		assertThat(randomBytes)
				.allSatisfy(b -> assertThat(b).isGreaterThanOrEqualTo((byte) 0).isLessThanOrEqualTo((byte) 1));

		randomBytes = Random.byte_().forRange((byte) 5, (byte) 10).generateList(20);
		assertThat(randomBytes)
				.allSatisfy(b -> assertThat(b).isGreaterThanOrEqualTo((byte) 5).isLessThanOrEqualTo((byte) 10));

		randomBytes = Random.byte_().forRange((byte) 10, (byte) 5).generateList(20);
		assertThat(randomBytes)
				.allSatisfy(b -> assertThat(b).isGreaterThanOrEqualTo((byte) 5).isLessThanOrEqualTo((byte) 10));
	}

}
