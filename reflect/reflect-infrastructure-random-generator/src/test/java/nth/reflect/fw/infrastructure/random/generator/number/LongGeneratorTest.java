package nth.reflect.fw.infrastructure.random.generator.number;

import static org.junit.Assert.*;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.number.LongGenerator;

public class LongGeneratorTest {

	@Test
	public void testRandomIntFactory() {
		long min=10;
		long max=20;
		LongGenerator longGenerator=new LongGenerator(min, max);
		for (int j=0;j<10;j++) {
			long l = longGenerator.generate();
			assertTrue(l>=min);
			assertTrue(l<=max);
		}
	}

}
