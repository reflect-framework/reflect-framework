package nth.reflect.fw.infrastructure.random.generator.number;

import static org.junit.Assert.*;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.number.BoolGenerator;

public class BoolGeneratorTest {

	@Test
	public void testRandomBoolFactory() {
		BoolGenerator boolGenerator=new BoolGenerator();
		boolean trueDetected=false;
		boolean falseDetected=false;
		for (int j=0;j<50;j++) {
			Boolean b = boolGenerator.generate();
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
