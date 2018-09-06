package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomDoubleFactoryTest {

	@Test
	public void testRandomDoubleFactory() {
		double min=10;
		double max=20;
		RandomDoubleFactory randomDoubleFactory= new RandomDoubleFactory(min, max);
		for (int j=0;j<10;j++) {
			double d = randomDoubleFactory.create();
			assertTrue(d>=min);
			assertTrue(d<=max);
		}
	}
}
