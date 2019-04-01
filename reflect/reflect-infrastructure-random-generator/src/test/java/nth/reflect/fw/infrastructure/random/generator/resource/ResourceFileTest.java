package nth.reflect.fw.infrastructure.random.generator.resource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ResourceFileTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test(expected = RuntimeException.class)
	public void testResourceFileStringBogusFileName() {
		ResourceFile resourceFile = new ResourceFile(Object.class);
		List<String> lines = resourceFile.readLines();
		assertThat(lines).hasSize(0);
	}

	@Test
	public void testResourceFileString() {
		ResourceFile resourceFile = new ResourceFile(ResourceFile.class);
		List<String> lines = resourceFile.readLines();

		assertThat(lines).hasSize(3);
		assertThat(lines).contains("1a;1b;1c");
		assertThat(lines).contains("2a;2b;2c");
		assertThat(lines).contains("3a;3b;3c");
	}

}
