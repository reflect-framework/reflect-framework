package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

public class RandomLocalTimeFactoryTest {

	@Test
	public void testRandomLocalTimeFactoryTest() {
		LocalTime min = LocalTime.of(9, 10, 12);
		LocalTime max = LocalTime.of(15,45,22,99);
		RandomLocalTimeFactory randomLocalTimeFactory=new RandomLocalTimeFactory(min, max);
		for (int j=0;j<10;j++) {
			LocalTime randomTime= randomLocalTimeFactory.create();
			assertTrue(randomTime.compareTo(min)>=0);
			assertTrue(randomTime.compareTo(max)<=0);
		}
	}

}
