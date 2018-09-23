package nth.reflect.fw.ui.style;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReflectDisplayWidthTest {

	@Test
	public void testGetMaxSizeInCentimeters() {
		assertEquals(10, ReflectDisplayWidth.SMALL.getMaxSizeInCentimeters());
		assertEquals(20, ReflectDisplayWidth.MEDIUM.getMaxSizeInCentimeters());
		assertEquals(Integer.MAX_VALUE, ReflectDisplayWidth.LARGE.getMaxSizeInCentimeters());
	}

	@Test
	public void testGetMaxSizeInPixels() {
		assertEquals(377, ReflectDisplayWidth.SMALL.getMaxSizeInPixels());
		assertEquals(755, ReflectDisplayWidth.MEDIUM.getMaxSizeInPixels());
		assertEquals(Integer.MAX_VALUE, ReflectDisplayWidth.LARGE.getMaxSizeInPixels());
	}

	@Test
	public void testForCentimeters() {
		assertEquals(ReflectDisplayWidth.SMALL, ReflectDisplayWidth.forCentimeters(5));
		assertEquals(ReflectDisplayWidth.MEDIUM, ReflectDisplayWidth.forCentimeters(15));
		assertEquals(ReflectDisplayWidth.LARGE, ReflectDisplayWidth.forCentimeters(25));
	}

	@Test
	public void testForPixels() {
		assertEquals(ReflectDisplayWidth.SMALL, ReflectDisplayWidth.forPixels(200));
		assertEquals(ReflectDisplayWidth.MEDIUM, ReflectDisplayWidth.forPixels(500));
		assertEquals(ReflectDisplayWidth.LARGE, ReflectDisplayWidth.forPixels(1000));
	}

}
