package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomBoolFactoryTest {

	@Test
	public void testRandomBoolFactory() {
		RandomBoolFactory randomBoolFactory=new RandomBoolFactory();
		boolean trueDetected=false;
		boolean falseDetected=false;
		for (int j=0;j<50;j++) {
			Boolean b = randomBoolFactory.create();
			if (b) {
				trueDetected=true;
			} else {
				falseDetected=true;
			}
		}
		assertTrue(trueDetected);
		assertTrue(falseDetected);
	}

}
