package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class RandomBigIntegerFactoryTest {

	
	@Test
	public void testLeftSmallerOrEqualToRight() {
		assertTrue(leftSmallerOrEqualToRight(BigInteger.valueOf(10),BigInteger.valueOf(20)));
		assertTrue(leftSmallerOrEqualToRight(BigInteger.valueOf(20),BigInteger.valueOf(20)));
		assertFalse(leftSmallerOrEqualToRight(BigInteger.valueOf(20),BigInteger.valueOf(10)));
	}
	
	@Test
	public void testBigIntCreationWithBitLength() {
		int bitLength = 4;
		RandomBigIntegerFactory randomBigIntegerFactory=new RandomBigIntegerFactory(bitLength);
		BigInteger maxValue=BigInteger.valueOf(2).pow(bitLength).subtract(BigInteger.ONE);
		for (int i=0;i<20;i++) {
			BigInteger random = randomBigIntegerFactory.create();
			assertTrue(leftSmallerOrEqualToRight(random,maxValue));
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

		RandomBigIntegerFactory randomBigIntegerFactory=new RandomBigIntegerFactory(min,max );
	
		for (int i=0;i<20;i++) {
			BigInteger random = randomBigIntegerFactory.create();
			assertTrue(leftSmallerOrEqualToRight( min, random));
			assertTrue(leftSmallerOrEqualToRight( random, max));
		}
	}
}
