package nth.reflect.util.random.generator.resource;

import java.util.List;

public class ResourceFileStringRepository implements Repository<String> {
	private List<String> allValues;

	/**
	 * The allValues are lazy loaded (only when needed) and than cashed for
	 * performance. These resource files do not change during runtime anyway.
	 */
	@Override
	public List<String> getAll() {
		if (allValues==null) {
			allValues=createAllValues();
		}
		return allValues;
	}

	private List<String> createAllValues() {
		List<String> values = readAllLines();
		return values;
	}

	private List<String> readAllLines() {
		ResourceFile resourceFile = new ResourceFile(this.getClass());
		List<String> lines = resourceFile.readLines();
		return lines;
	}
}
