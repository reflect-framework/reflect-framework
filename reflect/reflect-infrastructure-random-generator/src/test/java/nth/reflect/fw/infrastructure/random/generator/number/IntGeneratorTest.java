package nth.reflect.fw.infrastructure.random.generator.number;

import static org.junit.Assert.*;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;

public class IntGeneratorTest {

	@Test
	public void testRandomIntFactory() {
		int min=10;
		int max=20;
		IntGenerator intGenerator=new IntGenerator(min, max);
		for (int j=0;j<10;j++) {
			Integer i = intGenerator.generate();
			assertTrue(i>=min);
			assertTrue(i<=max);
		}
	}

}
