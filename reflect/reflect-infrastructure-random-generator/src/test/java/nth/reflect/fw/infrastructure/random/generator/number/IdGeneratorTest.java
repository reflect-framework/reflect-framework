package nth.reflect.fw.infrastructure.random.generator.number;

import static org.junit.Assert.*;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.number.IdGenerator;

public class IdGeneratorTest {

	@Test
	public void testCreate() {
		IdGenerator idGenerator=new IdGenerator();
		int integer1=idGenerator.generate();
		int integer2=idGenerator.generate();
		int integer3=idGenerator.generate();
		assertTrue(integer1==0);
		assertTrue(integer2==1);
		assertTrue(integer3==2);
	}

}
