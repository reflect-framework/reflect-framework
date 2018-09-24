package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.datetime.LocalDateGenerator;

public class LocalDateGeneratorTest {

	@Test
	public void testRandomLocalDateFactoryTest() {
		LocalDate min = LocalDate.of(1970, 1, 1);
		LocalDate max = LocalDate.of(2015, 12, 31);
		LocalDateGenerator localDateGenerator=new LocalDateGenerator(min, max);
		for (int j=0;j<10;j++) {
			LocalDate randomDate= localDateGenerator.generate();
			assertTrue(randomDate.compareTo(min)>=0);
			assertTrue(randomDate.compareTo(max)<=0);
		}
	}

}
