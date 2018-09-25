package nth.reflect.fw.infrastructure.random.generator.number;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.contains;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.number.IdGenerator;

public class IdGeneratorTest {

	@Test
	public void testGenerate() {
		IdGenerator idGenerator=Random.idGenerator();
		int integer1=idGenerator.generate();
		int integer2=idGenerator.generate();
		int integer3=idGenerator.generate();
		assertTrue(integer1==0);
		assertTrue(integer2==1);
		assertTrue(integer3==2);
	}

	@Test
	public void testGenerateList() {
		int size = 8;
		List<Integer> ids = Random.idGenerator().generateList(size);
		assertThat(ids, hasSize(size));
		assertThat(ids, contains(0,1,2,3,4,5,6,7));
	}

}
