package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.*;

import org.junit.Test;

public class IncIntFactoryTest {

	@Test
	public void testCreate() {
		IncIntFactory incIntFactory=new IncIntFactory();
		int integer1=incIntFactory.create();
		int integer2=incIntFactory.create();
		int integer3=incIntFactory.create();
		assertTrue(integer1==0);
		assertTrue(integer2==1);
		assertTrue(integer3==2);
	}

}
