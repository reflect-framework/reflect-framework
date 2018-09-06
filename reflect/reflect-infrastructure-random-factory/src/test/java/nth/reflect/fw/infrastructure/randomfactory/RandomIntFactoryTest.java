package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomIntFactoryTest {

	@Test
	public void testRandomIntFactory() {
		int min=10;
		int max=20;
		RandomIntFactory randomIntFactory=new RandomIntFactory(min, max);
		for (int j=0;j<10;j++) {
			Integer i = randomIntFactory.create();
			assertTrue(i>=min);
			assertTrue(i<=max);
		}
	}

}
