package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class RandomBigDecimalFactoryTest {

	@Test
	public void testLeftSmallerOrEqualToRight() {
		assertTrue(leftSmallerOrEqualToRight(BigDecimal.valueOf(10),BigDecimal.valueOf(20)));
		assertTrue(leftSmallerOrEqualToRight(BigDecimal.valueOf(20),BigDecimal.valueOf(20)));
		assertFalse(leftSmallerOrEqualToRight(BigDecimal.valueOf(20),BigDecimal.valueOf(10)));
	}
	
	@Test
	public void testBigDecimalCreation() {
		RandomBigDecimalFactory randomBigDecimalFactory=new RandomBigDecimalFactory();
		BigDecimal maxValue=BigDecimal.valueOf(Double.MAX_VALUE);
		for (int i=0;i<20;i++) {
			BigDecimal random = randomBigDecimalFactory.create();
			assertTrue(leftSmallerOrEqualToRight(random,maxValue));
		}
	}

	private boolean leftSmallerOrEqualToRight(BigDecimal left, BigDecimal right) {
		return left.compareTo(right)<1;
	}

	@Test
	public void testBigDecimalCreationWithMinMaxIntegers() {
		assertRandomNumbersBetweenMinMax(BigDecimal.valueOf(10), BigDecimal.valueOf(20));
		assertRandomNumbersBetweenMinMax(BigDecimal.valueOf(-10), BigDecimal.valueOf(10));
		assertRandomNumbersBetweenMinMax(BigDecimal.valueOf(-20), BigDecimal.valueOf(-10));
		assertRandomNumbersBetweenMinMax(BigDecimal.valueOf(10), BigDecimal.valueOf(10));
	}

	
	@Test
	public void testBigDecimalCreationWithMinMaxDecimals() {
		assertRandomNumbersBetweenMinMax(new BigDecimal("10.5"), new BigDecimal("20.3"));
		assertRandomNumbersBetweenMinMax(new BigDecimal("-10.4"), new BigDecimal("10.9"));
		assertRandomNumbersBetweenMinMax(new BigDecimal("-20.3"), new BigDecimal("-10.4"));
		assertRandomNumbersBetweenMinMax(new BigDecimal("10.9"), new BigDecimal("10.9"));
	}

	private void assertRandomNumbersBetweenMinMax(BigDecimal min, BigDecimal max) {

		RandomBigDecimalFactory randomBigDecimalFactory=new RandomBigDecimalFactory(min,max );
	
		for (int i=0;i<20;i++) {
			BigDecimal random = randomBigDecimalFactory.create();
			assertTrue(leftSmallerOrEqualToRight( min, random));
			assertTrue(leftSmallerOrEqualToRight( random, max));
		}
	}

}
