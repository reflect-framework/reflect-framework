package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class DomainNameGeneratorTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testForNoParameter() {
		Set<String> domainNames = Random.domainNameGenerator().generateSet(50);
		assertThat(domainNames,hasSize(greaterThan(5)));
		assertThat(domainNames,hasItems(startsWith(".")));
	}

}
