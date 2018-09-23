package nth.reflect.fw.ui.style;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReflectDisplayHeightTest {

	@Test
	public void testGetMaxSizeInCentimeters() {
		assertEquals(10, ReflectDisplayHeight.SMALL.getMaxSizeInCentimeters());
		assertEquals(20, ReflectDisplayHeight.MEDIUM.getMaxSizeInCentimeters());
		assertEquals(Integer.MAX_VALUE, ReflectDisplayHeight.LARGE.getMaxSizeInCentimeters());
		}

	@Test
	public void testGetMaxSizeInPixels() {
		assertEquals(377, ReflectDisplayHeight.SMALL.getMaxSizeInPixels());
		assertEquals(755, ReflectDisplayHeight.MEDIUM.getMaxSizeInPixels());
		assertEquals(Integer.MAX_VALUE, ReflectDisplayHeight.LARGE.getMaxSizeInPixels());
	}

	@Test
	public void testForCentimeters() {
		assertEquals(ReflectDisplayHeight.SMALL, ReflectDisplayHeight.forCentimeters(5));
		assertEquals(ReflectDisplayHeight.MEDIUM, ReflectDisplayHeight.forCentimeters(15));
		assertEquals(ReflectDisplayHeight.LARGE, ReflectDisplayHeight.forCentimeters(25));
	}

	@Test
	public void testForPixels() {
		assertEquals(ReflectDisplayHeight.SMALL, ReflectDisplayHeight.forPixels(200));
		assertEquals(ReflectDisplayHeight.MEDIUM, ReflectDisplayHeight.forPixels(500));
		assertEquals(ReflectDisplayHeight.LARGE, ReflectDisplayHeight.forPixels(1000));
	}

}
