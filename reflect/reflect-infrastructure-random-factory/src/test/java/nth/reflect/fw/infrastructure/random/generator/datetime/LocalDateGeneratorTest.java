package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class RandomLocalDateFactoryTest {

	@Test
	public void testRandomLocalDateFactoryTest() {
		LocalDate min = LocalDate.of(1970, 1, 1);
		LocalDate max = LocalDate.of(2015, 12, 31);
		RandomLocalDateFactory randomLocalDateFactory=new RandomLocalDateFactory(min, max);
		for (int j=0;j<10;j++) {
			LocalDate randomDate= randomLocalDateFactory.create();
			assertTrue(randomDate.compareTo(min)>=0);
			assertTrue(randomDate.compareTo(max)<=0);
		}
	}

}
