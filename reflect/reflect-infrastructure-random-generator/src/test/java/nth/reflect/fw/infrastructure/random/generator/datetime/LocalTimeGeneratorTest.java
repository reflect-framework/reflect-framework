package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.datetime.LocalTimeGenerator;

public class LocalTimeGeneratorTest {

	@Test
	public void testRandomLocalTimeFactoryTest() {
		LocalTime min = LocalTime.of(9, 10, 12);
		LocalTime max = LocalTime.of(15,45,22,99);
		LocalTimeGenerator localTimeGenerator=new LocalTimeGenerator(min, max);
		for (int j=0;j<10;j++) {
			LocalTime randomTime= localTimeGenerator.generate();
			assertTrue(randomTime.compareTo(min)>=0);
			assertTrue(randomTime.compareTo(max)<=0);
		}
	}

}
