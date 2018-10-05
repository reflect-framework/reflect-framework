package nth.reflect.fw.infrastructure.random.generator.resource;

import java.util.ArrayList;
import java.util.List;

public abstract class ResourceFileRepository<T> implements Repository<T> {

	private List<T> allValues;

	/**
	 * The allValues are lazy loaded (only when needed) and than cashed for
	 * performance. These resource files do not change during runtime anyway.
	 */
	@Override
	public List<T> getAll() {
		if (allValues==null) {
			allValues=createAllValues();
		}
		return allValues;
	}

	private List<T> createAllValues() {
		List<String> lines = readAllLines();
		List<T> values=new ArrayList<>();
		for (String line : lines) {
			T value=create(line);
			values.add(value);
		}
		return values;
	}

	public abstract T create(String line);

	private List<String> readAllLines() {
		ResourceFile resourceFile = new ResourceFile(this.getClass());
		List<String> lines = resourceFile.readLines();
		return lines;
	}
	
}
