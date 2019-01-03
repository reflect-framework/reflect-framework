package nth.reflect.fw.infrastructure.random;

import nth.reflect.fw.infrastructure.random.generator.address.AddressGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.CityGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.CountryGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.EmailAddressGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.PhoneNumberGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.PostalCodeGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.RegionGenerator;
import nth.reflect.fw.infrastructure.random.generator.address.StreetNameGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.FromEnumGenerator;
import nth.reflect.fw.infrastructure.random.generator.datetime.CalendarGenerator;
import nth.reflect.fw.infrastructure.random.generator.datetime.DateGenerator;
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
import nth.reflect.fw.infrastructure.random.generator.number.ByteGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.DoubleGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.FloatGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IdGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.IntGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.LongGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.ShortGenerator;
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

	public static AddressGenerator address() {
		return new AddressGenerator();
	}

	public static BigDecimalGenerator bigDecimal() {
		return new BigDecimalGenerator();
	}

	public static BigIntegerGenerator bigInteger() {
		return new BigIntegerGenerator();
	}

	public static BoolGenerator bool() {
		return new BoolGenerator();
	}

	public static CalendarGenerator calendar() {
		return new CalendarGenerator();
	}

	public static ChapterGenerator chapter() {
		return new ChapterGenerator();
	}

	public static CharacterGenerator character() {
		return new CharacterGenerator();
	}

	public static CityGenerator city() {
		return new CityGenerator();
	}

	public static ColorNameGenerator colorName() {
		return new ColorNameGenerator();
	}

	public static CompanyNameGenerator companyName() {
		return new CompanyNameGenerator();
	}

	public static CountryGenerator country() {
		return new CountryGenerator();
	}

	public static DomainNameGenerator domainName() {
		return new DomainNameGenerator();
	}

	public static DoubleGenerator double_() {
		return new DoubleGenerator();
	}

	public static EmailAddressGenerator emailAddress() {
		return new EmailAddressGenerator();
	}

	public static FemaleNameGenerator femaleName() {
		return new FemaleNameGenerator();
	}

	public static FirstNameGenerator firstName() {
		return new FirstNameGenerator();
	}

	public static FormatGenerator format() {
		return new FormatGenerator();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static FromEnumGenerator fromEnum(Class<? extends Enum> enumClass) {
		return new FromEnumGenerator(enumClass);
	}

	public static IdGenerator id() {
		return new IdGenerator();
	}

	public static IntGenerator integer() {
		return new IntGenerator();
	}

	public static LastNameGenerator lastName() {
		return new LastNameGenerator();
	}

	public static LocalDateGenerator localDate() {
		return new LocalDateGenerator();
	}

	public static LocalDateTimeGenerator localDateTime() {
		return new LocalDateTimeGenerator();
	}

	public static LocalTimeGenerator localTime() {
		return new LocalTimeGenerator();
	}

	public static LongGenerator long_() {
		return new LongGenerator();
	}

	public static LoremIpsumGenerator loremIpsum() {
		return new LoremIpsumGenerator();
	}

	public static MaleNameGenerator maleName() {
		return new MaleNameGenerator();
	}

	public static ParagraphGenerator paragraph() {
		return new ParagraphGenerator();
	}

	public static PersonNameGenerator personName() {
		return new PersonNameGenerator();
	}

	public static PhoneNumberGenerator phoneNumber() {
		return new PhoneNumberGenerator();
	}

	public static PostalCodeGenerator postalCode() {
		return new PostalCodeGenerator();
	}

	public static ProductGenerator product() {
		return new ProductGenerator();
	}

	public static RegionGenerator region() {
		return new RegionGenerator();
	}

	public static SentenceGenerator sentence() {
		return new SentenceGenerator();
	}

	public static StreetNameGenerator streetName() {
		return new StreetNameGenerator();
	}

	public static StringGenerator string() {
		return new StringGenerator();
	}

	public static SyllableGenerator syllable() {
		return new SyllableGenerator();
	}

	public static WordGenerator word() {
		return new WordGenerator();
	}

	public static ByteGenerator byte_() {
		return new ByteGenerator();
	}

	public static FloatGenerator float_() {
		return new FloatGenerator();
	}

	public static ShortGenerator short_() {
		return new ShortGenerator();
	}

	public static DateGenerator date() {
		return new DateGenerator();
	}

}
