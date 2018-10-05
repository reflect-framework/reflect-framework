package nth.reflect.fw.infrastructure.random.generator.resource;

import nth.reflect.fw.infrastructure.random.generator.address.Country;
import nth.reflect.fw.infrastructure.random.generator.address.CountryRepository;
import nth.reflect.fw.infrastructure.random.generator.name.ColorNameRepository;
import nth.reflect.fw.infrastructure.random.generator.name.CompanyNameRepository;
import nth.reflect.fw.infrastructure.random.generator.name.DomainNameRepository;
import nth.reflect.fw.infrastructure.random.generator.name.FemaleNameRepository;
import nth.reflect.fw.infrastructure.random.generator.name.LastNameRepository;
import nth.reflect.fw.infrastructure.random.generator.name.MaleNameRepository;
import nth.reflect.fw.infrastructure.random.generator.name.ProductRepository;
import nth.reflect.fw.infrastructure.random.generator.word.LoremIpsumRepository;

/**
 * Service Locator for {@link ResourceFileRepository}s to ensure we are only
 * using one instance of {@link ResourceFileRepository}s for best memory and
 * speed performance.
 * 
 * @author nilsth
 *
 */
public class Resources {

	private static final LoremIpsumRepository LOREM_IPSUM_REPOSITORY = new LoremIpsumRepository();
	private static final MaleNameRepository MALE_NAME_REPOSITORY = new MaleNameRepository();
	private static final CountryRepository COUNTRY_REPOSITORY = new CountryRepository();
	private static final DomainNameRepository DOMAIN_NAME_REPOSITORY = new DomainNameRepository();
	private static final ColorNameRepository COLOR_NAME_REPOSITORY = new ColorNameRepository();
	private static final ProductRepository PRODUCT_REPOSITORY = new ProductRepository();
	private static final CompanyNameRepository COMPANY_NAME_REPOSITORY = new CompanyNameRepository();
	private static final FemaleNameRepository FEMALE_NAME_REPOSITORY = new FemaleNameRepository();
	private static final LastNameRepository LAST_NAME_REPOSITORY = new LastNameRepository();

	public static Repository<Country> countryRepository() {
		return COUNTRY_REPOSITORY;
	}

	public static Repository<String> domainNameRepository() {
		return DOMAIN_NAME_REPOSITORY;
	}

	public static ColorNameRepository colorNameRepository() {
		return COLOR_NAME_REPOSITORY;
	}

	public static ProductRepository productRepository() {
		return PRODUCT_REPOSITORY;
	}

	public static CompanyNameRepository companyNameRepository() {
		return COMPANY_NAME_REPOSITORY;
	}

	public static FemaleNameRepository femaleNameRepository() {
		return FEMALE_NAME_REPOSITORY;
	}

	public static LastNameRepository lastNameRepository() {
		return LAST_NAME_REPOSITORY;
	}

	public static MaleNameRepository maleNameRepository() {
		return MALE_NAME_REPOSITORY;
	}

	public static LoremIpsumRepository loremIpsumRepository() {
		return LOREM_IPSUM_REPOSITORY;
	}

}
