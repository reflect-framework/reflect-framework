package nth.reflect.fw.infrastructure.random.generator.name;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class DomainNameGeneratorTest {

	@Test
	public void testForNoParameter() {
		Set<String> domainNames = Random.domainName().generateSet(50);
		assertThat(domainNames).size().isGreaterThan(5);
		assertThat(domainNames).allSatisfy(name -> assertThat(name).startsWith("."));
	}

}
