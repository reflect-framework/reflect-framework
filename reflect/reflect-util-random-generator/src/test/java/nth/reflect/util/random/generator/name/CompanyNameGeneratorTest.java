package nth.reflect.util.random.generator.name;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class CompanyNameGeneratorTest {

	static final String A_COMPANY_NAME = "3M";

	@Test
	public void testForNoParameter() {
		Set<String> companyNames = Random.companyName().generateSet(100 * 1000);
		assertThat(companyNames).size().isGreaterThan(100);
		assertThat(companyNames).contains(A_COMPANY_NAME);
		assertThat(companyNames).doesNotContain("", null);
	}

}
