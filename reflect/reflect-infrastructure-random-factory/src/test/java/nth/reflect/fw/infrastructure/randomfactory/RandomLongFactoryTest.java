package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomLongFactoryTest {

	@Test
	public void testRandomIntFactory() {
		long min=10;
		long max=20;
		RandomLongFactory randomLongFactory=new RandomLongFactory(min, max);
		for (int j=0;j<10;j++) {
			long l = randomLongFactory.create();
			assertTrue(l>=min);
			assertTrue(l<=max);
		}
	}

}
