package nth.reflect.fw.infrastructure.random.generator.number;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.number.BigIntegerGenerator;

public class BigIntegerGeneratorTest {

	
	@Test
	public void testLeftSmallerOrEqualToRight() {
		assertTrue(leftSmallerOrEqualToRight(BigInteger.valueOf(10),BigInteger.valueOf(20)));
		assertTrue(leftSmallerOrEqualToRight(BigInteger.valueOf(20),BigInteger.valueOf(20)));
		assertFalse(leftSmallerOrEqualToRight(BigInteger.valueOf(20),BigInteger.valueOf(10)));
	}
	
	@Test
	public void testBigInt() {
		BigIntegerGenerator bigIntegerGenerator=new BigIntegerGenerator();
		for (int i=0;i<20;i++) {
			BigInteger random = bigIntegerGenerator.generate();
			assertTrue(leftSmallerOrEqualToRight(random,BigInteger.valueOf(Long.MAX_VALUE)));
		}
	}

	private boolean leftSmallerOrEqualToRight(BigInteger left, BigInteger right) {
		return left.compareTo(right)<1;
	}

	@Test
	public void testBigIntCreationWithMinAndMaxValue() {
		assertRandomNumbersBetweenMinMax(10, 20);
		assertRandomNumbersBetweenMinMax(-10, 10);
		assertRandomNumbersBetweenMinMax(-20, -10);
		assertRandomNumbersBetweenMinMax(10, 10);
	}

	private void assertRandomNumbersBetweenMinMax(int minInt, int maxInt) {
		BigInteger min=BigInteger.valueOf(minInt);
		BigInteger max=BigInteger.valueOf(maxInt);

		BigIntegerGenerator bigIntegerGenerator=new BigIntegerGenerator(min,max );
	
		for (int i=0;i<20;i++) {
			BigInteger random = bigIntegerGenerator.generate();
			assertTrue(leftSmallerOrEqualToRight( min, random));
			assertTrue(leftSmallerOrEqualToRight( random, max));
		}
	}
}
