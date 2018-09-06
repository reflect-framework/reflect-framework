package nth.reflect.fw.infrastructure.randomfactory;

import java.math.BigDecimal;

public class RandomBigDecimalFactory implements Factory<BigDecimal> {

	private final Factory<BigDecimal> randomBigDecimalFactory;

	public RandomBigDecimalFactory() {
		randomBigDecimalFactory=new Factory<BigDecimal>() {
			
			@Override
			public BigDecimal create() {
		        return new BigDecimal(Math.random()).multiply(BigDecimal.valueOf(Double.MAX_VALUE));
			}
		};
	}
	
	public RandomBigDecimalFactory(BigDecimal min, BigDecimal max) {
		randomBigDecimalFactory=new Factory<BigDecimal>() {
			
			@Override
			public BigDecimal create() {
		        return  min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
			}
		};
	}
	

	@Override
	public BigDecimal create() {
		return randomBigDecimalFactory.create();
	}


}
