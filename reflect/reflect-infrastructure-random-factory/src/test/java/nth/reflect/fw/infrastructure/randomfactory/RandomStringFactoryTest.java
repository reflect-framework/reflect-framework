package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomStringFactoryTest {

	@Test
	public void testLetterStringCreation() {
		int length=10;
		RandomStringFactory randomStringFactory=new RandomStringFactory(length, true, false);
		String string=randomStringFactory.create();
		assertTrue(string.length()==length);
		for (Character ch : string.toCharArray()) {
			assertTrue(Character.isLetter(ch));
		}
	}
	
	@Test
	public void testNumberStringCreation() {
		int length=20;
		RandomStringFactory randomStringFactory=new RandomStringFactory(length, false, true);
		String string=randomStringFactory.create();
		assertTrue(string.length()==length);
		for (Character ch : string.toCharArray()) {
			assertTrue(Character.isDigit(ch));
		}
	}

	@Test
	public void testLetterAndNumberStringCreation() {
		int length=15;
		RandomStringFactory randomStringFactory=new RandomStringFactory(length, true, true);
		String string=randomStringFactory.create();
		assertTrue(string.length()==length);
		for (Character ch : string.toCharArray()) {
			assertTrue(Character.isLetter(ch) || Character.isDefined(ch));
		}
	}
	

}
