package nth.reflect.fw.infrastructure.random.generator.number;

import static org.junit.Assert.*;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.number.DoubleGenerator;

public class DoubleGeneratorTest {

	@Test
	public void testRandomDoubleFactory() {
		double min=10;
		double max=20;
		DoubleGenerator doubleGenerator= new DoubleGenerator(min, max);
		for (int j=0;j<10;j++) {
			double d = doubleGenerator.generate();
			assertTrue(d>=min);
			assertTrue(d<=max);
		}
	}
}
