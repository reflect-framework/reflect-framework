package nth.reflect.fw.infrastructure.random.csv.repository;

public class Repositories {

	private static ColorNameRepository colorNameRepository;
	private static ProductNameAndDescriptionAndCompanyRepository productNameAndDescriptionAndCompanyRepository;
	private static DomainNameRepository domainNameRepository;
	private static FemaleNameRepository femaleNameRepository;
	private static LastNameRepository lastNameRepository;
	private static MaleNameRepository maleNameRepository;
	private static LoremIpsumRepository loremIpsumGenerator;
	private static CountryAndPostalCodeRepository countryAndPostalCodeRepository;
	
	static {
		colorNameRepository=new ColorNameRepository();
		productNameAndDescriptionAndCompanyRepository=new ProductNameAndDescriptionAndCompanyRepository();
		domainNameRepository=new DomainNameRepository();
		femaleNameRepository=new FemaleNameRepository();
		lastNameRepository=new LastNameRepository();
		maleNameRepository=new MaleNameRepository();
		loremIpsumGenerator=new LoremIpsumRepository();
		countryAndPostalCodeRepository=new CountryAndPostalCodeRepository();
	}
	public static ColorNameRepository colorNameRepository() {
		return colorNameRepository;
	}
	public static  ProductNameAndDescriptionAndCompanyRepository productNameAndDescriptionAndCompanyRepository() {
		return productNameAndDescriptionAndCompanyRepository;
	}
	public static DomainNameRepository domainNameRepository() {
		return domainNameRepository;
	}
	public static FemaleNameRepository femaleNameRepository() {
		return femaleNameRepository;
	}
	public static LastNameRepository lastNameRepository() {
		return lastNameRepository;
	}
	public static MaleNameRepository maleNameRepository() {
		return maleNameRepository;
	}
	public static LoremIpsumRepository loremIpsumGenerator() {
		return loremIpsumGenerator;
	}
	public static CountryAndPostalCodeRepository countryAndPostalCodeRepository() {
		return countryAndPostalCodeRepository;
	}

}
