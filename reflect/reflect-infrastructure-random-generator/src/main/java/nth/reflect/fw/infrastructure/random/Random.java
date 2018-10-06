package nth.reflect.fw.infrastructure.random;

import nth.reflect.fw.infrastructure.random.generator.address.CountryGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.EmailAddressGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.PhoneNumberGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.PostalCodeGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.FromEnumGenerator;
import nth.reflect.fw.infrastructure.random.generator.datetime.CalendarGenerator;
import nth.reflect.fw.infrastructure.random.generator.datetime.LocalDateGenerator;
import nth.reflect.fw.infrastructure.random.generator.datetime.LocalDateTimeGenerator;
import nth.reflect.fw.infrastructure.random.generator.datetime.LocalTimeGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.ColorNameGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.CompanyNameGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.DomainNameGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.FemaleNameGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.FirstNameGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.LastNameGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.MaleNameGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.PersonNameGenerator;
import nth.reflect.fw.infrastructure.random.generator.name.ProductGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BigDecimalGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BigIntegerGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BoolGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.DoubleGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IdGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.LongGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.FormatGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.StringGenerator;
import nth.reflect.fw.infrastructure.random.generator.word.ChapterGenerator;
import nth.reflect.fw.infrastructure.random.generator.word.LoremIpsumGenerator;
import nth.reflect.fw.infrastructure.random.generator.word.ParagraphGenerator;
import nth.reflect.fw.infrastructure.random.generator.word.SentenceGenerator;
import nth.reflect.fw.infrastructure.random.generator.word.SyllableGenerator;
import nth.reflect.fw.infrastructure.random.generator.word.WordGenerator;

/**
 * A Factory (convenience class) for creating {@link RandomGenerator}s using a
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

	public static ChapterGenerator chapterGenerator() {
		return new ChapterGenerator();
	}

	public static CharacterGenerator characterGenerator() {
		return new CharacterGenerator();
	}

	public static ColorNameGenerator colorNameGenerator() {
		return new ColorNameGenerator();
	}

	public static CompanyNameGenerator companyNameGenerator() {
		return new CompanyNameGenerator();
	}

	public static CountryGenerator countryGenerator() {
return new CountryGenerator();		
	}

	public static DomainNameGenerator domainNameGenerator() {
		return new DomainNameGenerator();
	}

	public static DoubleGenerator doubleGenerator() {
		return new DoubleGenerator();
	}

	public static EmailAddressGenerator emailAddressGenerator() {
		return new EmailAddressGenerator();
	}

	public static FemaleNameGenerator femaleNameGenerator() {
		return new FemaleNameGenerator();
	}

	public static FirstNameGenerator firstNameGenerator() {
		return new FirstNameGenerator();
	}

	public static FormatGenerator formatGenerator() {
		return new FormatGenerator();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static FromEnumGenerator fromEnumGenerator(Class<? extends Enum> enumClass) {
		return new FromEnumGenerator(enumClass);
	}

	public static IdGenerator idGenerator() {
		return new IdGenerator();
	}

	public static IntGenerator intGenerator() {
		return new IntGenerator();
	}

	public static LastNameGenerator lastNameGenerator() {
		return new LastNameGenerator();
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

	public static LoremIpsumGenerator loremIpsumGenerator() {
		return new LoremIpsumGenerator();
	}

	public static MaleNameGenerator maleNameGenerator() {
		return new MaleNameGenerator();
	}

	public static ParagraphGenerator paragraphGenerator() {
		return new ParagraphGenerator();
	}

	public static PersonNameGenerator personNameGenerator() {
		return new PersonNameGenerator();
	}

	public static PhoneNumberGenerator phoneNumberGenerator() {
		return new PhoneNumberGenerator();
	}

	public static PostalCodeGenerator postalCodeGenerator() {
		return new PostalCodeGenerator();
	}

	public static ProductGenerator productGenerator() {
		return new ProductGenerator();
	}

	public static SentenceGenerator sentenceGenerator() {
		return new SentenceGenerator();
	}

	public static StringGenerator stringGenerator() {
		return new StringGenerator();
	}

	public static SyllableGenerator syllableGenerator() {
		return new SyllableGenerator();
	}

	public static WordGenerator wordGenerator() {
		return new WordGenerator();
	}


}
