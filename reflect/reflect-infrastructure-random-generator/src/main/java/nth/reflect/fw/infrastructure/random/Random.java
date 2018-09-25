package nth.reflect.fw.infrastructure.random;

import nth.reflect.fw.infrastructure.random.generator.datetime.CalendarGenerator;
import nth.reflect.fw.infrastructure.random.generator.datetime.LocalDateGenerator;
import nth.reflect.fw.infrastructure.random.generator.datetime.LocalDateTimeGenerator;
import nth.reflect.fw.infrastructure.random.generator.datetime.LocalTimeGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BigDecimalGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BigIntegerGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BoolGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.DoubleGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.EnumGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IdGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.LongGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.StringGenerator;

/**
 * A Factory (Convenience class) for creating {@link RandomGenerator}s using a
 * fluent interface
 * 
 * @author nilsth
 *
 */
public class Random {

	public static BigDecimalGenerator bigDecimalGenerator() {
		return new BigDecimalGenerator();
	}

	public static BigIntegerGenerator bigIntegerGenerator() {
		return new BigIntegerGenerator();
	}

	public static BoolGenerator boolGenerator() {
		return new BoolGenerator();
	}

	public static CalendarGenerator calendarGenerator() {
		return new CalendarGenerator();
	}

	public static DoubleGenerator doubleGenerator() {
		return new DoubleGenerator();
	}
	
	@SuppressWarnings("rawtypes")
	public static EnumGenerator enumGenerator(Class<? extends Enum> enumClass) {
		return new EnumGenerator(enumClass);
	}
	
	public static IdGenerator idGenerator() {
		return new IdGenerator();
	}
	
	public static IntGenerator intGenerator() {
		return new IntGenerator();
	}

	public static LocalDateGenerator localDateGenerator() {
		return new LocalDateGenerator();
	}
	
	public static LocalDateTimeGenerator localDateTimeGenerator() {
		return new LocalDateTimeGenerator();
	}
	
	public static LocalTimeGenerator localTimeGenerator() {
		return new LocalTimeGenerator();
	}
	
	public static LongGenerator longGenerator() {
		return new LongGenerator();
	}

	public static StringGenerator stringGenerator() {
		return new StringGenerator();
	}
	
	
}
